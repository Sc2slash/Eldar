package eldar.game.client.core.entities;

import java.awt.Color;
import java.awt.Graphics;

import eldar.game.client.Game;
import eldar.game.client.core.items.Armor;
import eldar.game.client.core.items.Weapon;
import eldar.game.utilities.Utilities;
import eldar.game.utilities.geometry.Rectangle.Rect2i;
import eldar.game.utilities.geometry.Vector.Vec2i;

public class Player extends Mob{

	
	public Player(String serverID, String ID, Rect2i box, int startAnimation,
			int maxHp, int curHP, int typeResource, int maxResource,
			int curResource) {
		super(serverID, ID, box, startAnimation, maxHp, curHP, typeResource,
				maxResource, curResource);
		//Test
		itemHelmet = new Armor("E0", null);
		itemChest = new Armor("E1", null);
		itemLeggins = new Armor("E2", null);
	}

	public Player(String serverID, String ID, String name, String description,
			Rect2i box, int startAnimation, int maxHp, int curHP,
			int typeResource, int maxResource, int curResource) {
		super(serverID, ID, name, description, box, startAnimation, maxHp, curHP,
				typeResource, maxResource, curResource);
	}

	boolean pvpActive;

	private Armor itemHelmet, itemChest, itemLeggins, itemGloves, itemRightWeapon, itemLeftWeapon;
	private Weapon leftHand, rightHand;
	
	public void render(Graphics g, float scale){
		Vec2i topLeft = new Vec2i((int)((box.x*scale-Game.cameraLocation.x*scale)),(int)((box.y*scale-Game.cameraLocation.y*scale)));
		Vec2i botRight = new Vec2i((int)((box.x*scale-Game.cameraLocation.x*scale)+box.w*scale),(int)((box.y*scale-Game.cameraLocation.y*scale)+box.h*scale));
			
		if(topLeft.x > Game.window.getWidth() || topLeft.y > Game.window.getHeight() || botRight.x < 0 || botRight.y < 0) return;
		g.drawImage(image, topLeft.x, topLeft.y, botRight.x, botRight.y, 
				animations[curAnimation].x, animations[curAnimation].y, animations[curAnimation].x+animations[curAnimation].w, animations[curAnimation].y+animations[curAnimation].h, null);
		if(itemHelmet != null && itemHelmet.getNumAnimations() > curAnimation){
			itemHelmet.draw(g, topLeft.x, topLeft.y, (botRight.x-topLeft.x), (botRight.y-topLeft.y), curAnimation);
		}
		if(itemChest != null && itemChest.getNumAnimations() > curAnimation){
			itemChest.draw(g, topLeft.x, topLeft.y, (botRight.x-topLeft.x), (botRight.y-topLeft.y), curAnimation);
		}
		if(itemLeggins != null && itemLeggins.getNumAnimations() > curAnimation){
			itemLeggins.draw(g, topLeft.x, topLeft.y, (botRight.x-topLeft.x), (botRight.y-topLeft.y), curAnimation);
		}
		if(Game.debugMode){
			g.setColor(Color.red);
			g.drawRect(topLeft.x, topLeft.y, (int)(box.w*scale), (int)(box.h*scale));
		}
	}
}
