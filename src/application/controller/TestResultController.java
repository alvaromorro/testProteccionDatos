package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.MainApp;
import application.logica.ReportGenerator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class TestResultController implements Controller {

	private MainApp mainApp;
	
	
	@FXML
	private Button volver;
	@FXML
	private Button informe;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		volver.setOnAction(this::handleAceptar);
		informe.setOnAction(this::handleGenerarInforme);
	}
	
	public void handleAceptar(ActionEvent event){
		Alert a= new Alert(AlertType.WARNING);
		a.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
		a.setHeaderText("Atención");
		a.setContentText("Se perdeá el informe al volver a la pantalla de inicio \n¿Desea continuar?");
		Optional<ButtonType> result = a.showAndWait();
		if (result.get() == ButtonType.OK){
			mainApp.abrirVista("view/VentanaPrincipal.fxml");
		} else {
		    // ... user chose CANCEL or closed the dialog
			a.close();
		}		
	}
	
	public void handleGenerarInforme(ActionEvent event){
		ReportGenerator rp = new ReportGenerator();
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}
}
