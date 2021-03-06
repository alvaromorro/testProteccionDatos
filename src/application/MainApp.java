package application;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import application.controller.Controller;
import application.logica.Pregunta;
import application.logica.ReadXML;
import application.logica.Test;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import application.logica.ResizeSceneListener;

public class MainApp extends Application {

	 private Stage primaryStage;
	 private BorderPane rootLayout;
	 
	    @Override
	    public void start(Stage primaryStage) {
	        this.primaryStage = primaryStage;

	        primaryStage.setOnCloseRequest(event -> {
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
	    			event.consume();
	    		}	
		    });
	        
	        initRootLayout();
	    }
	    

	    /**
	     * Initializes the root layout.
	     */
	    public void initRootLayout() {
	        try {
	        	primaryStage.setHeight(600);
				primaryStage.setWidth(800);
	            // Load root layout from fxml file.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(getURL("view/VentanaPrincipal.fxml"));
	            rootLayout = (BorderPane) loader.load();
	            rootLayout.getStyleClass().add("borderPane");
	            Controller controller = loader.getController();
	            controller.setMainApp(this);
	                        
	            // Show the scene containing the root layout.
	            Scene scene = new Scene(rootLayout);
	            scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());

	            primaryStage.setScene(scene);
	            primaryStage.show();
	         
	            final double initWidth  = scene.getWidth();
				final double initHeight = scene.getHeight();
				final double ratio = initWidth / initHeight;
				

				//para poder redimensionar la scene
				ResizeSceneListener sizeListener = new ResizeSceneListener(scene, ratio, initHeight, initWidth, rootLayout);
				scene.widthProperty().addListener(sizeListener);
				scene.heightProperty().addListener(sizeListener);
				
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    public void abrirVista(String urlVista) {
	    	FadeTransition fadeTransition = new FadeTransition(Duration.millis(250),primaryStage.getScene().getRoot());
			fadeTransition.setFromValue(1.0);
			fadeTransition.setToValue(0.0);
			fadeTransition.setOnFinished(new EventHandler<ActionEvent>() {
			    @Override
			    public void handle(ActionEvent event) {
			    	 try {
			           // Load view
			           FXMLLoader loader = new FXMLLoader();
			           loader.setLocation(MainApp.class.getResource(urlVista));
			           BorderPane vista = (BorderPane) loader.load();
			            
			           Controller controller = loader.getController();
			           setMainApp(controller);			            				            
			           // Set view into the center of root layout.
			           Scene scene = new Scene(vista);
			           primaryStage.setScene(scene);
			           scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			           vista.getStyleClass().add("borderPane");
			        }catch (IOException e) {
			            e.printStackTrace();
			        }
			    }
			});		
			fadeTransition.play();
	    }
	    
	    private void setMainApp(Controller c){
	    	c.setMainApp(this);
	    }

	    /**
	     * Returns the main stage.
	     * @return
	     */
	    public Stage getPrimaryStage() {
	        return primaryStage;
	    }
	    
	    public URL getURL(String file){
	    	return MainApp.class.getResource(file);
	    }
	    
	    public static void main(String[] args) {
	    	ReadXML xml  = new ReadXML();
	     	Test test = Test.getReference();
	     	
	     	//Generar la lista de preguntas y rellenarla con las preguntas del XML
	     	ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
	     	xml.readXML(preguntas);
	     	
	     	//Asignamos las preguntas al test
	     	test.setListaPreguntas(preguntas);

	        launch(args);
	    }
	    
//	   
}
