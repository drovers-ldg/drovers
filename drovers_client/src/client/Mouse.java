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
	public static boolean actionMenuMouseMove(){
		if(CharacterMenu.actionShow 
				&& CharacterMenu.mouseX > CharacterMenu.actionMenuCursorX + 32 
				&& CharacterMenu.mouseX < CharacterMenu.actionMenuCursorX + 82
				&& CharacterMenu.mouseY > CharacterMenu.actionMenuCursorY + 32
				&& CharacterMenu.mouseY < CharacterMenu.actionMenuCursorY + 52){
			return true;
		}
		else{
			return false;
		}
	}
	public static boolean actionMenuMouseExplore(){
		if(CharacterMenu.actionShow 
				&& CharacterMenu.mouseX > CharacterMenu.actionMenuCursorX + 32 
				&& CharacterMenu.mouseX < CharacterMenu.actionMenuCursorX + 82
				&& CharacterMenu.mouseY > CharacterMenu.actionMenuCursorY + 52
				&& CharacterMenu.mouseY < CharacterMenu.actionMenuCursorY + 72){
			return true;
		}
		else{
			return false;
		}
	}
	
	private void mouseReleasedClickCharMenu() throws IOException{
		if(Game.mouseX > 380){
			if(CharacterMenu.actionShow){
				if(actionMenuMouseMove()){
					if(CharacterMenu.actionWay == CharacterMenu.Way.UP){
						Sender.moveUp();
					}
					else if(CharacterMenu.actionWay == CharacterMenu.Way.DOWN){
						Sender.moveDown();
					}
					else if(CharacterMenu.actionWay == CharacterMenu.Way.LEFT){
						Sender.moveLeft();
					}
					else if(CharacterMenu.actionWay == CharacterMenu.Way.RIGHT){
						Sender.moveRight();
					}
					else if(CharacterMenu.actionWay == CharacterMenu.Way.UPLEFT){
						Sender.moveUpLeft();
					}
					else if(CharacterMenu.actionWay == CharacterMenu.Way.UPRIGHT){
						Sender.moveUpRight();
					}
					else if(CharacterMenu.actionWay == CharacterMenu.Way.DOWNRIGHT){
						Sender.moveDownRight();
					}
					else if(CharacterMenu.actionWay == CharacterMenu.Way.DOWNLEFT){
						Sender.moveDownLeft();
					}
					actionMenuClose();
				}
				else if(actionMenuMouseExplore()){
					if(CharacterMenu.actionWay == CharacterMenu.Way.UP){
						Sender.attackUp();
					}
					else if(CharacterMenu.actionWay == CharacterMenu.Way.DOWN){
						Sender.attackDown();
					}
					else if(CharacterMenu.actionWay == CharacterMenu.Way.LEFT){
						Sender.attackLeft();
					}
					else if(CharacterMenu.actionWay == CharacterMenu.Way.RIGHT){
						Sender.attackRight();
					}
					else if(CharacterMenu.actionWay == CharacterMenu.Way.UPLEFT){
						Sender.attackUpLeft();
					}
					else if(CharacterMenu.actionWay == CharacterMenu.Way.UPRIGHT){
						Sender.attackUpRight();
					}
					else if(CharacterMenu.actionWay == CharacterMenu.Way.DOWNRIGHT){
						Sender.attackDownRight();
					}
					else if(CharacterMenu.actionWay == CharacterMenu.Way.DOWNLEFT){
						Sender.attackDownLeft();
					}
					actionMenuClose();
				}
				else {
					actionMenuClose();
				}
			}	
			else if((Game.mouseX-380)/32 == Player.mapX){
				if(Game.mouseY/32 < Player.mapY){
					actionMenuShow();
					CharacterMenu.actionWay = CharacterMenu.Way.UP;
				}
				else if(Game.mouseY/32 > Player.mapY){
					actionMenuShow();
					CharacterMenu.actionWay = CharacterMenu.Way.DOWN;
				}
			}
			else if(Game.mouseY/32 == Player.mapY){
				if((Game.mouseX-380)/32 < Player.mapX){
					actionMenuShow();
					CharacterMenu.actionWay = CharacterMenu.Way.LEFT;
				}
				else if((Game.mouseX-380)/32 > Player.mapX){
					actionMenuShow();
					CharacterMenu.actionWay = CharacterMenu.Way.RIGHT;
				}
			}
			else if((Game.mouseX-380)/32 < Player.mapX && Game.mouseY/32 < Player.mapY){
				actionMenuShow();
				CharacterMenu.actionWay = CharacterMenu.Way.UPLEFT;
			}
			else if((Game.mouseX-380)/32 < Player.mapX && Game.mouseY/32 > Player.mapY){
				actionMenuShow();
				CharacterMenu.actionWay = CharacterMenu.Way.DOWNLEFT;
			}
			else if((Game.mouseX-380)/32 > Player.mapX && Game.mouseY/32 < Player.mapY){
				actionMenuShow();
				CharacterMenu.actionWay = CharacterMenu.Way.UPRIGHT;
			}
			else if((Game.mouseX-380)/32 > Player.mapX && Game.mouseY/32 > Player.mapY){
				actionMenuShow();
				CharacterMenu.actionWay = CharacterMenu.Way.DOWNRIGHT;
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
			CharacterMenu.actionMenuDetected = Mouse.actionMenuMouseMove();
		}
	}
}