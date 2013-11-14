package unit;

public class Unit{
	// Personal
	public int id;
	public int playerId;
	public String name;
	
	// GPS
	public int mapId;
	public int x;
	public int y;
	public int z;
	
	// Devices
	public int bodyId;
	
	// Code
	public int codeId;
	
	public Unit(int id, int playerId, String name, int mapId, int x, int y, int z, int bodyId, int codeId){
		this.id = id;
		this.playerId = playerId;
		this.name = name;
		this.mapId = mapId;
		this.x = x;
		this.y = y;
		this.z = z;
		this.bodyId = bodyId;
		this.codeId = codeId;
	}
}