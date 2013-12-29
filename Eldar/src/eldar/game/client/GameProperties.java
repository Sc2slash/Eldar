package eldar.game.client;

import java.awt.Dimension;

public class GameProperties {
	//Graphics
	public Dimension windowSize;
	public Dimension resolution;
	public boolean fullscreen;
	public int maxFps;
	//Audio
	public int masterVolume, musicVolume, effectsVolume, voicesVolume;
	public boolean disableSound;
	//Gameplay
	public boolean languageFilter;
	public boolean includeTips;
	public boolean generalChat;
	
	public GameProperties(String propertiesPath){
		
	}
}
