package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class StartTestController extends Controller {

	@FXML
	private Button botonComenzar;
	@FXML
	private Button botonVolver;
	
	private MainApp mainApp;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
	
	public void handleComenzar(){
		mainApp.abrirVista("view/TestPage.fxml");
	}
	
	public void handleVolver(){
		mainApp.abrirVista("view/VentanaPrincipal.fxml");
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
    }

}
