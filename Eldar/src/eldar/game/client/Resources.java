package eldar.game.client;

import eldar.game.client.core.levels.tiles.Tileset;

public class Resources {

	public static String resourcesPath = "resources/";
//	/*
//	 * Audio
//	 */
	

//	/*
//	 * Graphics
//	 */
//	Spritesheets & Tilesets
	public static Tileset tileset = new Tileset("/graphics/dirt.jpg", 32,32);
//	Sprites
	public static GameProperties gameProperties = new GameProperties("/data/.properties");
//	public static GameProperties defaultGameProperties = new GameProperties("/data/.defaultproperties");
}
