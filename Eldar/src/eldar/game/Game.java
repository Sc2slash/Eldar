package eldar.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import eldar.game.core.graphics.Screen;
import eldar.game.core.graphics.Window;
import eldar.game.launcher.Launcher;

public class Game implements Runnable{

	public Window window;
	public Screen screen;
	public Launcher launcher;
	private Thread thread;
	
	private boolean running = false;
	
	public Game(){
		launcher = new Launcher(this);
		launcher.start();
	}
	public synchronized void start(){	
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
	public void launch(){
		running = true;
		window = new Window(640,480,2,false,"Eldar",null);
		screen = new Screen(window);
		window.launchWindow();
		start();
	}
	public static void main(String[] args) {
		Game game = new Game();
	}
	
}
