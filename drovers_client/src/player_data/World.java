package player_data;

import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class World{
	public static Area_Map map;
	public static WorldMap worldMap;
	public static HashMap<String, Sprite> texture_set;
	
	public World(){
		map = new Area_Map();
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
				return texture_set.get("null").getImage();
			case 3:
				// deep forest
				return texture_set.get("null").getImage();
			case 4:
				// rocks
				return texture_set.get("ground").getImage();
			default:
				return texture_set.get("null").getImage();
		}
	}
}