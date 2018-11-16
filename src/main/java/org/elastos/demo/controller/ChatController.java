package org.elastos.demo.controller;

import org.elastos.demo.MainApp;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.application.Platform;


public class ChatController{
	private MainApp mainApp;
	
	@FXML
    private Label usernameLabelId;
	
	@FXML
    private TextField messageTextFieldId;
	
	@FXML
    private VBox windowVboxId;
	
	public ChatController(){
		//System.out.println("init ChatController");
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
	
	public void initUser(String username){
		usernameLabelId.setText(username);
		windowVboxId.getChildren().clear();
	}
	
	public void receiveFriendMessage(String userId, String message){
		String activeUserId=usernameLabelId.getText();
		
		if(userId.equals(activeUserId)){
			Label messageLabel=new Label("Friend: "+message);
			Platform.runLater(() -> windowVboxId.getChildren().add(messageLabel));
		}
	}
	
	@FXML
    private void onSend(){
		String messageText=messageTextFieldId.getText();
		if(!messageText.equals("")){
			messageTextFieldId.clear();
			Label messageLabel=new Label("You: "+messageText);
			windowVboxId.getChildren().add(messageLabel);
			
			String userId=usernameLabelId.getText();
			this.mainApp.sendFriendMessage(userId, messageText);
		}
	}
}