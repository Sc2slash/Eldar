package eldar.game.server.net.packets;

import eldar.game.client.net.ClientServer;
import eldar.game.server.net.GameServer;

public class Packet000Login extends Packet {

	private String username;
	//handle it
	private String password;
	
	public Packet000Login(byte[] data) {
		super(000);
		this.username = readData(data);
	}
	
	public Packet000Login(String username) {
		super(000);
		this.username = username;	
	}

	@Override
	public void writeData(ClientServer client) {
		client.sendData(getData());
	}

	@Override
	public void writeData(GameServer server) {
		server.sendDataToAllClients(getData());
	}
	
	public byte[] getData() {
		return ("000" + this.username).getBytes();
	}
	
	public String getUsername() {
		return username;
	}
	
}
