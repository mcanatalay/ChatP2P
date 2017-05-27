/**
 * This class extends TCPServer and has
 * it own seperate event bus. It basically
 * more purpose only version of TCPServer
 */

package Server;

import P2P.P2P;
import java.net.Socket;
import TCPServer.TCPServer;

public class Server extends TCPServer implements ClientListener {
        private ServerBus sevent_bus; //its own bus that signals to P2P class
    
	public Server(int port, P2P sevent_responder){
		super(port);
		this.sevent_bus = new ServerBus(); //Creates bus
		this.sevent_bus.addListener(sevent_responder); //Add parent as responder
	}
	
	/* Handle SLOTS */
	public void onAccept(Socket incoming){
		//This SLOT is important for overriding Thread!
		int conn_num = clients.add(new Client(this,incoming));
		clients.get(conn_num).setConnNumber(conn_num);
		clients.get(conn_num).start();
	}
	
	public void onDisconnect(int conn_num){
		clients.remove(conn_num);
	}
	
	public void onNewMessage(String message){
                sevent_bus.SignalNewMessage(message);
	}

	//This function sends given input to everyone
	public void sendEveryOne(String input){
		int size = clients.getSize();
		for(int i = 0; size > i; i++){
			if(clients.get(i) != null){
				clients.get(i).send(input);
			}
		}
	}
}
