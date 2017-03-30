package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.MainApp;
import application.logica.ReportGenerator;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class testResultController implements Controller {

	private MainApp mainApp;
	
	@FXML
	private Text textoSi;
	@FXML
	private Text textoNo;
	@FXML
	private Button aceptar;
	@FXML
	private ProgressBar progress;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		aceptar.setOnAction(this::handleAceptar);
	}
	
	public void handleAceptar(ActionEvent event){
		mainApp.abrirVista("view/VentanaPrincipal.fxml");
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}
}
