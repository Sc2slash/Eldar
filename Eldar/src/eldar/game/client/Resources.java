package eldar.game.client;

import java.io.File;
import java.io.IOException;

import javax.media.MediaLocator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Resources {


	public static String resourcesPath = "resources/";
	
	public GameProperties gameProperties = new GameProperties("/data/.properties");
//	public static GameProperties defaultGameProperties = new GameProperties("/data/.defaultproperties");
	public MediaLocator testSong = new MediaLocator(Resources.class.getResource("/audio/test.mp3"));
	
	public Resources(){
//		loadResources();
	}
	public void loadResources(){
		File fXmlFile = new File(resourcesPath+"data/Resource.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = null;
		Document doc = null;
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			doc = dBuilder.parse(fXmlFile);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		
		doc.getDocumentElement().normalize();
	 
		if(!doc.getDocumentElement().getNodeName().equals("Resources"))return;
	 
		NodeList nList = doc.getElementsByTagName("Characters");
		for (int i = 0; i < nList.getLength(); i++) {
			Node nNode = nList.item(i);
			
			if(nNode.getNodeType() == Node.ELEMENT_NODE){
				Element element = (Element) nNode;
				
			}
		}
	}
}
