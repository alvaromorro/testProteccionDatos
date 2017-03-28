package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.MainApp;
import application.logica.Pregunta;
import application.logica.Test;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

public class TestPageController extends Controller{

	private MainApp mainApp;
	private String textoNumeroPregunta;
	private Test test;
	private Pregunta preguntaActual;
	
	
	@FXML
	public Text numeroPreguntaTextField;
	@FXML
	public Text textoPreguntaTextField;
	@FXML
	public RadioButton botonSi;
	@FXML
	public RadioButton botonNo;
	
	final ToggleGroup group = new ToggleGroup();
 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		test = Test.getReference();
		preguntaActual = test.getPregunta(0);
		textoNumeroPregunta = "Pregunta " +preguntaActual.getNumeroPregunta()+" de "+test.getNumeroPreguntas();
		
		numeroPreguntaTextField.setText(textoNumeroPregunta);
		textoPreguntaTextField.setText(preguntaActual.getTextoPregunta());
		
		botonSi.setToggleGroup(group);
		botonNo.setToggleGroup(group);
		
	}
	
	public void handleContinuar(){
		//siguiente pregunta
	}
	
	public void handleVolver(){
		//popup de confirmación
		
	}
	
	public void handleRadioButton(){
		//hacer los botones exclusivos
	}
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}

}
