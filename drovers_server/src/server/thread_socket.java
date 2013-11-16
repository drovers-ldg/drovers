package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import World.World;
import messages.Message;
import messages.MessageDouble;
import messages.MessageIn;

class Thread_Socket extends Thread
{
	private Socket socket;
	private int client_id;
	
	// IO-streams
	private ObjectOutputStream out;
	private ObjectInputStream in;

	Thread_Socket(Socket socket, int client_id) throws IOException{
    	this.socket = socket;
    	this.client_id = client_id;
    	System.out.println(this.socket.getInetAddress().toString() + ":" + this.socket.getPort() + " id:" + this.client_id + " is connected;");
  
    	out = new ObjectOutputStream(this.socket.getOutputStream());
    	in = new ObjectInputStream(this.socket.getInputStream());
    	this.start();
	}
	
	public void run(){
		try{		
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
		}
		finally{
			Server.msg_buffer.add(new MessageIn(Message.Type.LOGOUT, client_id)); // logout
			Server.msg_buffer.add(new MessageIn(Message.Type.DISCONNECT, client_id)); // disconnect
		}
	}
	public void send(Message.Type type, String msg) throws IOException{
		new Message(type, msg).send(out);
	}
	public void send(String player, String data) throws IOException{
		new MessageDouble(player, data).send(out);
	}
	public void sendMap() throws IOException{
		World.areaMaps.get("null").writeExternal(out);;
	}
}