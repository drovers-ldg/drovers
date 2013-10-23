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
		
			
			out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())), true);
			in_server = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			new Client_Update();
			new Client_Listener();
			while(in.hasNextLine())
    		{
    			str = in.nextLine();
    			command_process(str);
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
	
	public static void command_process(String str){
		String [] in_command = str.split(" ");
		
		if(in_command[0].compareTo("login") == 0){
			if(in_command[1] != null && in_command[2] != null){
				out.println("IN:CONNECT:"+in_command[1]+":"+in_command[2]);
			}
			else{
				System.out.println("Invalid params");
			}
		}
	}
}

class Client_Update extends Thread
{
	Client_Update()
	{
		this.start();
	}

	public void run()
	{		
		while(Client.is_runing)
		{
			Client.out.println(Long.toString(System.currentTimeMillis()));
			
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

class Client_Listener extends Thread{
	Client_Listener(){
		this.start();
	}
	
	public void run(){
		String str = null;
		while(Client.is_runing){
			try 
			{
				str = Client.in_server.readLine();
				String [] msg = str.split(":");
				
				if(!(msg[0].compareTo("TIME") == 0)){
					System.out.println(str);
				}
			}
			catch (IOException e) 
			{
				e.printStackTrace();
			}
		}
	}
}