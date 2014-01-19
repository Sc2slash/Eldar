package eldar.game.client.core.items;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import eldar.game.client.Game;
import eldar.game.utilities.GameException;
import eldar.game.utilities.geometry.Rectangle.Rect2i;

public class Item {
	protected static HashMap<String, Item> items = new HashMap<String, Item>();
	//List created to facilitate search of an item by name
	protected static HashMap<String, String> nameItemsList = new HashMap<String, String>();
	
	BufferedImage iconImage;
	
	//Position of icon in the image, and the image of the loot drawn on the ground
	String id;
	Rect2i iconBox;
	String name;
	String description;
	
	int quantity;
	int buyValue, sellValue;
	
	public Item(String id, BufferedImage iconImage, Rect2i iconBox, String name, String description){
		this.id = id;
		this.iconImage = iconImage;
		this.iconBox = iconBox;
		this.name = name;
		this.description = description;
		items.put(id, this);
		
	}
	public Item(String id){
		this.id = id;
		if(!items.containsKey(id)) throw new GameException("Item doesn't exist ID:" + id);
		this.iconImage = items.get(id).iconImage;
		this.iconBox = items.get(id).iconBox;
		this.name = items.get(id).name;
		this.description = items.get(id).description;
	}
	
	public void drawIcon(Graphics g, int x, int y){
		g.drawImage(iconImage, x, y, x+Game.gameProperties.iconSize, y+Game.gameProperties.iconSize, iconBox.x, iconBox.y, iconBox.x+iconBox.w,iconBox.y+iconBox.h, null); 
	}
	public String getName(){
		return name;
	}
	public String getDescription(){
		return description;
	}
}
