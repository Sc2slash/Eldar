package eldar.game.client.core.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import eldar.game.client.Game;
import eldar.game.utilities.GameException;
import eldar.game.utilities.geometry.Rectangle.Rect2f;
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
	
	public Rect2i box;
	public String serverID;
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
	public Entity(String serverID, String ID, int type, Rect2i box, int startAnimation){
		if(!entities.containsKey(ID)) throw new GameException("Entity not yet created. ID:"+ID);
		this.image = entities.get(ID).image;
		this.animations = entities.get(ID).animations;
		this.name = entities.get(ID).name;
		this.serverID = serverID;
		this.box = box;
		curAnimation = startAnimation;
	}
	public Entity(String serverID, String ID, int type, String name, Rect2i box, int startAnimation){
		if(!entities.containsKey(ID)) throw new GameException("Entity not yet created. ID:"+ID);
		this.image = entities.get(ID).image;
		this.animations = entities.get(ID).animations;
		this.name = name;
		this.serverID = serverID;
		this.box = box;
		curAnimation = startAnimation;
	}
	
	public void render(Graphics g, float scale){
		Vec2i topLeft = new Vec2i((int)((box.x*scale-Game.cameraLocation.x*scale)),(int)((box.y*scale-Game.cameraLocation.y*scale)));
		Vec2i botLeft = new Vec2i((int)((box.x*scale-Game.cameraLocation.x*scale)+box.w*scale),(int)((box.y*scale-Game.cameraLocation.y*scale)+box.h*scale));
		
		if(topLeft.x > Game.window.getWidth() || topLeft.y > Game.window.getHeight() || botLeft.x < 0 || botLeft.y < 0) return;
		g.drawImage(image, topLeft.x, topLeft.y, botLeft.x, botLeft.y, 
				animations[curAnimation].x, animations[curAnimation].y, animations[curAnimation].x+animations[curAnimation].w, animations[curAnimation].y+animations[curAnimation].h, null);
		if(Game.debugMode){
			g.setColor(Color.red);
			g.drawRect(topLeft.x, topLeft.y, (int)(box.w*scale), (int)(box.h*scale));
		}
	}
	public void updatePosition(int x, int y){
		box.x = x;
		box.y = y;
	}
	public void updateAnimation(int curAnimation){
		if(curAnimation >= 0)
			this.curAnimation = curAnimation;
	}
	public void updateType(int type){
		if(type >= 0)
			this.type = type;
	}
	public void update(Rect2i box, int type, int curAnimation, String name){
		this.box = box;
		if(type >= 0)
			this.type = type;
		if(curAnimation >= 0)
			this.curAnimation = curAnimation;
		if(name != null)
			this.name = name;
	}
	public Rect2i getBox(){
		return box;
	}
	public int getType(){
		return type;
	}
	public String getServerID(){
		return serverID;
	}
	
}
