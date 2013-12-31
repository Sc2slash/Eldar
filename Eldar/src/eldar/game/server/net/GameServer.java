package eldar.game.server.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import eldar.game.client.Game;
import eldar.game.server.net.packets.Packet;
import eldar.game.server.net.packets.Packet.PacketTypes;
import eldar.game.server.net.packets.Packet000Login;

public class GameServer extends Thread {

	public int PACKET_SIZE = 1024;
	public int SERVER_PORT = 1337;
	
	private Game game;
	private DatagramSocket socket;
	
	private List<PlayerMP> connectedPlayers = new ArrayList<PlayerMP>();
	
	public GameServer(Game game) {
		this.game = game;
		try {
			this.socket = new DatagramSocket(SERVER_PORT);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	public void run() {
		while (true) {
			byte[] data = new byte[PACKET_SIZE];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			parsePacket(packet.getData(), packet.getAddress(), packet.getPort());
		}
 	}
	
	public void sendData(byte[] data, InetAddress client_ip, int client_port) {
		DatagramPacket packet = new DatagramPacket(data, data.length, client_ip, client_port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void parsePacket(byte[] data, InetAddress client_ip, int client_port) {
		String message = (new String(data)).trim();
		PacketTypes type = Packet.lookupPacket(message.substring(0, 3));
		switch (type) {
		default:
		case INVALID:
			break;
		case LOGIN:
			Packet000Login packet = new Packet000Login(data);
			System.out.println("["+ client_ip.getHostAddress()+":"+client_port+"] "+packet.getUsername()+" has connected");
			PlayerMP player = new PlayerMP(packet.getUsername(), client_ip, client_port);
			if (player != null) {
				this.connectedPlayers.add(player);
			}
			break;
		case DISCONNECT:
			break;
		}
	}
	
	public void sendDataToAllClients(byte[] data) {
		for (PlayerMP p : connectedPlayers) {
			sendData(data, p.player_ip, p.port);
		}
	}
	
	public boolean clientIsConnected(String username, InetAddress client_ip, int port) {
		for (PlayerMP p : connectedPlayers) {
			if (p.username.equals(username) && p.player_ip == client_ip && p.port == port) {
				return true;
			}
		}
		return false;
	}
	
}
