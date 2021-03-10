package business;
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import business.Modalidade;

public class CriarAulaHandler{
    //private CatalogoAulas catAu;
    //private CatalogoModalidades catMod;
    private EntityManagerFactory emf;
    private EntityManager em;


    public CriarAulaHandler(EntityManagerFactory emf){
    	 this.emf = emf;
    }

    public void criarAula(){
    	this.em = emf.createEntityManager();
    	CatalogoModalidades catMod = new CatalogoModalidades(this.em);
        System.out.println(catMod.getModalidades().toString());
    }

    public void especificaAula(String mod, String n, List<String> dias, Date horaI, Date dur){
    	CatalogoModalidades catMod = new CatalogoModalidades(this.em);
    	CatalogoAulas catAu = new CatalogoAulas(this.em);
    	Modalidade modalidade = catMod.getMod(mod);
        if(modalidade == null){
            System.out.println("Nao existe modalidade");
            return;
        }
        em.getTransaction().begin();
        catAu.putAula(horaI, dias, n, dur, modalidade);
        em.getTransaction().commit();
    }
}