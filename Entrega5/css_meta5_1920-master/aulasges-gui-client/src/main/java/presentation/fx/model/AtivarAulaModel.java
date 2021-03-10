package presentation.fx.model;

import java.util.Date;

import facade.handlers.IAtivarAulaServiceRemote;
import facade.handlers.ICriarAulaServiceRemote;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class AtivarAulaModel {
	private final StringProperty nomeAula;
	private final IntegerProperty nMaxAl;
	private final ObjectProperty<Date> dataInicio;
	private final ObjectProperty<Date> dataFinal;
	private final ObjectProperty<Instalacao> selectedInstalacao;
	private final ObservableList<Instalacao> instalacoes;

	public AtivarAulaModel(IAtivarAulaServiceRemote cs) {

		nomeAula = new SimpleStringProperty();
		nMaxAl = new SimpleIntegerProperty();
		dataInicio = new SimpleObjectProperty<>(null);
		dataFinal = new SimpleObjectProperty<>(this,"dataFinal");
		this.instalacoes = FXCollections.observableArrayList();
		cs.ativarAula().forEach(d -> instalacoes.add(new Instalacao(d.getCapacidade(), d.getNome())));
		selectedInstalacao = new SimpleObjectProperty<>(null);
	}

	public ObjectProperty<Instalacao> selectedInstalacaoProperty() {
		return selectedInstalacao;
	}

	public final Instalacao getSelectedInstalacao() {
		return selectedInstalacao.get();
	}

	public final void setSelectedInstalacao(Instalacao d) {
		selectedInstalacao.set(d);
	}

	public ObservableList<Instalacao> getInstalacoes() {
		return instalacoes;
	}

	
	
	public ObjectProperty<Date> dateInicialProperty() {
		return dataInicio;
	}

	public final Date getDateInicial() {
		return dataInicio.get();
	}

	public final void setDateInicial(Date d) {
		dataInicio.set(d);
	}
	
	
	
	
	
	
	
	
	public ObjectProperty<Date> dateFinalProperty() {
		return dataFinal;
	}

	public final Date getDateFinal() {
		return dataFinal.get();
	}

	public final void setDateFinal(Date d) {
		dataFinal.set(d);
	}
	
	

	public String getNomeAula() {
		return nomeAula.get();
	}

	public StringProperty nomeAulaProperty() {
		return nomeAula;
	}

	public int getNMaxAl() {
		return nMaxAl.get();
	}

	public IntegerProperty nMaxAlProperty() {
		return nMaxAl;
	}

	public void clearProperties() {
		nomeAula.set("");
		nMaxAl.set(0);
		selectedInstalacao.set(null);
	}

}
