package facade.dto;

import java.io.Serializable;
import java.util.Date;


public class ModalidadeDTO implements Serializable{
	private static final long serialVersionUID = -4087131153704256744L;
	private String nome;
	private Date duracaoMin;
	private double preco;
	
	public ModalidadeDTO(String nome, Date duracao,double preco){
        this.nome=nome;
        this.duracaoMin= duracao;
        this.preco = preco;

    }
	
	public Date getDuracao(){
        return this.duracaoMin;
    }

    public String getNome(){
        return this.nome;
    }
    public Double getPreco() {
    	return this.preco;
    }
}
