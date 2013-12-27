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
	private int pixels[];
	private int pixelData[];
	
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
	public void render(){
		//Add draw method for sprite
	}
	public void draw(Graphics g){
		for(int i = 0; i<pixels.length;i++){
			pixelData[i] = pixels[i];
		}
		g.drawImage(image, 0, 0, window.getWidth(), window.getHeight(), null);
	}
	
}
