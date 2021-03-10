package business;

import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity implementation class for Entity: Utente
 *
 */
@Entity
@NamedQuery(name=Utente.FIND_BY_NUMUTENTE, query="SELECT u FROM Utente u WHERE u.nome = :" + 
		Utente.STRING_NUMUTENTE)

public class Utente implements Serializable {
	public static final String FIND_BY_NUMUTENTE = "Utente.findBynumUtente";
	public static final String STRING_NUMUTENTE= "numUtente";

	
	private String nome;
	private String nif;   
	@Id
	private String numUtente;
	@OneToOne
	private Inscricao insc;
	private static final long serialVersionUID = 1L;

	Utente(){}
	
	public Utente(String nome, String nif, String numUtente){
        this.nome=nome;
        this.nif=nif;
        this.numUtente=numUtente;

    }

    public String getNumUtente(){
        return this.numUtente;
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
    
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}   
	public String getNif() {
		return this.nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	} 

	public void setnumUtente(String numUtente) {
		this.numUtente = numUtente;
	}

	public void setInsc(Inscricao insc) {
		this.insc = insc;
	}
   
}
