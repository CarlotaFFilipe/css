package facade.interfaces;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import business.Modalidade;

public interface IAula extends Serializable{
	public String getNome();
	
	public Date getHoraInicio();
	
	public Modalidade getModalidade();
	
	public Date getDuracao();
	
	public List<String> getDias();
}
