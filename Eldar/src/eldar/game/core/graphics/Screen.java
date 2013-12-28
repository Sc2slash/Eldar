package eldar.game.core.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

public class Screen {
	private final int CLEAR_COLOR = 0x00ffff;
	
	private Window window;
	private Dimension size;
	private BufferedImage image;
//	Pixels are the one where we draw which are then copied to pixelData which is the pixels of the image.
//	Gotta try to see if aditional step is necessary or if can drop directly on pixelData
	private int pixels[];
	private int pixelData[];
	
	int j = 0;
	public Screen(Window window){
		this.window = window;
		this.size = window.getCanvasSize();
		this.image = new BufferedImage(size.width,size.height,BufferedImage.TYPE_INT_RGB);
		this.pixelData = ((DataBufferInt)image.getRaster().getDataBuffer()).getData();
		this.pixels = new int[size.width*size.height];
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
	public void draw(Graphics g){
		clear();
		//Simple tile rendering test. need to change it later on to the level class
//		render(0,0, Sprite.spriteTest);
		for(int y = 0; y < size.height; y+=32){
			for(int x = 0; x < size.width; x+=32){
				render(x,y, Sprite.spriteTest);
			}
		}
		for(int i = 0; i<pixels.length;i++){
			pixelData[i] = pixels[i];
		}
		g.drawImage(image, 0, 0, window.getWidth(), window.getHeight(), null);
	}
	
}
