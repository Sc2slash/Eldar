package eldar.game.client.net.packets;

import eldar.game.client.Game;
import eldar.game.client.core.entities.Entity;
import eldar.game.utilities.geometry.Rectangle.Rect2i;

public class Packet007New_entity extends Packet{
	Entity entity;
	public Packet007New_entity(Entity entity){
		super(7);
		this.entity = entity;
	}
	public Entity getEntity(byte[] data){
		return null;
	}
	public byte[] getData() {
		return null;
		}
	
}
