package eldar.game.client.core.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import eldar.game.client.Game;
import eldar.game.client.core.entities.Entity;
import eldar.game.utilities.geometry.Vector.Vec2f;

public class KeyInputHandler implements KeyListener{

	private Game game;
	
	private boolean keys[] = new boolean[256];
	
	public KeyInputHandler(Game game){
		this.game = game;
		game.window.addKeyListener(this);
	}
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}
	public void keyTyped(KeyEvent e) {		
	}
	public boolean getKey(int keyCode){
		return keys[keyCode];
	}
	
}
