/**
 * This thread class is a connection handler
 * for each connection separately.
 */

package TCPServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class ServerThread extends Thread implements ServerThreadListener {
	/*variables*/
	protected int conn_num; //Connections number on List
	protected Socket client;
	protected boolean clientStatus;
	protected ServerThreadBus event_bus; //Event bus
	protected BufferedReader input; //Input stream
	protected PrintWriter output; //Output stream
	
	//constructor
	public ServerThread(Socket incoming){
		super();
		this.client = incoming;
		this.clientStatus = true;
		this.event_bus = new ServerThreadBus();
		this.event_bus.addListener(this);
		try{
			input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			output = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch(Exception e){
			System.out.println("Problem of Creating Stream!");
		}
	}
	
	public void setConnNumber(int conn_num){
		this.conn_num = conn_num;
	}
	
	public int getConnNumber(){
		return conn_num;
	}
	
	//Sends given string to connection
	public void send(String input){
		output.println(input);
		output.flush();
	}
	
	public void run(){
		try{
			//Signals that connection started to TCPServer
			event_bus.SignalStart();
			while(clientStatus){
				//Wait a input from user
				String recived_msg = input.readLine();
				if(recived_msg != null){
					//Signals that a input readed from user
					event_bus.SignalRead(recived_msg);
				} else{
					clientStatus = false;
				}
			}
		} catch(Exception e){
			System.out.println("Connection Problem");
		} finally{
			try{
				input.close();
				output.close();
				client.close();
			} catch(IOException e){
				System.out.println("Connection Temination Problem");
			}
		}
		//Signals that connection has been terminated
		event_bus.SignalDisconnect();
	}
	
	/* SLOTS */
	public void onRead(String input){};
	public void onStart(){};
	public void onDisconnect(){};
}
