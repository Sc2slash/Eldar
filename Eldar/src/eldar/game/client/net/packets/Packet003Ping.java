package eldar.game.client.net.packets;

public class Packet003Ping extends Packet {
	
	public Packet003Ping() {
		super(3);
	}

	public byte[] getData() {
		return (intToString(3) + end).getBytes();
	}
}
