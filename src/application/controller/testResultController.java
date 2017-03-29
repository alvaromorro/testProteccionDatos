package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.MainApp;
import application.logica.Test;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class testResultController extends Controller implements Initializable {

	private MainApp mainApp;
	
	@FXML
	private Text textoSi;
	@FXML
	private Text textoNo;
	@FXML
	private Button aceptar;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Test test = Test.getReference();
		int resSi = test.countResultadoSi();
		int resNo = test.getNumeroPreguntas() - resSi;
		textoSi.setText(resSi+"");
		textoNo.setText(resNo+"");
		
		aceptar.setOnAction(this::handleAceptar);
	}
	
	public void handleAceptar(ActionEvent event){
		mainApp.abrirVista("view/VentanaPrincipal.fxml");
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}
}
