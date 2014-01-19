package eldar.game.client.core.items;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import eldar.game.utilities.GameException;
import eldar.game.utilities.geometry.Rectangle.Rect2i;

public class Weapon extends Item{

	int maxDamage, minDamage;
	Stats stats;
	
	BufferedImage image;
	Rect2i[] animations;
	
	public Weapon(String id, BufferedImage iconImage, Rect2i iconBox,
			String name, String description, BufferedImage image, Rect2i[] animations) {
		super(id, iconImage, iconBox, name, description);
		this.image = image;
		this.animations = animations;
	}
	public Weapon(String id, Stats stats, int maxDamge, int minDamage){
		super(id);
		if(items.get(id) instanceof Weapon){
		this.image = ((Weapon)items.get(id)).image;
		this.animations = ((Weapon)items.get(id)).animations;
		this.stats = stats;
		}
		else
			throw new GameException("Selected item isn't a weapon");
	}
	public void draw(Graphics g, int x, int y, int w, int h, int animation){
		g.drawImage(image, x, y, x+w, y+h, animations[animation].x, animations[animation].y, animations[animation].x+animations[animation].w, animations[animation].y+animations[animation].h, null);
	}
	public int getNumAnimations(){
		return animations.length;
	}
}
