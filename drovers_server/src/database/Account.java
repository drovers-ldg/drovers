package database;

public class Account{
	public int id;
	public String accountName;
	public String accountPassword;
	public String playerName;
	public int gm;
	public boolean online;
	
	// recources
	public int thorium;
	public int metal;
	public int money;
	
	// coords
	public int mapId;
	
	// army
	public int unitsId;
	
	
	// SQL: id, accountName, accountPassword, gm, online, player_name, map_id, units_id, thorium, metal, money 
	Account(int id, 
			String accountName,
			String accountPassword, 
			int gm, 
			String playerName, 
			int mapId, 
			int unitsId, 
			int thorium,
			int metal, 
			int money){
		this.id = id;
		this.accountName = accountName;
		this.accountPassword = accountPassword;
		this.gm = gm;
		this.online = false;
		this.playerName = playerName;
		this.mapId = mapId;
		this.unitsId = unitsId;
		this.thorium = thorium;
		this.metal = metal;
		this.money = money;
	}
}