package eldar.game.client.core.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import eldar.game.client.Game;

public class InputHandler implements KeyListener, MouseListener{

	private Game game;
	
	private boolean keys[] = new boolean[256];
	
	public InputHandler(Game game){
		this.game = game;
		game.window.addKeyListener(this);
		game.window.addMouseListener(this);
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

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}
	
	public void mouseExited(MouseEvent e) {
		
	}
	public void mousePressed(MouseEvent e) {
		
	}
	public void mouseReleased(MouseEvent e) {
		
	}
}
