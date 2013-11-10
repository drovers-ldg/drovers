package database;

import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.Scanner;

public class DataBase{
	
	// Connections
	public static Connection connectionAccounts;
	public static DBAccounts accounts;
	public static DBPlayers players;
	
	// Setup
	public static String mysqlAddress;
	public static String mysqlPort;
	public static String mysqlUser;
	public static String mysqlPassword;
	public static String mysqlDBAccounts;
	
	public DataBase() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException, FileNotFoundException{
		readConfig();
		
		Properties setup = new Properties();
		setup.put("characterEncoding","UTF8");
		setup.put("user", mysqlUser);
		setup.put("password", mysqlPassword);
		String url = "jdbc:mysql://"+mysqlAddress+":"+mysqlPort+"/"+mysqlDBAccounts;
		try{
			connectionAccounts = DriverManager.getConnection(url, setup);
			// add other connections here
		}
		catch(Exception e){
			System.out.println("MySQL connection error. Check MySQL server status.");
		}
		
		accounts = new DBAccounts();
		players = new DBPlayers();
	}
	
	protected void readConfig() throws FileNotFoundException{
		Scanner in = new Scanner(new File("config\\server.conf"));
		while(in.hasNext()){
			String str = in.next();
			String data = in.nextLine();
			data = data.trim();
			switch(str){
				case "#MySQL_Address:":
					mysqlAddress = data;
					System.out.println("mysqlAddress:" + mysqlAddress);
					break;
				case "#MySQL_Port:":
					mysqlPort = data;
					System.out.println("mysqlAddress:" + mysqlPort);
					break;
				case "#MySQL_User:":
					mysqlUser = data;
					System.out.println("mysqlUser:" + mysqlUser);
					break;
				case "#MySQL_Password:":
					mysqlPassword = data;
					System.out.println("mysqlPassword:" + mysqlPassword);
					break;
				case "#MySQL_Accounts_DB:":
					mysqlDBAccounts = data;
					System.out.println("mysqlDBAccounts:" + mysqlDBAccounts);
					break;
				default:
					break;
			}
		}
		in.close();
	}
	
	protected void finalize() throws SQLException{
		if(connectionAccounts != null){
			connectionAccounts.close();
		}
	}
}