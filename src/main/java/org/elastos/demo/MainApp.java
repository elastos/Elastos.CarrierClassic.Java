package org.elastos.demo;

import org.elastos.carrier.FriendInfo;
import org.elastos.carrier.UserInfo;
import org.elastos.demo.ElastosCarrier;
import org.elastos.demo.controller.RootController;
import org.elastos.demo.controller.WelcomeController;
import org.elastos.demo.controller.WelcomeDataController;
import org.elastos.demo.controller.MainController;
import org.elastos.demo.controller.ChatController;
import org.elastos.demo.controller.AddFriendController;
import org.elastos.demo.controller.AllFriendsController;
import org.elastos.demo.controller.MyProfileController;
import javafx.application.Application;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.application.Platform;
import java.io.IOException;
import java.util.List;


public class MainApp extends Application{
	private static ElastosCarrier elastosCarrier;
	private Stage primaryStage;
    private BorderPane rootLayout;
	private RootController rootController;
	private BorderPane welcomeLayout;
	private WelcomeController welcomeController;
	private VBox welcomeDataLayout;
	private WelcomeDataController welcomeDataController;
	private BorderPane mainLayout;
	private MainController mainController;
	private Pane chatLayout;
	private ChatController chatController;
	private Pane addFriendLayout;
	private AddFriendController addFriendController;
	private Pane allFriendsLayout;
	private AllFriendsController allFriendsController;
	private Pane myProfileLayout;
	private MyProfileController myProfileController;
	
	@Override
	public void start(Stage primaryStage){
		this.primaryStage=primaryStage;
        this.primaryStage.setTitle("Elastos Messenger");
		
		this.initRootLayout();
		this.initWelcomeLayout();
		this.initWelcomeDataLayout();
		this.initMainLayout();
		this.initChatLayout();
		this.initAddFriendLayout();
		this.initAllFriendsLayout();
		this.initMyProfileLayout();
		
		this.setWelcomeLayout();
		this.elastosCarrier=new ElastosCarrier(this);
		this.elastosCarrier.start();
	}
	
	@Override
	public void stop(){
		this.kill();
	}
	
	public void kill(){
		this.elastosCarrier.kill();
	}
	
	public void exit(){
		this.kill();
		Platform.exit();
	}
	
	public void initRootLayout(){
		try{
			FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("root.fxml"));
			this.rootLayout=(BorderPane)loader.load();
			Scene scene=new Scene(this.rootLayout);
			primaryStage.setScene(scene);
			this.rootController=loader.getController();
			this.rootController.setMainApp(this);
			primaryStage.show();
		}
		catch(IOException ioe){}
	}
	
	public void initWelcomeLayout(){
		try{
			FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("welcome.fxml"));
			this.welcomeLayout=(BorderPane)loader.load();
			this.welcomeController=loader.getController();
			this.welcomeController.setMainApp(this);
		}
		catch(IOException ioe){}
	}
	
	public void initWelcomeDataLayout(){
		try{
			FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("welcome_data.fxml"));
			this.welcomeDataLayout=(VBox)loader.load();
			this.welcomeDataController=loader.getController();
			this.welcomeDataController.setMainApp(this);
		}
		catch(IOException ioe){}
	}
	
	public void initMainLayout(){
		try{
			FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("main.fxml"));
			this.mainLayout=(BorderPane)loader.load();
			this.mainController=loader.getController();
			this.mainController.setMainApp(this);
		}
		catch(IOException ioe){}
	}
	
	public void initChatLayout(){
		try{
			FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("chat.fxml"));
			this.chatLayout=(Pane)loader.load();
			this.chatController=loader.getController();
			this.chatController.setMainApp(this);
		}
		catch(IOException ioe){}
	}
	
	public void initAddFriendLayout(){
		try{
			FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("add_friend.fxml"));
			this.addFriendLayout=(Pane)loader.load();
			this.addFriendController=loader.getController();
			this.addFriendController.setMainApp(this);
		}
		catch(IOException ioe){}
	}
	
	public void initAllFriendsLayout(){
		try{
			FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("all_friends.fxml"));
			this.allFriendsLayout=(Pane)loader.load();
			this.allFriendsController=loader.getController();
			this.allFriendsController.setMainApp(this);
		}
		catch(IOException ioe){}
	}
	
	public void initMyProfileLayout(){
		try{
			FXMLLoader loader=new FXMLLoader(getClass().getClassLoader().getResource("my_profile.fxml"));
			this.myProfileLayout=(Pane)loader.load();
			this.myProfileController=loader.getController();
			this.myProfileController.setMainApp(this);
		}
		catch(IOException ioe){}
	}
	
	public void setWelcomeLayout(){
		this.rootLayout.setCenter(this.welcomeLayout);
	}
	
	public void setMainLayout(){
		this.rootLayout.setCenter(this.mainLayout);
	}
	
	public void initWelcomeData(String address, String userId){
		this.welcomeDataController.initWelcomeData(address, userId);
	}
	
	public void setAddFriendLayout(){
		this.mainLayout.setCenter(this.addFriendLayout);
	}
	
	public void setAllFriendsLayout(){
		this.mainLayout.setCenter(this.allFriendsLayout);
	}
	
	public void setMyProfileLayout(){
		UserInfo userInfo=this.elastosCarrier.getSelfInfo();
		this.myProfileController.init(userInfo);
		this.mainLayout.setCenter(this.myProfileLayout);
	}
	
	public void initChatByUsername(String username){
		this.chatController.initUser(username);
		this.mainLayout.setCenter(this.chatLayout);
	}
	
	public void refreshOnlineFriends(){
		List<FriendInfo> friendList=this.elastosCarrier.getFriends();
		this.mainController.refreshOnlineFriends(friendList);
	}
	
	public void initFriends(List<FriendInfo> friendList){
		this.allFriendsController.initFriends(friendList);
	}
	
	public void addFriendToList(FriendInfo friend){
		this.allFriendsController.addFriendToList(friend);
	}
	
	public void removeFriendFromList(){
		List<FriendInfo> friendList=this.elastosCarrier.getFriends();
		this.initFriends(friendList);
	}
	
	public void setWelcomeDataLayout(){
		Platform.runLater(() -> this.welcomeLayout.setCenter(this.welcomeDataLayout));
	}
	
	public void addFriend(String address, String message){
		this.elastosCarrier.addFriend(address, message);
	}
	
	public void acceptFriend(String userId){
		this.elastosCarrier.acceptFriend(userId);
	}
	
	public void removeFriend(String userId){
		this.elastosCarrier.removeFriend(userId);
	}
	
	public void addFriendRequest(String userId){
		this.allFriendsController.addFriendRequest(userId);
	}
	
	public void sendFriendMessage(String userId, String message){
		this.elastosCarrier.sendFriendMessage(userId, message);
	}
	
	public void receiveFriendMessage(String userId, String message){
		this.chatController.receiveFriendMessage(userId, message);
	}
	
	public void setSelfInfo(UserInfo userInfo){
		this.elastosCarrier.setSelfInfo(userInfo);
	}
	
	public static void main(String[] arguments){
		launch(arguments);
	}
	
}