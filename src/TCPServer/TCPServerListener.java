package TCPServer;

import java.net.Socket;

public interface TCPServerListener {
	void onAccept(Socket input);
	void onDisconnect(int conn_num);
}
