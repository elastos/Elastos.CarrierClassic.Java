package org.elastos.demo;

import org.elastos.carrier.Carrier;
import org.elastos.carrier.exceptions.CarrierException;
import org.elastos.carrier.UserInfo;
import org.elastos.carrier.FriendInfo;
import org.elastos.demo.MainApp;
import org.elastos.demo.common.Synchronizer;
import org.elastos.demo.common.TestOptions;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.lang.reflect.Field;
import java.lang.Thread;


public class ElastosCarrier extends Thread{
	private static Carrier carrier;
	private static TestOptions options;
	private static MainHandler handler;
	private MainApp mainApp;
	
	static{
		_loadElastosLib();
	}
	
	public ElastosCarrier(MainApp mainApp){
		this.mainApp=mainApp;
	}
	
	public void run(){
		String dataPath=this._getDataPath();
		options=new TestOptions(dataPath);
		handler=new MainHandler(this.mainApp);

		try{
			Carrier.initializeInstance(options, handler);
			this.carrier=Carrier.getInstance();
			this.carrier.start(1000);
			handler.getSynch().await();
		}
		catch(CarrierException e){
			e.printStackTrace();
		}
	}
	
	public void kill(){
		try{
			this.carrier.kill();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
	public List<FriendInfo> getFriends(){
		List<FriendInfo> friendList=null;
		try{
			friendList=this.carrier.getFriends();
		}
		catch(CarrierException e){}
		handler.getSynch().wakeup();
		return friendList;
	}
	
	public void addFriend(String address, String message){
		try{
			this.carrier.addFriend(address, message);
		}
		catch(CarrierException e){}
		handler.getSynch().wakeup();
	}
	
	public void acceptFriend(String userId){
		try{
			this.carrier.acceptFriend(userId);
		}
		catch(CarrierException e){}
		handler.getSynch().wakeup();
	}
	
	public void removeFriend(String userId){
		try{
			this.carrier.removeFriend(userId);
		}
		catch(CarrierException e){}
		handler.getSynch().wakeup();
	}
	
	public void sendFriendMessage(String userId, String message){
		try{
			this.carrier.sendFriendMessage(userId, message);
		}
		catch(CarrierException e){}
		handler.getSynch().wakeup();
	}
	
	public UserInfo getSelfInfo(){
		UserInfo userInfo=null;
		try{
			userInfo=this.carrier.getSelfInfo();
		}
		catch(CarrierException e){}
		handler.getSynch().wakeup();
		return userInfo;
	}
	
	public void setSelfInfo(UserInfo userInfo){
		try{
			this.carrier.setSelfInfo(userInfo);
		}
		catch(CarrierException e){}
		handler.getSynch().wakeup();
	}
	
	private static void _loadElastosLib(){
		try{
			String resourcePath="/lib/";
			ArrayList<String> libList=new ArrayList<String>();
			String osName=System.getProperty("os.name");
			if(osName.equals("Linux")){
				String jniDirName="jni";
				File jniDir=new File(jniDirName);
				if(!jniDir.exists()){
					jniDir.mkdir();
				}
				
				resourcePath+="linux";
				libList.add("libcrystal.so");
				libList.add("libelacarrier.so");
				libList.add("libelasession.so");
				libList.add("libcarrierjni.so");
				
				for(int i=0;i<libList.size();i++){
					String libFile=libList.get(i);
					InputStream is=ClassLoader.class.getResourceAsStream(resourcePath+"/"+libFile);
					File file=new File(jniDirName+"/"+libFile);
					OutputStream os=new FileOutputStream(file);
					byte[] buffer=new byte[1024];
					int length;
					while((length=is.read(buffer))!=-1){
						os.write(buffer, 0, length);
					}
					is.close();
					os.close();
				}
				
				String jniPath=jniDir.getAbsolutePath();
				_addJniLibraryPathForLinux(jniPath);
			}
			else{
				resourcePath+="windows";
				libList.add("crystal.dll");
				libList.add("elacarrier.dll");
				libList.add("elasession.dll");
				libList.add("libcarrierjni.dll");
				libList.add("pthreadVC2.dll");
				
				for(int i=0;i<libList.size();i++){
					String libFile=libList.get(i);
					InputStream is=ClassLoader.class.getResourceAsStream(resourcePath+"/"+libFile);
					File file=new File(libFile);
					OutputStream os=new FileOutputStream(file);
					byte[] buffer=new byte[1024];
					int length;
					while((length=is.read(buffer))!=-1){
						os.write(buffer, 0, length);
					}
					is.close();
					os.close();
				}
			}
		}
		catch(IOException ioe){}
	}
	
	private static void _addJniLibraryPathForLinux(String pathToAdd){
		try{
			final Field usrPathsField=ClassLoader.class.getDeclaredField("usr_paths");
			usrPathsField.setAccessible(true);

			final String[] paths=(String[])usrPathsField.get(null);
			
			for(String path : paths){
				if(path.equals(pathToAdd)){
					return;
				}
			}

			final String[] newPaths=Arrays.copyOf(paths, paths.length+1);
			newPaths[newPaths.length-1]=pathToAdd;
			usrPathsField.set(null, newPaths);
		}
		catch(Exception e){}
	}
	
	private String _getDataPath(){
		String dataDirName="data";
		File dataDir=new File(dataDirName);
		if(!dataDir.exists()){
			dataDir.mkdir();
		}
		return dataDir.getAbsolutePath();
	}
}