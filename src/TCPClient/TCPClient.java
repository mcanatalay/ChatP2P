/**
 * TCP Client that connects to a given adress/port
 */

package TCPClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

public class TCPClient implements TCPClientListener {
	//variables
	private Socket client; //holds main socket
	protected TCPClientBus event_bus; //holds the bus class
	protected String adress;
	protected int port;
	protected boolean clientStatus; //holds the connection status
	protected BufferedReader input; //holds the "input" stream
	protected PrintWriter output; //holds the "output" stream
	protected TCPClientReader reader; //holds the listener of inputs in thread
	
	//consturctor
	public TCPClient(String adress, int port){
		this.adress = adress;
		this.port = port;
		try {
			this.client = new Socket(adress,port); //open connection
			//create input stream
			input = new BufferedReader(new InputStreamReader(client.getInputStream()));
			//create output stream
			output = new PrintWriter(new OutputStreamWriter(client.getOutputStream()));
		} catch (IOException e) {
			onNotConnected();
		}
		this.event_bus = new TCPClientBus();
		//register the class to bus
		this.event_bus.addListener(this);
		//create reader
		this.reader = new TCPClientReader(this.input, this);
		this.reader.start();
	}
	
	//basic send class that send given string to server
	public void send(String input){
		output.println(input);
		output.flush();
	}
	
	public void onDisconnectParent(){
		try{
			input.close();
			output.close();
			client.close();
		} catch(IOException e){
			System.out.println("Connection Termination Problem");
		}
	}
	
	//SLOTS that will overwrited
	public void onConnect(){}
	public void onRead(String input){}
	public void onNotConnected(){
		System.exit(-1);
	}
	public void onDisconnect(){
		onDisconnectParent();
	}
}
