package client;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JFrame;

import player_data.World;

class Game
{	
	// Frame size
    public static int WIDTH = 1024;
    public static int HEIGHT = WIDTH / 16 * 9;
	
	// status
	public static boolean is_runing;
	public static BufferStrategy bs;
	public static Graphics g;
	public static State state;
	
	// Info
	public static String [] msg_log;
	public static String server_msg = "";
	public static long server_time = 0;
	public static long Ping = 0;
	public static long FPS = 0;
	public static long Time = 0;
	
	// Login options
	public static String address;
	public static int port;
	public static String login;
	public static String password;
	
	// Game data
	public static World game_data;
	
	Game() throws IOException, InterruptedException{
		read_config();
		
		JFrame frame = new JFrame("Drovers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(WIDTH, HEIGHT);
		
		Canvas gui = new Canvas();
		frame.getContentPane().add(gui);

		frame.setResizable(false);
		frame.setVisible(true);
		
		Thread Main_Thread = new Thread(new GameLoop(gui));
		Main_Thread.setPriority(Thread.MIN_PRIORITY);
		Main_Thread.start();
	}
	
	public static void read_config() throws FileNotFoundException{
		Scanner in = new Scanner(new File("config\\connection.conf"));
		
		while(in.hasNext()){
			String str = in.nextLine();
			if(str.contains("#address:")){
				String [] tmp = str.split("#address:");
				tmp[1] = tmp[1].trim();
				Game.address = tmp[1];
			}
			else if(str.contains("#port:")){
				String [] tmp = str.split("#port:");
				tmp[1] = tmp[1].trim();
				Game.port = Integer.parseInt(tmp[1]);
			}
		}
		
		in.close();
	}
	
	public static void ping()
	{
		if(System.currentTimeMillis() - Time > 100){
			Game.Ping = System.currentTimeMillis() - Game.server_time;
			Game.Time = System.currentTimeMillis();
		}
	}
}