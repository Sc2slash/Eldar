package eldar.game.client.core.spells;

import java.awt.image.BufferedImage;

import eldar.game.utilities.Timer;
import eldar.game.utilities.geometry.Rectangle.Rect2i;


public class Spell {
	int resourceRequired;
	int cooldown;
	Timer castTimer;
	
	BufferedImage image;
	
	//Position of icon in the image, and the image of the loot drawn on the ground
	Rect2i iconBox;
	String name;
	String description;
	public Spell(){
		castTimer = new Timer();
		castTimer.start();
	}
	public void castSpell(){
		
	}
}
