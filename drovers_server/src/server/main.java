package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
// main server file


class Drovers_Server 
{	
	static final int server_port = 3450;
	
	// Events
	public static int event_id = 0;
	public static Event_Buffer event_buffer; 
	
	public static void main(String [] args) throws IOException 
	{
		System.out.println("Server runing.");
		
		// Create data sets and tools
		event_buffer = new Event_Buffer();
		run_sockets();
	}
	
	public static void run_sockets() throws IOException
	{
		ServerSocket server_socket = new ServerSocket(server_port);
		
		// Run socket_thread
		try 
		{
			while(true) 
			{
				Socket socket = server_socket.accept();
				try 
				{
					new Thread_Socket(socket);
				}
				catch(IOException e) 
				{
					socket.close();
				}
			}
		} 
		finally 
		{
			server_socket.close();
		}
	}
}