package server;

public class Event{
	int id;
	int client_port;
	int client_id;
	String data;
	
	Event(){
		this.id = Server.event_id++;
		this.client_id = -1;
		this.client_port = 0;
		this.data = null;
	}
	
	Event(int client_id, int client_port, String data){
		this.id = Server.event_id++;
		this.client_id = client_id;
		this.client_port = client_port;
		this.data = data;
	}
}