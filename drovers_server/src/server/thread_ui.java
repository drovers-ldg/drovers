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
				e.printStackTrace();
			}
		}
		
		in.close();
	}
	
	private void command_process(String command) throws FileNotFoundException, UnsupportedEncodingException{
		String [] in_command = command.split(" ");
		
		switch(in_command[0])
		{
			//-----------------------
			case "help":
				System.out.println("========HELP=========");
				System.out.println(">shutdown");
				System.out.println(">debug [on\\off]");
				System.out.println(">account add [name] [password] [password]");
				System.out.println("=====================");
				break;
			//-----------------------			
			case "account":
				if(in_command[1].compareTo("add") == 0){
					if(in_command[2] != null && in_command[3] != null && in_command[4] != null
					&& (in_command[3].compareTo(in_command[4]) == 0)){
						DB.db_accounts.add_account(in_command[2], in_command[4]);
						System.out.println("Account \"" + in_command[2] + "\" is secessful created");
					}
					else{
						System.out.println("Error in entering params.");
						System.out.println(">account add [name] [password] [password]");
					}
				}
				break;
			//-----------------------
			case "debug":
				if(in_command[1].compareTo("on") == 0){
					Server.debug = true;
				}
				if(in_command[1].compareTo("off") == 0){
					Server.debug = false;
				}
				break;
			//-----------------------
			case "shutdown":
				Server.is_runing = false;
				DB.db_accounts.finalize();
				System.exit(0);
				break;
			//----------------------
			default:{
				System.out.println("Unknown command");
			}
		}
	}
}