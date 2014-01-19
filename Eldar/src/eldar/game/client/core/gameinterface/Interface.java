package eldar.game.client.core.gameinterface;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import eldar.game.client.Game;
import eldar.game.client.core.entities.Entity;
import eldar.game.utilities.geometry.Rectangle.Rect2i;
import eldar.game.utilities.geometry.Vector.Vec2i;

public class Interface {
	public Entity hoveredEntity;
	public Vec2i mousePosition;
	
	private Font defaultFont;
	List<Cursor> cursors = new ArrayList<Cursor>();
	
	private TextBox b;
	
	public Interface(){
		mousePosition = new Vec2i(Game.window.getFrame().getX(),Game.window.getFrame().getY());
		defaultFont = new Font("Times New Roman", Font.PLAIN, 25);
		}
	public void drawInterface(Graphics g){
		if(hoveredEntity != null){
			b = new TextBox("This is an entity!", new Rect2i(mousePosition.x,mousePosition.y,150,300), defaultFont, new Color(255,255,255));
			g.fillRect(mousePosition.x, mousePosition.y, 150, 300);
			b.draw(g);
		}
	}
}
