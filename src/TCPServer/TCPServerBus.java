/**
 * TCPServer Bus handles
 * Signals & Slots of TCPServer
 */

package TCPServer;

import java.util.*;
import java.net.Socket;

public class TCPServerBus {
	protected List<TCPServerListener> listeners = new ArrayList<TCPServerListener>();
	
	public void addListener(TCPServerListener incoming){
		listeners.add(incoming);
	}
	
	public ArrayList<TCPServerListener> getListeners(){
		return (ArrayList<TCPServerListener>) this.listeners;
	}
	
	public void SignalAccept(Socket input){
		for(TCPServerListener lst : getListeners())
			lst.onAccept(input);
		
	}
	
	public void SignalDisconnect(int conn_num){
		for(TCPServerListener lst : getListeners())
			lst.onDisconnect(conn_num);
	}
	
}
