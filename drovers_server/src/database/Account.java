package database;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Account implements Externalizable{
	
	private static final long serialVersionUID = 201311201743L;
	
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
	public String mapId;
	
	// army
	public int unitsId;
	
	
	// SQL: id, accountName, accountPassword, gm, online, player_name, map_id, units_id, thorium, metal, money 
	Account(int id, 
			String accountName,
			String accountPassword, 
			int gm, 
			String playerName, 
			String mapId, 
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


	@Override
	public void readExternal(ObjectInput arg0) throws IOException, ClassNotFoundException {
		// void
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(playerName);
		out.writeInt(gm);
		out.writeInt(thorium);
		out.writeInt(metal);
		out.writeInt(money);
		out.flush();
	}
}