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
	public static void addEntity(byte[] data) {
		String[] args = Packet.readData(data);
		Game.curLvl.addEntity(new Entity(Integer.toString(Packet.parseToInt(args[0])),Integer.toString(Packet.parseToInt(args[1])), Packet.parseToInt(args[2]), new Rect2i(Packet.parseToInt(args[3]), Packet.parseToInt(args[4]), Packet.parseToInt(args[5]), Packet.parseToInt(args[6])), Packet.parseToInt(args[7])));
	}
}
