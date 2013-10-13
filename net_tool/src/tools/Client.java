package tools;

import java.net.*;
import java.util.Scanner;
import java.io.*;

public class Client
{
	public static boolean is_runing;
	public static Scanner in;
	public static BufferedReader in_server;
	public static PrintWriter out;
	public static Client_Update client_update;
	
	public static void main(String[] args) throws IOException 
	{
		final int PORT = 3450;
		
		System.out.print("Enter the server addres: ");
		Scanner in = new Scanner(System.in);
		String str = in.nextLine();
		InetAddress addr = InetAddress.getByName(str);
		
		System.out.println("addr = " + addr);
		Socket socket = new Socket(addr, PORT);
		
		try 
		{
			System.out.println("socket = " + socket);
			is_runing = true;
			client_update = new Client_Update(socket);
			
			PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			
			while(in.hasNextLine())
    		{
    			str = in.nextLine();
				out.println(str);
			}
			
			// close connection
			out.println("END");
		}
		finally 
		{
			System.out.println("closing...");
			socket.close();
			in.close();
			out.close();
			is_runing = false;
		}
	}
}

class Client_Update extends Thread
{
	private static PrintWriter out;
	Client_Update(Socket socket) throws IOException
	{
		out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
		this.start();
	}
	
	@Override
	public void run()
	{
		
		while(Client.is_runing)
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
	}
};