package eldar.game.client.net.packets;

public class Packet003Ping extends Packet {
	
	public Packet003Ping() {
		super(003);
	}

	public byte[] getData() {
		char end = (char)255;
		return ("003" + end).getBytes();
	}
}