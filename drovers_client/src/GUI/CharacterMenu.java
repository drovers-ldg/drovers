package GUI;

import java.awt.Color;
import java.awt.Graphics;

import player_data.Player;
import player_data.World;
import player_data.WorldMap;

public class CharacterMenu{
	public static void draw(Graphics g){
		
		g.setColor(Color.white);
		g.drawString(Player.playerName, 0, 10);
		g.drawImage(World.texture_set.get("chat").getImage(), 0, 120, 380, 15, null);
		g.drawImage(World.texture_set.get("chat").getImage(), 0, 0, 380, 120, null);
		g.drawImage(World.texture_set.get("chat").getImage(), 0, 135, 380, 115, null);
		g.drawImage(World.texture_set.get("chat").getImage(), 0, 250, 380, 300, null);
		
		WorldMap.draw(g);
	}
}
