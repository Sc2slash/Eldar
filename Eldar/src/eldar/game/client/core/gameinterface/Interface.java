package eldar.game.client.core.gameinterface;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import eldar.game.client.core.entities.Entity;
import eldar.game.utilities.geometry.Vector.Vec2i;

public class Interface {
	public Entity hoveredEntity;
	public Vec2i mousePosition;
	
	public BufferedImage defaultCursor;
	
	public void drawInterface(Graphics g){
		if(defaultCursor == null){
			try {
				defaultCursor = ImageIO.read(Interface.class.getResourceAsStream("/graphics/cursors/default.png"));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		g.drawImage(defaultCursor, mousePosition.x,  mousePosition.y, null);
	}
	
}
