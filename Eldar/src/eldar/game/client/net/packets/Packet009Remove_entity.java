package eldar.game.client.net.packets;

import eldar.game.client.core.entities.Entity;

public class Packet009Remove_entity extends Packet{

	Entity entity;
	
	public Packet009Remove_entity(Entity entity) {
		super(9);
		this.entity = entity;
	}

	public byte[] getData() {
		return(intToString(9)+sep+intToString(Integer.parseInt(entity.serverID))+end).getBytes();
	}
	
}
