package presentation.fx.model;

import java.util.Date;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Modalidade {

	
	private final StringProperty nome = new SimpleStringProperty();
	//private final Date duracaoMin;
	private final double preco;

	
	public Modalidade(String nome, Date duracao,double preco){
        setName(nome);
        //this.duracaoMin= duracao;
        this.preco = preco;

    }
	
	 public final void setName(final String name) {
	        this.nameProperty().set(name);
	  }
	 
	 public final StringProperty nameProperty() {
	        return this.nome;
	}
	 
	public final String getName() {
	        return this.nameProperty().get();
	}
	
	public double getPreco() {
		return preco;
	}
	
	
	
    @Override
    public String toString(){
    	return getName();
    }
}
