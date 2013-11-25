package Logic;

import java.io.IOException;

import messages.Message;
import server.Server;
import database.DBAccounts;

public class SQMove{
	
	public static void moveUp(int clientId) throws IOException{
		int playerX = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapX;
		int playerY = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapY;
		if((playerY - 1 > 0) && World.WorldMap.map[playerX][playerY-1].movable){
			DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapY--;
		}
		Server.client_list.get(clientId).send(Message.Type.SQMOVEUP, null);
	}
	
	public static void moveDown(int clientId) throws IOException{	
		int playerX = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapX;
		int playerY = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapY;
		if((playerY + 1 < World.WorldMap.sizeY) && World.WorldMap.map[playerX][playerY+1].movable){
			DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapY++;
		}
		Server.client_list.get(clientId).send(Message.Type.SQMOVEDOWN, null);
	}
	
	public static void moveRight(int clientId) throws IOException{
		int playerX = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapX;
		int playerY = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapY;
		if((playerX + 1 < World.WorldMap.sizeX) && World.WorldMap.map[playerX+1][playerY].movable){
			DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapX++;
		}
		Server.client_list.get(clientId).send(Message.Type.SQMOVERIGHT, null);
	}
	
	public static void moveLeft(int clientId) throws IOException{
		int playerX = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapX;
		int playerY = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapY;
		if(playerX - 1 > 0 && World.WorldMap.map[playerX-1][playerY].movable){
			DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapX--;
		}
		Server.client_list.get(clientId).send(Message.Type.SQMOVELEFT, null);
	}

	public static void moveUpLeft(int clientId) throws IOException {
		int playerX = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapX;
		int playerY = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapY;
		if((playerX - 1 > 0) && (playerY - 1 > 0) && World.WorldMap.map[playerX-1][playerY-1].movable){
			DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapX--;
			DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapY--;
		}
		Server.client_list.get(clientId).send(Message.Type.SQMOVEUPLEFT, null);
	}

	public static void moveUpRight(int clientId) throws IOException {
		int playerX = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapX;
		int playerY = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapY;
		if((playerX + 1 < World.WorldMap.sizeX) && (playerY - 1 > 0) && World.WorldMap.map[playerX+1][playerY-1].movable){
			DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapX++;
			DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapY--;
		}
		Server.client_list.get(clientId).send(Message.Type.SQMOVEUPRIGHT, null);
	}

	public static void moveDownLeft(int clientId) throws IOException {
		int playerX = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapX;
		int playerY = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapY;
		if((playerX - 1 > 0) && (playerY + 1 <  World.WorldMap.sizeY) && World.WorldMap.map[playerX-1][playerY+1].movable){
			DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapX--;
			DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapY++;
		}
		Server.client_list.get(clientId).send(Message.Type.SQMOVEDOWNLEFT, null);
	}

	public static void moveDownRight(int clientId) throws IOException {
		int playerX = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapX;
		int playerY = DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapY;
		if((playerX + 1 < World.WorldMap.sizeX) && (playerY + 1 <  World.WorldMap.sizeY) && World.WorldMap.map[playerX+1][playerY+1].movable){
			DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapX++;
			DBAccounts.map.get(Server.client_list.get(clientId).get_account_id()).mapY++;
		}
		Server.client_list.get(clientId).send(Message.Type.SQMOVEDOWNRIGHT, null);
	}
}