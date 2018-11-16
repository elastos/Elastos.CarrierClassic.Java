package org.elastos.demo.controller;

import org.elastos.demo.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.application.Platform;
import java.io.IOException;


public class WelcomeController{
	private MainApp mainApp;
	
	@FXML
	private ProgressIndicator progressIndicatorId;
	
	public WelcomeController(){
		//System.out.println("init WelcomeController");
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
}