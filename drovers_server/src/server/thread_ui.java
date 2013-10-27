package server;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

class Server_UI extends Thread{
	
	Server_UI(){
		this.start();
	}
	
	public void run(){
		Scanner in = new Scanner(System.in);
		String command;
		
		while(Server.is_runing){
			System.out.print(">");
			command = in.nextLine();
			
			try {
				command_process(command);
			} catch (FileNotFoundException | UnsupportedEncodingException e) {
				System.out.println("Thread_ui err: 1 - invalid command");
			}
		}
		
		in.close();
	}
	
	private void command_process(String command) throws FileNotFoundException, UnsupportedEncodingException{
		if(command.matches("^help$")){
			System.out.println("========HELP=========");
			System.out.println(">shutdown");
			System.out.println(">debug [on\\off]");
			System.out.println(">account add [name] [password] [password]");
			System.out.println(">save [all\\accounts\\players]");
			System.out.println(">connections");
			System.out.println(">show accounts");
			System.out.println("=====================");
		}
		else if(command.contains("account") && !command.matches("^save accounts$") && !command.matches("^show accounts$")){
			if(command.matches("^account add [a-zA-Z0-9]+ [a-zA-Z0-9]+ [a-zA-Z0-9]+$")){
				String [] new_account = command.split(" ");
				
				// check password identity
				if(new_account[3].equals(new_account[4])){
					boolean add_result = DB.db_accounts.add_account(new_account[2], new_account[4]);
					if(add_result)
						System.out.println("Account \"" + new_account[2] + "\" is secessful created");
					else
						System.out.println("Account \"" + new_account[2] + "\" is already used");
				}
				else{
					System.out.println("passwords are different");
				}
			}
			else{
				System.out.println(">account add [name] [password] [password]");
			}
		}
		else if(command.contains("save")){
			if(command.matches("^save all$")){
				save_world();
			}
			else if(command.matches("^save accounts$")){
				DB.db_accounts.commit();
			}
			else if(command.matches("^save players$")){
				DB.db_players.commit();
			}
			else{
				System.out.println(">save [all\\accounts\\players]");
			}
		}
		else if(command.contains("debug")){
			if(command.matches("^debug on$")){
				Server.debug = true;
				System.out.println(">debug mode on");
			}
			else if(command.matches("^debug off$")){
				Server.debug = false;
				System.out.println(">debug mode off");
			}
			else{
				System.out.println(">debug [on\\off]");
			}
		}
		else if(command.matches("^shutdown$")){
			Server.is_runing = false;
			save_world();
			System.exit(0);
		}
		else if(command.matches("^connections$")){
			show_all_connections();
		}
		else if(command.matches("^show accounts$")){
			show_all_accounts();
		}
	}
	
	private void save_world() throws FileNotFoundException, UnsupportedEncodingException{
		DB.db_accounts.commit();
		DB.db_players.commit();
	}
	private void show_all_connections(){
		System.out.println("Connection list: " + Server.player_list.size());
		System.out.println("client_id | account_id");
		for(Client client: Server.player_list.values()){
			System.out.println(client.get_id() + " | " + client.get_account_id());
		}
		System.out.println();
	}
	private void show_all_accounts(){
		DB.db_accounts.show_all_accounts();
	}
}