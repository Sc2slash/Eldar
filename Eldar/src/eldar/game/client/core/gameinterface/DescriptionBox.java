package eldar.game.client.core.gameinterface;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import eldar.game.utilities.geometry.Rectangle.Rect2i;

public class DescriptionBox {
	TextBox title, content;
	Rect2i box;
	BufferedImage backgroundImage;
	Rect2i imageLocation;
	public DescriptionBox(BufferedImage backgroundImage, Rect2i imageLocation, String title, Font titleFont, String content, Font contentFont, Rect2i box){
		this.box = box;
		this.backgroundImage = backgroundImage;
	}
	public void draw(Graphics g){
		g.drawImage(backgroundImage, box.x, box.y, box.x+box.w, box.y+box.h,
				imageLocation.x, imageLocation.y, imageLocation.x+imageLocation.w, imageLocation.y+imageLocation.h, null);
	}
}
