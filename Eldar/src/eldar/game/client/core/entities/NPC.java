package eldar.game.client.core.entities;

import eldar.game.utilities.geometry.Rectangle.Rect2i;

public class NPC extends Mob{
	public NPC(String serverID, String ID, Rect2i box, int startAnimation,
			int maxHp, int curHP, int typeResource, int maxResource,
			int curResource) {
		super(serverID, ID, box, startAnimation, maxHp, curHP, typeResource,
				maxResource, curResource);
		// TODO Auto-generated constructor stub
	}
	public NPC(String serverID, String ID, String name, String description, Rect2i box,
			int startAnimation, int maxHp, int curHP, int typeResource,
			int maxResource, int curResource) {
		super(serverID, ID, name, description, box, startAnimation, maxHp, curHP, typeResource,
				maxResource, curResource);
		// TODO Auto-generated constructor stub
	}
	public void interact(){
		talkTo();
	}
	public void talkTo(){
		System.out.println("talking to " + name);
	}
}
