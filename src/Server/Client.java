/**
 * This is ServerThread extension for Server class
 * This is the class that handles each user seperatly
 */

package Server;

import java.net.Socket;

import TCPServer.ServerThread;

public class Client extends ServerThread {
	//variables
	private ClientBus cevent_bus; //its own bus that signals to Server class
	
	public Client(Server cevent_responder, Socket incoming){
		super(incoming); //Instantiate ServerThread
		this.cevent_bus = new ClientBus(); //Creates bus
		this.cevent_bus.addListener(cevent_responder); //Add parent as responder
	}
	
	/* SLOTS */
	public void onStart(){
                cevent_bus.SignalNewMessage("Connected: " + client.getLocalAddress());
	}
	
	public void onDisconnect(){
		cevent_bus.SignalDisconnect(conn_num);
		cevent_bus.SignalNewMessage("Disconnected: " + client.getLocalAddress());
	};
	
	public void onRead(String input){
                cevent_bus.SignalNewMessage(input);
	}
}
