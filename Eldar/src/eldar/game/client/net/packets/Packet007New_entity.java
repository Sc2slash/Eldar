package eldar.game.client.net.packets;

import eldar.game.client.core.entities.Entity;

public class Packet007New_entity extends Packet{
	Entity entity;
	public Packet007New_entity(Entity entity){
		super(007);
		this.entity = entity;
	}
	public Entity getEntity(byte[] data){
		return null;
	}
	public byte[] getData() {
		return (intToString(7) + sep + intToString(entity.serverID) + sep + intToString(entity.type) + sep + intToString((int) entity.location.x)+ sep + intToString((int) entity.location.y)+end).getBytes();  
	}
}
