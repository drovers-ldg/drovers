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

	Thread_Socket(Socket socket) throws IOException
	{
    	this.socket = socket;
    	
    	client_addres = this.socket.getInetAddress();
    	System.out.println(client_addres.toString() + ":" + this.socket.getPort() + " is connected;");
    	
    	in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    	out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream())), true);

    	this.start();
	}
	
	public void run()
	{
		try 
		{
			long time = System.currentTimeMillis();
			
			while(Server.is_runing) 
			{  
				String str = in.readLine();
				Send_Data();
				
				if(str.equals("END"))
					break;
				
				if( (System.currentTimeMillis() - time < 15) && (Server.event_buffer.event_buff.size() < Event_Buffer.lenght))
				{
					Server.event_buffer.event_buff.add(new Event(client_addres, this.socket.getPort(), str));
				}
				else
				{
					//System.out.println("buffer is cleared (" + Drovers_Server.event_buffer.event_buff.size() + ")");
					time = System.currentTimeMillis();
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
				this.socket.close();
				System.out.println(client_addres.toString() + ":" + this.socket.getPort() + " is disconnected;");
			}
			catch(IOException e)
			{
				System.err.println("Err! Socket is not closed!");
			}
		}
	}
	
	private void Print_Event_Buff()
	{
		//for(Event item: Drovers_Server.event_buffer.event_buff)
			//System.out.println("id: " + item.id + ", form: " + item.client_addres.toString() + ":" + item.client_port + ":" + item.data);
	}
	
	private void Clear_Event_Buff()
	{
		Server.event_buffer.event_buff.clear();
	}
	
	private void Send_Data()
	{
		out.println(Long.toString(System.currentTimeMillis()));
	}
}