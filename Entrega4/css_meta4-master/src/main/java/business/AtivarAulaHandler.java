package business;
import javafx.util.Pair; 
import java.util.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import business.Aula;
import business.Instalacao;

import java.text.*;

public class AtivarAulaHandler{
    private Aula aula;
    //private CatalogoAulas catAu;
    //private CatalogoInstalacoes catIns;
    private EntityManagerFactory emf;
    private EntityManager em;

    public AtivarAulaHandler(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void ativarAula(){
    	em = emf.createEntityManager();
    	CatalogoInstalacoes catInst = new CatalogoInstalacoes(em);
        System.out.println(catInst.getInstalacoes().toString());
    }

    public void informacoesAula(String nomeAula,String ins, Pair<Date, Date> datas, int nMaxAl){
    	CatalogoAulas catAu = new CatalogoAulas(this.em);
        CatalogoInstalacoes catIns= new CatalogoInstalacoes(this.em);
    	this.aula = catAu.getAula(nomeAula);
        if(this.aula == null){
            System.out.println("Nao existe aula com este nome.");
            return;
        }else if(aula.getEstado()) {
        	System.out.println("Aula ja foi ativada.");
            return;
        }
        Instalacao inst;
        if((inst = catIns.getInstalacao(ins) )== null){
            System.out.println("Instalacao invalida");
            return;
        }

        if(!inst.getModalidadesPermitidas().contains(this.aula.getModalidade())){
            System.out.println("Instalacao nao permite essa modalidade");
            return;
        }

        Date dNow = DataCorrente.getDataCorrente();
        SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
        ft.format(dNow);
        
        Boolean b=datas.getKey().getDate()==dNow.getDate() && datas.getKey().getMonth()==dNow.getMonth() && datas.getKey().getYear()==dNow.getYear();

        
        
        if(datas.getKey().before(dNow) && !b){
            System.out.println("O par de datas nao define um periodo no futuro");
            return;
        }
        if(nMaxAl > inst.getCapacidade()){
            System.out.println("Numero de participantes e superior a capacidade da instalacao");
            return;
        }
        
        List<Date> nh = this.aula.divideEmDias(datas.getKey(), datas.getValue());
        if(!inst.instalacaoNOcupada(nh, this.aula.getHoraInicio(),this.aula.getHoraFinal())){
            System.out.println("Instalacao indisponivel nesta hora");
            return;
        }
        em.getTransaction().begin();
        this.aula.ativar(inst, datas, nMaxAl);
        catIns.putAula(inst, aula);
        em.getTransaction().commit();
        System.out.println("A aula foi bem ativada!");
    }

    public void DivideAula(Date startDate, Date endDate){
        System.out.println(this.aula.divideEmDias(startDate, endDate).toString());
    }

}