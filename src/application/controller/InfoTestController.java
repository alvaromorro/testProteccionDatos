package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextArea;

import application.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class InfoTestController implements Controller {

	private MainApp mainApp;
	
	@FXML
	private Button volver;
	@FXML
	private JFXTextArea area;
	
	@Override
	public void setMainApp(MainApp main) {
		// TODO Auto-generated method stub
		this.mainApp = main;
	}
	
	private void handleVolver(ActionEvent event){
		mainApp.abrirVista("view/VentanaPrincipal.fxml");
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		volver.setOnAction(this::handleVolver);
		area.setEditable(false);
	}
}
