package client;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


class Thread_Update extends Thread
{
	private static PrintWriter out;
	
	Thread_Update(Socket socket) throws IOException
	{
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
	}
	
	public void run()
	{

	}
	
	public static void send(String msg)
	{
		if(Game.is_runing){
			if(msg.contains("login")){
				if(msg.matches("^login [a-zA-Z0-9]+ [a-zA-Z0-9]+$")){
					String [] login = msg.split(" ");
					out.println("IN:CONNECT:"+login[1]+":"+login[2]);
				}
			}
			else if(msg.matches("^logout$")){
				out.println("IN:LOGOUT");
			}
			else if(msg.matches("^CHAT:([a-zA-Z0-9]|\\s)+$")){
				Game.server_msg = msg;
				out.println(msg);
			}
		}
	}
}