package business;
import business.*;
import facade.dto.ModalidadeDTO;

import java.util.*;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

@Stateless
public class CatalogoModalidades{
   // private Map<String,Modalidade> modalidades ;
	@PersistenceContext
    private EntityManager em;

	/*public CatalogoModalidades(EntityManager em) {
		//this.modalidades = new HashMap<>();
		this.em = em;
	}*/
	
	public List<Modalidade> getModalidades(){
		
		TypedQuery<Modalidade> query = em.createQuery("SELECT m FROM Modalidade m", Modalidade.class);
		List<Modalidade> mods = query.getResultList();
		List<Modalidade> result = new ArrayList<>();
		for(Modalidade mod: mods) {
			result.add(mod);
		}
		return result;
		/*List<String> listMods= new ArrayList<String>();
		for(Map.Entry<String,Modalidade> entry: modalidades.entrySet()){
			listMods.add(entry.getKey());	        
		}
		return listMods;*/
	}

	public Modalidade getMod(String nomeMod){
		TypedQuery<Modalidade> query = em.createQuery("SELECT m FROM Modalidade m WHERE m.nome = :nomeMod", Modalidade.class);
		query.setParameter("nomeMod", nomeMod);
		return query.getSingleResult();
		//return this.modalidades.get(nomeMod);
	}

	@Transactional(Transactional.TxType.REQUIRES_NEW)
	public void putMod(Modalidade mod){
		//this.modalidades.put(mod.getName(), mod);
		em.persist(mod);
	}
}