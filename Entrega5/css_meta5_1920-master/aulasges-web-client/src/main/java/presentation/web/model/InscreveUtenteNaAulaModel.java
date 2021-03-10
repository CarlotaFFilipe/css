package presentation.web.model;
import java.util.LinkedList;

import facade.dto.AulaDTO;
import facade.dto.ModalidadeDTO;
import facade.handlers.IInscreverAulaServiceRemote;


public class InscreveUtenteNaAulaModel  extends Model{
	private String modalidade;
	private String tipo;
	private String aula;
	private String numUtente;
	private IInscreverAulaServiceRemote iiasr;


	public void setInscreverAulaHandler(IInscreverAulaServiceRemote iiasr) {
		this.iiasr = iiasr;
	}

	public void setMod(String mod) {
		this.modalidade = mod;	
	}

	public String getMod() {
		return this.modalidade;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;	
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setAula(String aula) {
		this.aula = aula;	
	}

	public String getAula() {
		return this.aula;
	}

	public void setNumUtente(String numUtente) {
		this.numUtente = numUtente;	
	}

	public String getNumUtente() {
		return this.numUtente;
	}

	public void clearFields() {
		numUtente = tipo = "";
		modalidade = aula = "1";
	}

	public Iterable<ModalidadeDTO> getModalidades () {
		return iiasr.inscreverAula();
	}
	
	public Iterable<AulaDTO> getAulas() {
		return iiasr.inscricao(this.modalidade,this.tipo);
	}
	
	public AulaDTO getAulaInscrever() {
		return iiasr.indicaAula(aula, numUtente);
	}
}
