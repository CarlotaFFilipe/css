package business;
import business.*;
import java.util.*;

public class Modalidade{
    private String nome;
    private Date duracao_min;
    private double preco;

    public Modalidade(String nome, Date duracao,double preco){
        this.nome=nome;
        this.duracao_min= duracao;
        this.preco = preco;

    }


    public Date getDuracao(){
        return this.duracao_min;
    }

    public String getName(){
        return this.nome;
    }

    public double getPreco(){
        return this.preco;
    }

    public String toString() {
        
    	return "Nome: "+ this.nome+";\n Preco: "+ this.preco+";\n Duracao minima: " + this.duracao_min.toString();
    }

}