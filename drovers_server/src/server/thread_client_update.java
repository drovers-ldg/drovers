package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

class Client_Update extends Thread
{
	private PrintWriter out;
	
	Client_Update(Socket socket) throws IOException
	{
	
		this.start();
	}
	
	public void run()
	{
		while(Server.is_runing)
		{
			out.println(Long.toString(System.currentTimeMillis()));

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
		System.out.println("out stream is closed");
	}
}