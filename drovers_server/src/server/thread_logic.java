package server;

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
				if(Server.player_list.size() == 0){
					try{
						Thread.sleep(1000);
					}
					catch (InterruptedException e){
						e.printStackTrace();
					}
				}
				else{
					Thread_Logic.events_process();
					send_time();
				}
				
				Last_Update = System.currentTimeMillis();
			}
		}
	}
	
	public void send_time(){
		for(Client value: Server.player_list.values()){
			value.send("TIME:" + Long.toString(System.currentTimeMillis()));
		}
	}
	
	public static void events_process(){
		for(int i = 0; i < Server.event_buffer.size(); ++i){
			Event tmp = Server.event_buffer.get(i);
			
			String [] data = tmp.data.split(":");
			
			switch(data[0])
			{
				// ------------------------
				case "TIME":{
					
					break;
				}
				// ------------------------
				case "IN":{
					switch(data[1])
					{
						case "CONNECT":
							events_in_connect(tmp.client_id, data[2], data[3]);
							break;
						case "LOGOUT":
							System.out.println("test point");
							events_in_logout(tmp.client_id);
							break;
					}
					break;
				}
				//-------------------------
				case "CREATE":{
						switch(data[1]){
							case "PLAYER":
							events_create_player(tmp.client_id, data[2]);
							break;	
						}
					}
					break;
				default:
			}
		}
		Server.event_buffer.clear();
	}
	
	private static void events_in_connect(int client_id, String account, String password){
		int account_id = DB.db_accounts.search_account(account);
		
		if(DB.db_accounts.compare_password(account_id, password) == true
			&& DB.db_accounts.check_login(account_id) == false)
		{	
			Server.player_list.get(client_id).send("CONNECTION:SUCESS");
			Server.player_list.get(client_id).set_account_id(account_id);
			DB.db_accounts.connect(account_id);
			if(Server.debug)
				System.out.println("Connection \""+account+"\" is sucess" );
		}
		else{
			Server.player_list.get(client_id).send("CONNECTION:FAILED");
			if(Server.debug)
				System.out.println("Connection \""+account+"\" is failed" );
		}
	}
	
	private static void events_create_player(int client_id, String name){
		if(Server.player_list.get(client_id).get_account_id() != -1){
			boolean result = DB.db_players.add_player(Server.player_list.get(client_id).get_account_id(), name);
			if(result)
				Server.player_list.get(client_id).send("CREATE:SUCESS");
			else
				Server.player_list.get(client_id).send("CREATE:FAILED");
		}
		else
		{
			Server.player_list.get(client_id).send("CREATE:FAILED");
		}
	}
	private static void events_in_logout(int client_id){
		int account_id = Server.player_list.get(client_id).get_account_id();
		
		if(DB.db_accounts.check_login(account_id) == true)
			DB.db_accounts.disconnect(account_id);
		System.out.println(DB.db_accounts.get_name(account_id) + " is logout by user");
	}
}