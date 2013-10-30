package client;

import java.util.Scanner;
//main client file

class Drovers_Client
{
	public static void main(String [] args) throws Exception
	{
		Scanner in = new Scanner(System.in);
		System.out.print("Server addres: ");
		Game.addres = in.nextLine();
		System.out.print("Login: ");
		Game.login = in.nextLine();
		System.out.print("Password: ");
		Game.password = in.nextLine();
		in.close();

		new Game();
	}
}
