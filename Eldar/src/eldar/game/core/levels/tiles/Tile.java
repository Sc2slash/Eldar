package eldar.game.core.levels.tiles;

import eldar.game.Resources;
import eldar.game.core.graphics.Screen;
import eldar.game.core.graphics.Sprite;
import eldar.game.utilities.GameException;

public class Tile {
	
	
	
	private int id;
	private Sprite sprite;
	
	private static Tileset tileset = Resources.tileset;
	public static Tile[] tiles = new Tile[tileset.numTiles];
	
	public static Tile testTile = new Tile(0);
	
	public Tile(int id){
		this.id = id;
		if(tiles[id] != null) throw new GameException("Tile already added, id: " + id);
		sprite = new Sprite(tileset, id%tileset.getWidth()*tileset.tileSize.width, id/tileset.getWidth()*tileset.tileSize.height, tileset.tileSize.width,tileset.tileSize.height);
	}
	public boolean isSolid(){
		return false;
	}
	public void interact(){
		
	}
	public void render(Screen screen, int x, int y){
		screen.render(x, y, sprite);
	}
}
