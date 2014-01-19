package eldar.game.client.core.gameinterface;

import com.sun.prism.Graphics;

import eldar.game.utilities.geometry.Rectangle.Rect2i;

public abstract class Button {
	//Location and size
	public Rect2i box;
	public void draw(Graphics g){
		g.fillRect(box.x, box.y, box.w, box.h);
	}
	public void click(){
		
	}
}
