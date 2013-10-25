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
					}
					break;
				}
				default:
			}
		}
		Server.event_buffer.clear();
	}
	
	private static void events_in_connect(int client_id, String account, String password){
		int account_id = DB.db_accounts.search_account(account);
		
		if(DB.db_accounts.compare_password(account_id, password) == true){
			Server.player_list.get(client_id).send("CONNECTION:SUCESS");
			if(Server.debug)
				System.out.println("Connection \""+account+"\" is sucess" );
		}
		else{
			Server.player_list.get(client_id).send("CONNECTION:FAILED");
			if(Server.debug)
				System.out.println("Connection \""+account+"\" is failed" );
		}
	}
}