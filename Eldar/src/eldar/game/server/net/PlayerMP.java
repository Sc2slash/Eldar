package eldar.game.server.net;

import java.net.InetAddress;

import eldar.game.client.core.entities.Player;

public class PlayerMP extends Player {
	
	public InetAddress player_ip;
	public int port;
	
	private int num_failed = 0;
	
	public PlayerMP(/* Basic player initialization here */String username, InetAddress player_ip, int port) {
		//Make use of the Player class extension here
		super(username);
		//
		this.player_ip = player_ip;
		this.port = port;
	}
	
	
}
