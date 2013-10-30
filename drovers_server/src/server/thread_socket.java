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
	private int client_id;
	
	// IO-streams
	private BufferedReader in;
	private PrintWriter out;

	Thread_Socket(Socket socket, int client_id) throws IOException
	{
    	this.socket = socket;
    	this.client_id = client_id;
    	client_addres = this.socket.getInetAddress();
    	System.out.println(client_addres.toString() + ":" + this.socket.getPort() + " id:" + this.client_id + " is connected;");
    	
    	in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
    	out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream())), true);

    	this.start();
	}
	
	public void run()
	{
		try
		{		
			while(this.socket.isConnected() && Server.is_runing) 
			{  
				String str = in.readLine();
				Server.event_buffer.add(new Event(client_id, this.socket.getPort(), str));
			}
		}
		catch(IOException e) {
		}
		finally{
			Server.event_buffer.add(new Event(client_id, this.socket.getPort(), "IN:LOGOUT"));
			Server.event_buffer.add(new Event(client_id, this.socket.getPort(), "DISCONNECT"));
		}
	}
	public void send(String msg){
		out.println(msg);
	}
	
	public PrintWriter get_out_stream(){
		return this.out;
	}
}