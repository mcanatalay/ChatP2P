/**
 * This class is event bus that handles
 * Signals & Slots
 */

package TCPServer;

import java.util.*;

public class ServerThreadBus {
	protected List<ServerThreadListener> listeners = new ArrayList<ServerThreadListener>();
	
	public void addListener(ServerThreadListener incoming){
		listeners.add(incoming);
	}
	
	public ArrayList<ServerThreadListener> getListeners(){
		return (ArrayList<ServerThreadListener>) this.listeners;
	}
	
	/* SIGNALS */
	
	public void SignalRead(String input){
		for(ServerThreadListener lst : getListeners())
			lst.onRead(input);
	}
	
	public void SignalDisconnect(){
		for(ServerThreadListener lst : getListeners())
			lst.onDisconnect();
	}
	
	public void SignalStart(){
		for(ServerThreadListener lst : getListeners())
			lst.onStart();
	}
}