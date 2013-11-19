package client;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import GUI.LoginMenu;

public class key_input extends KeyAdapter 
{
	public void keyPressed(KeyEvent e){
		switch(State.state){
			case "login":
				if(LoginMenu.focusLoginString){
					// ENTER TO LOGIN STRING
				}
				else if(LoginMenu.focusPasswordString){
					// ENTER TO PASSWORD STRING
				}
				else
					break;
		
			default:
		}
		if(Game.state.get_console() && e.getKeyCode() != KeyEvent.VK_ENTER){
			Chat.set_console_type(e);
		}
		else{
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				try {
					Game.state.console();
				}
				catch (IOException e1) {
				}
			}
		}
	} 	
	
	public void keyReleased(KeyEvent e) {	

	}
}
