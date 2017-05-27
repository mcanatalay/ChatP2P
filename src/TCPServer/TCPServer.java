/**
 * This class creates a ServerSocket
 * and creates a ServerThread for each connection.
 * It also handles some signals coming from ServerThread
 */

package TCPServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer extends Thread implements TCPServerListener {
	//variables
	private ServerSocket server;
	protected TCPServerBus event_bus;
	protected ServerThreadList clients;
	protected int port;
	protected boolean serverStatus;
	
	//constructor takes port
	public TCPServer(int port){
		super();
		try{
			this.port = port;
			this.server = new ServerSocket(port);
			this.event_bus = new TCPServerBus();
			this.event_bus.addListener(this);
			this.clients = new ServerThreadList();
		} catch(IOException e){
			System.out.println("Server cannot be initialized on "+ port);
			System.exit(-1);
		}
	}
	
	//starts the server
	public void run(){
		this.serverStatus = true;
		while(serverStatus){
			try{
				//waits for a connection and redirects it
				Socket incoming = this.server.accept();
				event_bus.SignalAccept(incoming);
			} catch (IOException e){
				System.out.println("Exception encountered on accept. Ignoring.");
			}
		}
		
        try{ 
            this.server.close(); 
            System.out.println("Server Stopped"); 
        } 
        catch(Exception ioe){ 
            System.out.println("Problem stopping server socket"); 
            System.exit(-1); 
        } 
	}
	
	//handles a new connection (important for overriding)
	public void onAccept(Socket input){
		int conn_num = clients.add(new ServerThread(input));
		clients.get(conn_num).setConnNumber(conn_num);
		clients.get(conn_num).start();
	}
	
	public void onDisconnect(int conn_num){
		clients.remove(conn_num);
	}
}
