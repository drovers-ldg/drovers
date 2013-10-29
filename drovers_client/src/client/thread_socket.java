package client;

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
	protected BufferedReader in;
	protected PrintWriter out;
	protected Socket socket;
	
	Thread_Socket() throws IOException
	{
		// port 3450
		InetAddress server_address = InetAddress.getByName(Game.addres);
		this.socket = new Socket(server_address, 3450);
	}
	
	public void run()
	{	
		try
		{
			this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(this.socket.getOutputStream())), true);
			new Thread_Update(this.socket).start();
			
			String msg;
			Thread_Update.send("login " + Game.login + " " + Game.password);
			
			while(Game.is_runing)
			{
				msg = in.readLine();
				process_msg(msg);
			}
		}
		catch (IOException e) 
		{
			
		}
		finally{
			out.println("IN:LOGOUT");
			try {
				in.close();
				out.close();
				socket.close();
			}catch (IOException e){
				e.printStackTrace();
			}
		}	
	}
	
	public void process_msg(String msg){
		if(msg.matches("^TIME:[0-9]+$")){
			String [] tmp = msg.split(":");
			Game.server_time = Long.parseLong(tmp[1]);
		}
		else{
			Game.server_msg = msg;
		}
	}
	public void send_msg(String msg){
		out.println(msg);
	}
}