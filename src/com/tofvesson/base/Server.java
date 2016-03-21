package com.tofvesson.base;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.tofvesson.api.Callback;
import com.tofvesson.api.ICallback;
import com.tofvesson.api.ICoordinate;
import com.tofvesson.api.IMap;
import com.tofvesson.api.IServerService;

public class Server implements IServerService{
	
	public boolean economicMode=false;
	private int eco=0;
	Thread clientListener;
	Map<Integer, Socket> clients;
	List<String> tasklist;
	Map<Integer, Boolean> auth;
	Callback onFinished;
	boolean active=false;
	final int port;
	final Runnable r = new Runnable(){
		@Override
		public void run() {
			try{
				while(active){
					for(Socket s : clients.values())
						if(new BufferedReader(new InputStreamReader(s.getInputStream())).ready()) ;
				}
			}catch(Exception e){
				active=false;
				onFinished.setCallback(ID_CRASH);
			}
		}
	};
	
	public Server(int port){this.port=port; active=true;}
	public Server(){this(DEFAULT_PORT);}
	
	@Override
	public void run() {
		clientListener = new Thread(r);
		clients = new HashMap<>();
		tasklist = new ArrayList<>();
		auth = new HashMap<>();
	}

	@Override
	public String[] getTasklist() {
		return (String[]) tasklist.toArray();
	}

	@Override
	public Map<Integer, Socket> getClientList() {
		return clients;
	}

	@Override
	public boolean isClientAuthenticated(int id) {
		return auth.get((Integer)id);
	}

	@Override
	public void handleNewClient(Socket s) {
		updateClientList(s, false);
		try{
		new PrintWriter(s.getOutputStream()).println(SRV_AUTH_CHAL);
		}catch(Exception e){e.printStackTrace();}
	}

	@Override
	public void updateTasklist() {
		
	}

	@Override
	public void onNewDataListener() {
		
	}

	@Override
	public void handleNewData() {
		
	}

	@Override
	public void challengeAuth(Socket s) {
		
	}
	@Override
	public ICallback start(Runnable onFinish) {
		return onFinished = new Callback(onFinish);
	}
	@Override
	public void stop() {
		if(clientListener.isAlive()) active=false;
		try{
			for(Socket s : clients.values())
				if(!s.isClosed())
					new PrintWriter(s.getOutputStream()).println(SRV_GLOBAL_STOP);
		}catch(Exception e){e.printStackTrace();}
	}
	@Override
	public IMap getMap() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void loadSubMap(Socket s, ICoordinate start, ICoordinate end) {
		// TODO Auto-generated method stub
		
	}
	
	private void updateClientList(Socket newClient, boolean overrideEco){
		if(!economicMode || (economicMode && eco==4) || overrideEco){
			Map<Integer, Socket> cli = new HashMap<>();
			int i=0;
			for(Socket s : clients.values())
				if(!s.isClosed())
					cli.put(i++, s);
			clients=cli;
		}
		clients.put(clients.size(), newClient);
		if(economicMode) eco=(eco+1)%5;
	}

}
