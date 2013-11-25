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
	
	// SQMove
	public static void moveUp() throws IOException{
		new Message(Message.Type.SQMOVEUP).send(out);
	}
	public static void moveDown() throws IOException{
		new Message(Message.Type.SQMOVEDOWN).send(out);
	}
	public static void moveLeft() throws IOException{
		new Message(Message.Type.SQMOVELEFT).send(out);
	}
	public static void moveRight() throws IOException{
		new Message(Message.Type.SQMOVERIGHT).send(out);
	}
	public static void moveUpLeft() throws IOException{
		new Message(Message.Type.SQMOVEUPLEFT).send(out);
	}
	public static void moveUpRight() throws IOException{
		new Message(Message.Type.SQMOVEUPRIGHT).send(out);
	}
	public static void moveDownLeft() throws IOException{
		new Message(Message.Type.SQMOVEDOWNLEFT).send(out);
	}
	public static void moveDownRight() throws IOException{
		new Message(Message.Type.SQMOVEDOWNRIGHT).send(out);
	}
}