package TCPClient;

public interface TCPClientListener {
	void onConnect();
	void onRead(String input);
	void onDisconnect();
}
