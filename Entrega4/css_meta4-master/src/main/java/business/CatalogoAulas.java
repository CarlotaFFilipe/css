package business;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import business.Aula;
import business.Modalidade;

public class CatalogoAulas{
    //private Map<String,Aula> aulas;
    private EntityManager em;


	public CatalogoAulas(EntityManager em) {
		//this.aulas = new HashMap<>();
		this.em = em;
	}
	
	public Aula getAula(String nome){
		TypedQuery<Aula> query = em.createNamedQuery(Aula.FIND_BY_NOME, Aula.class);
		query.setParameter(Aula.STRING_NOME, nome);
		List<Aula> result = query.getResultList();
		if(result.isEmpty()) {
			return null;
		}
		return result.get(0);
		/*if(aulas.containsKey(nome))
			return aulas.get(nome);
		return null;*/
	}



	public void putAula(Date hora_inicio, List<String> dias, String nome, Date duracao, Modalidade mod){
		TypedQuery<Aula> query = em.createNamedQuery(Aula.FIND_BY_NOME, Aula.class);
		query.setParameter(Aula.STRING_NOME, nome);
		if(!query.getResultList().isEmpty() || nome.length() != 6 || !string3Alfanumericos(nome) || nome.contains(" ")) {
			System.out.println("Nome invalido");
			return;
		}	
		Date jan = new Date(0);
		if(duracao.getHours()<jan.getHours() && duracao.getMinutes()<jan.getMinutes()){
			System.out.println("Duracao negativa.");
			return;
		}
		if( duracao.getTime()< mod.getDuracao().getTime()){
			System.out.println("Duracao invalida.");
			return;
		}
		
		Aula aula = new Aula(hora_inicio,dias,nome,duracao,mod);
		//this.aulas.put(nome, aula);
		em.persist(aula);
        System.out.println("A aula foi bem criada!");
	}


	public List<Aula> getAulas(){
		TypedQuery<Aula> query = em.createQuery("SELECT a FROM Aula a", Aula.class);
		return query.getResultList();
		/*List<Aula> listAulas= new ArrayList<>();
		for(Map.Entry<String,Aula> entry: this.aulas.entrySet()){
			listAulas.add(entry.getValue());
		}
		return listAulas;*/
	}

	private boolean string3Alfanumericos(String s){
		char c;
		int countAlfabeto=0;
		int countNumerico=0;
		for(int i= 0; i<s.length();i++){
			c = s.charAt(i);
			if(Character.isLetter(c)){
				countAlfabeto++;
			}else if(Character.isDigit(c)){
				countNumerico++;
			}
		}
		if(countAlfabeto > 1 && countNumerico>1 && countAlfabeto+countNumerico>=3)
			return true;
		return false;

	}
	
}