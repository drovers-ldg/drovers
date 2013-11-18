package database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import unit.Unit;

class DBUnits{
	protected static Statement statement;
	protected static ResultSet result;
	public static HashMap<Integer, Unit> map;
		
	// SQL
	protected final static String sqlInsertUnit = "INSERT INTO units (player_id, name, body_id, code_id) VALUES (?, ?, ?, ?)";
	protected final static String sqlDeleteUnit = "DELETE FROM units WHERE id = ?";
	
	DBUnits() throws SQLException{
		map = new HashMap<Integer, Unit>();
		readTable();
	}
	
	private static void readTable() throws SQLException{
		// ID, playerId, name, mapId, x, y, z, bodyId, codeId
		Statement statement = DataBase.connectionUnits.createStatement();
		
		String sql = "SELECT * FROM units";
		result = statement.executeQuery(sql);
		
		while(result.next()){
			map.put(result.getInt("id"), new Unit(result.getInt("id"),
												  result.getInt("player_id"),
												  result.getString("name"),
												  result.getInt("map_id"),
												  result.getInt("x"),
												  result.getInt("y"),
												  result.getInt("z"),
												  result.getInt("body_id"),
												  result.getInt("code_id")));
		}
		System.out.println("DB.Units loaded. Fields: " + map.size());
	}
	
	protected void finalize() throws SQLException{
		if(statement != null)
			statement.close();
		if(result != null)
			result.close();
	}
	
	public static void addUnit(int playerId, String name, int mapId, int bodyId, int codeId) throws SQLException{
		PreparedStatement statement = DataBase.connectionUnits.prepareStatement(sqlInsertUnit);
		// player_id
		statement.setInt(1, playerId);
		statement.setString(2, name);
		statement.setInt(3, bodyId);
		statement.setInt(4, codeId);
		statement.execute();
		
		// Add to memory
		// int id, int playerId, String name, int mapId, int x, int y, int z, int bodyId, int codeId
		map.put(map.size()+1, new Unit(map.size()+1, playerId, name, 0, 0, 0, 0, bodyId, codeId));
		System.out.println("Unit created;\n");
	}
	
	public static void deleteItem(int id) throws SQLException{
		PreparedStatement statement = DataBase.connectionAccounts.prepareStatement(sqlDeleteUnit);
		statement.setInt(1, id);
		statement.execute();
		map.remove(id);
		System.out.println("Unit deleted;\n");
	}
	
	public static void showAllUnits(){
		for(Unit item: map.values()){
			System.out.println("id: " + item.id 
					+ "\taccount_id: " + item.playerId 
					+ "\tname:" + item.name 
					+ "\tmap:" + item.mapId + "." + item.x + "." + item.y + "." + item.z
					+ "\tbody_id: " + item.bodyId 
					+ "\tcode_id: " + item.codeId);
		}
	}
}