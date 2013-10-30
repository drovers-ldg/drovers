package server;

import java.io.IOException;
import java.net.Socket;

class Client{
	private int client_id;
	private int account_id;
	private int player_id;
	private Thread_Socket thread;
	
	Client(Socket socket) throws IOException{
		this.player_id = -1;
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
	public int get_player_id(){
		return this.player_id;
	}
	public void send(String msg){
		if(Server.is_runing)
			this.thread.send(msg);
	}
	public void set_account_id(int account_id){
		this.account_id = account_id;
	}
	public void set_player_id(int player_id){
		this.player_id = player_id;
	}
	public void disconnect(){
		if(account_id != -1){
			DB.db_accounts.disconnect(this.account_id);
			this.account_id = -1;
			this.player_id = -1;
		}
	}
	public boolean get_connection(){
		return DB.db_accounts.check_login(account_id);
	}
	public Thread_Socket get_socket(){
		return this.thread;
	}
}