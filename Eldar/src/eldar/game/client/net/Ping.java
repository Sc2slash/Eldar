package eldar.game.client.net;

import eldar.game.client.Game;
import eldar.game.client.net.packets.Packet003Ping;
import eldar.game.utilities.Timer;

public class Ping extends Thread {

	Game game = null;
	
	public Ping(Game game) {
		this.game = game;
	}
	
	//Pings the server, and returns the ping time in milliseconds
	//If unable to ping the server, returns -1
	public int pingMS() {
		int PING_TIMEOUT_S = 2;
		game.client_server.ping_succeeded = false;
		Packet003Ping ping = new Packet003Ping();
		Timer t = new Timer();
		t.start();
		game.client_server.sendData(ping.getData());
		while (!game.client_server.ping_succeeded && t.getTimeS() < PING_TIMEOUT_S);
		if (!game.client_server.ping_succeeded) return -1;
		return (int) (t.getTimeS() * 1000);
	}
}
