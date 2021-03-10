package presentation.web.model;

import java.util.Date;

import facade.dto.AulaDTO;
import facade.dto.ModalidadeDTO;
import facade.handlers.IInscreverAulaServiceRemote;
import facade.handlers.IVisualizarServiceRemote;
import javafx.util.Pair;

public class VisualizarOcupacaoAulaModel  extends Model{
	private Date data;
	private IVisualizarServiceRemote visualizarHandlers;
	private String nome;
	
	public void setVisualizarHandler(IVisualizarServiceRemote visualizarHandlers) {
		this.visualizarHandlers = visualizarHandlers;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public void setNome(String nome) {
		this.nome=nome;
	}
	
	public void setDate(String data) {
		this.data = new Date (data);	
	}

	public Date getDate() {
		return this.data;
	}
	public Iterable<Pair<AulaDTO, Pair<Date,Date>>> getModalidades () {
		return visualizarHandlers.verInstalacao(nome, data);
	}
	
	public void clearFields() {
		nome = "";//data = 
	}
}
