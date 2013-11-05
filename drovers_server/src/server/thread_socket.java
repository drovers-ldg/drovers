package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import messages.Message;
import messages.MessageIn;

class Thread_Socket extends Thread
{
	private Socket socket;
	private int client_id;
	
	// IO-streams
	private ObjectOutputStream out;
	private ObjectInputStream in;

	Thread_Socket(Socket socket, int client_id) throws IOException
	{
    	this.socket = socket;
    	this.client_id = client_id;
    	System.out.println(this.socket.getInetAddress().toString() + ":" + this.socket.getPort() + " id:" + this.client_id + " is connected;");
    
    	in = new ObjectInputStream(this.socket.getInputStream());
    	out = new ObjectOutputStream(this.socket.getOutputStream());
    	
    	this.start();
	}
	
	public void run()
	{
		try
		{		
			while(this.socket.isConnected()){  
				Object pack = in.readObject();
				
				if(pack instanceof Message){
					Message msg = (Message)pack;
					Server.msg_buffer.add(new MessageIn(msg, client_id));
				}
			}
		}
		catch(IOException e) {
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			Server.event_buffer.add(new Event(client_id, this.socket.getPort(), "IN:LOGOUT"));
			Server.event_buffer.add(new Event(client_id, this.socket.getPort(), "DISCONNECT"));
		}
	}
	public void send(String msg) throws IOException{
		new Message(0, msg).send(out);
	}
	
	public ObjectOutputStream get_out_stream(){
		return this.out;
	}
}