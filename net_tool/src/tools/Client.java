package tools;

import java.net.*;
import java.io.*;

public class Client
{
	public BufferedReader in;
	public PrintWriter out;
	
	public static void main(String[] args) throws IOException 
	{
		final int PORT = 3450;

		InetAddress addr = InetAddress.getByName("127.0.0.1");
		System.out.println("addr = " + addr);
		Socket socket = new Socket(addr, PORT);

		try 
		{
			System.out.println("socket = " + socket);
			
			BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			
			String str = "howdy";
			while(true)
			{
				out.println(str);
			}
		}
		finally 
		{
			System.out.println("closing...");
			socket.close();
		}
	}
}