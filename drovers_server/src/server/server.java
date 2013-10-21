package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

class Server
{
	static final int server_port = 3450;
	public static boolean is_runing;
	// Events
	public static int event_id = 0;
	public static int client_id = 0;
	public static Event_Buffer event_buffer;
	public static HashMap<Integer, Client> player_list;
	
	public Server() throws IOException, InterruptedException
	{
		// Create data sets and tools
		event_buffer = new Event_Buffer();
		player_list = new HashMap<Integer, Client>();
		run_sockets();
	}
	
	public static void run_sockets() throws IOException, InterruptedException
	{
		ServerSocket server_socket = new ServerSocket(server_port);
		// Run socket_thread
		try 
		{
			Server.is_runing = true;
			while(true) 
			{
				Socket socket = server_socket.accept();
				try
				{
					player_list.put(client_id, new Client(socket));
				}
				catch(IOException e) 
				{
					System.out.println("Err! socket: " + socket.toString() + " is closed!");
					socket.close();
				}
			}
		}
		
		finally 
		{
			if(player_list.size() != 0)
			{
				System.out.println("Err! not all connections is interupted!");
				player_list.clear();
			}
			
			Server.is_runing = false;
			server_socket.close();
			System.gc();
		}
	}
}