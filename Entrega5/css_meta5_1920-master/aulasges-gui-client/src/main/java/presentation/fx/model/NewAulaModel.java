package presentation.fx.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import facade.handlers.ICriarAulaServiceRemote;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

public class NewAulaModel {
	private final StringProperty n;
	private final StringProperty dias;
	private final StringProperty horaI;
	private final StringProperty dur;
	
	
	private final ObjectProperty<Modalidade> selectedModalidade;
	private final ObservableList<Modalidade> modalidades;
	
	
	public NewAulaModel(ICriarAulaServiceRemote cs) {
		
		n = new SimpleStringProperty();
		dias = new SimpleStringProperty();
		horaI = new SimpleStringProperty();
		dur = new SimpleStringProperty();
		this.modalidades = FXCollections.observableArrayList();
			cs.criarAula().forEach(d -> modalidades.add(new Modalidade(d.getNome(), d.getDuracao(),d.getPreco())));
		selectedModalidade = new SimpleObjectProperty<>(null);
	}
	
	public ObjectProperty<Modalidade> selectedModalidadeProperty() {
		return selectedModalidade;
	}

	public final Modalidade getSelectedModalidade() {
		return selectedModalidade.get();
	}
	
	public final void setSelectedModalidade(Modalidade d) {
		selectedModalidade.set(d);
	}

	public ObservableList<Modalidade> getModalidades() {
		return modalidades;
	}
	
	public String getNome() {
		return n.get();
	}

	public StringProperty nomeProperty() {
		return n;
	}
	
	public List<String> getDias() {
		String help = dias.get();
		List<String> oui= new ArrayList<>();
		String[] s =help.split(",");
		for(String st : s) 
			oui.add(st);
		return oui;
	}

	public StringProperty diasProperty() {
		return dias;
	}
	
	public Date getHoraInicio() {
		return new Date(horaI.get());
	}

	public StringProperty horaInicioProperty() {
		return horaI;
	}
	
	public Date getDur() {
		return new Date(dur.get());
	}

	public StringProperty durProperty() {
		return dur;
	}
	
	public void clearProperties() {
		n.set("");
		dias.set("");
		horaI.set("");
		dur.set("");
		selectedModalidade.set(null);
	}
}

