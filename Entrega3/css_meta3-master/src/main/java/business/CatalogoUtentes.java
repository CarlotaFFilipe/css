package business;
import java.util.*;
public class CatalogoUtentes{
  private Map<String,Utente> utentes;

  public CatalogoUtentes() {
		this.utentes = new HashMap<>();
  }
  
  public void putUtente(Utente u){
    this.utentes.put(u.getNumUtente(),u);
  }

  public List<Utente> getUtentes(){
    List<Utente> listUtentes= new ArrayList<>();
		for(Map.Entry<String,Utente> entry: this.utentes.entrySet()){
			listUtentes.add(entry.getValue());
		}
		return listUtentes;
  }

  public Utente getUtente(String numUtente) {
	  if(utentes.containsKey(numUtente))
			return utentes.get(numUtente);
		return null;
  }
}