package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DBPlayers {
	protected static Connection connect;
	protected static Statement statement;
	protected static ResultSet result;
	
	DBPlayers() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
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
		
		String sql = "SELECT * FROM players";
		result = statement.executeQuery(sql);
		
		System.out.println("DB.Players");
		while (result.next()) {
	       	int id = result.getInt("id");
			int account_id = result.getInt("account_id");
			String player_name = result.getString("player_name");
			System.out.println("id: " + id + "\tacc_id: " + account_id + "\tname: " + player_name);
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