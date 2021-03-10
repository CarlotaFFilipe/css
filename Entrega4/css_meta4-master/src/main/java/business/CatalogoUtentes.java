package business;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import business.Utente;
public class CatalogoUtentes{
	//private Map<String,Utente> utentes;
	private EntityManager em;

	public CatalogoUtentes( EntityManager em) {
		//this.utentes = new HashMap<>();
		this.em = em;
	}

	public void putUtente(Utente u){
		//this.utentes.put(u.getNumUtente(),u);
		em.persist(u);
	}

	public List<Utente> getUtentes(){
		TypedQuery<Utente> query = em.createQuery("SELECT u FROM Utente u", Utente.class);
		return query.getResultList();  
		/*List<Utente> listUtentes= new ArrayList<>();
		for(Map.Entry<String,Utente> entry: this.utentes.entrySet()){
			listUtentes.add(entry.getValue());
		}
		return listUtentes;*/
	}

	public Utente getUtente(String numUtente) {
		TypedQuery<Utente> query = em.createQuery("SELECT u FROM Utente u WHERE u.numUtente = :numUtente", Utente.class);
		query.setParameter("numUtente", numUtente);
		return query.getSingleResult();


		/*/*if(utentes.containsKey(numUtente))
			return utentes.get(numUtente);
		return null;*/
	}
}