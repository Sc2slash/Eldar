package eldar.game.core.graphics;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import eldar.game.utilities.GameException;

public class Spritesheet {
	protected String path;
	protected Dimension size;
	public int pixels[];
	
	public Spritesheet(String path){
		BufferedImage image = null;
		try {
			image = ImageIO.read(Spritesheet.class.getResourceAsStream(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(image == null) throw new GameException("Failed to load spritesheet: " + path);
		
		this.path = path;
		size = new Dimension(image.getWidth(), image.getHeight());
		pixels = image.getRGB(0, 0, size.width, size.height, null, 0, size.width);
	}
	public Dimension getSize(){
		return size;
	}
	public int getWidth(){
		return size.width;
	}
	public int getHeight(){
		return size.height;
	}
	public String getPath(){
		return path;
	}
}
