package client;

import java.io.IOException;
import java.io.ObjectOutputStream;
import messages.Message;


public class Sender{
	protected static ObjectOutputStream out;
	
	public Sender(ObjectOutputStream out) throws IOException{
		Sender.out = out;
	}
	public static void send(String msg) throws IOException{
		new Message(Message.Type.CHAT, msg).send(out);
	}
	public static void logout() throws IOException{
		new Message(Message.Type.LOGOUT).send(out);
	}
	public static void login(String account, String password) throws IOException{
		new Message(Message.Type.LOGIN, account+":"+password).send(out);
	}
	public static void updateMap() throws IOException{
		new Message(Message.Type.UPDATEAREA).send(out);
	}
	public static void updateWorld() throws IOException{
		new Message(Message.Type.UPDATEWORLD).send(out);
	}	
	public static void updatePlayer() throws IOException{
		new Message(Message.Type.UPDATEPLAYER).send(out);
	}
}