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
	protected final static String sqlMoveUnitMap = "UPDATE units SET mapX = ?, mapY = ? WHERE id = ?";
	
	DBUnits() throws SQLException{
		map = new HashMap<Integer, Unit>();
		readTable();
	}
	
	private static void readTable() throws SQLException{
		// ID, playerId, name, mapX, mapY, areaX, areaY, bodyId, codeId
		Statement statement = DataBase.connectionUnits.createStatement();
		
		String sql = "SELECT * FROM units";
		result = statement.executeQuery(sql);
		
		while(result.next()){
			map.put(result.getInt("id"), new Unit(result.getInt("id"),
												  result.getInt("player_id"),
												  result.getString("name"),
												  result.getInt("mapX"),
												  result.getInt("mapY"),
												  result.getInt("areaX"),
												  result.getInt("areaY"),
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
		// ID, playerId, name, mapX, mapY, areaX, areaY, bodyId, codeId
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
					+ "\tmap:" + item.mapX + "." + item.mapY + "." + item.areaX + "." + item.areaY
					+ "\tbody_id: " + item.bodyId 
					+ "\tcode_id: " + item.codeId);
		}
	}
	
	public static void moveUnits(int mapX, int mapY, int id) throws SQLException{
		PreparedStatement statement = DataBase.connectionAccounts.prepareStatement(sqlMoveUnitMap);
		statement.setInt(1, mapX);
		statement.setInt(2, mapY);
		statement.setInt(3, id);
		statement.execute();
	}
}