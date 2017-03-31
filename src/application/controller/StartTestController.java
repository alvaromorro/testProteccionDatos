package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class StartTestController implements Controller {

	@FXML
	private Button botonComenzar;
	@FXML
	private Button botonVolver;
	@FXML
	private JFXTextArea textArea;
	
	private MainApp mainApp;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		textArea.setEditable(false);

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
