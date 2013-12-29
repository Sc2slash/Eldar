package eldar.game.core.levels.tiles;

import java.awt.Dimension;

import eldar.game.core.graphics.Spritesheet;

public class Tileset extends Spritesheet{

	public Dimension tileSize;
	public int numTiles;
	public Tileset(String path, int tileWidth, int tileHeight) {
		super(path);
		numTiles = (size.width/tileWidth)*(size.height/tileHeight);
		tileSize = new Dimension(tileWidth, tileHeight);
	}
}
