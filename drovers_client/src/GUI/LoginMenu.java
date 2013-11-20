package GUI;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import client.Chat;
import client.InputField;
import client.PasswordField;
import client.Sender;
import client.key_input;
import player_data.World;

public class LoginMenu{
	// Fields
	public static InputField loginString;
	public static PasswordField passwordString;
	public static String errString;
	
	public LoginMenu(){
		init();
	}
	
	private static void init(){
		loginString = new InputField();
		passwordString = new PasswordField();
		errString = "";
	}
	
	public static void draw(Graphics g){
		g.setColor(Color.red);
		g.drawString(errString, 450, 90);
		
		g.setColor(Color.white);
		g.drawString("Login:", 355, 115);
		g.drawString("Passw:", 355, 135);
				
		g.drawImage(World.getTexture(5), 400, 100, 200, 20, null); // 200 x 20 - 'Login' field
		g.drawString(loginString.str, 405, 115);
		g.drawImage(World.getTexture(5), 400, 120, 200, 20, null); // 200 x 20 - 'Password' filed
		g.drawString(passwordString.str, 405, 135);
		
		g.drawImage(World.getTexture(6), 450, 150, 100, 20, null); // 100 x 20 - 'Login' button
		g.drawImage(World.getTexture(7), 450, 500, 100, 20, null); // 100 x 20 - 'Exit' button
		g.drawImage(World.getTexture(8), 50, 500, 100, 20, null); // 100 x 20 - 'Author' button
	}
	
	public static void clearCtrl(){
		key_input.loginMenuLoginFeild = false;
		key_input.loginMenuPasswordField = false;
		errString = "";
	}
	
	public static void loginButton() throws IOException{
		if(loginString.str.matches("^[0-9a-zA-Z]+$") && passwordString.pass.matches("^[0-9a-zA-Z]+$")){
			Sender.login(loginString.str, passwordString.pass);
			Chat.add_to_msg_log("[GAME] Try to login.");
		}
		else{
			errString = "Check login and password";
		}
	}
	
	public static void exitButton(){
		
	}
	
	public static void authorsButton(){
		
	}
}