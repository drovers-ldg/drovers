package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

public class DBPlayers {
	protected static Statement statement;
	protected static ResultSet result;
	public static HashMap<Integer, Player> map;
	
	// SQL
	protected static String sqlInsertPlayer = "INSERT INTO players (account_id, player_name) VALUES (?, ?)";
	
	DBPlayers() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		map = new HashMap<Integer, Player>();
		readTable();
	}
	
	private static void readTable() throws SQLException{
		Statement statement = DataBase.connectionAccounts.createStatement();
		
		String sql = "SELECT * FROM players";
		result = statement.executeQuery(sql);
		
		while (result.next()) {
			map.put(result.getInt("id"), new Player(result.getInt("id"),
													result.getInt("account_id"),
													result.getString("player_name")));
		}
		System.out.println("DB.Players loaded;");
	}
	
	protected void finalize() throws SQLException{
		if(statement != null)
			statement.close();
		if(result != null)
			result.close();
	}
	
	public static int searchId(String playerName){
		for(Player item: map.values()){
			if(item.playerName.equals(playerName))
				return item.id;
		}
		return -1;
	}

	public static boolean addPlayer(int accountId, String playerName) throws SQLException {
		for(Player item: map.values()){
			if(item.playerName.equals(playerName)){
				return false;
			}
		}
		map.put(map.size()+1, new Player(map.size()+1, accountId, playerName));
		
		// Add account to MySQL
		PreparedStatement statement = DataBase.connectionAccounts.prepareStatement(sqlInsertPlayer);
		statement.setInt(1, accountId);
		statement.setString(2, playerName);
		statement.execute();
		
		return true;
	}
}

class Player{
	public int id;
	public int accountId;
	public String playerName;
	
	Player(int id, int accountId, String playerName){
		this.accountId = accountId;
		this.playerName = playerName;
	}
}