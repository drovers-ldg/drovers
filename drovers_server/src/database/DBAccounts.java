package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBAccounts {
	protected static Statement statement;
	protected static ResultSet result;
	
	DBAccounts() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		readTable();
	}
	
	private static void readTable() throws SQLException{
		Statement statement = DataBase.connectionAccounts.createStatement();
		
		String sql = "SELECT * FROM accounts";
		result = statement.executeQuery(sql);
		
		System.out.println("DB.Accounts");
		while (result.next()) {
	       	int id = result.getInt("id");
			String name = result.getString("account_name");
			String pass = result.getString("account_password");
			int gm = result.getInt("gm");
			boolean online = result.getBoolean("online");
			System.out.println("id: " + id + "\tname: " + name + "\tpass: " + pass + "\tgm: " + gm + "\tonline: " + online);
		}
	}
	
	protected void finalize() throws SQLException{
		if(statement != null)
			statement.close();
		if(result != null)
			result.close();
	}
}