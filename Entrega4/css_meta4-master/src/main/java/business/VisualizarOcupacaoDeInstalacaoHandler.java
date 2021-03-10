package business;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import business.Instalacao;

import java.text.*;
import javafx.util.Pair; 

public class VisualizarOcupacaoDeInstalacaoHandler{
    private Instalacao ins;
    //private CatalogoInstalacoes catIns;
    private EntityManagerFactory emf;
    private EntityManager em;

    public VisualizarOcupacaoDeInstalacaoHandler(EntityManagerFactory emf) {
    	this.emf = emf;
    }

    public void verInstalacao(String nome,Date data){
    	this.em =emf.createEntityManager();
        CatalogoInstalacoes catIns= new CatalogoInstalacoes(this.em);
    	this.ins = catIns.getInstalacao(nome);
        if(ins == null){
            System.out.println("Instalacao invalida");
            return;
        }
        catIns.getAulasCPeriodos(this.ins,data);
    }
}