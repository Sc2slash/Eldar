package eldar.game.client.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import eldar.game.client.Game;
import eldar.game.client.core.entities.Entity;
import eldar.game.client.net.packets.Packet;
import eldar.game.client.net.packets.Packet.PacketTypes;
import eldar.game.client.net.packets.Packet001Login_confirm;
import eldar.game.client.net.packets.Packet003Ping;
import eldar.game.client.net.packets.Packet004Connect;
import eldar.game.client.net.packets.Packet005Connection_succeeded;
import eldar.game.client.net.packets.Packet006Check_connection;
import eldar.game.utilities.Timer;
import eldar.game.utilities.geometry.Vector.Vec2f;

public class ClientServer extends Thread {
	
	public final int PACKET_SIZE = 1024;
	public final int SERVER_PORT = 8124;
	
	public int connection_id = -1;
	
	public long start_ping_time;
	public boolean ping_succeeded;
	
	private Game game;
	private InetAddress server_ip;
	private DatagramSocket socket;
	
	public boolean thread_is_running = false;
	
	public ClientServer(Game game, String server_ip) {
		this.game = game;
		try {
			this.socket = new DatagramSocket();
			this.server_ip = InetAddress.getByName(server_ip);
		} catch (SocketException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		thread_is_running = true;
		while (thread_is_running) {
			byte[] data = new byte[PACKET_SIZE];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			try {
				System.out.println("Trying to receive packet");
				socket.receive(packet);
				System.out.println("Received packet");
			} catch (IOException e) {
				e.printStackTrace();
			}
			if (!thread_is_running) break;
			parsePacket(packet.getData());
		}
 	}
	
	public void parsePacket(byte[] data) {
		PacketTypes type = Packet.getPrefix(data);
		switch (type) {
		default:
		case INVALID:
			break;
		case LOGIN:
			break;
		case LOGIN_CONFIRM:
		{
			Packet001Login_confirm packet = new Packet001Login_confirm(data);
			System.out.println("LOGIN_CONFIRM - " + packet.get_is_valid());
			game.launcher.valid_login = packet.get_is_valid();
			break;
		}
		case DISCONNECT:
			break;
		case PING:
			ping_succeeded = true;
			break;
		case CHECK_CONNECTION:
		{
			Packet006Check_connection response = new Packet006Check_connection(connection_id);
			sendData(response.getData());
			break;
		}
		case CONNECTION_SUCCEEDED: 
		{
			Packet005Connection_succeeded response = new Packet005Connection_succeeded(data);
			System.out.println("IdentifierID = " + response.getIdentifierID());
			this.connection_id = response.getIdentifierID();
		}
			break;
		case NEW_ENTITY:
			addEntity(data);
			break;
//		case UPDATE_ENTITY:
//			break;
//		case REMOVE_ENTITY:
//			break;
		}
	}
	
	public void addEntity(byte[] data) {
		String[] args = Packet.readData(data);
		Game.curLvl.addEntity(new Entity(Integer.parseInt(args[0]),args[1], Integer.parseInt(args[2]), new Vec2f(Float.parseFloat(args[3]), Float.parseFloat(args[4])), Integer.parseInt(args[5])));
	}

	public void sendData(byte[] data) {
		DatagramPacket packet = new DatagramPacket(data, data.length, server_ip, SERVER_PORT);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void kill() {
		thread_is_running = false;
		this.interrupt();
	}
 	
	//Pings the server, and returns the ping time in milliseconds
	//If unable to ping the server, returns -1
	public int pingMS() {
		int PING_TIMEOUT_S = 2;
		ping_succeeded = false;
		Packet003Ping ping = new Packet003Ping();
		Timer t = new Timer();
		t.start();
		sendData(ping.getData());
		while (!ping_succeeded && t.getTimeS() < PING_TIMEOUT_S);
		if (t.getTimeS() > PING_TIMEOUT_S) return -1;
		return (int) (t.getTimeS() * 1000);
	}
}
