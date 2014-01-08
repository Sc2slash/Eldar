package eldar.game.client.core.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.MouseMotionListener;

import eldar.game.client.Game;
import eldar.game.utilities.geometry.Vector.Vec2f;

public class MouseInputHandler implements MouseListener, MouseMotionListener{

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
	public void mouseDragged(MouseEvent e) {
		
	}
	public void mouseMoved(MouseEvent e) {
		Game.gameInterface.hoveredEntity = Game.curLvl.getEntity(new Vec2f(e.getPoint().x+Game.cameraLocation.x, e.getPoint().y+Game.cameraLocation.y));
	}
}
