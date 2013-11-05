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
		Game.server_msg = "CHAT:" + msg;
		new Message(Message.Type.CHAT, msg).send(out);
	}
	
	public static void logout() throws IOException{
		new Message(Message.Type.LOGOUT).send(out);
	}
	
	public static void login(String account, String password) throws IOException{
		new Message(Message.Type.LOGIN, account+":"+password).send(out);
	}
}