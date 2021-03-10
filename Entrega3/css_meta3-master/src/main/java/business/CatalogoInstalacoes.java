package business;
import business.*;
import java.util.*;
import javafx.util.Pair; 

public class CatalogoInstalacoes{
    private Map<String,Instalacao> instalacoes;



	public CatalogoInstalacoes() {
		this.instalacoes = new HashMap<>();
	}
	
	public List<String> getInstalacoes(){
		List<String> listInst= new ArrayList<String>();
		for(Map.Entry<String,Instalacao> entry: instalacoes.entrySet()){
			listInst.add(entry.getKey());
		}
		return listInst;
	}

	public Instalacao getInstalacao(String ins){
		return this.instalacoes.get(ins);
	}

	public void putAula(Instalacao ins, Aula aula){
		ins.putAula(aula);
	}

	public List<Pair<Aula, Pair<Date,Date>>> getAulasCPeriodos(Instalacao ins,Date data){
		return ins.getAulaCData(data);
	}

	public void putInstalacao(Instalacao ins){
		this.instalacoes.put(ins.getName(), ins);
	}
}
