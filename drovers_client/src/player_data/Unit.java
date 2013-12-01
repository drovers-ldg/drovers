package player_data;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import client.Chat;

public class Unit implements Externalizable{
	private static final long serialVersionUID = 201312012207L;
	public String name;
	// GPS
	public int areaX;
	public int areaY;
	
	// Code
	transient public int codeId;
	
	// Combat data
	public int hp;
	public int hpMax;
	public int speed;
	public int armor;
	// Attack
	public int damage;
	public int attackSpeed;
	// Modifer
	public String type;
	
	public Unit(){
	}

	public void paramsUpdate() {
		switch(this.type){
			case "sturm":
				this.name = "Sturm";
				this.hp = 100;
				this.hpMax = 100;
				this.armor = 10;
				this.speed = 5;
				this.attackSpeed = 3;
				this.damage = 5;
			break;
		case "scout":
				this.name = "Scout";
				this.hp = 50;
				this.hpMax = 50;
				this.armor = 2;
				this.speed = 8;
				this.attackSpeed = 9;
				this.damage = 1;
			break;
		case "art":
				this.name = "Art";
				this.hp = 70;
				this.hpMax = 70;
				this.armor = 2;
				this.speed = 3;
				this.attackSpeed = 1;
				this.damage = 35;
			break;
		}
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException,ClassNotFoundException {
		this.name = in.readUTF();
		Chat.add_to_msg_log(name);
		this.type = in.readUTF();
		Chat.add_to_msg_log(type);
		this.areaX = in.readInt();
		Chat.add_to_msg_log(String.valueOf(areaX));
		this.areaY = in.readInt();
		Chat.add_to_msg_log(String.valueOf(areaY));
		this.hp = in.readInt();
		Chat.add_to_msg_log(String.valueOf(hp));
		this.paramsUpdate();
		Chat.add_to_msg_log("Recived: " + this.name + " " + this.type);
	}

	@Override
	public void writeExternal(ObjectOutput arg0) throws IOException {
		// TODO Auto-generated method stub	
	}
}