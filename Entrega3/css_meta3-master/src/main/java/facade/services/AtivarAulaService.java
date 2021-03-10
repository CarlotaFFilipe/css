package facade.services;
import business.*;
import java.util.*;
import javafx.util.*;
public class AtivarAulaService{

    private AtivarAulaHandler ativarHandler;

    public AtivarAulaService(AtivarAulaHandler ativarHandler){
        this.ativarHandler = ativarHandler;
    }

    public void ativarAula(){
        this.ativarHandler.ativarAula();
    }

    public void informacoesAula(String nomeAula,String ins, Pair<Date, Date> datas, int nMaxAl){
        this.ativarHandler.informacoesAula(nomeAula, ins, datas, nMaxAl);
    }
}