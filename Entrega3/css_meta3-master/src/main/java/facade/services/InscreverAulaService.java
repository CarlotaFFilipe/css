package facade.services;
import business.*;

public class InscreverAulaService {
    private InscreverAulaHandler inscreverHandler;

    public InscreverAulaService(InscreverAulaHandler inscreverHandler){
        this.inscreverHandler = inscreverHandler;
    }

    public void inscreverAula(){
        this.inscreverHandler.inscreverAula();
    }


    public void inscricao(String mod,String tipo){
        this.inscreverHandler.inscricao(mod,tipo);
    }

    public void indicaAula(String aula, String numUtente){
        this.inscreverHandler.indicaAula(aula,numUtente);
    }
}