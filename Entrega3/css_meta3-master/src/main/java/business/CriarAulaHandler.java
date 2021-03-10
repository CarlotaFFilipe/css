package business;
import java.util.*;

public class CriarAulaHandler{
    private Aula aula;
    private CatalogoAulas catAu;
    private CatalogoModalidades catMod;

    public CriarAulaHandler(CatalogoAulas catAu, CatalogoModalidades catMod){
        this.catAu = catAu;
        this.catMod = catMod;
    }

    public void criarAula(){
        System.out.println(catMod.getModalidades().toString());
    }

    public void especificaAula(String mod, String n, List<String> dias, Date horaI, Date dur){
        Modalidade modalidade = this.catMod.getMod(mod);
        if(modalidade == null){
            System.out.println("Nao existe modalidade");
            return;
        }
        this.catAu.putAula(horaI, dias, n, dur, modalidade);
    }
}