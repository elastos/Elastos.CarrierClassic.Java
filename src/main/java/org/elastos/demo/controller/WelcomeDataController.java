package org.elastos.demo.controller;

import org.elastos.demo.MainApp;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.application.Platform;
import java.io.IOException;


public class WelcomeDataController{
	private MainApp mainApp;
	
	@FXML
	private TextField addressTextFieldId;
	
	@FXML
	private TextField userIdTextFieldId;
	
	public WelcomeDataController(){
		//System.out.println("init WelcomeDataController");
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
	
	public void initWelcomeData(String address, String userId){
		addressTextFieldId.setText(address);
		userIdTextFieldId.setText(userId);
		this.mainApp.setWelcomeDataLayout();
	}
	
	@FXML
    private void onStartChat(){
		this.mainApp.setMainLayout();
	}
}