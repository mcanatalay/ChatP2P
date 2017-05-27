/**
 * Event bus for Client. It connected to Server
 */

package Server;

import java.util.*;

public class ClientBus {
	protected List<ClientListener> listeners = new ArrayList<ClientListener>();
	
	public void addListener(ClientListener incoming){
		listeners.add(incoming);
	}
	
	public ArrayList<ClientListener> getListeners(){
		return (ArrayList<ClientListener>) this.listeners;
	}
	
	/* SIGNALS */
	
	public void SignalNewMessage(String message){
		for(ClientListener lst : getListeners())
			lst.onNewMessage(message);
	}

	public void SignalDisconnect(int conn_num){
		for(ClientListener lst : getListeners())
			lst.onDisconnect(conn_num);	
	}
}