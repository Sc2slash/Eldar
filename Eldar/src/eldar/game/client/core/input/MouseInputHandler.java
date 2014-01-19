package eldar.game.client.core.input;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import eldar.game.client.Game;
import eldar.game.client.GameProperties;
import eldar.game.client.core.entities.Banker;
import eldar.game.client.core.entities.Entity;
import eldar.game.client.core.entities.NPC;
import eldar.game.client.core.entities.Trader;
import eldar.game.utilities.geometry.Vector.Vec2f;
import eldar.game.utilities.geometry.Vector.Vec2i;

public class MouseInputHandler implements MouseListener, MouseMotionListener{
	
	public MouseInputHandler(){
		Game.window.addMouseListener(this);
		Game.window.addMouseMotionListener(this);
	}
	public void mouseClicked(MouseEvent e) {
		//first check interface interaction then game stuff
		Entity entity = Game.gameInterface.hoveredEntity;
		if(entity != null){
			entity.interact();
		}
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
		float scale = GameProperties.resolutionScales[Game.gameProperties.resolution];
		Game.gameInterface.mousePosition = new Vec2i(e.getPoint().x, e.getPoint().y);
		Game.gameInterface.hoveredEntity = Game.curLvl.getEntity(new Vec2f(e.getPoint().x/scale+Game.cameraLocation.x, e.getPoint().y/scale+Game.cameraLocation.y));
	}
}
