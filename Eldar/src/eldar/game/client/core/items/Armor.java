package eldar.game.client.core.items;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import eldar.game.utilities.GameException;
import eldar.game.utilities.geometry.Rectangle.Rect2i;

public class Armor extends Item{

	Stats stats;
	
	BufferedImage image;
	Rect2i[] animations;
	
	public Armor(String id, BufferedImage iconImage, Rect2i iconBox,
			String name, String description, BufferedImage image, Rect2i[] animations) {
		super(id, iconImage, iconBox, name, description);
		this.image = image;
		this.animations = animations;
	}
	public Armor(String id, Stats stats){
		super(id);
		if(items.get(id) instanceof Armor){
		this.image = ((Armor)items.get(id)).image;
		this.animations = ((Armor)items.get(id)).animations;
		this.stats = stats;
		}
		else
			throw new GameException("Selected item isn't armor");
	}

	public void draw(Graphics g, int x, int y, int w, int h, int animation){
		g.drawImage(image, x, y, x+w, y+h, animations[animation].x, animations[animation].y, animations[animation].x+animations[animation].w, animations[animation].y+animations[animation].h, null);
	}
	public int getNumAnimations(){
		return animations.length;
	}
}
