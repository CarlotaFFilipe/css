package business;
import business.*;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import javafx.util.Pair; 

public class CatalogoInstalacoes{
    //private Map<String,Instalacao> instalacoes;
    private EntityManager em;



	public CatalogoInstalacoes(EntityManager em) {
		//this.instalacoes = new HashMap<>();
		this.em = em;
	}
	
	public List<String> getInstalacoes(){
		TypedQuery<Instalacao> query = em.createQuery("SELECT i FROM Instalacao i", Instalacao.class);
		List<Instalacao> insts = query.getResultList();
		List<String> result = new ArrayList<>();
		for(Instalacao inst: insts) {
			result.add(inst.getName());
		}
		return result;
		
		/*List<String> listInst= new ArrayList<String>();
		for(Map.Entry<String,Instalacao> entry: instalacoes.entrySet()){
			listInst.add(entry.getKey());
		}
		return listInst;*/
	}

	public Instalacao getInstalacao(String ins){
		TypedQuery<Instalacao> query = em.createQuery("SELECT i FROM Instalacao i WHERE i.nome = :ins", Instalacao.class);
		query.setParameter("ins", ins);

		return query.getSingleResult();
		//return this.instalacoes.get(ins);
	}

	public void putAula(Instalacao ins, Aula aula){
		ins.putAula(aula);
		em.persist(ins);///???????
	}

	public List<Pair<Aula, Pair<Date,Date>>> getAulasCPeriodos(Instalacao ins,Date data){
		return ins.getAulaCData(data);
	}

	public void putInstalacao(Instalacao ins){
		//instalacoes.put(ins.getName(), ins);
		em.persist(ins);
	}
}
