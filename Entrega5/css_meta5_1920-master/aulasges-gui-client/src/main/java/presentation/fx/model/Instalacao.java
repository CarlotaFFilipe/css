package presentation.fx.model;

import java.util.Date;
import java.util.List;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.util.Pair;

public class Instalacao {

	private final StringProperty nome = new SimpleStringProperty();
	//private final StringProperty modPermitidas = new SimpleStringProperty();
	private final IntegerProperty capacidade = new SimpleIntegerProperty();


	public Instalacao(int capacidade,String nome){
		setName(nome);
		//setModPermitidas(modPermitidas);
		setCapacidade(capacidade);
	}


	public final void setName(final String name) {
		this.nameProperty().set(name);
	}

	public final StringProperty nameProperty() {
		return this.nome;
	}

	public final String getName() {
		return this.nameProperty().get();
	}
	
	
	/*public final void setModPermitidas(final String modPermitidas) {
		this.modPermitidasProperty().set(modPermitidas);
	}*/

	/*public final StringProperty modPermitidasProperty() {
		return this.modPermitidas;
	}*/

	/*public final String getModPermitidas() {
		return this.modPermitidasProperty().get();
	}*/
	
	
	
	
	public final void setCapacidade(final int capacidade) {
		this.capacidadeProperty().set(capacidade);
	}

	public final IntegerProperty capacidadeProperty() {
		return this.capacidade;
	}

	public final int getCapacidade() {
		return this.capacidadeProperty().get();
	}
	


	@Override
	public String toString(){
		return getName();
	}
}
