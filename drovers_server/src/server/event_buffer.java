package server;

import java.util.Vector;

class Event_Buffer{
	public Vector<Event> event_buff;
	
	Event_Buffer(){
		event_buff = new Vector<Event>();
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
	public Event get(int index){
		return this.event_buff.get(index);
	}
	public void delete(int index){
		this.event_buff.remove(index);
	}
}