package eldar.game.client.core.levels.tiles;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import eldar.game.client.Game;
import eldar.game.client.GameProperties;
import eldar.game.utilities.Utilities;
import eldar.game.utilities.geometry.Rectangle.Rect2i;

public class Tileset {
	
	private BufferedImage tileset;
	private int tileWidth;
	private int tileHeight;
	private int numTiles;
	
	private int tileDrawWidth;
	private int tileDrawHeight;
	
	private Rect2i[] tileBox;
	
	public Tileset(String path,  int tileWidth, int tileHeight, int spacing){
		this.tileset = Utilities.loadImage(path);
		this.tileHeight = tileHeight;
		this.tileWidth = tileWidth;
		this.numTiles = (tileset.getWidth()/tileWidth)*(tileset.getHeight()/tileHeight);
		tileBox = new Rect2i[numTiles];
		int numTilesX = tileset.getWidth()/tileWidth;
		for(int i = 0; i < numTiles; i++){
			tileBox[i] = new Rect2i((i%numTilesX*tileWidth+spacing*i),(i/numTilesX*tileHeight+spacing*i),(i%numTilesX*tileWidth+spacing*i)+tileWidth,(i/numTilesX*tileHeight+spacing*i)+tileHeight);
		}
		setScale(GameProperties.resolutionScales[Game.gameProperties.resolution]);
	}
	public void renderTile(int x, int y, Graphics g, float scale, int tileID){
		g.drawImage(tileset, x, y, (int)(x+tileWidth*scale), (int)(y+tileHeight*scale), tileBox[tileID].x, tileBox[tileID].y, tileBox[tileID].w, tileBox[tileID].h, null);
	}
	public int getTileWidth(){
		return tileWidth;
	}
	public int getTileHeiht(){
		return tileHeight;
	}
	public void setScale(float s){
		tileDrawWidth = (int) (tileWidth*s);
		tileDrawHeight = (int) (tileHeight*s);
	}
}
