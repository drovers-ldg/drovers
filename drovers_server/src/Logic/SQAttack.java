package Logic;

import java.io.IOException;
import java.util.Set;
import java.util.Vector;

import server.Server;
import database.DBAccounts;

public class SQAttack{
	public static int battleIds = 0;
	
	public static void checkAndRunBattle(int clientId, int mapX, int mapY){
		Vector<Integer> playersEnemies = new Vector<Integer>();
		Set<Integer> keys = DBAccounts.map.keySet();
		for(Integer key: keys){
			if(DBAccounts.map.get(key).mapX == mapX && DBAccounts.map.get(key).mapY == mapY){
				playersEnemies.add(key);
			}
		}

		synchronized(Server.battlesList){
			int id = battleIds++;
			if(playersEnemies.size() != 0){
				// ID, mapX(to), mapY(to), mapX(from), mapY(from), begginerId, allEnemies
				Server.battlesList.put(id, new BattleThread(id, mapX, mapY, mapX, mapY+1, clientId, playersEnemies));
			}
		}
	}
	
	public static void attackUp(int clientId) {
		System.out.println("ClientID " + clientId + ", initiate battle");
		synchronized(DBAccounts.map){
			int mapX = -1;
			int mapY = -1;
			
			synchronized(Server.client_list){
				mapX = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapX;
				mapY = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapY - 1; // Up
				if(mapY >= 0){
					checkAndRunBattle(clientId, mapX, mapY);
				}
			}
		}		
	}

	public static void attackDown(int clientId) {
		System.out.println("ClientID " + clientId + ", initiate battle");
		synchronized(DBAccounts.map){
			int mapX = -1;
			int mapY = -1;
			
			synchronized(Server.client_list){
				mapX = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapX;
				mapY = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapY + 1; // Up
				if(mapY < World.WorldMap.sizeY){
					checkAndRunBattle(clientId, mapX, mapY);
				}
			}
		}
	}

	public static void attackLeft(int clientId) {
		System.out.println("ClientID " + clientId + ", initiate battle");
		synchronized(DBAccounts.map){
			int mapX = -1;
			int mapY = -1;
			
			synchronized(Server.client_list){
				mapX = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapX - 1;
				mapY = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapY; // Up
				if(mapX >= 0){
					checkAndRunBattle(clientId, mapX, mapY);
				}
			}
		}
	}
	
	public static void attackRight(int clientId) {
		System.out.println("ClientID " + clientId + ", initiate battle");
		synchronized(DBAccounts.map){
			int mapX = -1;
			int mapY = -1;
			
			synchronized(Server.client_list){
				mapX = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapX + 1;
				mapY = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapY; // Up
				if(mapX < World.WorldMap.sizeX){
					checkAndRunBattle(clientId, mapX, mapY);
				}
			}
		}
	}

	public static void attackUpLeft(int clientId) throws IOException {
		Server.client_list.get(clientId).send("[SERVER]", "Пока нельзя атаковать по диагонали.");
	}

	public static void attackUpRight(int clientId) throws IOException {
		Server.client_list.get(clientId).send("[SERVER]", "Пока нельзя атаковать по диагонали.");
	}

	public static void attackDownLeft(int clientId) throws IOException {
		Server.client_list.get(clientId).send("[SERVER]", "Пока нельзя атаковать по диагонали.");
	}

	public static void attackDownRight(int clientId) throws IOException {
		Server.client_list.get(clientId).send("[SERVER]", "Пока нельзя атаковать по диагонали.");
	}
}