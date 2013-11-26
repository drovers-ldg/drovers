package GUI;

import java.awt.Color;
import java.awt.Graphics;

import player_data.Player;
import player_data.PlayersOnline;
import player_data.World;
import player_data.WorldMap;

public class CharacterMenu{
	public static int nodeX;
	public static int nodeY;
	public static int mouseX;
	public static int mouseY;
	public static boolean showMapCursor = false;
	
	public CharacterMenu(){
		nodeX = 0;
		nodeY = 0;
	}
	
	public static void draw(Graphics g){
		WorldMap.draw(g);

		g.drawImage(World.texture_set.get("chat").getImage(), 0, 120, 380, 15, null);
		g.drawImage(World.texture_set.get("chat").getImage(), 0, 0, 380, 120, null);
		g.drawImage(World.texture_set.get("chat").getImage(), 0, 135, 380, 115, null);
		g.drawImage(World.texture_set.get("chat").getImage(), 0, 250, 380, 300, null);
		
		g.setColor(Color.white);
		g.drawString(Player.playerName, 0, 10);
		PlayersOnline.draw(g);
		g.drawImage(World.texture_set.get("char").getImage(), Player.mapX*32+380, Player.mapY*32, 32, 32, null);
		
		if(showMapCursor){
			g.drawImage(World.texture_set.get("cursor").getImage(), (mouseX/32)*32-5, (mouseY/32)*32, 32, 32, null);
		}
	}
}
