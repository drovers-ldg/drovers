package World;
import java.io.PrintWriter;

public class Area_Map{
	public int size_x;
	public int size_y;
	public Node [][] map;
	
	public Area_Map(){
		size_x = 0;
		size_y = 0;
		map = null;
	}
	public Area_Map(int size_x, int size_y){
		this.size_x = size_x;
		this.size_y = size_y;
		
		map = new Node[size_x][size_y];
		for(int i = 0; i < size_x; ++i){
			for(int j = 0; j < size_y; ++j){
				map[i][j] = new Node();
			}
		}
	}
	
	public void Send_Map(PrintWriter out){
		if(out != null){
			out.println("LOAD:MAP:SIZE:"+size_x+":"+size_y);
		
			for(int i = 0; i < size_x; ++i){
				String line = "LOAD:MAP:LINE:"+i+":";
				for(int j = 0; j < size_y; ++j)
					line += map[i][j].type + ":";
				out.println(line);
			}
		}
	}
}