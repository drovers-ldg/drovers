package client;

import java.awt.Color;
import java.awt.Graphics;

class State
{	
	private Graphics g;
	private String state;
	private Frame frame;
	
	State()
	{
		this.g = null;
		this.frame = null;
		this.state = "menu";
	}
	State(Frame frame, String state)
	{
		this.g = null;
		this.frame = frame;
		this.state = state;
	}
	
	void set_graphic(Graphics g) 
	{ 
		this.g = g;
	}
	void set_frame(Frame frame)
	{
		this.frame = frame;
	}
	
	void set_state(String state)
	{
		this.state = state;
	}
	
	void draw()
	{	
		// State switcher
		switch(state)
		{
			case "menu":
			{
				break;
			}
			case "test":
			{
				
				break;
			}
			default:
			{
				
			}
		}
		
		draw_info();
	}
	
	void draw_info()
	{
		// draw info
		g.setColor(Color.white);
		g.drawString("Window: " + state, 0, 10);
		g.drawString("FPS: " + Long.toString(Frame.FPS), 0, 40);
		g.drawString("Msg: " + Frame.server_msg, 0, 50);
		g.drawString("Ping: " + Frame.Ping, 0, 60);
	}
}