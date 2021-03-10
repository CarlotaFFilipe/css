package presentation.fx;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import facade.handlers.IAtivarAulaServiceRemote;
import facade.handlers.ICriarAulaServiceRemote;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import presentation.fx.inputcontroller.AtivarAulaController;
import presentation.fx.inputcontroller.NewAulaController;
import presentation.fx.model.AtivarAulaModel;
import presentation.fx.model.NewAulaModel;

public class Startup extends Application {
    
    private static IAtivarAulaServiceRemote ativaAulaService;
    private static ICriarAulaServiceRemote criarAulaService;
	

	@Override 
    public void start(Stage stage) throws IOException {
    
		// This line to resolve keys against Bundle.properties
		// ResourceBundle i18nBundle = ResourceBundle.getBundle("i18n.Bundle", new Locale("en", "UK"));
        ResourceBundle i18nBundle = ResourceBundle.getBundle("i18n.Bundle", new Locale("pt", "PT"));
		
    	FXMLLoader aulaLoader = new FXMLLoader(getClass().getResource("/fxml/newAula.fxml"), i18nBundle);
    	Parent root = aulaLoader.load();
    	NewAulaController newAulaController = aulaLoader.getController();  
    	FXMLLoader ativarAulaLoader = new FXMLLoader(getClass().getResource("/fxml/ativarAula.fxml"), i18nBundle);
    	Parent root2 = aulaLoader.load();
    	AtivarAulaController ativarAulaController = ativarAulaLoader.getController();
    	
    	NewAulaModel newAulaModel = new NewAulaModel(criarAulaService);
    	newAulaController.setModel(newAulaModel);
    	newAulaController.setAulaService(criarAulaService);
    	newAulaController.setI18NBundle(i18nBundle);
    	
    	AtivarAulaModel ativarAulaModel = new AtivarAulaModel(ativaAulaService);
    	ativarAulaController.setModel(ativarAulaModel);
    	ativarAulaController.setAulaService(ativaAulaService);
    	ativarAulaController.setI18NBundle(i18nBundle);
    	
        Scene scene = new Scene(root, 450, 275);
       
        stage.setTitle(i18nBundle.getString("application.title"));
        stage.setScene(scene);
        stage.show();
    }
	
	public static void startGUI(IAtivarAulaServiceRemote ativaAulaHandler,ICriarAulaServiceRemote criarAulaHandler) {
		Startup.ativaAulaService = ativaAulaHandler;
		Startup.criarAulaService = criarAulaHandler;
        launch();
	}
}
