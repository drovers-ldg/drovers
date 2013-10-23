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
				if(Server.player_list.size() == 0)
					try {Thread.sleep(1000);} catch (InterruptedException e){e.printStackTrace();}
				
				send_time();
				Last_Update = System.currentTimeMillis();
			}
		}
	}
	
	public void send_time(){
		for(Client value: Server.player_list.values()){
			value.send("TIME:"+Long.toString(System.currentTimeMillis()));
		}
	}
}