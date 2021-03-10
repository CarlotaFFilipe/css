package business;
import business.*;

public class Utente{
    private String nome;
    private String nif;
    private String num_Utente;

    private Inscricao insc;

    public Utente(String nome, String nif, String num_Utente){
        this.nome=nome;
        this.nif=nif;
        this.num_Utente=num_Utente;

    }

    public String getNumUtente(){
        return this.num_Utente;
    }

    public void putIns(Inscricao ins){
        this.insc = ins;
    }

    public Inscricao getInscricao(){
        return this.insc;
    }

    public boolean temInscricao(){
        return this.insc != null;
    }

}