package TCPServer;

public interface ServerThreadListener {
	void onRead(String input);
	void onStart();
	void onDisconnect();
}
