package GUI;

import java.awt.Graphics;
import player_data.World;

public class AreaMapMenu{
	public static void draw(Graphics g){
		World.areaMap.draw(g);
	}
}