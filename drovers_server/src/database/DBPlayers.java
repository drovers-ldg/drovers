package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBPlayers {
	protected static Statement statement;
	protected static ResultSet result;
	
	DBPlayers() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		readTable();
	}
	
	private static void readTable() throws SQLException{
		Statement statement = DataBase.connectionAccounts.createStatement();
		
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
		if(statement != null)
			statement.close();
		if(result != null)
			result.close();
	}
}