package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.MainApp;
import javafx.fxml.Initializable;

public abstract class Controller implements Initializable {

	public void setMainApp(MainApp main){}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}

}
