package eldar.game.client.net.packets;

public class Packet006Check_connection extends Packet {
	
	public int identifier_id = -1;
	
	public Packet006Check_connection(byte[] data) {
		super(6);
		String data_parts[] = readData(data);
		this.identifier_id = parseToInt(data_parts[0]);
	}

	public Packet006Check_connection(int identifier_id) {
		super(6);
		this.identifier_id = identifier_id;
	}

	public byte[] getData() {
		char end = (char)255;
		return (intToString(6) + sep + intToString(this.identifier_id) + end).getBytes();
	}
	
	public int getIdentifierID() {
		return identifier_id;
	}
}

