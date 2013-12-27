package eldar.game.core;

import eldar.game.core.graphics.Window;

public class Game {
	public static Window window;
	
	public Game(){
		window = new Window(600,450,2,false,"Eldar",null);
		window.launchWindow();
	}
	
	public static void main(String[] args) {
		Game game = new Game();
	}

}
