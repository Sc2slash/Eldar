package eldar.game.client.core.entities;

import java.awt.Color;
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
	
	public static enum EntityTypes{
		PLAYER(0), NPC(1), TRADER(2), HOSTILE(3);
		int typeID;
		private EntityTypes(int type){
			this.typeID = type;
		}
		public int getTypeID(){
			return typeID;
		}
	}
	
	private static HashMap<String, Entity> entities = new HashMap<String, Entity>();
	
	public Vec2f location;
	public int serverID;
	public int type;
	
	public String name;
	public Rect2i animations[];
	
	private BufferedImage image;

	int curAnimation;
	
	public Entity(String ID, BufferedImage image, Rect2i[] animations, String name){
		this.image = image;
		this.animations= animations;
		this.name = name;
		if(entities.containsKey(ID)) 
			throw new GameException("ID already being used:"+ID);
		entities.put(ID, this);
	}
	public Entity(int serverID, String ID, int type, Vec2f location, int startAnimation){
		if(!entities.containsKey(ID)) throw new GameException("Entity not yet created. ID:"+ID);
		this.image = entities.get(ID).image;
		this.animations = entities.get(ID).animations;
		this.name = entities.get(ID).name;
		this.serverID = serverID;
		this.location = location;
		curAnimation = startAnimation;
	}
	public Entity(int serverID, String ID, int type, String name, Vec2f location, int startAnimation){
		if(!entities.containsKey(ID)) throw new GameException("Entity not yet created. ID:"+ID);
		this.image = entities.get(ID).image;
		this.animations = entities.get(ID).animations;
		this.name = name;
		this.serverID = serverID;
		this.location = location;
		curAnimation = startAnimation;
	}
	
	public void render(Graphics g, float scale){
		Vec2i topLeft = new Vec2i((int)((location.x*scale-Game.cameraLocation.x)),(int)((location.y*scale-Game.cameraLocation.y)));
		Vec2i botLeft = new Vec2i((int)((location.x*scale-Game.cameraLocation.x)+animations[curAnimation].w*scale),(int)((location.y*scale-Game.cameraLocation.y)+animations[curAnimation].h*scale));
		
		if(topLeft.x > Game.window.getWidth() || topLeft.y > Game.window.getHeight() || botLeft.x < 0 || botLeft.y < 0) return;
		g.drawImage(image, topLeft.x, topLeft.y, botLeft.x, botLeft.y, 
				animations[curAnimation].x, animations[curAnimation].y, animations[curAnimation].x+animations[curAnimation].w, animations[curAnimation].y+animations[curAnimation].h, null);
		if(Game.debugMode){
			g.setColor(Color.red);
			g.drawRect(topLeft.x, topLeft.y, (int)(animations[curAnimation].w*scale), (int)(animations[curAnimation].h*scale));
		}
	}
	public void updatePosition(int x, int y){
		location.x = x;
		location.y = y;
	}
}
