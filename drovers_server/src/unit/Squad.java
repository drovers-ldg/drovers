package unit;

import java.io.Serializable;
import java.sql.SQLException;

import database.DBSquads;
import database.DBUnits;

public class Squad implements Serializable{
	private static final long serialVersionUID = 201311301514L;
	
	public int id;
	public int accountId;
	
	public Unit unit1; 
	public Unit unit2;
	public Unit unit3;
	
	public Squad(int accountId) throws SQLException{
		id = DBSquads.map.size()+1;
		this.accountId = accountId;
		setDefaultSQ(accountId);
	}
	
	public Squad(int id, int accountId, int unitId1, int unitId2, int unitId3){
		this.id = id;
		this.accountId = accountId;
		
		this.unit1 = DBUnits.map.get(unitId1);
		this.unit2 = DBUnits.map.get(unitId2);
		this.unit2 = DBUnits.map.get(unitId3);
	}
	
	public void setDefaultSQ(int playerId) throws SQLException{
		this.unit1 = new Unit(DBUnits.map.size()+1, playerId, 0, 0, "sturm"); // sturm
		this.unit2 = new Unit(DBUnits.map.size()+2, playerId, 0, 0, "scout"); // scout-marker
		this.unit3 = new Unit(DBUnits.map.size()+3, playerId, 0, 0, "art");   // art
		
		DBUnits.addUnit(playerId, "sturm");
		DBUnits.addUnit(playerId, "scout");
		DBUnits.addUnit(playerId, "art");
		DBSquads.addSquad(this);
	}
}