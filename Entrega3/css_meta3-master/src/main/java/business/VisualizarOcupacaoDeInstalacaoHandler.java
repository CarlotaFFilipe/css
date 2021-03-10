package business;
import java.util.*;
import java.text.*;
import javafx.util.Pair; 

public class VisualizarOcupacaoDeInstalacaoHandler{
    private Instalacao ins;
    private CatalogoInstalacoes catIns;

    public VisualizarOcupacaoDeInstalacaoHandler(CatalogoInstalacoes catIns) {
        this.catIns = catIns;
    }

    public void verInstalacao(String nome,Date data){
        this.ins = this.catIns.getInstalacao(nome);
        if(ins == null){
            System.out.println("Instalacao invalida");
            return;
        }
        this.catIns.getAulasCPeriodos(this.ins,data);
    }
}