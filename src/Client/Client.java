/**
 * Main class that holds all others as instances
 */

package Client;

import P2P.P2P;
import TCPClient.*;

public class Client extends TCPClient {
    private ClientBus cevent_bus; //its own bus that signals to P2P class
    
    public Client(String ipadress, int port, P2P cevent_responder) {
            super(ipadress, port);
            this.cevent_bus = new ClientBus(); //Creates bus
            this.cevent_bus.addListener(cevent_responder); //Add parent as responder
    }

    //@override
    public void send(String input){
            output.println(input);
            output.flush();
    }

    public void onNotConnected(){
            System.out.println("Not Connected Server is off!");
            System.exit(1);
    }

    public void onDisconnect(){
            System.out.println("Connection Lost Disconnected from server!");
            onDisconnectParent();
            System.exit(1);
    }
	
	
    public void onRead(String input){
        cevent_bus.SignalNewMessage(input);
    }

}
