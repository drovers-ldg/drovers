package messages;

import java.util.ArrayList;

public class MessagesBuffer{
	ArrayList<MessageIn> list;
	
	public MessagesBuffer(){
		list = new ArrayList<MessageIn>();
	}
	public void add(MessageIn msg){
		list.add(msg);
	}
	public void clear(){
		list.clear();
	}
}