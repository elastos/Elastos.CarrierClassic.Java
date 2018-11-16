package org.elastos.demo.controller;

import org.elastos.demo.MainApp;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;


public class AddFriendController{
	private MainApp mainApp;
	
	@FXML
    private TextField adressTextFieldId;
	
	@FXML
    private TextField messageTextFieldId;
	
	public AddFriendController(){
		//System.out.println("init AddFriendController");
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
	
	@FXML
    private void onAdd(){
		String addressText=adressTextFieldId.getText();
		String messageText=messageTextFieldId.getText();
		if(!messageText.equals("") && !addressText.equals("")){
			adressTextFieldId.clear();
			messageTextFieldId.clear();
			this.mainApp.addFriend(addressText, messageText);
		}
	}
}