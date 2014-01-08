package eldar.game.client;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.media.MediaLocator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import eldar.game.client.core.entities.Entity;
import eldar.game.utilities.Utilities;
import eldar.game.utilities.geometry.Rectangle.Rect2i;

public class Resources {


	public static String resourcesPath = "resources/";
	
	public GameProperties gameProperties = new GameProperties("/data/.properties");
//	public static GameProperties defaultGameProperties = new GameProperties("/data/.defaultproperties");
	public MediaLocator testSong = new MediaLocator(Resources.class.getResource("/audio/test.mp3"));
	
	
	public Resources(){
//		loadResources();
		new Entity("0", Utilities.loadImage("/graphics/Image.png"), new Rect2i[]{new Rect2i(0,0,24,32), new Rect2i(24,0,24,32)},"NakedHuman");
	}
	public void loadResources(){
	}
}
