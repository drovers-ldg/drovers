package player_data;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class World{
	public static Area_Map areaMap;
	public static WorldMap worldMap;
	public static HashMap<String, Sprite> texture_set;
	
	public World(){
		areaMap = new Area_Map();
		worldMap = new WorldMap();
		
		// maps
		texture_set = new HashMap<String, Sprite>();
		load_textures();
	}

	public Sprite getSprite(String path)
	{
	    BufferedImage sourceImage = null;
	    try {
	        java.net.URL url = this.getClass().getClassLoader().getResource(path);
	        sourceImage = ImageIO.read(url);
	    } 
	    catch (IOException e){
	        e.printStackTrace();
	    }
	    Sprite sprite = new Sprite(Toolkit.getDefaultToolkit().createImage(sourceImage.getSource()));    
	    return sprite;
	}
	
	public void load_textures(){
		texture_set.put("null", getSprite("null.png"));
		texture_set.put("grass", getSprite("grass.png"));
		texture_set.put("ground", getSprite("ground.png"));
		texture_set.put("forest", getSprite("forest.png"));
		
		// Fields
		texture_set.put("inputString1", getSprite("inputString1.png"));
		
		// Buttons
		texture_set.put("buttonLogin", getSprite("loginButton.png"));
		texture_set.put("buttonExit", getSprite("exitButton.png"));
		texture_set.put("buttonAuthor", getSprite("authorButton.png"));
		
	}
	
	public static Image getTexture(int type){
		// all types and codes see at player_data/WorldMap/WorldMapNode.class
		switch(type){
			case 0:
				// null
				return texture_set.get("null").getImage();
			case 1:
				// grass
				return texture_set.get("grass").getImage();
			case 2:
				// forest
				return texture_set.get("forest").getImage();
			case 3:
				// deep forest
				return texture_set.get("null").getImage();
			case 4:
				// rocks
				return texture_set.get("ground").getImage();
			case 5:
				// inputString-1
				return texture_set.get("inputString1").getImage();
			case 6:
				// loginButton
				return texture_set.get("buttonLogin").getImage();
			case 7: 
				// exitButton
				return texture_set.get("buttonExit").getImage();
			case 8: 
				// authorButton
				return texture_set.get("buttonAuthor").getImage();
			default:
				return texture_set.get("null").getImage();
		}
	}
}