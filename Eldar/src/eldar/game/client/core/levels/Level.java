package eldar.game.client.core.levels;

import java.awt.Graphics;
import java.util.HashMap;
import java.util.Scanner;

import eldar.game.client.Game;
import eldar.game.client.core.entities.Banker;
import eldar.game.client.core.entities.Entity;
import eldar.game.client.core.entities.Player;
import eldar.game.client.core.entities.Trader;
import eldar.game.client.core.levels.tiles.Tileset;
import eldar.game.client.net.packets.Packet;
import eldar.game.utilities.GameException;
import eldar.game.utilities.Utilities;
import eldar.game.utilities.geometry.Rectangle.Rect2i;
import eldar.game.utilities.geometry.Vector.Vec2f;

public class Level {
	
	Game game;
	
	private int ID;
	
	private String name;
	
	private int[] terrain;
	private Tileset tileset;
	private int width, height;
	private int tileWidth, tileHeight;
	private float scale;
	
	private HashMap<String,Entity> entities = new HashMap<String, Entity>();
	
	public Level(Game game, String path){
		this.game = game;
		loadLevel(path);
	}
	public void loadLevel(String path){
		Scanner sc = new Scanner(Level.class.getResourceAsStream(path));
		String header = sc.next();
		if(!header.equals("tm")) throw new GameException("Invalid map file " + path);
		width = sc.nextInt();
		height= sc.nextInt();
		terrain = new int[width*height];
		String tilesetPath = sc.next();
		tileWidth = sc.nextInt();
		tileHeight = sc.nextInt();
		int spacing = sc.nextInt();
		tileset = new Tileset(tilesetPath,tileWidth, tileHeight, spacing);
		for(int i = 0; i < terrain.length; i++){
			terrain[i] = sc.nextInt()-1;
		}
		entities.put("0", new Banker("0","C0", new Rect2i(0, 0, 24, 32),6, 2, 2, 2, 2, 2));
		entities.put("1", new Entity("1","O0", new Rect2i(30, 0, 24, 32),2));
		entities.put("2", new Trader("2","C0", new Rect2i(60, 0, 24, 32),1, 2, 2, 2, 2, 2));
		entities.put("3", new Player("3","C0", new Rect2i(90, 0, 24, 32),6, 2, 2, 2, 2, 2));
	}
	public void drawLevel(Graphics g){
		for(int y = (int) ((Game.cameraLocation.y*scale)/tileHeight), yLoc = (int) -((Game.cameraLocation.y*scale)%tileHeight); y < height && yLoc < game.window.getHeight(); y++, yLoc += tileHeight){
			for(int x = (int) ((Game.cameraLocation.x*scale)/tileWidth), xLoc = (int) -((Game.cameraLocation.x*scale)%tileWidth); x < width && xLoc < game.window.getWidth(); x++, xLoc += tileWidth){
				if(x < 0 || y < 0) continue;
				if(terrain[x+y*width] != -1)
					tileset.renderTile(xLoc, yLoc, g, terrain[x+y*width]);
			}
		}
		for(Entity entity : entities.values()){
			entity.render(g, scale);
		}
	}
	public void setScale(float s){
		tileWidth = (int) (tileset.getTileWidth()*s);
		tileHeight = (int) (tileset.getTileHeiht()*s);
		scale = s;
		tileset.setScale(s);
	}
	public void addEntity(Entity entity){
		entities.put(entity.getServerID(), entity);
	}
	public void removeEntity(String serverID){
		entities.remove(serverID);
	}	
	public Entity getEntity(Vec2f position){
		for(Entity entity : entities.values()){
			if(Utilities.checkCollision(entity.getBox(), position))
				return entity;
		}
		return null;
	}
	public void addEntity(byte[] data) {
		String[] args = Packet.readData(data);
		//This can vary based on the number of arguments the entity constructor takes in
		int numArguments = 8;
		int numEntities = args.length/numArguments;
		for(int i = 0; i < numEntities; i++){
			Game.curLvl.addEntity(new Entity(Integer.toString(Packet.parseToInt(args[0+i*numArguments])),Integer.toString(Packet.parseToInt(args[1+i*numArguments])),  new Rect2i(Packet.parseToInt(args[2+i*numArguments]), Packet.parseToInt(args[3+i*numArguments]), Packet.parseToInt(args[4+i*numArguments]), Packet.parseToInt(args[5+i*numArguments])), Packet.parseToInt(args[6+i*numArguments])));
		}
	}
	public void updateEntity(byte[] data){
		String[] args = Packet.readData(data);
		int numArguments = 4;
		int numEntities = args.length/numArguments;
		for(int i = 0; i < numEntities; i++){
			Entity entity = entities.get(Integer.toString(Packet.parseToInt(args[0+i*numArguments])));
			entity.updatePosition(Packet.parseToInt(args[1+i*numArguments]), Packet.parseToInt(args[2+i*numArguments]));
			entity.updateAnimation(Packet.parseToInt(args[3+i*numArguments]));
		}
	}
}
