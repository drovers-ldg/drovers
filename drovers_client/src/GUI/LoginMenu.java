package GUI;

import java.awt.Color;
import java.awt.Graphics;

import player_data.World;

public class LoginMenu{
	
	public static boolean focusLoginString = false;
	public static boolean focusPasswordString = false;
	public static String loginString;
	public static String passwordString;
	
	public static void draw(Graphics g){
		g.setColor(Color.white);
		g.drawString("Login:", 355, 115);
		g.drawString("Passw:", 355, 135);
		g.drawImage(World.getTexture(5), 400, 100, 200, 20, null);
		g.drawImage(World.getTexture(5), 400, 120, 200, 20, null);
	}
	
	public static void clearCtrl(){
		focusLoginString = false;
		focusPasswordString = false;
	}
}