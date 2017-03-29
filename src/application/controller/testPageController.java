package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JSpinner.NumberEditor;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import application.MainApp;
import application.logica.Pregunta;
import application.logica.Test;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;

public class testPageController extends Controller{

	private MainApp mainApp;
	private String textoNumeroPregunta;
	private Test test;
	private Pregunta preguntaActual;
	private int numeroPregunta = 0;
	private int maximoPreguntas;
	
	
	@FXML
	private Text numeroPreguntaTextField;
	@FXML
	private Text textoPreguntaTextField;
	@FXML
	private RadioButton botonSi;
	@FXML
	private RadioButton botonNo;
	@FXML
	private Button continuar;
	
	final ToggleGroup group = new ToggleGroup();
 
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		continuar.setOnAction(this::handleContinuar);
		
		this.test = Test.getReference();
		maximoPreguntas = test.getNumeroPreguntas();
		preguntaActual = test.getPregunta(0);
		numeroPregunta = preguntaActual.getNumeroPregunta();
		textoNumeroPregunta = "Pregunta " +numeroPregunta+" de "+ maximoPreguntas;
		
		numeroPreguntaTextField.setText(textoNumeroPregunta);
		textoPreguntaTextField.setText(preguntaActual.getTextoPregunta());
		
		botonSi.setToggleGroup(group);
		botonNo.setToggleGroup(group);
		
	}
	
	public void handleContinuar(ActionEvent event){
		if(numeroPregunta != maximoPreguntas){
			actualizarPregunta();
		}else{
			getRespuesta();
			finTest();
		}
		
	
	}
	
	public void handleVolver(){
		//popup de confirmación
		
	}
	
	private void finTest(){
		System.out.println("Fin del test");
		mainApp.abrirVista("view/TestResult.fxml");
	}
	
	public void actualizarPregunta(){

		if(getRespuesta()){
			numeroPregunta = numeroPregunta + 1;
			preguntaActual = test.getPregunta(numeroPregunta - 1);
			textoNumeroPregunta = "Pregunta " + numeroPregunta +" de "+ maximoPreguntas;
			
			numeroPreguntaTextField.setText(textoNumeroPregunta);
			textoPreguntaTextField.setText(preguntaActual.getTextoPregunta());
		}
	}
	
	
	private boolean getRespuesta(){
	
		if(!(group.getSelectedToggle() == null)){
			RadioButton boton = (RadioButton) group.getSelectedToggle();
			boton.setSelected(false);
			
			preguntaActual.setRespuesta(boton.getText());
			return true;
			
		}else{
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Atención");
			alert.setHeaderText("Seleccione una respuesta para continuar");
			
			alert.showAndWait();	
			return false;
		}		
	}
	public void setMainApp(MainApp mainApp){
		this.mainApp = mainApp;
	}

}
