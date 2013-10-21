package server;

import java.util.ArrayList;

class Event_Buffer{
	final static int lenght = 64;
	private ArrayList<Event> event_buff;
	
	Event_Buffer(){
		event_buff = new ArrayList<Event>();
	}
	
	public void clear(){
		event_buff.clear();
	}
	
	public int size(){
		return this.event_buff.size();
	}
	
	public void add(Event event){
		this.event_buff.add(event);
	}
}