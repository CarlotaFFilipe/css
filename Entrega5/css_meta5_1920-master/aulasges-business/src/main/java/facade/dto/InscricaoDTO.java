package facade.dto;

import java.io.Serializable;
import java.util.Date;


import business.Aula;
//import business.LocalDateAttributeConverter;

public class InscricaoDTO implements Serializable{
	private static final long serialVersionUID = -4087131153704256744L;
	private double preco;
	private boolean ehRegular;
	private Aula aula;
	//@Convert(converter = LocalDateAttributeConverter.class)
	private Date data;
	
	public InscricaoDTO(double preco, boolean ehRegular,Aula aula,Date data) {
		 this.preco = preco;
	     this.ehRegular = ehRegular;
	     this.aula = aula;
	     this.data = data;
	}
	
	public double getPreco() {
		return this.preco;
	}
	
	public boolean getEhRegular() {
		return this.ehRegular;
	}
	
	public Aula getAula() {
		return this.aula;
	}
	
	public Date getData() {
		return this.data;
	}
}
