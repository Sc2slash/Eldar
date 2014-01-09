package eldar.game.client.net.packets;

import eldar.game.client.core.entities.Entity;

public class Packet008Update_entity extends Packet{

	Entity entity;
	
	public Packet008Update_entity(int packetID) {
		super(8);
	}

	public byte[] getData() {
		return (intToString(6)+sep+intToString(Integer.parseInt(entity.serverID))+sep+intToString(entity.getBox().x)+sep+intToString(entity.getBox().y)).getBytes();
	}
	
}
