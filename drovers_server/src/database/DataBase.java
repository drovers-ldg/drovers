package database;

import java.sql.SQLException;

public class DataBase{
	
	public DBAccounts accounts;
	public DBPlayers players;
	
	public DataBase() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException{
		accounts = new DBAccounts();
		players = new DBPlayers();
	}
}