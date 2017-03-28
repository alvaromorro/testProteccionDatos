package application.controller;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

public class VentanaPrincipalController extends Controller {
	
	private MainApp mainApp;
	
	@FXML
	private Button botonNuevoTest;
	@FXML
	private Button botonInfoTest;
	
	@FXML
	public void handleNuevoTest(){
		//Iniciar un nuevo Test
		System.out.println("Nevo test");
		mainApp.abrirVista("view/StartTest.fxml");
	}
	
	@FXML
	public void handleSalir(){
		//Cerrar la aplicaión
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Dialogo de confirmación");
		alert.setHeaderText("Va a cerrarla aplicación");
	

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
		   System.exit(0);
		} else {
		    // ... user chose CANCEL or closed the dialog
			alert.close();
		}	
	}
	
	@FXML
	public void handleInfoTest(){
		//Mostrar info 
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}

}
