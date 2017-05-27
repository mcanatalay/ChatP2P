package Chat;

import P2P.P2P;
import P2P.P2PListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Chat implements P2PListener {
    private P2P p2p;
    private String name = "default";
    
    public Chat(){
        p2p = new P2P(this);
        p2p.initializeServer();
        try{
            BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Enter a name: ");
            String n = bufferRead.readLine();
            name = n;
            System.out.println("Choose (wait/connect): ");
            String s = bufferRead.readLine();
            if(s.equalsIgnoreCase("wait")){
                System.out.println("Waiting for connection...");
            } else if(s.equalsIgnoreCase("connect")){
                System.out.println("Local Address: ");
                String a = bufferRead.readLine();
                p2p.initializeClient(a);
            }
            while(true){
                String input = bufferRead.readLine();
                p2p.send(name + ": " + input);
            }
        } catch(IOException e){}
    }
    
    public void onNewMessageP2P(String input){
        System.out.println(input);
    }
    
}
