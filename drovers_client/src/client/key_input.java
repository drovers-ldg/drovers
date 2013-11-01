package client;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class key_input extends KeyAdapter 
{
	public void keyPressed(KeyEvent e){
		
		if(Game.state.get_console() && e.getKeyCode() != KeyEvent.VK_ENTER){
			Game.state.set_console_type(e);
		}
		else{
			if(e.getKeyCode() == KeyEvent.VK_ENTER){
				Game.state.console();
			}
		}
	} 	
	
	public void keyReleased(KeyEvent e) {	

	}
}
