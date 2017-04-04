package application.controller;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXTextField;

import application.MainApp;
import application.logica.Pregunta;
import application.logica.ReportGenerator;
import application.logica.Test;
import javafx.animation.FadeTransition;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.stage.Stage;

public class TestPageController implements Controller{

	private MainApp mainApp;
	private String textoNumeroPregunta;
	private Test test;
	private Pregunta preguntaActual;
	private int numeroPregunta = 0;
	private int maximoPreguntas;
	
	@FXML
	private TextArea textArea;
	@FXML
	private JFXTextField numeroPreguntaTextField;
	@FXML
	private RadioButton botonSi;
	@FXML
	private RadioButton botonNo;
	@FXML
	private Button volver;
	@FXML
	private Button continuar;
	@FXML
	private ProgressBar progress;
	
	final ToggleGroup group = new ToggleGroup();
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		continuar.setOnAction(this::handleContinuar);
		volver.setOnAction(this::handleVolver);
		this.test = Test.getReference();
		maximoPreguntas = test.getNumeroPreguntas();
		preguntaActual = test.getPregunta(0);
		numeroPregunta = preguntaActual.getNumeroPregunta();
		textoNumeroPregunta = "Pregunta " +numeroPregunta+" de "+ maximoPreguntas;
		
		numeroPreguntaTextField.setText(textoNumeroPregunta);
		textArea.setText(preguntaActual.getTextoPregunta());
		textArea.setEditable(false);
		
		botonSi.setToggleGroup(group);
		botonNo.setToggleGroup(group);
		
	}
	
	public void handleContinuar(ActionEvent event){
		if(numeroPregunta != maximoPreguntas){
			actualizarPregunta();
		}else{
			if(getRespuesta()){
				finTest();
				mainApp.abrirVista("view/TestResult.fxml");
			}		
		}	
	
	}
	
	public void handleVolver(ActionEvent event){
		//popup de confirmación
		Alert alert = new Alert(AlertType.WARNING);
		alert.setHeaderText("Atención");
		alert.getDialogPane().getButtonTypes().addAll(ButtonType.CANCEL);
		alert.setContentText("Se perdeá el progreso al volver a la pantalla de inicio \n¿Desea continuar?");

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == ButtonType.OK){
			mainApp.abrirVista("view/VentanaPrincipal.fxml");;
		} else {
		    // ... user chose CANCEL or closed the dialog
			alert.close();
		}			
	}
	
	@SuppressWarnings("unchecked")
	private void finTest(){
		System.out.println("Botón pulsado");

		Stage dialogStage = new Stage();
		
	    FXMLLoader loader = new FXMLLoader(mainApp.getURL("view/loadingBar.fxml"));
		try {
			BorderPane root = (BorderPane) loader.load();
			Scene scene = new Scene(root);
			dialogStage.setScene(scene);
			ProgressController pc = loader.getController();
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
			try{
			FadeTransition fadeOut = new FadeTransition(Duration.millis(500),textArea);
			fadeOut.setAutoReverse(true);
			fadeOut.setFromValue(1.0);
			fadeOut.setToValue(0.0);
			
			fadeOut.setOnFinished(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent event) {
			    	numeroPregunta = numeroPregunta + 1;
					preguntaActual = test.getPregunta(numeroPregunta - 1);
					textoNumeroPregunta = "Pregunta " + numeroPregunta +" de "+ maximoPreguntas;
					
					numeroPreguntaTextField.setText(textoNumeroPregunta);
					textArea.setText(preguntaActual.getTextoPregunta());	
			    	FadeTransition fadeIn = new FadeTransition(Duration.millis(500),textArea);
					fadeIn.setAutoReverse(true);
					fadeIn.setFromValue(0.0);
					fadeIn.setToValue(1.0);
					fadeIn.play();
			    }
			});
			fadeOut.play();

			}catch(Exception e){e.printStackTrace();}
		}
	}

	private boolean getRespuesta(){
		int res = 0;
		if(!(group.getSelectedToggle() == null)){
			RadioButton boton = (RadioButton) group.getSelectedToggle();
			boton.setSelected(false);
			if(boton.getText().equals("Si")){
				System.out.println("Si");
				res = 1;
			}else{
				System.out.println("No");
				res = 2;
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
