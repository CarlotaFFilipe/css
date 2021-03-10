package facade.interfaces;

import java.io.Serializable;
import java.util.Date;

public interface IModalidade extends Serializable{
	public Date getDuracao();

    public String getNome();
    public Double getPreco();
}
