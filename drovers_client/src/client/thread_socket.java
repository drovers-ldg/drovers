package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import messages.Message;
import messages.Message.Type;
import player_data.World;

class Thread_Socket extends Thread
{
	protected static ObjectInputStream in;
	protected static ObjectOutputStream out;
	protected static Socket socket;
	
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

			Message msg;
			
			while(Game.is_runing)
			{
				msg = Message.read(in);
				processMsg(msg);
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
	
	public void processMsg(Message msg) throws IOException{
		if(msg.type.equals(Message.Type.DEFAULT)){
			msgDefault(msg.data);
		}
		else if(msg.type.equals(Message.Type.CHAT)){
			msgChat(msg.data);
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
		else if(msg.type.equals(Message.Type.AREASIZE)){
			msgAreaSize(msg.data);
		}
		else if(msg.type.equals(Message.Type.AREALINE)){
			msgAreaLine(msg.data);
		}
	}
	private void msgDefault(String data){
		Game.server_msg = data;
	}
	private void msgChat(String data){
		Chat.add_to_msg_log(data);
	}
	private void msgTime(String data){
		Game.server_time = Long.parseLong(data);
		Game.ping();
	}
	private void msgConnectionSucess() throws IOException{
		//new Message(Message.Type.UPDATEAREA).send(out);
		Game.state.set_state("map");
		Chat.add_to_msg_log("[SERVER] Connection to \""+ Game.address  + "\" sucess.");
	}
	private void msgConnectionFailed(){
		Game.state.set_state("menu");
		Chat.add_to_msg_log("[SERVER] Failed by connection.");
	}
	private void msgAreaSize(String data){
		String [] tmp = data.split(":");
		World.map.rebuild_size(Integer.parseInt(tmp[0]), Integer.parseInt(tmp[1]));
	}
	private void msgAreaLine(String data){
		String [] tmp = data.split(":");			
		int line_index = Integer.parseInt(tmp[0]);
		int [] line_data = new int[Integer.parseInt(tmp[1])];
		for(int i = 2; i < tmp.length; ++i){
			line_data[i-2] = Integer.parseInt(tmp[i]);
		}
		World.map.rebuild_line(line_index, line_data);
	}
	
	public void send(Message.Type type, String msg) throws IOException{
		new Message(type, msg).send(out);;
	}
}