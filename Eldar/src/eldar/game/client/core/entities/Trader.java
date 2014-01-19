package eldar.game.client.core.entities;

import java.util.List;

import eldar.game.client.core.items.Item;
import eldar.game.utilities.geometry.Rectangle.Rect2i;

public class Trader extends NPC{
	List <Item> items;
	
	public Trader(String serverID, String ID, Rect2i box, int startAnimation,
			int maxHp, int curHP, int typeResource, int maxResource,
			int curResource) {
		super(serverID, ID, box, startAnimation, maxHp, curHP, typeResource,
				maxResource, curResource);
		// TODO Auto-generated constructor stub
	}
	public Trader(String serverID, String ID, String name, String description, Rect2i box,
			int startAnimation, int maxHp, int curHP, int typeResource,
			int maxResource, int curResource) {
		super(serverID, ID, name, description, box, startAnimation, maxHp, curHP, typeResource,
				maxResource, curResource);
		// TODO Auto-generated constructor stub
	}
	public void interact(){
		trade();
	}
	
	public void getItems(){
	}
	public void trade(){
		System.out.println("Trading..");
	}
}
