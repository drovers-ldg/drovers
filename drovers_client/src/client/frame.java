package client;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.swing.JFrame;


@SuppressWarnings("serial")
class Game extends JFrame implements Runnable
{	
	// status
	public static boolean is_runing;
	public static BufferStrategy bs;
	public static Graphics g;
	public static State state;
	
	// Info
	public static String server_msg = "";
	public static long server_time = 0;
	public static long Ping = 0;
	public static long FPS = 0;
	public static long Time = 0;
	
	// Timer
	public static long Frame_MAX = 60;
	public static long Frame_Delta = 1000/Frame_MAX;
	
	// Login options
	public static String addres;
	public static String login;
	public static String password;
	
	Game(String addres, String login, String password) throws IOException, InterruptedException{
		Game.addres = addres;
		Game.login = login;
		Game.password = password;
		
		this.setPreferredSize(new Dimension(640, 480));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BorderLayout());
		this.pack();
		this.setResizable(false);
		this.setVisible(true);
		this.start();
	}
	
	public void start(){
		Game.is_runing = true;
		new Thread(this).run();
	}
	
	public void run(){
		init();
		
		long LastTime = System.currentTimeMillis();
		long el_FPS = 0;
		long ElapsedTime = 0;
		long Last_Update = 0;
		// Open socket
		try {
			new Thread_Socket().start();
		} 
		catch (IOException e) {
			e.printStackTrace();
		}
		
		// Run main Game cycle
		while(Game.is_runing){
			if(System.currentTimeMillis() - Last_Update >= Game.Frame_Delta){
				ping();
				render();
				el_FPS++;
				Last_Update = System.currentTimeMillis();
			}
			// Calculate the FPS
			ElapsedTime = System.currentTimeMillis() - LastTime;
			
			if (ElapsedTime >= 1000){
				FPS = el_FPS;
			    el_FPS = 0;
			    LastTime = System.currentTimeMillis();
			}
		}
	}
	
	public void join()
	{
		this.join();
	}
	
	protected void init()
	{
		Game.Time = System.currentTimeMillis();
		state = new State(this, "menu");
	}

	protected void render()
	{	
		bs = getBufferStrategy();
		if (bs == null){
			createBufferStrategy(2);
			requestFocus();
			return;
		}
		g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		// draw state
		state.set_graphic(g);	
		state.draw();
		
		g.dispose();
		bs.show();
	}
	
	protected void ping()
	{
		if(System.currentTimeMillis() - Time > 100)
		{
			Game.Ping = System.currentTimeMillis() - Game.server_time;
			Game.Time = System.currentTimeMillis();
		}
	}
}