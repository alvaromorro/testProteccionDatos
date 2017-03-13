package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import application.controller.VentanaPrincipalController;
import application.logica.Pregunta;
import application.logica.ReadXML;
import application.logica.Test;
import application.logica.TestPage;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainApp extends Application {

	 private Stage primaryStage;
	 private BorderPane rootLayout;

	    @Override
	    public void start(Stage primaryStage) {
	        this.primaryStage = primaryStage;
	        this.primaryStage.setTitle("AddressApp");

	        initRootLayout();
	    }

	    /**
	     * Initializes the root layout.
	     */
	    public void initRootLayout() {
	        try {
	            // Load root layout from fxml file.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource("view/VentanaPrincipal.fxml"));
	            rootLayout = (BorderPane) loader.load();
	            
	            VentanaPrincipalController controller = loader.getController();
	            controller.setMainApp(this);

	            // Show the scene containing the root layout.
	            Scene scene = new Scene(rootLayout);
	            primaryStage.setScene(scene);
	            primaryStage.show();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	   
	    public void abrirVista(String urlVista) {
	        try {
	            // Load view
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(MainApp.class.getResource(urlVista));
	            BorderPane vista = (BorderPane) loader.load();

	            // Set view into the center of root layout.
	            rootLayout.setCenter(vista);
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }

	    /**
	     * Returns the main stage.
	     * @return
	     */
	    public Stage getPrimaryStage() {
	        return primaryStage;
	    }
	    


	    public static void main(String[] args) {
	    	ReadXML xml  = new ReadXML();
	     	Test test = new Test();
	     	TestPage page1 = new TestPage();
	     	TestPage page2 = new TestPage();
	     	TestPage page3 = new TestPage();
	     	
	     	//Añadimos las páginas al test
	     	test.addPagina(page1);
	     	test.addPagina(page2);
	     	test.addPagina(page3);
	     	
	     	//Generar la lista de preguntas y rellenarla con las preguntas del XML
	     	ArrayList<Pregunta> preguntas = new ArrayList<Pregunta>();
	     	xml.readXML(preguntas);
	     	
	     	//Asignamos las preguntas a las páginas
	     	page1.setListaPreguntas(preguntas.subList(0, 9));
	     	page1.setNumeroPreguntas(10);
	     	
//	    	page2.setListaPreguntas(preguntas.subList(10, 19));
//	     	page2.setNumeroPreguntas(10);
//	     	
//	    	page3.setListaPreguntas(preguntas.subList(20, 29));
//	     	page3.setNumeroPreguntas(10);
    
	        launch(args);
	    }
	    
//	   
}
