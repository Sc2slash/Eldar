package eldar.game.client;


import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.media.MediaLocator;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import eldar.game.client.core.entities.Entity;
import eldar.game.client.core.items.Armor;
import eldar.game.utilities.GameException;
import eldar.game.utilities.Utilities;
import eldar.game.utilities.geometry.Rectangle.Rect2i;

public class Resources {


	public static String resourcesPath = "resources/";
	
	public GameProperties gameProperties = new GameProperties("/data/.properties");
//	public static GameProperties defaultGameProperties = new GameProperties("/data/.defaultproperties");
	public MediaLocator testSong = new MediaLocator(Resources.class.getResource("/audio/test.mp3"));
	
	private HashMap<String, BufferedImage> graphics = new HashMap<String, BufferedImage>();
	
	public Resources(){
		loadResources();
	}
	public void loadResources(){
		try{
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			Document doc = dBuilder.parse(resourcesPath+"data/Resources.xml");
			doc.getDocumentElement().normalize();
			if(!doc.getDocumentElement().getNodeName().equals("Resources")) throw new GameException("Failed to load resource file!");
			Element docEle = doc.getDocumentElement();
			loadCharacters(docEle);
			loadObjects(docEle);
			loadItems(docEle);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	public void loadCharacters(Element docEle){
		NodeList charactersList = docEle.getElementsByTagName("Characters");
		Node charactersNode = charactersList.item(0);
		Element characterElement = (Element)charactersNode;
		String charactersPath = characterElement.getAttribute("FolderPath");
		String characterPrefix = characterElement.getAttribute("Prefix");
		NodeList nList = charactersNode.getChildNodes();
		for(int i = 0; i < nList.getLength(); i++){
			Node node = nList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element ele = (Element) node;
				String name = node.getNodeName();
				String id = ele.getAttribute("ID");
				String path = charactersPath+ele.getAttribute("Path");
				String description = ele.getAttribute("Description");
				if(!graphics.containsKey(path))
					graphics.put(path, Utilities.loadImage(path));
				BufferedImage image = graphics.get(path);
				NodeList animationsList = ele.getElementsByTagName("Animations");
				node = animationsList.item(0);
				NodeList nAnimationList = node.getChildNodes();
				List <Rect2i> animationBoxes = new ArrayList<Rect2i>();
				for(int a = 0; a<nAnimationList.getLength();a++){
					Node animationNode = nAnimationList.item(a);
					if(animationNode.getNodeType() == Node.ELEMENT_NODE){
						Element animation = (Element)animationNode;
						animationBoxes.add(new Rect2i(Integer.parseInt(animation.getAttribute("x")),Integer.parseInt(animation.getAttribute("y")),
								Integer.parseInt(animation.getAttribute("w")),Integer.parseInt(animation.getAttribute("h"))));
					}
				}
				Rect2i[] boxes = new Rect2i[animationBoxes.size()];
				for(int b = 0; b < animationBoxes.size(); b++){
					boxes[b] = animationBoxes.get(b);
				}
				new Entity(characterPrefix+id, image, boxes, name, description);
			}
		}
	}
	public void loadObjects(Element docEle){
		NodeList objectsList = docEle.getElementsByTagName("Objects");
		Node objectsNode = objectsList.item(0);
		Element objectElement = (Element)objectsNode;
		String objectsPath = objectElement.getAttribute("FolderPath");
		String objectPrefix = objectElement.getAttribute("Prefix");
		NodeList nList = objectsNode.getChildNodes();
		for(int i = 0; i < nList.getLength(); i++){
			Node node = nList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element ele = (Element) node;
				String name = node.getNodeName();
				String id = ele.getAttribute("ID");
				String path = objectsPath+ele.getAttribute("Path");
				String description = ele.getAttribute("Description");
				if(!graphics.containsKey(path))
					graphics.put(path, Utilities.loadImage(path));
				BufferedImage image = graphics.get(path);
				NodeList animationsList = ele.getElementsByTagName("Animations");
				node = animationsList.item(0);
				NodeList nAnimationList = node.getChildNodes();
				List <Rect2i> animationBoxes = new ArrayList<Rect2i>();
				for(int a = 0; a<nAnimationList.getLength();a++){
					Node animationNode = nAnimationList.item(a);
					if(animationNode.getNodeType() == Node.ELEMENT_NODE){
						Element animation = (Element)animationNode;
						animationBoxes.add(new Rect2i(Integer.parseInt(animation.getAttribute("x")),Integer.parseInt(animation.getAttribute("y")),
								Integer.parseInt(animation.getAttribute("w")),Integer.parseInt(animation.getAttribute("h"))));
					}
				}
				Rect2i[] boxes = new Rect2i[animationBoxes.size()];
				for(int b = 0; b < animationBoxes.size(); b++){
					boxes[b] = animationBoxes.get(b);
				}
				new Entity(objectPrefix+id, image, boxes, name, description);
			}
		}
	}
	public void loadItems(Element docEle){
		loadEquipment(docEle);
		loadWeapons(docEle);
		loadOthers(docEle);
	}
	public void loadEquipment(Element docEle){
		NodeList equipmentsList = docEle.getElementsByTagName("Equipment");
		Node equipmentsNode = equipmentsList.item(0);
		Element equipmentElement = (Element)equipmentsNode;
		String equipmentsPath = equipmentElement.getAttribute("FolderPath");
		String equipmentPrefix = equipmentElement.getAttribute("Prefix");
		NodeList nList = equipmentsNode.getChildNodes();
		for(int i = 0; i < nList.getLength(); i++){
			Node node = nList.item(i);
			if(node.getNodeType() == Node.ELEMENT_NODE){
				Element ele = (Element) node;
				String name = node.getNodeName();
				String id = ele.getAttribute("ID");
				String path = equipmentsPath+ele.getAttribute("Path");
				String description = ele.getAttribute("Description");
				if(!graphics.containsKey(path))
					graphics.put(path, Utilities.loadImage(path));
				BufferedImage image = graphics.get(path);
				NodeList animationsList = ele.getElementsByTagName("Animations");
				node = animationsList.item(0);
				NodeList nAnimationList = node.getChildNodes();
				List <Rect2i> animationBoxes = new ArrayList<Rect2i>();
				for(int a = 0; a<nAnimationList.getLength();a++){
					Node animationNode = nAnimationList.item(a);
					if(animationNode.getNodeType() == Node.ELEMENT_NODE){
						Element animation = (Element)animationNode;
						animationBoxes.add(new Rect2i(Integer.parseInt(animation.getAttribute("x")),Integer.parseInt(animation.getAttribute("y")),
								Integer.parseInt(animation.getAttribute("w")),Integer.parseInt(animation.getAttribute("h"))));
					}
				}
				Rect2i[] boxes = new Rect2i[animationBoxes.size()];
				for(int b = 0; b < animationBoxes.size(); b++){
					boxes[b] = animationBoxes.get(b);
				}
				new Armor(equipmentPrefix+id, null, null, name, description, image, boxes);
			}
		}
	}
	public void loadWeapons(Element docEle){
			
	}
	public void loadOthers(Element docEle){
		
	}
}
