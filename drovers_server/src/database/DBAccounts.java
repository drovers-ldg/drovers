package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DBAccounts {
	protected static Statement statement;
	protected static ResultSet result;
	public static HashMap<Integer, Account> map;
	
	DBAccounts() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		map = new HashMap<Integer, Account>();
		readTable();
	}
	
	protected static void readTable() throws SQLException{
		Statement statement = DataBase.connectionAccounts.createStatement();
		
		String sql = "SELECT * FROM accounts";
		result = statement.executeQuery(sql);
		
		while (result.next()) {
			map.put(result.getInt("id"), new Account(result.getInt("id"),
													 result.getString("account_name"), 
													 result.getString("account_password"), 
													 result.getInt("gm")));
		}
		System.out.println("DB.Accounts loaded;");
	}
	
	protected void finalize() throws SQLException{
		if(statement != null)
			statement.close();
		if(result != null)
			result.close();
	}
	
	public static int searchId(String accountName){
		for(Account item: map.values()){
			if(item.accountName.equals(accountName)){
				return item.id;
			}
		}
		return -1; // negative result
	}
	
	public static boolean comparePassword(int id, String password){
		return map.get(id).accountPassword.equals(password);
	}
	
	public static void connect(int accountId){
		map.get(accountId).online = true;
	}

	public static void disconnect(int accountId) {
		System.out.println("DISCONNECT");
		map.get(accountId).online = false;
	}

	public static void showAllAccounts() {
		for(Account item: map.values()){
			System.out.println("id:"+item.id+"\tacc:"+item.accountName+"\tgm:"+item.gm+"\tonline:"+item.online);
		}
	}

	public static boolean addAccount(String accountName, String accountPassword) {
		if(DBAccounts.searchId(accountName) == -1){
			return false;
		}
		else{
			map.put(map.size()+1, new Account(map.size()+1, accountName, accountPassword, 0));
			return true;
		}
	}
}
