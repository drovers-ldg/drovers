package server;

import java.io.IOException;
// main server file

class Drovers_Server
{	
	public static void main(String [] args) throws IOException, InterruptedException
	{
		System.out.println("Server runing.");
		new Server();
	}
}