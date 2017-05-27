package P2P;

import java.util.ArrayList;
import java.util.List;

public class P2PBus {
	protected List<P2PListener> listeners = new ArrayList<P2PListener>();
	
	//adds instance to list
	public void addListener(P2PListener incoming){
		listeners.add(incoming);
	}
	
	//returns all listeners
	public ArrayList<P2PListener> getListeners(){
		return (ArrayList<P2PListener>) this.listeners;
	}
	
	//Signals "new message"
	public void SignalNewMessage(String input){
		for(P2PListener lst: getListeners())
			lst.onNewMessageP2P(input);
        }
}
