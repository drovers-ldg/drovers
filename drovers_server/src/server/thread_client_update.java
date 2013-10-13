package server;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


class Client_Update extends Thread
{
	private static PrintWriter out;
	Client_Update(Socket socket) throws IOException
	{
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		this.start();
	}
	
	public void run()
	{
		while(Drovers_Server.server_runing)
		{
			out.println(Long.toString(System.currentTimeMillis()));

			try 
			{
				Thread.sleep(1000);
			} 
			catch (InterruptedException e) 
			{
				e.printStackTrace();
			}
		}
	}
};