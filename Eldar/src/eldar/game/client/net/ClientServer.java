package eldar.game.client.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import eldar.game.client.Game;

public class ClientServer extends Thread {
	
	public int PACKET_SIZE = 1024;
	public int SERVER_PORT = 1337;
	
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
//			if (packet.getData().length > 0) {
//				//handle it
//			}
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
