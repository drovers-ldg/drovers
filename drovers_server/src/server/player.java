package server;

import java.io.IOException;
import java.net.Socket;

class Client{
	private String name;
	private int client_id;
	private Thread_Socket thread;
	
	Client(Socket socket) throws IOException{
		this.client_id = Server.client_id++;
		this.thread = new Thread_Socket(socket, this.client_id);
	}
	
	public int get_id(){
		return this.client_id;
	}
	
	public String get_name(){
		return this.name;
	}
	
	public void send(String msg){
		if(Server.is_runing)
			this.thread.send(msg);
	}
}