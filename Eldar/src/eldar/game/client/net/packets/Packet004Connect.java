package eldar.game.client.net.packets;

public class Packet004Connect extends Packet {
	
	public Packet004Connect() {
		super(004);
	}
	
	public byte[] getData() {
		char end = (char)255;
		return ("004" + end).getBytes();
	}
	
}
