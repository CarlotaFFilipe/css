package facade.interfaces;

import java.io.Serializable;
import java.util.Date;

import business.Aula;

public interface IInscricao extends Serializable{
	public double getPreco();
	
	public boolean getEhRegular();
	
	public Aula getAula();
	
	public Date getData();
}
