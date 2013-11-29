package Logic;

import java.util.Vector;
import database.DBAccounts;

public class BattleThread extends Thread{
	public int id;
	public Vector<Integer> players;
	public int mapX1;
	public int mapY1;
	public int mapX2;
	public int mapY2;
	
	public BattleThread(int id, int mapX1, int mapY1, int mapX2, int mapY2, Integer playerIdBegginer, Vector<Integer> playerIdEnemies){
		this.id = id;
		this.mapX1 = mapX1;
		this.mapY1 = mapY1;
		this.mapX2 = mapX2;
		this.mapY2 = mapY2;
		
		this.players = new Vector<Integer>();
		this.addPlayer(playerIdBegginer);
		for(Integer playerId: playerIdEnemies){
			this.addPlayer(playerId);
		}
		this.run();
	}
	
	public void run(){
		try{
			System.out.println("Battle is begin: " + mapX1 + ", " + mapY1 + " <- " + mapX2 + ", " + mapY2 + " id: " + id);
		}
		finally{
			for(Integer playerId: players){
				this.exitPlayer(playerId);
			}
			this.interrupt();
		}
	}
	
	public void addPlayer(Integer playerId){
		this.players.add(playerId);
		DBAccounts.map.get(playerId).battleId = id;
		// Send MapsData
	}
	
	public void exitPlayer(Integer playerId){
		this.players.remove(playerId);
		DBAccounts.map.get(playerId).battleId = -1;
	}
}