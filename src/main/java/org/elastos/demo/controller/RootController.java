package org.elastos.demo.controller;

import org.elastos.demo.MainApp;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;


public class RootController{
	private MainApp mainApp;
	
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
}