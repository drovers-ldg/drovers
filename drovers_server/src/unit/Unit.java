package unit;

public class Unit{
	// Personal
	public int id;
	public int playerId;
	public String name;
	
	// GPS
	public int mapX;
	public int mapY;
	public int areaX;
	public int areaY;
	
	// Devices
	public int bodyId;
	
	// Code
	public int codeId;
	
	public Unit(int id, int playerId, String name, int mapX, int mapY, int areaX, int areaY, int bodyId, int codeId){
		this.id = id;
		this.playerId = playerId;
		this.name = name;
		this.mapX = mapX;
		this.mapY = mapY;
		this.areaX = areaX;
		this.areaY = areaY;
		this.bodyId = bodyId;
		this.codeId = codeId;
	}
}