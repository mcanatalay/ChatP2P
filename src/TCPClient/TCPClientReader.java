/**
 * This thread class waits until it takes a response from server
 * it signals that response to parent class TCPClient
 * TCPClient onRead catch this
 */

package TCPClient;

import java.io.BufferedReader;

public class TCPClientReader extends Thread {
	protected TCPClientBus event_bus;
	protected BufferedReader input;
	protected boolean clientStatus;
	
	public TCPClientReader(BufferedReader input,TCPClient event_responder){
		super();
		this.input = input;
		this.event_bus = new TCPClientBus();
		this.event_bus.addListener(event_responder);
	}
	
	public void run(){
		try{
			event_bus.SignalConnect();
			this.clientStatus = true;
			while(clientStatus){
				String recived_msg = input.readLine();
				if(recived_msg != null){
					event_bus.SignalRead(recived_msg);
				} else{
					clientStatus = false;
				}
			}
		} catch(Exception e){
			System.out.println("Connection Problem");
		}
		event_bus.SignalDisconnect();
	}
}
