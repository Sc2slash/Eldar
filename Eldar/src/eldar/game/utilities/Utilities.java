package eldar.game.utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Utilities {
	public static BufferedImage loadImage(String path){
		BufferedImage image = null;
		try {
			image = ImageIO.read(Utilities.class.getResource(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		if(image == null) throw new ResourceException("Failed to load resource " + path);
		return image;
	}

}
