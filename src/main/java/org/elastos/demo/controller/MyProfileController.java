package org.elastos.demo.controller;

import org.elastos.demo.MainApp;
import org.elastos.carrier.UserInfo;
import javafx.fxml.FXML;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.scene.text.Text;


public class MyProfileController{
	private MainApp mainApp;
	
	private UserInfo userInfo;
	
	@FXML
    private TextField nameTextFieldId;
	
	@FXML
    private TextField genderTextFieldId;
	
	@FXML
    private TextField phoneTextFieldId;
	
	@FXML
    private TextField emailTextFieldId;
	
	@FXML
    private TextField regionTextFieldId;
	
	@FXML
    private TextField descriptionTextFieldId;
	
	public MyProfileController(){
		//System.out.println("init MyProfileController");
	}
	
	public void setMainApp(MainApp mainApp){
		this.mainApp=mainApp;
	}
	
	public void init(UserInfo userInfo){
		this.userInfo=userInfo;
		nameTextFieldId.setText(this.userInfo.getName());
		genderTextFieldId.setText(this.userInfo.getGender());
		phoneTextFieldId.setText(this.userInfo.getPhone());
		emailTextFieldId.setText(this.userInfo.getEmail());
		regionTextFieldId.setText(this.userInfo.getRegion());
		descriptionTextFieldId.setText(this.userInfo.getDescription());
	}
	
	@FXML
    private void onSave(){
		String name=nameTextFieldId.getText();
		String gender=genderTextFieldId.getText();
		String phone=phoneTextFieldId.getText();
		String email=emailTextFieldId.getText();
		String region=regionTextFieldId.getText();
		String description=descriptionTextFieldId.getText();
		
		this.userInfo.setName(name);
		this.userInfo.setGender(gender);
		this.userInfo.setPhone(phone);
		this.userInfo.setEmail(email);
		this.userInfo.setRegion(region);
		this.userInfo.setDescription(description);
		
		this.mainApp.setSelfInfo(this.userInfo);
	}
}