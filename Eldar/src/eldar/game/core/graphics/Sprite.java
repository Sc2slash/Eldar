package eldar.game.core.graphics;

import java.awt.Dimension;

public class Sprite {
	
	public static Sprite spriteTest = new Sprite();
	
	private Dimension size;
	private Spritesheet spritesheet;
	private int pixels[];
	
	public Sprite(){
		size = new Dimension(0,0);
	}
	public int getWidth(){
//		return size.width;
		return 900;
	}
	public int getHeight(){
//		return size.height;
		return 900;
	}
}
