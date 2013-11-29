package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import GUI.LoginMenu;
import messages.Message;
import messages.MessageDouble;
import player_data.Area_Map;
import player_data.Player;
import player_data.PlayersOnline;
import player_data.World;
import player_data.WorldMap;

class Thread_Socket extends Thread
{
	protected static ObjectInputStream in;
	protected static ObjectOutputStream out;
	protected static Socket socket;
	protected static boolean waitMap1Update;
	protected static boolean waitMap2Update;
	protected static boolean waitWorldUpdate;
	protected static boolean waitPlayerUpdate;
	protected static boolean waitSQUpdate;
	
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
				if(waitSQUpdate){
					World.playersOnline.setSize(in.readInt());
					for(int i = 0; i < World.playersOnline.size(); ++i){
						World.playersOnline.set(i, new PlayersOnline(in.readInt(), in.readInt(), in.readUTF()));
					}
					waitSQUpdate = false;
				}
				else if(waitPlayerUpdate){
					Player player = new Player();
					player.readExternal(in);
					processMsg(player);
				}
				else if(waitMap1Update){
					Area_Map map = new Area_Map();
					map.readExternal(in);
					loadMap1(map);
				}
				else if(waitMap2Update){
					Area_Map map = new Area_Map();
					map.readExternal(in);
					loadMap2(map);
				}
				else if(waitWorldUpdate){
					WorldMap worldMap = new WorldMap();
					worldMap.readExternal(in);
					processMsg(worldMap);
				}
				else {
					Object msg = in.readObject();
					
					if(msg instanceof MessageDouble){
						processMsg((MessageDouble)msg);
					}
					else if(msg instanceof Message){
						processMsg((Message)msg);
					}
					else if(msg instanceof Player){
						processMsg((Player)msg);
					}
					else{
						Game.server_msg = "Unexpected type of message";
					}
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
	
	private void loadMap2(Area_Map map) throws IOException {
		World.areaMap2 = map;
		waitMap2Update = false;
		waitSQUpdate = true;
		Sender.sendSQUpdate();
		Chat.add_to_msg_log("AREA2 LOADED");
	}

	private void loadMap1(Area_Map map) throws IOException {
		World.areaMap1 = map;
		waitMap1Update = false;
		waitMap2Update = true;
		Sender.UpdateArea2();
		Chat.add_to_msg_log("AREA1 LOADED");
	}

	public void processMsg(MessageDouble msg){
		msgChat(msg.data, msg.data2);
	}
		
	public void processMsg(WorldMap worldMap) throws IOException{
		World.worldMap = worldMap;
		waitWorldUpdate = false;
		Game.state.set_state("char");
		Chat.add_to_msg_log("[SERVER] Connection to \""+ Game.address  + "\" sucess.");
	}
	
	public void processMsg(Player player) throws IOException{
		waitPlayerUpdate = false;
		Chat.add_to_msg_log("PLAYER DATA RECIVED: " + Player.playerName);
		World.playerData = player;	
		waitWorldUpdate = true;
		Sender.updateWorld();
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
		else if(msg.type.equals(Message.Type.SQMOVEUP)){
			Player.mapY--;
		}
		else if(msg.type.equals(Message.Type.SQMOVEDOWN)){
			Player.mapY++;
		}
		else if(msg.type.equals(Message.Type.SQMOVELEFT)){
			Player.mapX--;
		}
		else if(msg.type.equals(Message.Type.SQMOVERIGHT)){
			Player.mapX++;
		}
		else if(msg.type.equals(Message.Type.SQMOVEUPLEFT)){
			Player.mapY--;
			Player.mapX--;
		}
		else if(msg.type.equals(Message.Type.SQMOVEUPRIGHT)){
			Player.mapY--;
			Player.mapX++;
		}
		else if(msg.type.equals(Message.Type.SQMOVEDOWNLEFT)){
			Player.mapY++;
			Player.mapX--;
		}
		else if(msg.type.equals(Message.Type.SQMOVEDOWNRIGHT)){
			Player.mapY++;
			Player.mapX++;
		}
		else if(msg.type.equals(Message.Type.UPDATESQUADS)){
			waitSQUpdate = true;
			Sender.sendSQUpdate();
		}
		else if(msg.type.equals(Message.Type.BATTLEAREA1)){
			waitMap1Update = true;
			Sender.UpdateArea1();
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
		waitPlayerUpdate = true;
		Sender.updatePlayer();
		Chat.add_to_msg_log("[SERVER] Connection sucess!");
	}
	private void msgConnectionFailed(){
		Game.state.set_state("login");
		LoginMenu.errString = "Wrong login or password";
		Chat.add_to_msg_log("[SERVER] Failed by connection.");
	}
	
	public void send(Message.Type type, String msg) throws IOException{
		new Message(type, msg).send(out);
	}
}