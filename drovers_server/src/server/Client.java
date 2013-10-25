package server;

import java.io.IOException;
import java.net.Socket;

class Client{
	private int account_id;
	private int client_id;
	private Thread_Socket thread;
	
	Client(Socket socket) throws IOException{
		this.account_id = -1;
		this.client_id = Server.client_id++;
		this.thread = new Thread_Socket(socket, this.client_id);
	}
		
	public int get_id(){
		return this.client_id;
	}
	
	public int get_account_id(){
		return this.account_id;
	}
	
	public void send(String msg){
		if(Server.is_runing)
			this.thread.send(msg);
	}
	
	public void set_account_id(int account_id){
		this.account_id = account_id;
	}
}