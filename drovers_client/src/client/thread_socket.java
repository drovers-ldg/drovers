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
	
	Thread_Socket() throws IOException
	{
		// port 3450
		// address 127.0.0.1
		InetAddress server_address = InetAddress.getByName("127.0.0.1");
		Socket socket = new Socket(server_address, 3450);
		this.run(socket);
	}

	public void run(Socket socket) throws IOException
	{	
		try 
		{
			this.in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			
			String msg;
			while(Frame.client_runing)
			{
				msg = in.readLine();
				Frame.server_msg = msg;
			}
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
	
		socket.close();
	}
	
	protected void finalize() throws Exception
	{
		out.println("END");
		this.in.close();
		this.out.close();
	}
}