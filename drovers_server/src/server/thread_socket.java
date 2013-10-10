package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

class Thread_Socket extends Thread
{
	private Socket socket;
	private InetAddress client_addres;

	
	// IO-streams
	private BufferedReader in;
	private PrintWriter out;
	
	Thread_Socket(Socket in_socket) throws IOException
	{
    	this.socket = in_socket;
    	client_addres = this.socket.getInetAddress();
    	System.out.println(client_addres.toString() + " is connected;");
    	// Set streams
    	in = new BufferedReader(new InputStreamReader(in_socket.getInputStream()));
    	out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(in_socket.getOutputStream())), true);
    	
    	this.start();
	}

	@Override
	public void run() 
	{
		try 
		{
			while (true) 
			{  
				String str = in.readLine();
				
				if(Drovers_Server.event_buffer.event_buff.size() < Event_Buffer.lenght)
				{
					Drovers_Server.event_buffer.event_buff.add(new Event(client_addres, socket.getPort(), str));
				}
				else
				{
					Print_Event_Buff();
					Clear_Event_Buff();
				}
			}
		} 
		catch(IOException e) 
		{
			//System.err.println("IO Exception");
		}
		finally 
		{
			try 
			{
				socket.close();
				in.close();
				out.close();
				
				System.out.println(client_addres.toString() + ":" + socket.getPort() + " is disconnected;");
			} 
			catch(IOException e)
			{
				System.err.println("Socket not closed");
			}
		}
	}
	
	public void Print_Event_Buff()
	{
		for(Event item: Drovers_Server.event_buffer.event_buff)
			System.out.println("id: " + item.id + ", form: " + item.client_addres.toString() + ":" + item.client_port + ":" + item.data);
		System.out.println();
	}
	
	public void Clear_Event_Buff()
	{
		Drovers_Server.event_buffer.event_buff.clear();
	}
}