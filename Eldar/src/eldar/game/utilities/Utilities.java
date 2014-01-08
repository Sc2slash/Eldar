package eldar.game.utilities;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import eldar.game.utilities.geometry.Rectangle.Rect2i;
import eldar.game.utilities.geometry.Vector.Vec2f;

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
	public static boolean checkCollision(Rect2i loc1, Vec2f loc2){
		if(loc1.x < loc2.x && (loc1.x+loc1.w)>loc2.x && loc1.y < loc2.y && (loc1.y+loc1.h)>loc2.y)
			return true;
		return false;
	}
}
