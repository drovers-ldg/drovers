package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import messages.Message;
import messages.MessageDouble;
import player_data.Area_Map;
import player_data.World;

class Thread_Socket extends Thread
{
	protected static ObjectInputStream in;
	protected static ObjectOutputStream out;
	protected static Socket socket;
	protected static boolean waitMapUpdate;
	
	Thread_Socket() throws IOException
	{
		// port 3450
		InetAddress server_address = InetAddress.getByName(Game.address);
		socket = new Socket(server_address, Game.port);
	}
	
	public void run()
	{	
		try
		{
			in = new ObjectInputStream(socket.getInputStream());
			new Sender(new ObjectOutputStream(socket.getOutputStream()));
		
			while(Game.is_runing)
			{
				if(!waitMapUpdate){
					Object msg = in.readObject();
					
					if(msg instanceof MessageDouble){
						processMsg((MessageDouble)msg);
					}
					else if(msg instanceof Message){
						processMsg((Message)msg);
					}
					else{
						Game.server_msg = "Unexpected type of message";
					}
				}
				else {
					Area_Map map = new Area_Map();
					map.readExternal(in);
					processMsg(map);
				}
			}
		}
		catch (IOException e) 
		{
			
		} 
		catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		finally{
			try {
				Sender.logout();
			} 
			catch (IOException e1) {
				e1.printStackTrace();
			}
			
			try {
				in.close();
				socket.close();
			}catch (IOException e){
				e.printStackTrace();
			}
		}	
	}
	
	public void processMsg(MessageDouble msg){
		msgChat(msg.data, msg.data2);
	}
	public void processMsg(Area_Map map){
		World.map = map;
		Game.state.set_state("map");
		waitMapUpdate = false;
	}
	public void processMsg(Message msg) throws IOException{
		if(msg.type.equals(Message.Type.DEFAULT)){
			msgDefault(msg.data);
		}
		else if(msg.type.equals(Message.Type.TIME)){
			msgTime(msg.data);
		}
		else if(msg.type.equals(Message.Type.CONNECTIONSUCESS)){
			msgConnectionSucess();
		}
		else if(msg.type.equals(Message.Type.CONNECTIONFAILED)){
			msgConnectionFailed();
		}
	}
	private void msgDefault(String data){
		Game.server_msg = data;
	}
	private void msgChat(String player, String data){
		Chat.add_to_msg_log("["+player+"]: " + data);
	}
	private void msgTime(String data){
		Game.server_time = Long.parseLong(data);
		Game.ping();
	}
	private void msgConnectionSucess() throws IOException{
		waitMapUpdate = true;
		Sender.updateMap();
		Chat.add_to_msg_log("[SERVER] Connection to \""+ Game.address  + "\" sucess.");
	}
	private void msgConnectionFailed(){
		Game.state.set_state("menu");
		Chat.add_to_msg_log("[SERVER] Failed by connection.");
	}
	
	public void send(Message.Type type, String msg) throws IOException{
		new Message(type, msg).send(out);;
	}
}