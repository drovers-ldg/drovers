package client;
//main client file

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

class Drovers_Client
{
	public static void main(String [] args) throws Exception
	{
		Thread_Socket socket = new Thread_Socket();
		Frame client = new Frame();
		
		socket.join();
		client.join();
	}
}

class Frame extends Canvas implements Runnable
{
	private static final long serialVersionUID = 1L;
	// status
	public static boolean client_runing;

	// thread
	private Thread render_thread;
	
	// Info
	public static String server_msg;
	
	Frame()
	{
		this.setPreferredSize(new Dimension(640, 480));
		JFrame frame = new JFrame("Drovers");
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.add(this, BorderLayout.CENTER);
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		
		
		Frame.client_runing = true;
		render_thread = new Thread(this);
		render_thread.run();
	}
	
	public void run()
	{
		init();
		
		while(Frame.client_runing)
		{
			render();
		}
	}
	
	public void join() throws InterruptedException
	{
		this.render_thread.join();
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
		g.drawString("Msg: " + server_msg, 0, 50);
		
		
		g.dispose();
		bs.show();
	}
}