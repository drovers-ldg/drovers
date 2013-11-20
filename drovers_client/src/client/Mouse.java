package client;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import GUI.LoginMenu;

public class Mouse implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
		switch(State.state){
			case "login":
				try {
					mouseClickLogin();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;
			default:
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Game.server_msg = "CLICK! 2";
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Game.server_msg = "CLICK! 3";
	}

	@Override
	public void mousePressed(MouseEvent e) {
		Game.server_msg = "CLICK! 4";
		switch(State.state){
			case "login":
				mousePressLogin();
			break;
		default:
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		Game.server_msg = "CLICK! 5";
	}
	
	private void mouseClickLogin() throws IOException{
		// GUI.LoginMenu.java;
		// Login field
		if(Game.mouseX > 400 && Game.mouseX < 600 && Game.mouseY > 100 && Game.mouseY < 120){
			Game.server_msg = "LOGIN FIELD DETECTED";
			LoginMenu.clearCtrl();
			key_input.loginMenuLoginFeild = true;
		}
		// Password field
		else if(Game.mouseX > 400 && Game.mouseX < 600 && Game.mouseY > 120 && Game.mouseY < 140){
			Game.server_msg = "PASSWORD FIELD DETECTED";
			LoginMenu.clearCtrl();
			key_input.loginMenuPasswordField = true;
		}
		// 'Login' button
		else if(Game.mouseX > 450 && Game.mouseX < 550 && Game.mouseY > 150 && Game.mouseY < 170){
			Game.server_msg = "'Login' button";
			LoginMenu.buttonLoginTexture = 6;
			LoginMenu.clearCtrl();
			LoginMenu.loginButton();
		}
		// 'Exit' button
		else if(Game.mouseX > 450 && Game.mouseX < 550 && Game.mouseY > 500 && Game.mouseY < 520){
			Game.server_msg = "'Exit' button";
			LoginMenu.buttonExitTexture = 7;
			LoginMenu.clearCtrl();
			LoginMenu.exitButton();
		}
		else{
			LoginMenu.clearCtrl();
		}
	}
	
	private void mousePressLogin(){
		// GUI.LoginMenu.java;
		// 'Login' button
		if(Game.mouseX > 450 && Game.mouseX < 550 && Game.mouseY > 150 && Game.mouseY < 170){
			LoginMenu.buttonLoginTexture = 8; // 8
		}
		// 'Exit' button
		else if(Game.mouseX > 450 && Game.mouseX < 550 && Game.mouseY > 500 && Game.mouseY < 520){
			LoginMenu.buttonExitTexture = 9; // 9
		}
	}
}

class MouseMotion implements MouseMotionListener{

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Game.mouseX = e.getX();
		Game.mouseY = e.getY();
	}
	
}