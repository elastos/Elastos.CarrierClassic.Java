package org.elastos.demo.controller;

import org.elastos.demo.MainApp;
import org.elastos.carrier.FriendInfo;
import org.elastos.carrier.ConnectionStatus;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.application.Platform;
import java.util.List;
import java.util.ArrayList;


public class AllFriendsController{
	private MainApp mainApp;
	private ArrayList<String> friendRequestList;
	
	@FXML
    private VBox allVboxId;
	
	@FXML
    private VBox incomingVboxId;
	
	public AllFriendsController(){
		//System.out.println("init AllFriendsController");
		this.friendRequestList=new ArrayList<String>();
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
	
	public void initFriends(List<FriendInfo> friendList){
		Platform.runLater(() -> allVboxId.getChildren().clear());
		for(int i=0;i<friendList.size();i++){
			String userId=friendList.get(i).getUserId();
			Text userIdText=new Text(userId);
			Button removeButton=new Button("Remove");
			removeButton.setUserData(userId);
			removeButton.setOnAction(new EventHandler<ActionEvent>(){
					@Override public void handle(ActionEvent ae) {
						Button button=(Button)ae.getSource();
						String removedUserId=(String)button.getUserData();
						mainApp.removeFriend(removedUserId);
					}
				});
			Platform.runLater(() -> allVboxId.getChildren().add(userIdText));
			Platform.runLater(() -> allVboxId.getChildren().add(removeButton));
		}
	}
	
	public void addFriendToList(FriendInfo friend){
		String userId=friend.getUserId();
		Text userIdText=new Text(userId);
		Button removeButton=new Button("Remove");
		removeButton.setUserData(userId);
		removeButton.setOnAction(new EventHandler<ActionEvent>(){
				@Override public void handle(ActionEvent ae) {
					Button button=(Button)ae.getSource();
					String removedUserId=(String)button.getUserData();
					mainApp.removeFriend(removedUserId);
				}
			});
		Platform.runLater(() -> allVboxId.getChildren().add(userIdText));
		Platform.runLater(() -> allVboxId.getChildren().add(removeButton));
		this.removeFriendRequest(userId);
	}
	
	public void initFriendRequests(){
		Platform.runLater(() -> incomingVboxId.getChildren().clear());
		for(int i=0;i<this.friendRequestList.size();i++){
			String userId=this.friendRequestList.get(i);
			Text userIdText=new Text(userId);
			Button acceptButton=new Button("Accept");
			acceptButton.setUserData(userId);
			acceptButton.setOnAction(new EventHandler<ActionEvent>(){
					@Override public void handle(ActionEvent ae) {
						Button button=(Button)ae.getSource();
						String acceptUserId=(String)button.getUserData();
						mainApp.acceptFriend(acceptUserId);
					}
				});
			Platform.runLater(() -> incomingVboxId.getChildren().add(userIdText));
			Platform.runLater(() -> incomingVboxId.getChildren().add(acceptButton));
		}
	}
	
	public void addFriendRequest(String userId){
		this.friendRequestList.add(userId);
		this.initFriendRequests();
	}
	
	public void removeFriendRequest(String userId){
		this.friendRequestList.remove(userId);
		this.initFriendRequests();
	}
}