package client;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.swing.JFrame;

import player_data.World;

class Game
{	
	// Frame
	private static final int FRAME_DELAY = 17;
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
	public static String addres;
	public static String login;
	public static String password;
	
	// Game data
	public static World game_data;
	
	Game() throws IOException, InterruptedException{
		Game.addres = "localhost";
		
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
	
	public static void ping()
	{
		if(System.currentTimeMillis() - Time > 100){
			Game.Ping = System.currentTimeMillis() - Game.server_time;
			Game.Time = System.currentTimeMillis();
		}
	}
		
	public static class GameLoop implements Runnable {
		private static Canvas gui;
		private static long cycleTime;
		// FPS
		private static long LastTime = System.currentTimeMillis();
		private static long el_FPS = 0;
		private static long ElapsedTime = 0;
		@SuppressWarnings("unused")
		private static long Last_Update = 0;
 
		public GameLoop(Canvas canvas) {
			Game.is_runing = true;
			gui = canvas;
			this.init();
		}
 
		public void run(){			
			try{
				new Thread_Socket().start();
			}
			catch (IOException e){
				e.printStackTrace();
			}
			
			cycleTime = System.currentTimeMillis();
			gui.createBufferStrategy(2);
			BufferStrategy strategy = gui.getBufferStrategy();
 
			while (Game.is_runing) {
				render(strategy);
				synchFramerate();
				
				ElapsedTime = System.currentTimeMillis() - LastTime;
				if (ElapsedTime >= 1000){
					FPS = el_FPS;
				    el_FPS = 0;
				    LastTime = System.currentTimeMillis();
				}
			}
		}
		
		private void init(){
			Game.Time = System.currentTimeMillis();
			Game.game_data = new World();
			Game.msg_log = new String[10];
			for(int i = 0; i < Game.msg_log.length; ++i){
				Game.msg_log[i] = "";
			}
			
			state = new State("menu");
			gui.addKeyListener(new key_input());
		}
 
		private void render(BufferStrategy strategy) {
			Graphics g = strategy.getDrawGraphics();

			g.setColor(Color.black);
			g.fillRect(0, 0, gui.getWidth(), gui.getHeight());
			
			state.set_graphic(g);	
			state.draw();
			
			g.dispose();
			strategy.show();
			
			el_FPS++;
			Last_Update = System.currentTimeMillis();
		}
 
		private void synchFramerate() {
			cycleTime = cycleTime + FRAME_DELAY;
			long difference = cycleTime - System.currentTimeMillis();
			try {
				Thread.sleep(Math.max(0, difference));
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}