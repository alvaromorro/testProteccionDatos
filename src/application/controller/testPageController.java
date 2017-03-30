package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javax.swing.JSpinner.NumberEditor;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

import application.MainApp;
import application.logica.Pregunta;
import application.logica.ReportGenerator;
import application.logica.Test;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class testPageController implements Controller{

	private MainApp mainApp;
	private String textoNumeroPregunta;
	private Test test;
	private Pregunta preguntaActual;
	private int numeroPregunta = 0;
	private int maximoPreguntas;
	
	
	@FXML
	private TextArea textoPreguntaTextField;
	@FXML
	private Text numeroPreguntaTextField;
	@FXML
	private RadioButton botonSi;
	@FXML
	private RadioButton botonNo;
	@FXML
	private Button continuar;
	@FXML
	private ProgressBar progress;
	
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
		textoPreguntaTextField.setEditable(false);
		
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
		System.out.println("Botón pulsado");

		Stage dialogStage = new Stage();
		
	    FXMLLoader loader = new FXMLLoader(mainApp.getURL("view/loadingBar.fxml"));
		try {
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			dialogStage.setScene(scene);
			progressController pc = loader.getController();
			progress = pc.getProgressBar();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		dialogStage.setAlwaysOnTop(true);
		dialogStage.show();

		Task tarea = new Task<Void>(){

		@Override
		protected Void call() throws Exception {
			System.out.println("Ejecutando tarea");
			ReportGenerator report = new ReportGenerator();
			System.out.println("Fin tarea");
			return null;
		}};

		tarea.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            @Override
            public void handle(WorkerStateEvent t) {            
            	dialogStage.close();
            }
        });
		
		Thread th = new Thread(tarea);

		th.setDaemon(true);

		progress.progressProperty().bind(tarea.progressProperty());
		th.start();
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
		String res = "";
		if(!(group.getSelectedToggle() == null)){
			RadioButton boton = (RadioButton) group.getSelectedToggle();
			boton.setSelected(false);
			if(boton.getText().equals("Si")){
				System.out.println("Si");
				res = "Si";
			}else{
				System.out.println("No");
				res = "No";
			}
			preguntaActual.setRespuesta(res);
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
