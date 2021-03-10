package business;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Modalidade
 *
 */
@Entity
@NamedQuery(name=Modalidade.FIND_BY_NOME, query="SELECT m FROM Modalidade m WHERE m.nome = :" + 
		Modalidade.STRING_NOME)

public class Modalidade implements Serializable {

	public static final String FIND_BY_NOME = "Modalidade.findByNome";
	public static final String STRING_NOME= "nome";
	

	@Id
	private String nome;
	@Convert(converter = LocalTimeAttributeConverter.class)
	private Date duracaoMin;
	private double preco;
	private static final long serialVersionUID = 1L;

	Modalidade(){}
	
	public Modalidade(String nome, Date duracao,double preco){
        this.nome=nome;
        this.duracaoMin= duracao;
        this.preco = preco;

    }


    public Date getDuracao(){
        return this.duracaoMin;
    }

    public String getName(){
        return this.nome;
    }

    public String toString() {
        
    	return "Nome: "+ this.nome+";\n Preco: "+ this.preco+";\n Duracao minima: " + this.duracaoMin.toString();
    }

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setduracaoMin(Date duracaoMin) {
		this.duracaoMin = duracaoMin;
	}   
	public double getPreco() {
		return this.preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
   
}
