package client;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.IOException;

import player_data.Player;
import GUI.CharacterMenu;
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
			case "char":
				mouseClickChar();
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
		switch(State.state){
			case "char":
				try {
					mouseReleasedClickCharMenu();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				break;
			default:
		}
	}
	private void actionMenuShow(){
		CharacterMenu.actionShow = true;
		CharacterMenu.actionMenuCursorX = (CharacterMenu.mouseX/32)*32-5;
		CharacterMenu.actionMenuCursorY = (CharacterMenu.mouseY/32)*32;
		CharacterMenu.actionShowMouseX = (CharacterMenu.mouseX/32+1)*32-5;
		CharacterMenu.actionShowMouseY = (CharacterMenu.mouseY/32+1)*32;
		CharacterMenu.showMapCursor = false;
	}
	private void actionMenuClose(){
		CharacterMenu.actionShow = false;
		CharacterMenu.showMapCursor = true;
	}
	private boolean actionMenuMouseCoords(){
		if(CharacterMenu.mouseX > CharacterMenu.actionShowMouseX-25	&& CharacterMenu.mouseX < CharacterMenu.actionShowMouseX+25
		&& CharacterMenu.mouseY > CharacterMenu.actionShowMouseY 	&& CharacterMenu.mouseX < CharacterMenu.actionShowMouseY+50){
			return false;
		}
		else{
			return true;
		}
	}	
	private void mouseReleasedClickCharMenu() throws IOException{
		if(Game.mouseX > 380){
			if((Game.mouseX-380)/32 == Player.mapX){
				if(Game.mouseY/32 < Player.mapY){
					if(CharacterMenu.actionShow){
						if(actionMenuMouseCoords()){
							Sender.moveUp();
							actionMenuClose();
						}
						else{
							actionMenuClose();
						}
					}
					else {
						actionMenuShow();
					}
				}
				else if(Game.mouseY/32 > Player.mapY){
					if(CharacterMenu.actionShow){
						if(actionMenuMouseCoords()){
							Sender.moveDown();
							actionMenuClose();
						}
						else{
							actionMenuClose();
						}
					}
					else {
						actionMenuShow();
					}
				}
			}
			else if(Game.mouseY/32 == Player.mapY){
				if((Game.mouseX-380)/32 < Player.mapX){
					if(CharacterMenu.actionShow){
						if(actionMenuMouseCoords()){
							Sender.moveLeft();
							actionMenuClose();
						}
						else{
							actionMenuClose();
						}
					}
					else {
						actionMenuShow();
					}
				}
				else if((Game.mouseX-380)/32 > Player.mapX){
					if(CharacterMenu.actionShow){
						if(actionMenuMouseCoords()){
							Sender.moveRight();	
							actionMenuClose();
						}
						else{
							actionMenuClose();
						}
					}
					else {
						actionMenuShow();
					}
				}
			}
			else if((Game.mouseX-380)/32 < Player.mapX && Game.mouseY/32 < Player.mapY){
				if(CharacterMenu.actionShow){
					if(actionMenuMouseCoords()){
						Sender.moveUpLeft();
						actionMenuClose();
					}
					else{
						actionMenuClose();
					}
				}
				else {
					actionMenuShow();
				}
			}
			else if((Game.mouseX-380)/32 < Player.mapX && Game.mouseY/32 > Player.mapY){
				if(CharacterMenu.actionShow){
					if(actionMenuMouseCoords()){
						Sender.moveDownLeft();
						actionMenuClose();
					}
					else{
						actionMenuClose();
					}
				}
				else {
					actionMenuShow();
				}
			}
			else if((Game.mouseX-380)/32 > Player.mapX && Game.mouseY/32 < Player.mapY){
				if(CharacterMenu.actionShow){
					if(actionMenuMouseCoords()){
						Sender.moveUpRight();
						actionMenuClose();
					}
					else{
						actionMenuClose();
					}
				}
				else {
					actionMenuShow();
				}
			}
			else if((Game.mouseX-380)/32 > Player.mapX && Game.mouseY/32 > Player.mapY){
				if(CharacterMenu.actionShow){
					if(actionMenuMouseCoords()){
						Sender.moveDownRight();
						actionMenuClose();
					}
					else{
						actionMenuClose();
					}
				}
				else {
					actionMenuShow();
				}
			}	
		}
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
	
	private void mouseClickChar(){
		if(Game.mouseX > 380){
			CharacterMenu.nodeX = (Game.mouseX - 380) / 32;
			CharacterMenu.nodeY = (Game.mouseY)/ 32;
		}
	}
}

class MouseMotion implements MouseMotionListener{

	@Override
	public void mouseDragged(MouseEvent arg0) {

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		Game.mouseX = e.getX();
		Game.mouseY = e.getY();
		
		switch(State.state){
			case "char":
				mouseMoveChar();
				break;
			default:
		}
	}
	
	public static void mouseMoveChar(){
		switch(State.state){
			case "char":
				mouseMoveCharCharMenu();
				break;
			default:
		}
	}
	
	private static void mouseMoveCharCharMenu(){
		if(Game.mouseX > 380){
			CharacterMenu.showMapCursor = true;
			CharacterMenu.mouseX = Game.mouseX;
			CharacterMenu.mouseY = Game.mouseY;
		}
	}
}