package server;

import java.io.IOException;

import messages.Message;
import messages.MessageIn;
import World.*;

class Thread_Logic extends Thread
{
	// Timer
	private final long Logic_Update = 10;
	private final long Logic_Delta = 1000/Logic_Update;
	
	// World
	private static World world_data;
	
	Thread_Logic(){
		world_data = new World();
		this.start();
	}
	
	public void run(){
		long Last_Update = System.currentTimeMillis();		
		while(Server.is_runing){
			if(System.currentTimeMillis() - Last_Update >= Logic_Delta){
				if(Server.client_list.size() == 0 || Server.event_buffer.size() == 0){
					try{
						Thread.sleep(1000);
					}
					catch (InterruptedException e){
						e.printStackTrace();
					}
				}
				else{
					try {
						Thread_Logic.process_events();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				
				Last_Update = System.currentTimeMillis();
			}
		}
	}
	
	public void send_time() throws IOException{
		for(Client value: Server.client_list.values()){
			value.send(Message.Type.TIME, Long.toString(System.currentTimeMillis()));
		}
	}
	
	public static void process_events() throws IOException{
		for(int i = 0; i < Server.msg_buffer.size(); ++i){
			MessageIn tmp = Server.msg_buffer.get(i);
			
			if(tmp.type.equals(Message.Type.DEFAULT)){
				System.out.println(tmp.data);
			}
			else if(tmp.type.equals(Message.Type.TIME)){
				
			}
			else if(tmp.type.equals(Message.Type.CHAT)){
				event_chat_msg(tmp.client_id, tmp.data);
			}
			else if(tmp.type.equals(Message.Type.LOGIN)){
				String [] data = tmp.data.split(":");
				events_in_connect(tmp.client_id, data[1], data[2]);
			}
			else if(tmp.type.equals(Message.Type.LOGOUT)){
				events_in_logout(tmp.client_id);
			}
			else if(tmp.type.equals(Message.Type.DISCONNECT)){
				close_socket(tmp.client_id);
			}
			else if(tmp.type.equals(Message.Type.UPDATEAREA)){
				update_map(tmp.client_id);
			}
			else if(tmp.type.equals(Message.Type.CREATEPLAYER)){
				events_create_player(tmp.client_id, tmp.data);
			}
			else if(tmp.type.equals(Message.Type.CHOSEPLAYER)){
				events_chose_player(tmp.client_id, tmp.data);
			}
			
		}
		Server.msg_buffer.clear();
	}
	
	private static void events_in_connect(int client_id, String account, String password) throws IOException{
		int account_id = DB.db_accounts.search_account(account);
		
		if(DB.db_accounts.compare_password(account_id, password) == true
			&& DB.db_accounts.check_login(account_id) == false)
		{	
			update_map(client_id);
			Server.client_list.get(client_id).send(Message.Type.DEFAULT, "CONNECTION:SUCESS");
			Server.client_list.get(client_id).set_account_id(account_id);
			DB.db_accounts.connect(account_id);
			if(Server.debug)
				System.out.println("Connection \""+account+"\" is sucess" );
		}
		else{
			Server.client_list.get(client_id).send(Message.Type.DEFAULT, "CONNECTION:FAILED");
			if(Server.debug)
				System.out.println("Connection \""+account+"\" is failed" );
		}
	}
	
	private static void events_create_player(int client_id, String name) throws IOException{
		if(Server.client_list.get(client_id).get_account_id() != -1){
			boolean result = DB.db_players.add_player(Server.client_list.get(client_id).get_account_id(), name);
			if(result)
				Server.client_list.get(client_id).send(Message.Type.DEFAULT, "CREATE:SUCESS");
			else
				Server.client_list.get(client_id).send(Message.Type.DEFAULT, "CREATE:FAILED");
		}
		else
		{
			Server.client_list.get(client_id).send(Message.Type.DEFAULT, "CREATE:FAILED");
		}
	}
	private static void events_in_logout(int client_id){
		try{
			if(Server.client_list.get(client_id) != null)
				Server.client_list.get(client_id).disconnect();
		}catch(Exception e){
			System.out.println("Disconnection err: " + e);
		}
	}
	private static void close_socket(int client_id){
		Server.client_list.remove(client_id);
	}
	private static void update_map(int client_id) throws IOException{
		world_data.world_map.get("null").send(Server.client_list.get(client_id).get_socket().get_out_stream());
	}
	private static void events_chose_player(int client_id, String player_name){
		Server.client_list.get(client_id).set_player_id(DB.db_players.search_player(Server.client_list.get(client_id).get_account_id(), player_name));
	}
	private static void event_chat_msg(int client_id, String msg) throws IOException{
		int account_id = Server.client_list.get(client_id).get_account_id();
		if(account_id == -1)
			return;
		else{
			String account_name = DB.db_accounts.get_name(account_id);
			for(Client client: Server.client_list.values()){
				client.send(Message.Type.CHAT, account_name + ":" + msg);
			}
		}
	}
}