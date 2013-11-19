package client;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MouseListener extends MouseMotionAdapter{
	public void mouseMoved(MouseEvent e){
		Game.mouseX = e.getX();
		Game.mouseY = e.getY();
	}
	
	public void mouseDrugged(MouseEvent e){
		Game.mouseX = e.getX();
		Game.mouseY = e.getY();	
	}	
}