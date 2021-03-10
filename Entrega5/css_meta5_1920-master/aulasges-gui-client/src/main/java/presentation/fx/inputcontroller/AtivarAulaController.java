package presentation.fx.inputcontroller;

import java.util.Date;
import java.util.function.UnaryOperator;

import facade.handlers.IAtivarAulaServiceRemote;
import facade.handlers.ICriarAulaServiceRemote;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.TextFormatter.Change;
import javafx.util.Pair;
import javafx.util.converter.IntegerStringConverter;
import javafx.util.converter.NumberStringConverter;
import presentation.fx.model.AtivarAulaModel;
import presentation.fx.model.Instalacao;
import presentation.fx.model.Modalidade;
import presentation.fx.model.NewAulaModel;

public class AtivarAulaController extends BaseController{
	@FXML
	private TextField nomeAulaTextField;
	@FXML
	private DatePicker dataInicioTextField;
	@FXML
	private DatePicker dataFinalTextField;
	@FXML
	private TextField nMaxAlTextField;
	
	@FXML
	private ComboBox<Instalacao> instalacaoComboBox;
	
	private AtivarAulaModel model;

	private IAtivarAulaServiceRemote aulaService;
	
	public void setAulaService(IAtivarAulaServiceRemote aulaService) {
		this.aulaService = aulaService;
	}


	public void setModel(AtivarAulaModel model) {
		this.model = model;
		nomeAulaTextField.textProperty().bindBidirectional(model.nomeAulaProperty());
		//dataInicioTextField.valueProperty().bindBidirectional(model.dateInicialProperty());  
		//dataFinalTextField.dayCellFactoryProperty().bindBidirectional(model.dateFinalProperty());
		nMaxAlTextField.textProperty().bindBidirectional(model.nMaxAlProperty(), new NumberStringConverter());
		instalacaoComboBox.setItems(model.getInstalacoes());   
		instalacaoComboBox.setValue(model.getSelectedInstalacao());
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

		nMaxAlTextField.setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter(),
				0, integerFilter));
	}
	
	@FXML
	void ativarAulaAction(ActionEvent event) {
		String errorMessages = validateInput();

		if (errorMessages.length() == 0) {
			Pair<Date,Date> datas = new Pair<>(model.getDateInicial(), model.getDateFinal());			aulaService.informacoesAula(model.getNomeAula(),model.getSelectedInstalacao().getName(),datas,model.getNMaxAl()); 
			model.clearProperties();
			showInfo(i18nBundle.getString("ativar.aula.success"));

		} else
			showError(i18nBundle.getString("ativar.aula.error.validating") + ":\n" + errorMessages);
	}
	
	
	
	private String validateInput() {	
		StringBuilder sb = new StringBuilder();
		String nomeAula = model.getNomeAula();
		if (nomeAula == null || nomeAula.length() == 0)
			sb.append(i18nBundle.getString("ativar.aula.invalid.name"));
		Date dataInicial = model.getDateInicial();
		if (dataInicial == null) {
			sb.append(i18nBundle.getString("ativar.aula.invalid.datas"));
		}
		Date dataFinal = model.getDateFinal();
		if (dataFinal==null ) {
			sb.append(i18nBundle.getString("ativar.aula.invalid.datas"));
		}
		if (model.getNMaxAl() == 0) {
			if (sb.length() > 0)
				sb.append("\n");
			sb.append(i18nBundle.getString("ativar.customer.invalid.nMaxAl"));
		}
		if (model.getSelectedInstalacao()== null) {
			if (sb.length() > 0)
				sb.append("\n");
			sb.append(i18nBundle.getString("ativar.aula.invalid.instalacaoSelected"));
		}
		return sb.toString();
	}
	
	
	
	
	@FXML
	void instalacaoTypeSelected(ActionEvent event) {
		model.setSelectedInstalacao(instalacaoComboBox.getValue());
	}
}
