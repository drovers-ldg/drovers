package client;

import java.awt.Color;
import java.awt.Graphics;
import java.io.IOException;

import player_data.World;

class State
{	
	private Graphics g;
	private String state;
	
	// command line
	private static boolean console_is_open;
	public static String console_type;

	State(String state){
		this.g = null;
		this.state = state;

		console_is_open = false;
		State.console_type = "";
	}
	
	void set_graphic(Graphics g) { 
		this.g = g;
	}
	
	void set_state(String state){
		this.state = state;
	}
	
	public boolean get_console(){
		return console_is_open;
	}
	void console() throws IOException{
		if(console_is_open){
			Chat.process_command(State.console_type);
			State.console_type = "";
			console_is_open = false;
		}
		else
			console_is_open = true;
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
		draw_msg_log();
		draw_console();
		draw_info();
	}
	void draw_msg_log(){
		g.setColor(Color.white);
		for(int i = 0; i < Game.msg_log.length; ++i){
			g.drawString(Game.msg_log[i], 0, 120+(i*10));
		}
	}
	void draw_console(){
		if(console_is_open){
			g.setColor(Color.white);
			g.drawString(">" + State.console_type, 0, 100);
		}
	}
	
	void draw_info(){
		// draw info
		g.setColor(Color.white);
		g.drawString("Window: " + state, 0, 50);
		g.drawString("FPS: " + Long.toString(Game.FPS), 0, 60);
		g.drawString("Msg: " + Game.server_msg, 0, 70);
		g.drawString("Ping: " + Game.Ping, 0, 80);
	}
	
	void draw_map(){
		World.map.draw_map(g);
	}
}