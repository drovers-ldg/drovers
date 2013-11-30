package player_data;

import java.io.Serializable;

public class Unit implements Serializable{
	private static final long serialVersionUID = 201311301514L;
	
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
}