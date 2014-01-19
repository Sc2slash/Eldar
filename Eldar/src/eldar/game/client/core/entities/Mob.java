package eldar.game.client.core.entities;

import eldar.game.utilities.geometry.Rectangle.Rect2i;


public abstract class Mob extends Entity{

	public Mob(String serverID, String ID, Rect2i box, int startAnimation, 
			int maxHp, int curHP, int typeResource, int maxResource, int curResource) {
		super(serverID, ID, box, startAnimation);
		this.maxHP = maxHP;
		this.curHP = curHP;
		
	}
	public Mob(String serverID, String ID, String name, String description, Rect2i box, int startAnimation,
			int maxHp, int curHP, int typeResource, int maxResource, int curResource) {
		super(serverID, ID, name, description, box, startAnimation);
		// TODO Auto-generated constructor stub
	}
	int maxHP, curHP;
	int typeResource; // 0 none 1 mana 2 energy 3 fury
	int maxResource, curResource;
}
