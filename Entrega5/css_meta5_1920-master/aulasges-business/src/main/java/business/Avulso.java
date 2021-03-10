package business;



import javax.persistence.Entity;

import business.Inscricao;
@Entity
public class Avulso extends Inscricao{

	Avulso(){}
    public Avulso(double preco){
        super(preco,false);

    }
}