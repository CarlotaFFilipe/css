package business;
import business.*;
import facade.dto.InstalacaoDTO;

import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import javafx.util.Pair; 

@Stateless
public class CatalogoInstalacoes{
    //private Map<String,Instalacao> instalacoes;
	@PersistenceContext
    private EntityManager em;



	public CatalogoInstalacoes(EntityManager em) {
		//this.instalacoes = new HashMap<>();
		this.em = em;
	}
	
	public List<Instalacao> getInstalacoes(){
		TypedQuery<Instalacao> query = em.createQuery("SELECT i FROM Instalacao i", Instalacao.class);
		List<Instalacao> insts = query.getResultList();
		List<Instalacao> result = new ArrayList<>();
		for(Instalacao inst: insts) {
			result.add(inst);
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
	
	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public void putInstalacao(Instalacao ins){
		//instalacoes.put(ins.getName(), ins);
		em.persist(ins);
	}
}
