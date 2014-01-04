package eldar.game.client.net.packets;

public abstract class Packet {
	
	public static enum PacketTypes {
		INVALID(-1), LOGIN(000), LOGIN_CONFIRM(001), DISCONNECT(002);
		
		private int packetID;
		private PacketTypes(int packetID) {
			this.packetID = packetID;
		}
		
		public int getID() {
			return packetID;
		}
	}
	
	public byte packetID;
	
	public Packet(int packetID) {
		this.packetID = (byte) packetID;
	}
	
	public abstract byte[] getData();
	
	public String[] readData(byte[] data) {
		String message = new String(data).trim().substring(3);
		String message_parts[] = message.split("&");
		return message_parts;
	}
	
	public static PacketTypes lookupPacket(String id) {
		try {
			return lookupPacket(Integer.parseInt(id));
		} catch (NumberFormatException e) {
			return PacketTypes.INVALID;
		}
	}
	
	public static PacketTypes lookupPacket(int id) {
		for (PacketTypes p : PacketTypes.values()) {
			if (p.getID() == id) {
				return p;
			}
		}
		return PacketTypes.INVALID;
	}
	
}
