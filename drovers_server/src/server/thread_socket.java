package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Set;

import database.DBAccounts;
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
			Server.msg_buffer.add(new MessageIn(Message.Type.LOGOUT, client_id));
			Server.msg_buffer.add(new MessageIn(Message.Type.DISCONNECT, client_id));
			this.interrupt();
		}
	}
	public void send(Message.Type type, String msg) throws IOException{
		new Message(type, msg).send(out);
	}
	public void send(String player, String data) throws IOException{
		new MessageDouble(player, data).send(out);
	}
	public void sendMap() throws IOException{
		synchronized(DBAccounts.map){
			World.areaMaps.get(DBAccounts.map.get(Server.client_list.get(this.client_id).get_account_id()).mapId).writeExternal(out);
		}
	}

	public void sendWorld() throws IOException {
		World.worldMap.writeExternal(out);
	}
	
	public void sendPlayer() throws IOException {
		synchronized(DBAccounts.map){
			DBAccounts.map.get(Server.client_list.get(client_id).get_account_id()).writeExternal(out);
		}
	}

	public void sendPlayersOnlineRequest()  throws IOException{
		new Message(Message.Type.UPDATESQUADS).send(out);
	}
	
	public void sendPlayersOnline() throws IOException {
		synchronized(DBAccounts.map){
			int onlineCount = 0;
			Set<Integer> client_list = Server.client_list.keySet();
			for(Integer index: client_list){
				if(Server.client_list.get(index).get_connection()){
					onlineCount++;
				}
			}
			out.writeInt(onlineCount);
			for(Integer index: client_list){
				out.writeInt(DBAccounts.map.get(Server.client_list.get(index).get_account_id()).mapX);
				out.writeInt(DBAccounts.map.get(Server.client_list.get(index).get_account_id()).mapY);
				out.writeUTF(DBAccounts.map.get(Server.client_list.get(index).get_account_id()).playerName);
			}
			out.flush();
		}
	}
}