package GUI;

import java.awt.Color;
import java.awt.Graphics;
import player_data.Player;
import player_data.WorldMap;

public class CharacterMenu{
	public static void draw(Graphics g){
		
		g.setColor(Color.white);
		g.drawString(Player.playerName, 0, 10);
		
		WorldMap.draw(g);
	}
}
