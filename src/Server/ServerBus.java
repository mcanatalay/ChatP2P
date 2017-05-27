/**
 * Client Bus that holds instance of ClientListener
 * Calls the SLOT, and redirect input to SLOT
 */

package Server;

import java.util.ArrayList;
import java.util.List;

public class ServerBus {
	protected List<ServerListener> listeners = new ArrayList<ServerListener>();
	
	//adds instance to list
	public void addListener(ServerListener incoming){
		listeners.add(incoming);
	}
	
	//returns all listeners
	public ArrayList<ServerListener> getListeners(){
		return (ArrayList<ServerListener>) this.listeners;
	}
	
	//Signals "new message"
	public void SignalNewMessage(String input){
		for(ServerListener lst: getListeners())
			lst.onNewMessageServer(input);
        }
}
