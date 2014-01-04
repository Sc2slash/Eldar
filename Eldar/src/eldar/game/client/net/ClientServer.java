package eldar.game.client.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import eldar.game.client.Game;
import eldar.game.client.net.packets.Packet;
import eldar.game.client.net.packets.Packet.PacketTypes;
import eldar.game.client.net.packets.Packet001Login_confirm;
import eldar.game.client.net.packets.Packet003Ping;
import eldar.game.client.net.packets.Packet004Connect;
import eldar.game.client.net.packets.Packet005Connection_succeeded;
import eldar.game.client.net.packets.Packet006Check_connection;
import eldar.game.utilities.Timer;

public class ClientServer extends Thread {
	
	public int PACKET_SIZE = 1024;
	public int SERVER_PORT = 8124;
	
	public int connection_id;
	
	public long start_ping_time;
	public boolean ping_succeeded;
	
	private Game game;
	private InetAddress server_ip;
	private DatagramSocket socket;
	
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
		connect();
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
			System.out.println("SERVER > " + new String(packet.getData()));
			parsePacket(packet.getData());
		}
 	}
	
	public void parsePacket(byte[] data) {
		String message = (new String(data)).trim();
		PacketTypes type = Packet.lookupPacket(message.substring(0, 3));
		switch (type) {
		default:
		case INVALID:
			break;
		case LOGIN:
			break;
		case LOGIN_CONFIRM:
		{
			Packet001Login_confirm packet = new Packet001Login_confirm(data);
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
		}
		}
	}
	
	public void sendData(byte[] data) {
		DatagramPacket packet = new DatagramPacket(data, data.length, server_ip, SERVER_PORT);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//Connects to the server, receiving an unique identifier id
	public void connect() {
		Packet004Connect connect = new Packet004Connect();
		sendData(connect.getData());
		byte[] data = new byte[PACKET_SIZE];
		DatagramPacket packet = new DatagramPacket(data, data.length);
		try {
			socket.receive(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
		String message = (new String(data)).trim();
		PacketTypes type = Packet.lookupPacket(message.substring(0, 3));
		switch(type) {
		case CONNECTION_SUCCEEDED:
		{
			Packet005Connection_succeeded response = new Packet005Connection_succeeded(data);
			System.out.println("IdentifierID = " + response.getIdentifierID());
			connection_id = response.getIdentifierID();
		}
			break;
		default:
			connection_id = -1;
		}
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
