package GUI;

import java.awt.Color;
import java.awt.Graphics;

import client.Mouse;
import player_data.Player;
import player_data.PlayersOnline;
import player_data.World;
import player_data.WorldMap;

public class CharacterMenu{
	public enum Way {NULL,
					UP, DOWN,
					LEFT, RIGHT,
					UPLEFT, UPRIGHT,
					DOWNLEFT, DOWNRIGHT};
	
	public static int nodeX;
	public static int nodeY;
	public static int mouseX;
	public static int mouseY;
	public static int actionShowMouseX;
	public static int actionShowMouseY;
	public static boolean showMapCursor;
	public static boolean actionShow;
	public static int actionMenuCursorX;
	public static int actionMenuCursorY;
	public static Way actionWay;
	public static boolean actionMenuDetected;
	
	public CharacterMenu(){
		nodeX = 0;
		nodeY = 0;
		actionShowMouseX = 0;
		actionShowMouseY = 0;
		showMapCursor = false;
		
		// action menu
		actionShow = false;
		actionMenuCursorX = 0;
		actionMenuCursorY = 0;
		actionMenuDetected = false;
		actionWay = Way.NULL;
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
		if(actionShow){
			g.drawImage(World.texture_set.get("cursorSelected").getImage(), actionMenuCursorX, actionMenuCursorY, 32, 32, null);
			g.drawImage(World.texture_set.get("chat").getImage(), actionShowMouseX, actionShowMouseY, 50, 40, null);
			g.setColor(Color.white);
			if(Mouse.actionMenuMouseMove()){
				g.setColor(Color.red);
				g.drawString("Move", actionShowMouseX, actionShowMouseY+10);
				g.setColor(Color.white);
			}
			else{
				g.setColor(Color.white);
				g.drawString("Move", actionShowMouseX, actionShowMouseY+10);
			}
			
			if(Mouse.actionMenuMouseExplore()){
				g.setColor(Color.red);
				g.drawString("Attack", actionShowMouseX, actionShowMouseY+30);
				g.setColor(Color.white);
			}
			else{
				g.setColor(Color.white);
				g.drawString("Attack", actionShowMouseX, actionShowMouseY+30);
			}
		}
	}
}
