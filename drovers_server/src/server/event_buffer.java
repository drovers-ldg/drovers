package server;

import java.util.ArrayList;

class Event_Buffer
{
	final static int lenght = 256;
	ArrayList<Event> event_buff;
	
	Event_Buffer()
	{
		event_buff = new ArrayList<Event>();
	}
}