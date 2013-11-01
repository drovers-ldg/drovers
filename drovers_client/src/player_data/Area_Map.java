package player_data;

import java.awt.Graphics;

public class Area_Map{
	private int [][] map;
	private int size_x;
	private int size_y;
	
	public Area_Map(){
		this.size_x = 0;
		this.size_y = 0;
	}
	
	public void rebuild_size(int size_x, int size_y){
		this.size_x = size_x;
		this.size_y = size_y;
		this.map = new int[size_x][size_y];
	}
	public void rebuild_line(int line_number, int [] line_data){
		for(int i = 0; i < line_data.length; ++i){
			map[line_number][i] = line_data[i];
		}
	}
	public int[][] get_map(){
		return map;
	}
	public String get_type(int i, int j){
		switch(map[i][j]){
			case 0: return "grass";
			case 1: return "ground";
			
			default: return "null";
		}
	}
	
	public void draw_map(Graphics g){
		for(int i = 0; i < this.size_x; ++i)
			for(int j = 0; j < this.size_y; ++j)
				g.drawImage(World.texture_set.get(this.get_type(i, j)).getImage(), i*64, j*64, 64, 64, null);
	}
}