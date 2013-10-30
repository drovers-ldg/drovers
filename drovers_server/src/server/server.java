package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

class Server
{
	// Debug mode
	public static boolean debug = false;
	
	// Socket settings
	static final int server_port = 3450;
	public static boolean is_runing;
	private static ServerSocket server_socket;
	
	// Events
	public static int event_id = 0;
	public static int client_id = 0;
	public static Event_Buffer event_buffer;
	public static HashMap<Integer, Client> client_list; 
	public static DB server_db;
	
	public Server() throws IOException, InterruptedException
	{
		// Create data sets and tools
		server_db = new DB();
		event_buffer = new Event_Buffer();
		client_list = new HashMap<Integer, Client>();
		run_sockets();
	}
	
	public static void run_sockets() throws IOException, InterruptedException
	{
		// Run socket_thread
		try 
		{
			server_socket = new ServerSocket(server_port);
			Server.is_runing = true;
			new Thread_Logic();
			
			System.out.println("Server runing.");
			new Server_UI();
			
			while(Server.is_runing) 
			{
				Socket socket = server_socket.accept();
				try
				{
					client_list.put(client_id, new Client(socket));
				}
				catch(IOException e) 
				{
					System.out.println("Err! socket: " + socket.toString() + " is closed!");
					socket.close();
				}
			}
		}
		catch(Exception e){
			System.out.println(e);
		}
	}
	
	protected void finalize() throws IOException{
		if(client_list.size() != 0)
		{
			System.out.println("Err! not all connections is interupted!");
			client_list.clear();
		}
		
		Server.is_runing = false;
		server_socket.close();
		System.gc();
	}
}