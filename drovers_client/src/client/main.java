package client;

import java.util.Scanner;
//main client file

class Drovers_Client
{
	public static void main(String [] args) throws Exception
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Server addres: ");
		String addres = in.nextLine();
		System.out.print("Login: ");
		String login = in.nextLine();
		System.out.print("Password: ");
		String password = in.nextLine();
		in.close();
		
		new Game(addres, login, password);
		Game.is_runing = false;
	}
}