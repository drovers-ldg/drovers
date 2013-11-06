package World;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;

public class Area_Map implements Externalizable{
	private static final long serialVersionUID = 61120131305L;
	
	public int size_x;
	public int size_y;
	public int [][] map;
	
	public Area_Map(){
		this.size_x = 0;
		this.size_y = 0;
		this.map = null;
	}
	public Area_Map(int size_x, int size_y){
		this.size_x = size_x;
		this.size_y = size_y;
		map = new int[size_x][size_y];
		
		for(int i = 0; i < size_x; ++i){
			for(int j = 0; j < size_y; ++j){
				map[i][j] = 1;
			}
		}
	}
	
	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// void
	}
	
	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(this.size_x);
		out.writeInt(this.size_y);
		for(int i = 0; i < size_x; ++i){
			for(int j = 0; j < size_y; ++j){
				out.writeInt(this.map[i][j]);
			}
		}
		out.flush();
	}
}