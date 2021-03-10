package business;

import javax.persistence.Entity;

import business.*;
@Entity
public class Regular extends Inscricao{

	Regular(){}
    public Regular(double preco){
        super(preco,true);

    }
}