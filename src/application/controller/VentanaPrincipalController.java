package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class VentanaPrincipalController implements Initializable {
	
	private MainApp mainApp;
	
	@FXML
	private Button botonNuevoTest;
	@FXML
	private Button botonInfoTest;
	
	@FXML
	public void handleNuevoTest(){
		//Iniciar un nuevo Test
		System.out.println("Nevo test");
		mainApp.abrirVista("view/TestPage.fxml");
	}
	
	@FXML
	public void handleSalir(){
		//Cerrar la aplicaión
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
