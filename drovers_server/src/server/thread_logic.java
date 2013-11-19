package server;

import java.io.IOException;
import java.sql.SQLException;

import database.DBAccounts;
import database.DBPlayers;
import messages.Message;
import messages.MessageIn;

class Thread_Logic extends Thread
{
	// Timer
	private final long Logic_Update = 10;
	private final long Logic_Delta = 1000/Logic_Update;
	
	Thread_Logic(){
		this.start();
	}
	
	public void run(){
		long Last_Update = System.currentTimeMillis();		
		while(Server.is_runing){
			if(System.currentTimeMillis() - Last_Update >= Logic_Delta){
				if(Server.client_list.size() == 0 || Server.msg_buffer.size() == 0){
					try{
						Thread.sleep(1000);
					}
					catch (InterruptedException e){
						e.printStackTrace();
					}
				}
				else{
					try {
						Thread_Logic.processEvents();
					} catch (IOException e) {
						e.printStackTrace();
					} catch (SQLException e) {
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
	
	public static void processEvents() throws IOException, SQLException{
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
				events_in_connect(tmp.client_id, data[0], data[1]);
			}
			else if(tmp.type.equals(Message.Type.LOGOUT)){
				events_in_logout(tmp.client_id);
			}
			else if(tmp.type.equals(Message.Type.DISCONNECT)){
				close_socket(tmp.client_id);
			}
			else if(tmp.type.equals(Message.Type.UPDATEAREA)){
				updateMap(tmp.client_id);
			}
			else if(tmp.type.equals(Message.Type.CREATEPLAYER)){
				events_create_player(tmp.client_id, tmp.data);
			}
			else if(tmp.type.equals(Message.Type.CHOSEPLAYER)){
				events_chose_player(tmp.client_id, tmp.data);
			}
			else if(tmp.type.equals(Message.Type.UPDATEWORLD)){
				eventWorldUpdate(tmp.client_id);
			}
		}
		Server.msg_buffer.clear();
	}
	
	private static void updateMap(int client_id) throws IOException {
		Server.client_list.get(client_id).sendMap();
	}

	private static void events_in_connect(int client_id, String account, String password) throws IOException{
		
		int account_id = DBAccounts.searchId(account);
		if(DBAccounts.comparePassword(account_id, password) && !DBAccounts.map.get(account_id).online){
			if(Server.client_list.get(client_id).get_account_id() != -1){
				Server.client_list.get(client_id).disconnect();
			}
			Server.client_list.get(client_id).set_account_id(account_id);
			DBAccounts.connect(account_id);
			Server.client_list.get(client_id).send(Message.Type.CONNECTIONSUCESS, null);
		}
		else{
			Server.client_list.get(client_id).send(Message.Type.CONNECTIONFAILED, null);
		}
	}
	
	private static void events_create_player(int client_id, String name) throws IOException, SQLException{
		if(Server.client_list.get(client_id).get_account_id() != -1){
			boolean result = DBPlayers.addPlayer(Server.client_list.get(client_id).get_account_id(), name);
			if(result){
				events_in_logout(client_id);
				Server.client_list.get(client_id).send(Message.Type.DEFAULT, "CREATE:SUCESS");
			}
			else{
				Server.client_list.get(client_id).send(Message.Type.DEFAULT, "CREATE:FAILED");
			}
		}
		else{
			Server.client_list.get(client_id).send(Message.Type.DEFAULT, "CREATE:FAILED");
		}
	}
	
	private static void events_in_logout(int client_id){
		try{
			if(Server.client_list.get(client_id)  != null)
				Server.client_list.get(client_id).disconnect();
		}catch(Exception e){
			System.out.println("Disconnection err: " + e);
		}
	}
	private static void close_socket(int client_id){
		Server.client_list.remove(client_id);
	}
	
	private static void events_chose_player(int client_id, String player_name){
		//Server.client_list.get(client_id).set_player_id(DB.db_players.search_player(Server.client_list.get(client_id).get_account_id(), player_name));
	}
	
	private static void event_chat_msg(int client_id, String msg) throws IOException{
		int accountId = Server.client_list.get(client_id).get_account_id();
		if(accountId == -1){
			return;
		}
		else{
			String accountName = DBAccounts.map.get(accountId).accountName;
			for(Client client: Server.client_list.values()){
				client.send(accountName, msg);
			}
		}
	}
	
	private static void eventWorldUpdate(int client_id) throws IOException{
		Server.client_list.get(client_id).sendWorld();
	}
}