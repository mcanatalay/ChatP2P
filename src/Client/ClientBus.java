/**
 * Client Bus that holds instance of ClientListener
 * Calls the SLOT, and redirect input to SLOT
 */

package Client;

import java.util.ArrayList;
import java.util.List;

public class ClientBus {
	protected List<ClientListener> listeners = new ArrayList<ClientListener>();
	
	//adds instance to list
	public void addListener(ClientListener incoming){
		listeners.add(incoming);
	}
	
	//returns all listeners
	public ArrayList<ClientListener> getListeners(){
		return (ArrayList<ClientListener>) this.listeners;
	}
	
	//Signals "new message"
	public void SignalNewMessage(String input){
		for(ClientListener lst: getListeners())
			lst.onNewMessageClient(input);
        }
}
