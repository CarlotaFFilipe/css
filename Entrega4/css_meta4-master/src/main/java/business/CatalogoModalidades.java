package business;
import business.*;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

public class CatalogoModalidades{
   // private Map<String,Modalidade> modalidades ;
    private EntityManager em;

	public CatalogoModalidades(EntityManager em) {
		//this.modalidades = new HashMap<>();
		this.em = em;
	}
	
	public List<String> getModalidades(){
		
		TypedQuery<Modalidade> query = em.createQuery("SELECT m FROM Modalidade m", Modalidade.class);
		List<Modalidade> mods = query.getResultList();
		List<String> result = new ArrayList<>();
		for(Modalidade mod: mods) {
			result.add(mod.getName());
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

	public void putMod(Modalidade mod){
		//this.modalidades.put(mod.getName(), mod);
		em.persist(mod);
	}
}