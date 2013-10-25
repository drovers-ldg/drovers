package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Scanner;

class DB_Players{
	private int db_players_id;
	private HashMap<Integer, Player> db_players;
	
	DB_Players() throws FileNotFoundException{
		db_players = new HashMap<Integer, Player>();
		read_player_table();
	}
	
	private void read_player_table() throws FileNotFoundException{
		Scanner in = new Scanner(new File(DB.db_players_address));
		if(in.hasNextLine()){
			this.db_players_id = in.nextInt();
			if(this.db_players_id != 0){
				while(in.hasNextLine()){
					int id = in.nextInt();
					int account_id = in.nextInt();
					String name = in.next();
					db_players.put(id, new Player(id, account_id, name));
				}
			}
		}
		else{
			this.db_players_id = 0;
			System.out.println("Loading clear DB:players.db");
		}
		in.close();
		System.out.println("Complete loading DB:players.db");
	}
	
	public void add_player(int account_id, String name){
		int id = this.db_players_id++;
		db_players.put(id,  new Player(id, account_id, name));
	}
	
	public void commit() throws FileNotFoundException{
		PrintWriter out = new PrintWriter(DB.db_players_address);
		out.println(db_players_id);
		int size = db_players.size();
		int counter = 0;
		if(size != 0){
			for(Player value: db_players.values()){
				out.print(value.get_id() + " " + value.get_account_id() + " " + value.get_name());
				counter++;
				if(counter < size)
					out.println();
			}
		}
		
		out.close();
		System.out.println("DB:players.db saved");
	}
	
	protected void finalize() throws FileNotFoundException{
		commit();
	}
}

class Player{
	private int player_id;
	private int account_id;
	private String player_name;
	
	Player(int player_id, int account_id, String player_name){
		this.player_id = player_id;
		this.account_id = account_id;
		this.player_name = player_name;
	}
	public int get_account_id(){
		return this.account_id;
	}
	public int get_id(){
		return this.player_id;
	}
	public String get_name(){
		return this.player_name;
	}
}