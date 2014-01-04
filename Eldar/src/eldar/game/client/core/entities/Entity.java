package eldar.game.client.core.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import eldar.game.client.Game;
import eldar.game.client.GameProperties;
import eldar.game.utilities.GameException;
import eldar.game.utilities.geometry.Rectangle.Rect2i;
import eldar.game.utilities.geometry.Vector.Vec2f;
import eldar.game.utilities.geometry.Vector.Vec2i;

public class Entity {
	
	private static HashMap<String, Entity> entities = new HashMap<String, Entity>();
	
	protected Vec2f location;
		
	BufferedImage image;
	int curAnimation;
	protected Rect2i animations[];

	
	public Entity(String ID, BufferedImage image, Rect2i[] animations){
		this.image = image;
		this.animations= animations;
		if(entities.containsKey(ID)) 
			throw new GameException("ID already being used:"+ID);
		entities.put(ID, this);
	}
	public Entity(String ID, Vec2f location, int startAnimation){
		if(!entities.containsKey(ID)) throw new GameException("Entity not yet created. ID:"+ID);
		this.image = entities.get(ID).image;
		this.animations = entities.get(ID).animations;
		this.location = location;
		curAnimation = startAnimation;
	}
	
	public void render(Graphics g){
		float scale = GameProperties.resolutionScales[Game.gameProperties.resolution];
		Vec2i topLeft = new Vec2i((int)((location.x*scale-Game.cameraLocation.x)),(int)((location.y*scale-Game.cameraLocation.y)));
		Vec2i botLeft = new Vec2i((int)((location.x*scale-Game.cameraLocation.x)+animations[curAnimation].w*scale),(int)((location.y*scale-Game.cameraLocation.y)+animations[curAnimation].h*scale));
		
		if(topLeft.x > Game.window.getWidth() || topLeft.y > Game.window.getHeight() || botLeft.x < 0 || botLeft.y < 0) return;
		g.drawImage(image, topLeft.x, topLeft.y, botLeft.x, botLeft.y, 
				animations[curAnimation].x, animations[curAnimation].y, animations[curAnimation].x+animations[curAnimation].w, animations[curAnimation].y+animations[curAnimation].h, null);
	}
	public void updatePosition(int x, int y){
		location.x = x;
		location.y = y;
	}
}
