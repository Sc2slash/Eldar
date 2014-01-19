package eldar.game.client.core.gameinterface;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.ArrayList;
import java.util.List;

import eldar.game.client.Game;
import eldar.game.utilities.geometry.Rectangle.Rect2i;

public class TextBox {
	String text;
	Rect2i box;
	int height;
	Font font;
	Color color;
	
	List <String> lines = new ArrayList<String>();
	
	public TextBox(String text, int x, int y){
		this.text = text;
	}
	public TextBox(String text, Rect2i box, Font font, Color color){
		this.text = text;
		this.box = box;
		this.font = font;
		this.color = color;
		int i = 0;
		BufferStrategy bs = Game.window.getBufferStrategy();
		Graphics graphics = bs.getDrawGraphics();
		FontMetrics m = graphics.getFontMetrics(font);
		String[] words = text.split(" ");
		String curString = "";
		if(lines.size() == 0 && m.stringWidth(curString+words[i]) > box.w){
			curString="..."; 	 
		}
		else{
			int wordsLine = 0;
			while(i < words.length){
				if(m.stringWidth(curString+words[i]) > box.w){
					if(m.stringWidth(words[i])>box.w){
						curString+="...";
						break;
					}				
					lines.add(curString);
					curString = "";
					wordsLine = 0;
					continue;
				}
				if(wordsLine != 0){
					curString += " ";
				}
				curString += words[i];
				wordsLine++;
				i++;
			}
		}
		lines.add(curString);
		height = font.getSize();
	}
	public void draw(Graphics g){
		g.setFont(font);
		g.setColor(color);
		for(int i = 0; i < lines.size(); i++){
			g.drawString(lines.get(i), box.x, (box.y+i*height)+height);
		}
	}
}
