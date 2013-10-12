package client;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.*;

class Thread_Socket extends Thread
{
	protected BufferedReader in;
	protected PrintWriter out;
	protected Socket socket;
	
	Thread_Socket() throws IOException
	{
		// port 3450
		// address 127.0.0.1
		InetAddress server_address = InetAddress.getByName("127.0.0.1");
		socket = new Socket(server_address, 3450);
	}
	
	public void run()
	{	
		try 
		{
			this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			
			String msg;
			
			while(Frame.is_runing)
			{
				msg = in.readLine();
				Frame.server_msg = msg.toString();
			}
		}
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		
		// close socket
		out.println("END");	
	}
	
	protected void finalize() throws Exception
	{
		this.socket.close();
		this.in.close();
		this.out.close();
	}
}

class Client_Update extends Thread
{
	private PrintWriter out;
	
	Client_Update(Socket socket) throws IOException
	{
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		this.start();
	}
	
	public void run()
	{
		while(Frame.is_runing)
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
	}
}