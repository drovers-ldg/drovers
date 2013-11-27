package GUI;

import java.awt.Color;
import java.awt.Graphics;
import player_data.Player;
import player_data.World;

public class AreaMapMenu{
	public static void draw(Graphics g){
		World.areaMap.draw(g);

		g.drawImage(World.texture_set.get("chat").getImage(), 0, 120, 380, 15, null);
		g.drawImage(World.texture_set.get("chat").getImage(), 0, 0, 380, 120, null);
		g.drawImage(World.texture_set.get("chat").getImage(), 0, 135, 380, 115, null);
		g.drawImage(World.texture_set.get("chat").getImage(), 0, 250, 380, 300, null);
		
		g.setColor(Color.white);
		g.drawString(Player.playerName, 0, 10);
	
		// PlayersOnline.draw(g); - Draw enemies SQ
		// g.drawImage(World.texture_set.get("char").getImage(), Player.mapX*32+380, Player.mapY*32, 32, 32, null); - Show own SQ
	}
}