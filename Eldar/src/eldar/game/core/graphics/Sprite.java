package eldar.game.core.graphics;

import java.awt.Dimension;

public class Sprite {
	
	public static Sprite spriteTest = new Sprite(new Spritesheet("/graphics/dirt.jpg"));
	
	protected Dimension size;
	protected Spritesheet spritesheet;
	public int pixels[];
	
	public Sprite(){
		size = new Dimension(0,0);
	}
	public Sprite(Spritesheet spritesheet){
		size = new Dimension(spritesheet.getWidth(), spritesheet.getHeight());
		pixels = new int[size.width*size.height];
		for(int i = 0; i < pixels.length; i++){
			pixels[i] = spritesheet.pixels[i];
		}
	}
	public Sprite(Spritesheet spritesheet, int x, int y, int w, int h){
		size = new Dimension(w,h);
		this.spritesheet = spritesheet;
		pixels = new int[w*h];
		for(int yp = 0; yp < h; yp++){
			for(int xp = 0; xp < w; xp++){
				pixels[xp+yp*w] = spritesheet.pixels[(x+xp)+(y+yp)*spritesheet.getWidth()];
			}
		}
	}
	
	public int getWidth(){
		return size.width;
	}
	public int getHeight(){
		return size.height;
	}
}
