package eldar.game.client.core.entities;

import eldar.game.utilities.geometry.Rectangle.Rect2i;

public class Hostile extends Mob{
	
	public Hostile(String serverID, String ID, Rect2i box, int startAnimation,
			int maxHp, int curHP, int typeResource, int maxResource,
			int curResource) {
		super(serverID, ID, box, startAnimation, maxHp, curHP, typeResource,
				maxResource, curResource);
		
	}
	public Hostile(String serverID, String ID, String name, String description,
			Rect2i box, int startAnimation, int maxHp, int curHP,
			int typeResource, int maxResource, int curResource) {
		super(serverID, ID, name, description, box, startAnimation, maxHp, curHP,
				typeResource, maxResource, curResource);

	}
	boolean aggroing;
	public void interact(){
		attack();
	}
	public void attack(){
		System.out.println("Attacking..");
	}
}
