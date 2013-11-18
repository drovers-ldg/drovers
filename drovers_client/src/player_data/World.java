package player_data;

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
}