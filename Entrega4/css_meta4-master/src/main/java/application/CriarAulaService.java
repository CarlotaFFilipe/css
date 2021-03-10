package application;
import business.*;
import java.util.*;

public class CriarAulaService {

    private CriarAulaHandler criarHandler;

    public CriarAulaService(CriarAulaHandler criarHandler){
        this.criarHandler = criarHandler;
    }

    public void criarAula(){
        this.criarHandler.criarAula();
    }

    public void especificaAula(String mod, String n, List<String> dias, Date horaI, Date dur){
        this.criarHandler.especificaAula(mod, n, dias, horaI, dur);

    }
}