package eldar.game;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import eldar.game.core.graphics.Screen;
import eldar.game.core.graphics.Window;

public class Game implements Runnable{

	public Window window;
	public Screen screen;
	
	private Thread thread;
	private boolean running = false;
	
	public Game(){
		window = new Window(600,450,2,false,"Eldar",null);
		screen = new Screen(window);
		window.launchWindow();
		start();
	}
	public synchronized void start(){	
		running = true;
		thread = new Thread(this, "Game");
		thread.start();
	}
	public synchronized void stop(){
		running = false;
		try{
	        thread.join();
	    } 
		catch(InterruptedException e){
	        e.printStackTrace();
	    }
	}
	public void render(){
		BufferStrategy bs = window.getBufferStrategy();
		if(bs == null){
			window.createBufferStrategy(3);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		g.fillRect(0, 0, window.getWidth(), window.getHeight());
		screen.draw(g);
		g.dispose();
		bs.show();
	}
	public void update(){
	}
	public void handleInput(){
	}

	public void run() {
		while(running){
			handleInput();
			render();
			update();
		}
	}
	
	public static void main(String[] args) {
		Game game = new Game();
	}

}
