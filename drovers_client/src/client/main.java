package client;

//main client file

class Drovers_Client
{
	public static void main(String [] args) throws Exception
	{
		Game.addres = "localhost";		
		Game.login = "admin";
		Game.password = "test";
		new Game();
	}
}