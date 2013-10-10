package server;

import java.net.InetAddress;

class Event
{
	int id;
	int client_port;
	InetAddress client_addres;
	String data;
	
	Event()
	{
		this.id = Drovers_Server.event_id++;
		this.client_addres = null;
		this.client_port = 0;
		this.data = null;
	}
	
	Event(InetAddress client_addres, int client_port, String data)
	{
		this.id = Drovers_Server.event_id++;
		this.client_addres = client_addres;
		this.client_port = client_port;
		this.data = data;
	}
}