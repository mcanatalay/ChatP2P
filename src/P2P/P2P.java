package P2P;

import Client.Client;
import Client.ClientListener;
import Server.Server;
import Server.ServerListener;

public class P2P implements ClientListener, ServerListener {
    public Server p2pServer = null;
    public Client p2pClient = null;
    private P2PBus pevent_bus;
    
    public P2P(P2PListener pevent_responder){
        this.pevent_bus = new P2PBus(); //Creates bus
        this.pevent_bus.addListener(pevent_responder); //Add parent as responder
    }
    
    public void initializeServer(){
        p2pServer = new Server(1111,this);
        p2pServer.start();
    }
        
    public void initializeClient(String ipaddress){
        p2pClient = new Client(ipaddress,1111,this);
    }
    
    public void send(String input){
        if(p2pClient != null){
            p2pClient.send(input);
        } else{
            p2pServer.sendEveryOne(input);
            pevent_bus.SignalNewMessage(input);
        }
    }
    
    public void onNewMessageClient(String input){
        p2pServer.sendEveryOne(input);
        pevent_bus.SignalNewMessage(input);
    }
    
    public void onNewMessageServer(String input){
        if(p2pClient != null){
            p2pClient.send(input);
        } else{
            p2pServer.sendEveryOne(input);
            pevent_bus.SignalNewMessage(input);
        }
    }
    
}
