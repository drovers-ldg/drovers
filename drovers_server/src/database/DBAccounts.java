package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBAccounts {
	protected static Connection connect;
	protected static Statement statement;
	protected static ResultSet result;
	
	DBAccounts() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		
		Properties setup = new Properties();
		setup.put("characterEncoding","UTF8");
		setup.put("user", "root");
		setup.put("password", "root");
		
		connect = DriverManager.getConnection("jdbc:mysql://localhost/accounts", setup);
		
		readTable();
	}
	
	private static void readTable() throws SQLException{
		Statement statement = connect.createStatement();
		
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
		if(connect != null)
			connect.close();
		if(statement != null)
			statement.close();
		if(result != null)
			result.close();
	}
}