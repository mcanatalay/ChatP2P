/**
 * TCP Client Bus that holds instance of responder
 * It also redirect given input to proper slots
 */


package TCPClient;

import java.util.*;

public class TCPClientBus {
	protected List<TCPClientListener> listeners = new ArrayList<TCPClientListener>();
	
	public void addListener(TCPClientListener incoming){
		listeners.add(incoming);
	}
	
	public ArrayList<TCPClientListener> getListeners(){
		return (ArrayList<TCPClientListener>) this.listeners;
	}
	
	public void SignalConnect(){
		for(TCPClientListener lst : getListeners())
			lst.onConnect();
	}

	public void SignalRead(String input){
		for(TCPClientListener lst : getListeners())
			lst.onRead(input);
	}

	public void SignalDisconnect(){
		for(TCPClientListener lst : getListeners())
			lst.onDisconnect();
	}	
}
