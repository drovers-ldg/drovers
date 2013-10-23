package server;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Scanner;

class DB{
	public static DB_Accounts db_accounts;
	final public static String db_accounts_address = "db\\accoutns.db";
	
	DB() throws IOException{
		db_accounts = new DB_Accounts();
	}
}

class DB_Accounts{
	private int db_accounts_id;
	private HashMap<Integer, Account> db_accounts;	
	
	DB_Accounts() throws IOException{
		db_accounts = new HashMap<Integer, Account>();
		read_account_table();
	}
	
	private void read_account_table() throws IOException{
		Scanner in = new Scanner(new File(DB.db_accounts_address));
		
		if(in.hasNextLine()){
			this.db_accounts_id = in.nextInt();
			
			while(in.hasNextLine()){
				int id = in.nextInt();
				db_accounts.put(id, new Account(id, in.next(), in.next()));
			}
		}
		else{
			this.db_accounts_id = 0;
			System.out.println("Loading clead DB:accounts.db");
		}
		
		in.close();
		System.out.println("Complete loading DB:accounts.db");
		
		// Debug
		if(Server.debug){
			for(Account value: db_accounts.values()){
				System.out.println(value.get_id() + " " + value.get_name() + " " + value.get_password());
			}
			System.out.println("Next ID: " + this.db_accounts_id);
		}
	}
	
	private void add_account(String name, String password){
		int id = this.db_accounts_id++;
		db_accounts.put(id, new Account(id, name, password));
	}
	
	private void commit() throws FileNotFoundException, UnsupportedEncodingException{
		PrintWriter out = new PrintWriter(DB.db_accounts_address);
		out.println(db_accounts_id);
		for(Account value: db_accounts.values()){
			out.println(value.get_id() + " " + value.get_name() + " " + value.get_password());
		}
		out.close();
		System.out.println("DB:accounts.db saved");
	}
	
	protected void finalize() throws FileNotFoundException, UnsupportedEncodingException{
		commit();
	}
}
class Account{
	private int account_id;
	private String account_name;
	private String account_password;
	
	Account(int account_id, String account_name, String account_password){
		this.account_id = account_id;
		this.account_name = account_name;
		this.account_password = account_password;
	}
	
	public int get_id() {
		return this.account_id;
	}
	
	public String get_name(){
		return this.account_name;
	}
	
	public String get_password(){
		return this.account_password;
	}
	
	public void set_password(String new_password){
		this.account_password = new_password;
	}
	
	public boolean compare_password(String password){
		if(this.account_password.compareTo(password) == 0)
			return true;
		else
			return false;
	}
}