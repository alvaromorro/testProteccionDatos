package application.controller;

import java.awt.Desktop;
import java.net.URI;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import application.MainApp;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Hyperlink;

public class VentanaPrincipalController implements Controller {
	
	private MainApp mainApp;
	
	@FXML
	private Button botonNuevoTest;
	@FXML
	private Button salir;
	@FXML
	private Hyperlink linkREPD;
	@FXML
	private Hyperlink linkAEPD;
	@FXML
	private Hyperlink linkGuia;

	
	@FXML
	public void handleNuevoTest(ActionEvent event){
		//Iniciar un nuevo Test
		System.out.println("Nevo test");
		mainApp.abrirVista("view/StartTest.fxml");
	}
	
	@FXML
	public void handleSalir(ActionEvent event){
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
	
	public void openBrowser(String url){
		try {
			Desktop.getDesktop().browse(new URI(url));
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setContentText(e.getMessage());
			alert.show();
			e.printStackTrace();
		}
	}
	
	public void handleLinkREPD(ActionEvent event){
		openBrowser("https://goo.gl/u7LH5H");
	}
	
	public void handleLinkAEPD(ActionEvent event){
		openBrowser("https://goo.gl/8pLrJ5");
	}
	public void handleLinkGuia(ActionEvent event){
		openBrowser("https://goo.gl/44H8Rg");
	}
	@FXML
	public void handleInfoTest(ActionEvent event){
		mainApp.abrirVista("view/Informacion.fxml"); 
	}
	
	public void setMainApp(MainApp mainApp) {
		this.mainApp = mainApp;
    }

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		botonNuevoTest.setOnAction(this::handleNuevoTest);
		salir.setOnAction(this::handleSalir);
		linkREPD.setOnAction(this::handleLinkREPD);
		linkAEPD.setOnAction(this::handleLinkAEPD);
		linkGuia.setOnAction(this::handleLinkGuia);
	}
}
