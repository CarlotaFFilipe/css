package business;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

//import main.java.dbutils.LocalDateAttributeConverter;

/**
 * Entity implementation class for Entity: Inscricao
 *
 */
@Entity

public class Inscricao implements Serializable {
	/**
	 * Product primary key. Needed by JPA. Notice that it is not part of the
	 * original domain model.
	 */
	@Id @GeneratedValue  private int id;
	
	private double preco;
	private boolean ehRegular;
	@OneToOne
	private Aula aula;
	@Convert(converter = LocalDateAttributeConverter.class)
	private Date data;
	private static final long serialVersionUID = 1L;

	Inscricao(){}
	
	public Inscricao(double preco,boolean ehRegular){
        this.preco = preco;
        this.ehRegular = ehRegular;
    }

    public String getTipo(){
        if(this.ehRegular)
            return "Regular";
        return "Avulso";
    }

    public void putAulaEData(Aula aula, Date data){
    	this.data = data;
        this.aula = aula;
        
    }
    
	public double getPreco() {
		return this.preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}   
	public boolean getEhRegular() {
		return this.ehRegular;
	}

	public void setEhRegular(boolean ehRegular) {
		this.ehRegular = ehRegular;
	}   
	public Aula getAula() {
		return this.aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}   
	public Date getData() {
		return this.data;
	}

	public void setData(Date data) {
		this.data = data;
	}
   
}

