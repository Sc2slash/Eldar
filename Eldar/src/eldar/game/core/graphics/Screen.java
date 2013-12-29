package eldar.game.core.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.io.IOException;

import javax.imageio.ImageIO;

import eldar.game.core.levels.tiles.Tile;

public class Screen {
	private final int CLEAR_COLOR = 0x00ffff;
	
	private Window window;
	private Dimension size;
	private BufferedImage image;
//	Pixels are the one where we draw which are then copied to pixelData which is the pixels of the image.
//	Gotta try to see if aditional step is necessary or if can drop directly on pixelData
	private int pixels[];
	
	int j = 0;
	public Screen(Window window){
		this.window = window;
		this.size = window.getCanvasSize();
		this.image = new BufferedImage(size.width,size.height,BufferedImage.TYPE_INT_RGB);
		this.pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		clear();
	}
	public void clear(){
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = CLEAR_COLOR;
		}
	}
	public void render(int x, int y, Sprite sprite){
		//Add draw method for sprite
		for(int yl = 0, yp = y; yl < sprite.getHeight() && yp < size.height; yl++, yp++){
			if(yp < 0) continue;
			for(int xl = 0, xp = x; xl < sprite.getWidth() && xp < size.width; xl++, xp++){
				if(xp < 0) continue;
//				all it does for now is just draw yellow where image is supposed to be
				pixels[xp+yp*size.width] = sprite.pixels[xl+yl*sprite.getWidth()];
			}
		}
	}
	public void drawText(String text, int x, int y, Font font){
		Graphics2D g = (Graphics2D) image.getGraphics();
		g.setFont(font);
		g.drawString(text, x, y);
		g.dispose();
	}
	public void draw(Graphics g){
		clear();
		//Simple tile rendering test. need to change it later on to the level class
		for(int y = 0; y < size.height; y+=32){
			for(int x = 0; x < size.width; x+=32){
				Tile.testTile.render(this, x,y);
			}
		}
		g.drawImage(image, 0, 0, window.getWidth(), window.getHeight(), null);
	}
	
}
