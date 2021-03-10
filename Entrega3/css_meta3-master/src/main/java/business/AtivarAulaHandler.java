package business;
import javafx.util.Pair; 
import java.util.*;
import java.text.*;

public class AtivarAulaHandler{
    private Aula aula;
    private CatalogoAulas catAu;
    private CatalogoInstalacoes catIns;

    public AtivarAulaHandler(CatalogoAulas catAu, CatalogoInstalacoes catIns) {
        this.catAu = catAu;
        this.catIns = catIns;
    }

    public void ativarAula(){
        System.out.println(this.catIns.getInstalacoes().toString());
    }

    public void informacoesAula(String nomeAula,String ins, Pair<Date, Date> datas, int nMaxAl){
        this.aula = this.catAu.getAula(nomeAula);
        if(this.aula == null){
            System.out.println("Nao existe aula com este nome.");
            return;
        }else if(this.aula.getEstado()) {
        	System.out.println("Aula ja foi ativada.");
            return;
        }
        Instalacao inst;
        if((inst = this.catIns.getInstalacao(ins) )== null){
            System.out.println("Instalacao invalida");
            return;
        }

        if(!inst.getModalidadesPermitidas().contains(this.aula.getModalidade())){
            System.out.println("Instalacao nao permite essa modalidade");
            return;
        }

        Date dNow = new Date( );
        SimpleDateFormat ft = new SimpleDateFormat ("dd-MM-yyyy");
        ft.format(dNow);

        if(datas.getKey().before(dNow)){
            System.out.println("O par de datas nao define um periodo no futuro");
            return;
        }
        if(nMaxAl > inst.getCapacidade()){
            System.out.println("Numero de participantes e superior a capacidade da instalacao");
            return;
        }
        
        //TODO: a instalação está livre no horário/dias-da-semana da aula, durante todo o período em que a aula estiver ativa
        List<Date> nh = this.aula.divideEmDias(datas.getKey(), datas.getValue());
        if(!inst.instalacaoNOcupada(nh, this.aula.getHoraInicio(),this.aula.getHoraFinal())){
            System.out.println("Instalacao indisponivel nesta hora");
            return;
        }

        this.aula.ativar(inst, datas, nMaxAl);
        this.catIns.putAula(inst, aula);
        System.out.println("A aula foi bem ativada!");
    }

    public void DivideAula(Date startDate, Date endDate){
        System.out.println(this.aula.divideEmDias(startDate, endDate).toString());
    }

}