package player_data;

import java.awt.Graphics;
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
		map = null;
	}
	
	public void rebuild_size(int size_x, int size_y){
		this.size_x = size_x;
		this.size_y = size_y;
		this.map = new int[size_x][size_y];
	}
	
	public void draw(Graphics g){
		for(int i = 0; i < this.size_x; ++i)
			for(int j = 0; j < this.size_y; ++j)
				g.drawImage(World.getTexture(map[i][j]), i*64, j*64, 64, 64, null);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		this.size_x = in.readInt();
		this.size_y = in.readInt();
		this.map = new int[this.size_x][this.size_y];
		
		for(int i = 0; i < this.size_x; ++i){
			for(int j = 0; j < this.size_y; ++j){
				this.map[i][j] = in.readInt();
			}
		}
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		//void	
	}
}