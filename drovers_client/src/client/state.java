package client;

import java.awt.Color;
import java.awt.Graphics;

import player_data.World;

class State
{	
	private Graphics g;
	private String state;
	
	State(){
		this.g = null;
		this.state = "menu";
	}
	State(String state){
		this.g = null;
		this.state = state;
	}
	
	void set_graphic(Graphics g) { 
		this.g = g;
	}
	
	void set_state(String state){
		this.state = state;
	}
	
	void draw(){	
		// State switcher
		switch(state)
		{
			case "menu":{
				
				break;
			}
			case "map":{
				draw_map();
				break;
			}
			default:{
				
			}
		}
		
		draw_info();
	}
	
	void draw_info(){
		// draw info
		g.setColor(Color.white);
		g.drawString("Window: " + state, 5, 50);
		g.drawString("FPS: " + Long.toString(Game.FPS), 5, 60);
		g.drawString("Msg: " + Game.server_msg, 5, 70);
		g.drawString("Ping: " + Game.Ping, 5, 80);
	}
	
	void draw_map(){
		World.map.draw_map(g);
	}
}