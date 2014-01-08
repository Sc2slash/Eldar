package eldar.game.client;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferStrategy;

import javax.swing.JTextField;

import eldar.game.client.core.gameinterface.Interface;
import eldar.game.client.core.graphics.Window;
import eldar.game.client.core.input.KeyInputHandler;
import eldar.game.client.core.levels.Level;
import eldar.game.client.launcher.Launcher;
import eldar.game.client.net.ClientServer;
import eldar.game.client.net.packets.Packet004Connect;
import eldar.game.utilities.Timer;
import eldar.game.utilities.geometry.Vector.Vec2f;

public class Game implements Runnable{
	
	public static String SERVER_ADDRESS = new String("25.155.82.122");

	public static boolean debugMode = true;
	public static Window window;
	public static Launcher launcher;
	public static Level curLvl;
	public static KeyInputHandler keyInputHandler;
	public static GameProperties gameProperties;
	public static Resources gameResources;
	public static Interface gameInterface;
	
	public static ClientServer client_server;
	public boolean client_server_is_running;
	
	private boolean running = false;

	private Thread thread;
	
	public static Vec2f cameraLocation;
	
	public Game(){
		gameResources = new Resources();
		gameProperties = gameResources.gameProperties;
		this.connectToServer();
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
		g.dispose();
		bs.show();
	}
	public void update(){
	}
	public void handleInput(){
		//Temporary just to test map
		if(keyInputHandler.getKey(KeyEvent.VK_UP)){
			cameraLocation.y -= 1;
		}
		if(keyInputHandler.getKey(KeyEvent.VK_DOWN)){
			cameraLocation.y += 1;
		}
		if(keyInputHandler.getKey(KeyEvent.VK_LEFT)){
			cameraLocation.x -= 1;
		}
		if(keyInputHandler.getKey(KeyEvent.VK_RIGHT)){
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
		keyInputHandler = new KeyInputHandler(this);
		window.launchWindow();
		curLvl = new Level(this, "/levels/test.txt");
		curLvl.setScale(GameProperties.resolutionScales[gameProperties.resolution]);
		cameraLocation = new Vec2f(0,0);
		JTextField numberEnter = new JTextField("Enter numbers here", 20); 
		window.getFrame().add(numberEnter);
		start();
	}
	
	public void connectToServer() {
		client_server = new ClientServer(this, SERVER_ADDRESS);
		client_server_is_running = true;
		client_server.start();
		Timer t = new Timer();
		t.start();
		Packet004Connect connect = new Packet004Connect();
		client_server.sendData(connect.getData());
		while (t.getTimeS() < 2 && client_server.connection_id == -1);
		System.out.println(t.getTimeS());
		
		if (client_server.connection_id == -1) {
			client_server_is_running = false;
			client_server.kill();
		}
	}
	
	public boolean client_server_is_running() {
		return (this.client_server_is_running);
	}
	
	public static void main(String[] args) {
		Game game = new Game();	
	}
	
}
