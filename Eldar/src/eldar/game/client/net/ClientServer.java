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

public class ClientServer extends Thread {
	
	public int PACKET_SIZE = 1024;
	public int SERVER_PORT = 8124;
	
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
			Packet001Login_confirm packet = new Packet001Login_confirm(data);
			if (!packet.isValid()) {
				//ask for resend
			}
			game.launcher.valid_login = packet.get_is_valid();
			break;
		case DISCONNECT:
			break;
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
}
