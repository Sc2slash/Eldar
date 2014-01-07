package eldar.game.client.net.packets;

public class Packet004Connect extends Packet {
	
	public Packet004Connect() {
		super(4);
	}
	
	public byte[] getData() {
		return (intToString(4) + end).getBytes();
	}
	
}
