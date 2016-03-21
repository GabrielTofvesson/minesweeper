package com.tofvesson.api;

import java.net.Socket;
import java.util.Map;

public interface IServerService extends Runnable{
	
	/**
	 * Default port to open server on.
	 */
	public static final int DEFAULT_PORT=0x539;
	
	//Op Codes Start
	
	//Callback codes Start
	/**
	 * Crash callback code.
	 */
	public static final int ID_CRASH=0x01;
	/**
	 * Graceful (proper) server stop callback code.
	 */
	public static final int ID_GRACEFUL=0x02;
	//Callback codes End
	
	//Server command codes Start
	/**
	 * Server authentication challenge.
	 */
	public static final int SRV_AUTH_CHAL=0x01;
	/**
	 * Server good message response.
	 */
	public static final int SRV_RESP_OK=0x02;
	/**
	 * Server bad message response.
	 */
	public static final int SRV_RESP_BAD=0x04;
	/**
	 * Stopping server broadcast.
	 */
	public static final int SRV_GLOBAL_STOP=0x08;
	//Server command codes End
	
	//Client command codes Start
	/**
	 * Client notify new connection.
	 */
	public static final int CLI_RQ_NEW=0x01;
	/**
	 * Client authentication challenge response start. End challenge response with "CLI_TASK_STOP".
	 */
	public static final int CLI_AUTH_CHAL=0x02;
	/**
	 * Client graceful disconnect.
	 */
	public static final int CLI_AUTH_DC=0x04;
	/**
	 * Client request spectator mode.
	 */
	public static final int CLI_AUTH_VIEW=0x08;
	/**
	 * Client reveal grid position.
	 */
	public static final int CLI_TASK_REVEAL=0x10;
	/**
	 * Client toggle grid position flag status.
	 */
	public static final int CLI_TASK_FLAG=0x20;
	/**
	 * Client load sub-map grid.
	 */
	public static final int CLI_TASK_LOAD=0x40;
	/**
	 * End-of-data command. Unprecedes data after a task command.
	 */
	public static final int CLI_TASK_STOP=0x80;
	//Client command codes End
	
	//Op Codes End
	
	public abstract String[] getTasklist();
	public abstract Map<?, ?> getClientList();
	public abstract boolean isClientAuthenticated(int id);
	abstract void handleNewClient(Socket s);
	abstract void updateTasklist();
	abstract void onNewDataListener();
	abstract void handleNewData();
	abstract void challengeAuth(Socket s);
	public abstract ICallback start(Runnable onFinish);
	public abstract void stop();
	public abstract IMap getMap();
	public abstract void loadSubMap(Socket s, ICoordinate start, ICoordinate end);
}
