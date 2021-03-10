package presentation.fx.inputcontroller;

import java.util.Date;
import java.util.List;
import java.util.function.UnaryOperator;

import facade.handlers.ICriarAulaServiceRemote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import presentation.fx.model.Modalidade;
import presentation.fx.model.NewAulaModel;

public class NewAulaController extends BaseController{
	@FXML
	private TextField nTextField;
	@FXML
	private TextField diasTextField;
	@FXML
	private TextField horaITextField;
	@FXML
	private TextField durTextField;

	@FXML
	private ComboBox<Modalidade> modalidadeComboBox;


	private NewAulaModel model;

	private ICriarAulaServiceRemote aulaService;

	public void setAulaService(ICriarAulaServiceRemote aulaService) {
		this.aulaService = aulaService;
	}


	public void setModel(NewAulaModel model) {
		this.model = model;
		//modTextField.textProperty().bindBidirectional(model.modProperty());
		nTextField.textProperty().bindBidirectional(model.nomeProperty());
		diasTextField.textProperty().bindBidirectional(model.diasProperty());  
		horaITextField.textProperty().bindBidirectional(model.horaInicioProperty());   	
		durTextField.textProperty().bindBidirectional(model.durProperty());   	
		modalidadeComboBox.setItems(model.getModalidades());   
		modalidadeComboBox.setValue(model.getSelectedModalidade());
	}

	@FXML
	private void initialize() {
		UnaryOperator<Change> integerFilter = change -> {
			String newText = change.getControlNewText();
			if (newText.matches("[0-9]*")) { 
				return change;
			}
			return null;
		};
	}

	@FXML
	void createAulaAction(ActionEvent event) {
		String errorMessages = validateInput();

		if (errorMessages.length() == 0) {
			aulaService.especificaAula(model.getSelectedModalidade().getName(), 
					model.getNome(), model.getDias(), model.getHoraInicio(),model.getDur());
			model.clearProperties();
			showInfo(i18nBundle.getString("new.aula.success"));

		} else
			showError(i18nBundle.getString("new.aula.error.validating") + ":\n" + errorMessages);
	}




	private String validateInput() {	
		StringBuilder sb = new StringBuilder();
		String designation = model.getNome();
		if (designation == null || designation.length() == 0)
			sb.append(i18nBundle.getString("new.aula.invalid.designation"));
		List<String> dias = model.getDias();
		if (dias == null || dias.size() == 0) {
			sb.append(i18nBundle.getString("new.aula.invalid.dias"));
		}
		Date horaInicio = model.getHoraInicio();
		if (horaInicio==null) {
			sb.append(i18nBundle.getString("new.aula.invalid.horaI"));
		}
		
		Date dur = model.getDur();
		if (dur==null) {
			sb.append(i18nBundle.getString("new.aula.invalid.duracao"));
		}
	
		
		if (model.getSelectedModalidade() == null) {
			if (sb.length() > 0)
				sb.append("\n");
			sb.append(i18nBundle.getString("new.aula.invalid.modalidade"));
		}

		return sb.toString();
	}


	@FXML
	void modalidadeTypeSelected(ActionEvent event) {
		model.setSelectedModalidade(modalidadeComboBox.getValue());
	}

}
