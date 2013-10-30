package player_data;

public class Area_Map{
	private int [][] map;
	
	public Area_Map(){
		
	}
	
	public void rebuild_size(int size_x, int size_y){
		map = new int[size_x][size_y];
	}
	public void rebuild_line(int line_number, int [] line_data){
		for(int i = 0; i < line_data.length; ++i){
			map[line_number][i] = line_data[i];
		}
	}
	public int[][] get_map(){
		return map;
	}
}