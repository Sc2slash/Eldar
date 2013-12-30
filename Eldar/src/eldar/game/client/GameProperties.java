package eldar.game.client;

import java.awt.Dimension;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class GameProperties {
	
	public static final Dimension[] resolutions = {new Dimension(960,720), new Dimension(640,480), new Dimension(480,360), new Dimension( 360, 270), new Dimension(288,216) };
	public static final Dimension[] windowSizes = {new Dimension(1920, 1080), new Dimension(1360,768), new Dimension(1280, 1024), new Dimension(1280, 800), new Dimension(1024, 768) };
	

	
	private String propertiesPath;
	//Graphics
	public int windowSize;
	public int resolution;
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
		this.propertiesPath = propertiesPath;
		loadProperties();
	}

	public void loadProperties(){
		Properties prop = new Properties();
		try {
			prop.load(GameProperties.class.getResourceAsStream(propertiesPath));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//Graphics
		windowSize = Integer.parseInt(prop.getProperty("windowSize"));
		resolution = Integer.parseInt(prop.getProperty("resolution"));
		fullscreen = Boolean.parseBoolean(prop.getProperty("fullscreen"));
		maxFps = Integer.parseInt(prop.getProperty("maxFps"));
		//Audio
		masterVolume = Integer.parseInt(prop.getProperty("masterVolume"));
		musicVolume = Integer.parseInt(prop.getProperty("musicVolume"));
		effectsVolume = Integer.parseInt(prop.getProperty("effectsVolume"));
		voicesVolume = Integer.parseInt(prop.getProperty("voicesVolume"));
		disableSound = Boolean.parseBoolean(prop.getProperty("disableSound"));
		//Gameplay
		languageFilter = Boolean.getBoolean(prop.getProperty("languageFilter"));
		includeTips = Boolean.getBoolean(prop.getProperty("includeTips"));
		generalChat = Boolean.getBoolean(prop.getProperty("generalChat"));
	}
	
	public void writeProperties(){
		Properties prop = new Properties();
		//Graphics
		prop.setProperty("windowSize", Integer.toString(windowSize));
		prop.setProperty("resolution", Integer.toString(resolution));
		prop.setProperty("fullscreen", Boolean.toString(fullscreen));
		prop.setProperty("maxFps", Integer.toString(maxFps));
		//Audio
		prop.setProperty("masterVolume", Integer.toString(masterVolume));
		prop.setProperty("musicVolume", Integer.toString(musicVolume));
		prop.setProperty("effectsVolume", Integer.toString(effectsVolume));
		prop.setProperty("voicesVolume", Integer.toString(voicesVolume));
		prop.setProperty("disableSound", Boolean.toString(disableSound));
		//Gameplay
		prop.setProperty("languageFilter", Boolean.toString(languageFilter));
		prop.setProperty("includeTips", Boolean.toString(includeTips));
		prop.setProperty("generalChat", Boolean.toString(generalChat));
		try {
			prop.store(new FileOutputStream(Resources.resourcesPath+propertiesPath), null);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
