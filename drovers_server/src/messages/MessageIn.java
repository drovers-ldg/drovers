package messages;

public class MessageIn extends Message{
	private static final long serialVersionUID = 1L;
	
	public int client_id;
	
	public MessageIn(Message msg, int client_id){
		super(msg.type, msg.data, msg.prefix);
		this.client_id = client_id;
	}
}