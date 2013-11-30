package player_data;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;


public class Squad  implements Externalizable{
	private static final long serialVersionUID = 201311301514L;	
	public Unit unit1; 
	public Unit unit2;
	public Unit unit3;
	
	public Squad(){
	}
	
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		unit1 = readUnit(in);
		unit2 = readUnit(in);
		unit3 = readUnit(in);
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		//void
	}
	
	public Unit readUnit(ObjectInput in) throws IOException{
		Unit unit = new Unit();
		unit.name = in.readUTF();
		unit.areaX = in.readInt();
		unit.areaY = in.readInt();
		unit.hp = in.readInt();
		unit.hpMax = in.readInt();
		unit.speed = in.readInt();
		unit.armor = in.readInt();
		unit.damage = in.readInt();
		unit.attackSpeed = in.readInt();
		unit.type = in.readUTF();
		return unit;
	}
}