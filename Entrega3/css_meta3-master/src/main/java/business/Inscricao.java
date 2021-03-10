package business;
import java.util.*;

public class Inscricao{

    private double preco;
    private boolean ehRegular;
    private Aula aula;
    private Date data;
    
    public Inscricao(double preco,boolean ehRegular){
        this.preco = preco;
        this.ehRegular = ehRegular;
    }

    public double getPreco(){
        return this.preco;
    }

    public String getTipo(){
        if(this.ehRegular)
            return "Regular";
        return "Avulso";
    }

    public void putAulaEData(Aula aula, Date data){
        this.aula = aula;
        this.data = data;
    }

    public Date getData(){
        return this.data;
    }

    public Aula getAula(){
        return this.aula;
    }

}