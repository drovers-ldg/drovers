package unit;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Unit implements Externalizable{
	private static final long serialVersionUID = 201312012207L;
	// Personal
	public int id;
	public int playerId;
	public String name;
	
	// GPS
	public int areaX;
	public int areaY;
	
	// Code
	public int codeId;
	
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
	
	public Unit(int id, int playerId, int areaX, int areaY, String type){
		this.id = id;
		this.playerId = playerId;
		this.areaX = areaX;
		this.areaY = areaY;
		this.codeId = this.id;
		
		this.type = type;
		
		switch(type){
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
	public void readExternal(ObjectInput arg0) throws IOException, ClassNotFoundException {
		// TODO Auto-generated method stub
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeUTF(this.name);
		System.out.println(this.name);
		out.writeUTF(this.type);
		System.out.println(this.type);
		out.writeInt(this.areaX);
		System.out.println(this.areaX);
		out.writeInt(this.areaY);
		System.out.println(this.areaY);
		out.writeInt(this.hp);
		System.out.println(this.hp);
		System.out.println("Sending " + this.id + " done");
		out.flush();
	}
}