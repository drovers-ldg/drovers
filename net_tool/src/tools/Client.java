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
			socket.close();
			in.close();
			out.close();
			is_runing = false;
		}
	}
	
	public static void command_process(String str){
		String [] in_command = str.split(" ");
		
		
		switch(in_command[0]){
			case "login":
				if(in_command[1] != null && in_command[2] != null){
					out.println("IN:CONNECT:" + in_command[1] + ":" + in_command[2]);
				}
				break;
			case "create":
				if(in_command[1] != null){
					out.println("CREATE:PLAYER:" + in_command[1]);
				}
				break;
			default:
			{
				System.out.println("Unknown command");
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

		try 
		{
			while(Client.is_runing)
			{
				Client.out.println(Long.toString(System.currentTimeMillis()));
				Thread.sleep(100);
			}
		} 
		catch (InterruptedException e)
		{
			
		}
	}
};

class Client_Listener extends Thread{
	Client_Listener(){
		this.start();
	}
	
	public void run(){
		String str = null;
		try 
		{
			while(Client.is_runing){
				str = Client.in_server.readLine();
				String [] msg = str.split(":");
				
				if(!msg[0].equals("TIME")){
					System.out.println(str);
				}
			}
			
		}
		catch (IOException e)
		{
			System.out.println("Disconect by server.");
			Client.is_runing = false;
		}
	}
}