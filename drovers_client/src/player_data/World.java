package player_data;

import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class World{
	public Area_Map map;
	
	public World(){
		map = new Area_Map();
	}

	public Sprite getSprite(String path)
	{
	    BufferedImage sourceImage = null;
	        
	    try 
	    {
	        java.net.URL url = this.getClass().getClassLoader().getResource(path);
	        sourceImage = ImageIO.read(url);
	    } 
	    catch (IOException e)
	    {
	        e.printStackTrace();
	    }

	    Sprite sprite = new Sprite(Toolkit.getDefaultToolkit().createImage(sourceImage.getSource()));
	        
	    return sprite;
	}
}