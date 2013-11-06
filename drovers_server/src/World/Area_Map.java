package World;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Area_Map implements Serializable{
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
				map[i][j] = 0;
			}
		}
	}
	public void send(ObjectOutputStream out) throws IOException{
		if(out != null)
			out.writeObject(this);
	}
}