package org.elastos.demo.controller;

import org.elastos.demo.MainApp;
import org.elastos.carrier.FriendInfo;
import org.elastos.carrier.ConnectionStatus;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Platform;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;


public class MainController{
	private MainApp mainApp;
	
	private Map<String, FriendInfo> onlineFriendsMap;
	
	@FXML
	private VBox friendListVboxId;
	
	public MainController(){
		//System.out.println("init MainController");
		onlineFriendsMap=new HashMap<String, FriendInfo>();
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
	
	public void refreshOnlineFriends(List<FriendInfo> friendList){
		Platform.runLater(() -> friendListVboxId.getChildren().clear());
		
		for(int i=0;i<friendList.size();i++){
			FriendInfo friend=friendList.get(i);
			if(friend.getConnectionStatus()==ConnectionStatus.Connected){
				String friendLabel=friend.getLabel();
				if(friendLabel.equals("") || friendLabel==null){
					friendLabel=friend.getUserId();
				}
				
				String friendName=friendLabel;
				Button button=new Button(friendName);
				button.setOnAction(new EventHandler<ActionEvent>(){
					@Override public void handle(ActionEvent e) {
						mainApp.initChatByUsername(friendName);
					}
				});
				Platform.runLater(() -> friendListVboxId.getChildren().add(button));
			}
		}
	}
	
	@FXML
    private void onBack(){
		this.mainApp.setWelcomeLayout();
	}
	
	@FXML
    private void onMyProfile(){
		this.mainApp.setMyProfileLayout();
	}
	
	@FXML
    private void onAddFriend(){
		this.mainApp.setAddFriendLayout();
	}
	
	@FXML
    private void onAll(){
		this.mainApp.setAllFriendsLayout();
	}
	
	@FXML
    private void onExit(){
		this.mainApp.exit();
	}
}