package org.elastos.demo;

import org.elastos.carrier.AbstractCarrierHandler;
import org.elastos.carrier.Carrier;
import org.elastos.carrier.ConnectionStatus;
import org.elastos.carrier.PresenceStatus;
import org.elastos.carrier.UserInfo;
import org.elastos.carrier.FriendInfo;
import org.elastos.carrier.exceptions.CarrierException;
import org.elastos.demo.MainApp;
import org.elastos.demo.common.Synchronizer;
import org.elastos.demo.common.TestOptions;
import java.util.List;


public class MainHandler extends AbstractCarrierHandler{
	private Synchronizer synch;
	private MainApp mainApp;
	
	public MainHandler(MainApp mainApp){
		this.synch=new Synchronizer();
		this.mainApp=mainApp;
	}
	
	public Synchronizer getSynch(){
		return this.synch;
	}
	
	@Override
	public void onIdle(Carrier carrier){
		//System.out.println("onIdle");
	}
	
	@Override
	public void onConnection(Carrier carrier, ConnectionStatus status){
		System.out.println("onConnection");
		try{
			String address=carrier.getAddress();
			String userId=carrier.getUserId();
			this.mainApp.initWelcomeData(address, userId);
		}
		catch(CarrierException e){}
		this.synch.wakeup();
	}
	
	@Override
	public void onReady(Carrier carrier){
		System.out.println("onReady");
		this.synch.wakeup();
	}
	
	@Override
	public void onSelfInfoChanged(Carrier carrier, UserInfo info){
		System.out.println("onSelfInfoChanged");
	}
	
	@Override
	public void onFriends(Carrier carrier, List<FriendInfo> friends){
		System.out.println("onFriends");
		this.mainApp.initFriends(friends);
		this.synch.wakeup();
	}
	
	@Override
	public void onFriendConnection(Carrier carrier, String friendId, ConnectionStatus status){
		System.out.println("onFriendConnection");
		this.mainApp.refreshOnlineFriends();
		this.synch.wakeup();
	}
	
	@Override
	public void onFriendInfoChanged(Carrier carrier, String friendId, FriendInfo info){
		System.out.println("onFriendInfoChanged");
	}
	
	@Override
	public void onFriendPresence(Carrier carrier, String friendId, PresenceStatus presence){
		System.out.println("onFriendPresence");
	}
	
	@Override
	public void onFriendRequest(Carrier carrier, String userId, UserInfo info, String hello){
		System.out.println("onFriendRequest");
		this.mainApp.addFriendRequest(userId);
		this.synch.wakeup();
	}
	
	@Override
	public void onFriendAdded(Carrier carrier, FriendInfo info){
		System.out.println("onFriendAdded");
		this.mainApp.addFriendToList(info);
		this.synch.wakeup();
	}
	
	@Override
	public void onFriendRemoved(Carrier carrier, String friendId){
		System.out.println("onFriendRemoved");
		this.mainApp.removeFriendFromList();
		this.synch.wakeup();
	}
	
	@Override
	public void onFriendMessage(Carrier carrier, String from, byte[] message){
		System.out.println("onFriendMessage");
		String userId=from;
		String messageText=new String(message);
		this.mainApp.receiveFriendMessage(userId, messageText);
		this.synch.wakeup();
	}
	
	@Override
	public void onFriendInviteRequest(Carrier carrier, String from, String data){
		System.out.println("onFriendInviteRequest");
	}
}