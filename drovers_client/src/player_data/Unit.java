package player_data;

public class Unit{
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