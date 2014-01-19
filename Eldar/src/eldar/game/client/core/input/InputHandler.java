package eldar.game.client.core.input;

import eldar.game.client.Game;

public class InputHandler {
	KeyInputHandler keyHandler;
	MouseInputHandler mouseHandler;
	
	public InputHandler(Game game){
		keyHandler = new KeyInputHandler();
		mouseHandler = new MouseInputHandler();
	}
	public void handleInput(){
		keyHandler.handleInput();
	}
}
