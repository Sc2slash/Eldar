package eldar.game.client.core.levels;

import java.awt.Graphics;
import java.util.Scanner;

import eldar.game.client.Game;
import eldar.game.client.core.levels.tiles.Tileset;
import eldar.game.utilities.GameException;

public class Level {
	
	Game game;
	
	private int[] terrain;
	private Tileset tileset;
	private int width, height;
	private int tileWidth, tileHeight;
	private float scale;
	
	public Level(Game game, String path){
		this.game = game;
		loadLevel(path);
	}
	public void loadLevel(String path){
		Scanner sc = null;
		sc = new Scanner(Level.class.getResourceAsStream(path));
		String header = sc.next();
		if(!header.equals("tm")) throw new GameException("Invalid map file " + path);
//		sc.nextLine();
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
	}
	public void drawLevel(Graphics g){
		for(int y = (int) (Game.cameraLocation.y/tileHeight), yLoc = (int) -(Game.cameraLocation.y%tileHeight); y < height && yLoc < game.window.getHeight(); y++, yLoc += tileHeight){
			for(int x = (int) (Game.cameraLocation.x/tileWidth), xLoc = (int) -(Game.cameraLocation.x%tileWidth); x < width && xLoc < game.window.getWidth(); x++, xLoc += tileWidth){
				if(x < 0 || y < 0) continue;
				if(terrain[x+y*width] != -1)
					tileset.renderTile(xLoc, yLoc, g, scale, terrain[x+y*width]);
			}
		}
	}
	public void setScale(float i){
		tileWidth = (int) (tileset.getTileWidth()*i);
		tileHeight = (int) (tileset.getTileHeiht()*i);
		scale = i;
	}
}
