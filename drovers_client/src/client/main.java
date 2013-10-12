package client;
//main client file

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.io.IOException;

import javax.swing.JFrame;

class Drovers_Client
{
	public static void main(String [] args) throws Exception
	{
		new Frame();
		Frame.is_runing = false;
	}
}

class Frame extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	
	// status
	public static boolean is_runing;
	
	// Info
	public static String server_msg;
	public static long FPS;
	
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
			render();
			
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
		g.drawString("FPS: " + Long.toString(FPS), 0, 40);
		g.drawString("Msg: " + server_msg, 0, 50);
		
		
		g.dispose();
		bs.show();
	}
}