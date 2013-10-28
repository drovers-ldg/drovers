package client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


class Thread_Update extends Thread
{
	private PrintWriter out;
	
	Thread_Update(Socket socket) throws IOException
	{
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
	}
	
	public void run()
	{
		while(Game.is_runing)
		{
			out.println("TIME:"+System.currentTimeMillis());
			
			try 
			{
				Thread.sleep(100);
			} 
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		this.out.close();
	}
	
	public void send(String msg)
	{
		if(Game.is_runing){
			out.println(msg);
		}
	}
}