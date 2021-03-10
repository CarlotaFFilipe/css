package business;
import business.*;
import java.util.*;

public class CatalogoModalidades{
    private Map<String,Modalidade> modalidades ;

	public CatalogoModalidades() {
		this.modalidades = new HashMap<>();
	}
	
	public List<String> getModalidades(){
		List<String> listMods= new ArrayList<String>();
		for(Map.Entry<String,Modalidade> entry: modalidades.entrySet()){
			listMods.add(entry.getKey());	        
		}
		return listMods;
	}

	public Modalidade getMod(String nomeMod){
		return this.modalidades.get(nomeMod);
	}

	public void putMod(Modalidade mod){
		this.modalidades.put(mod.getName(), mod);
	}
}