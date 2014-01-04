package eldar.game.client;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JTextField;

import eldar.game.client.core.graphics.TextArea;
import eldar.game.client.core.graphics.Window;
import eldar.game.client.core.input.InputHandler;
import eldar.game.client.core.levels.Level;
import eldar.game.client.launcher.Launcher;
import eldar.game.client.net.ClientServer;
import eldar.game.utilities.geometry.Vector.Vec2f;

public class Game implements Runnable{
	
	public String SERVER_ADDRESS = new String("25.155.82.122");

	public static Window window;
	public static Launcher launcher;
	public static Level curLvl;
	public static ClientServer client;
	public static InputHandler inputHandler;
	public static GameProperties gameProperties;
	public static Resources gameResources;
	private boolean running = false;
	private TextArea textArea;
	private Thread thread;
	
	public static Vec2f cameraLocation;
	
	public Game(){
		gameResources = new Resources();
		gameProperties = gameResources.gameProperties;
		client = new ClientServer(this, SERVER_ADDRESS);
		client.start();
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
		g.fillRect(0,0,window.getWidth(), window.getHeight());
		curLvl.drawLevel(g);
		textArea.drawTextArea(g);
		g.dispose();
		bs.show();
	}
	public void update(){
	}
	public void handleInput(){
		//Temporary just to test map
		if(inputHandler.getKey(KeyEvent.VK_UP)){
			cameraLocation.y -= 1;
		}
		if(inputHandler.getKey(KeyEvent.VK_DOWN)){
			cameraLocation.y += 1;
		}
		if(inputHandler.getKey(KeyEvent.VK_LEFT)){
			cameraLocation.x -= 1;
		}
		if(inputHandler.getKey(KeyEvent.VK_RIGHT)){
			cameraLocation.x += 1;
		}
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
		window = new Window(GameProperties.windowSizes[gameProperties.windowSize].width,
				GameProperties.windowSizes[gameProperties.windowSize].height,false, gameProperties.fullscreen,"Eldar",null);
		inputHandler = new InputHandler(this);
		window.launchWindow();
		curLvl = new Level(this, "/levels/test.txt");
		curLvl.setScale(GameProperties.resolutionScales[gameProperties.resolution]);
		textArea = new TextArea();
		cameraLocation = new Vec2f(0,0);
		JTextField numberEnter = new JTextField("Enter numbers here", 20); 
		window.getFrame().add(numberEnter);
		start();
	}
	public static void main(String[] args) {
		Game game = new Game();	
	}
	
}
