package client;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.swing.JFrame;

class Frame extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	// status
	public static boolean is_runing;
	
	// Info
	public static String server_msg = "0";
	public static long Ping = 0;
	public static long FPS = 0;
	public static long Time = 0;
	
	Frame() throws IOException, InterruptedException
	{
		this.setPreferredSize(new Dimension(640, 480));
		JFrame frame = new JFrame("Drovers");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		this.start();
	}
	
	public void start()
	{
		Frame.is_runing = true;
		new Thread(this).run();
	}
	
	public void run()
	{
		init();
		
		long LastTime = System.currentTimeMillis();
		long el_FPS = 0;
		long ElapsedTime = 0;
		
		// Open socket
		try 
		{
			new Thread_Socket().start();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		// Run main Game cycle
		while(Frame.is_runing)
		{
			ping();
			render();
			
			// Calculate the FPS
			ElapsedTime = System.currentTimeMillis() - LastTime;
			el_FPS++;
			if (ElapsedTime >= 1000)
			{
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
		Frame.Time = System.currentTimeMillis();
	}
	
	protected void render()
	{
		BufferStrategy bs = getBufferStrategy();
		if (bs == null){
			createBufferStrategy(2);
			requestFocus();
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, getWidth(), getHeight());
		
		
		// draw the info
		g.setColor(Color.white);
		g.drawString("FPS: " + Long.toString(Frame.FPS), 0, 40);
		g.drawString("Msg: " + Frame.server_msg, 0, 50);
		g.drawString("Ping: " + Frame.Ping, 0, 60);
		
		g.dispose();
		bs.show();
	}
	
	protected void ping()
	{
		if(System.currentTimeMillis() - Time > 100)
		{
			Frame.Ping = System.currentTimeMillis() - Long.parseLong(Frame.server_msg);
			Frame.Time = System.currentTimeMillis();
		}
	}
}