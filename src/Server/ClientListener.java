package Server;

public interface ClientListener {
	void onNewMessage(String message);
	void onDisconnect(int conn_num);
}
