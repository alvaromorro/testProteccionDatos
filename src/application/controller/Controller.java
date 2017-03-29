package application.controller;

import java.net.URL;
import java.util.ResourceBundle;

import application.MainApp;
import javafx.fxml.Initializable;

public interface Controller extends Initializable {

	public void setMainApp(MainApp main);
	
	@Override
	public void initialize(URL location, ResourceBundle resources);

}
