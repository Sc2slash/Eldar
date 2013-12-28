package eldar.game.core.levels.tiles;

import eldar.game.core.graphics.Screen;
import eldar.game.core.graphics.Sprite;
import eldar.game.core.graphics.Spritesheet;

public class Tile {
	
	
	private final Spritesheet TILESET = new Spritesheet("/graphics/dirt.jpg");
	private final int TILE_WIDTH  = 32;
	private final int TILE_HEIGHT = 32;
	
	private int id;
	private Sprite sprite;
	
	public Tile(int id){
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
