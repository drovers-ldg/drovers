package messages;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Message implements Serializable{

	private static final long serialVersionUID = 1L;
	
	public int type;
	public int prefix;
	public String data;
	
	public Message(){
		this.type = -1;
		this.prefix = -1;
		this.data = null;
	}
	public Message(int type){
		this();
		this.type = type;
	}
	public Message(int type, String data){
		this(type);
		this.data = data;
	}
	
	public Message(int type, String data, int prefix){
		this(type, data);
		this.prefix = prefix;
	}
	
	public void send(ObjectOutputStream out) throws IOException{
		out.writeObject(this);
	}
	
	public static Message read(ObjectInputStream in) throws ClassNotFoundException, IOException{
		return (Message)in.readObject();
	}
}