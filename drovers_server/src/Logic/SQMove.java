package Logic;

import java.io.IOException;

import server.Server;
import database.DBAccounts;

public class SQMove{
	
	public static void moveUp(int clientId) throws IOException{
		System.out.println("Move up:" + clientId);
		
		int playerX = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapX;
		int playerY = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapY;
		
		if((playerY - 1 > 0) && World.WorldMap.map[playerX][playerY-1].movable){
			DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapY--;
		}
		Server.client_list.get(clientId).sendPlayer();
	}
	
	public static void moveDown(int clientId) throws IOException{
		System.out.println("Move down:" + clientId);
		
		int playerX = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapX;
		int playerY = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapY;
		
		if((playerY + 1 < World.WorldMap.sizeY) && World.WorldMap.map[playerX][playerY+1].movable){
			DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapY++;
		}
		
		Server.client_list.get(clientId).sendPlayer();
	}
	
	public static void moveRight(int clientId){
		System.out.println("Move right:" + clientId);
	}
	
	public static void moveLeft(int clientId){
		System.out.println("Move left:" + clientId);
	}

	public static void moveUpLeft(int clientId) {
		System.out.println("Move up-left:" + clientId);
	}

	public static void moveUpRight(int clientId) {
		System.out.println("Move up-right:" + clientId);
	}

	public static void moveDownLeft(int clientId) {
		System.out.println("Move down-left:" + clientId);
	}

	public static void moveDownRight(int clientId) {
		System.out.println("Move down-right:" + clientId);
	}
}