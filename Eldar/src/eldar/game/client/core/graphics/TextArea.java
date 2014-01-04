package eldar.game.client.core.graphics;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import eldar.game.utilities.geometry.Rectangle.Rect2i;

public class TextArea{
	Font font;
	Rect2i bounds;
	
	int charWidth, charHeight;
	
	String[] messages;
	public TextArea(){
		font = new Font("Andalus", Font.PLAIN, 30);
		bounds = new Rect2i(50,700,450,250);
		FontMetrics m = new FontMetrics(font){};
		charHeight = m.getHeight();
//		Game.window.getFrame().getContentPane().add(this);
	}
	public void drawTextArea(Graphics g){
		g.setFont(font);
		g.setColor(Color.black);
		g.drawString("Hello world", 30, 30);
		g.setColor(new Color(1.0f, 1.0f, 1.0f, 0.9f));
		g.fillRect(bounds.x, bounds.y, bounds.w, bounds.h);
	}
}
