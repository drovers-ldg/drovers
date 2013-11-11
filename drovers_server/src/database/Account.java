package database;

public class Account{
	public int id;
	public String accountName;
	public String accountPassword;
	public int gm;
	public boolean online;
	
	Account(int id, String accountName, String accountPassword, int gm){
		this.id = id;
		this.accountName = accountName;
		this.accountPassword = accountPassword;
		this.gm = gm;
		this.online = false;
	}
}