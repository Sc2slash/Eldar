package eldar.game.client.core.gameinterface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import eldar.game.utilities.geometry.Rectangle.Rect2i;

public class Icon {
	HashMap<String, Icon> icons = new HashMap<String, Icon>();
	
	BufferedImage image;
	Rect2i box;
	String name, description;
	
	public void draw(Graphics g, int x, int y){
		
	}
}
