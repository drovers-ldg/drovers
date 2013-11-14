package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import unit.Unit;

class DBUnits{
	protected static Statement statement;
	protected static ResultSet result;
	public static HashMap<Integer, Unit> map;
		
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
		System.out.println("DB.Units loaded;");
	}
	
	protected void finalize() throws SQLException{
		if(statement != null)
			statement.close();
		if(result != null)
			result.close();
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