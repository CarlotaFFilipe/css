package business;
import java.util.*;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import business.Instalacao;
import facade.dto.AulaDTO;

import java.text.*;
import javafx.util.Pair; 

@Stateless
public class VisualizarOcupacaoDeInstalacaoHandler{
    private Instalacao ins;
    @EJB
    private CatalogoInstalacoes catIns;
    //private EntityManagerFactory emf;
    //private EntityManager em;

    /*public VisualizarOcupacaoDeInstalacaoHandler(EntityManagerFactory emf) {
    	this.emf = emf;
    }*/

    public List<Pair<AulaDTO, Pair<Date,Date>>> verInstalacao(String nome,Date data){
    	//this.em =emf.createEntityManager();
        //CatalogoInstalacoes catIns= new CatalogoInstalacoes(this.em);
    	this.ins = this.catIns.getInstalacao(nome);
        if(ins == null){
            System.out.println("Instalacao invalida");
            return null;
        }
        List<Pair<AulaDTO, Pair<Date,Date>>> novo= new ArrayList<Pair<AulaDTO, Pair<Date,Date>>>();
        List<Pair<Aula, Pair<Date,Date>>> list = this.catIns.getAulasCPeriodos(this.ins,data);
        for(Pair<Aula, Pair<Date,Date>> pair: list ) {
        	AulaDTO aaa= new AulaDTO(pair.getKey().getHoraInicio(),pair.getKey().getDias(),pair.getKey().getName(),pair.getKey().getDuracao(),pair.getKey().getModalidade());
        	Pair<Date,Date> novoPar= new Pair<Date,Date>(pair.getValue().getKey(), pair.getValue().getValue());
            Pair<AulaDTO, Pair<Date,Date>> no = new  Pair<AulaDTO, Pair<Date,Date>> (aaa, novoPar);
        	novo.add(no);
        }
        return novo;
    }
}