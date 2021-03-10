package facade.interfaces;

import java.io.Serializable;
import java.util.List;

import business.Modalidade;

public interface IInstalacao extends Serializable{

	
	public int getCapacidade();
	public List<Modalidade> getModalidadesPermitidas();

    public String getNome();
}
