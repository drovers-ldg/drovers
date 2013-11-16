package World;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class WorldMap{
	public static WorldMapNode [][] map;
	public static int sizeX;
	public static int sizeY;
	
	public WorldMap() throws FileNotFoundException{
		loadMap();
	}
	
	private static void loadMap() throws FileNotFoundException{
		Scanner in = new Scanner(new File("maps\\world.map"));
		
		sizeX = in.nextInt();
		sizeY = in.nextInt();
		map = new WorldMapNode[sizeX][sizeY];
		
		for(int i = 0; i < sizeX; ++i){
			for(int j = 0; j < sizeY; ++j){
				map[i][j] = new WorldMapNode(in.next(), in.nextInt());
			}
		}
		
		in.close();
	}
	
}

class WorldMapNode{
	public int id;
	public String areaName;
	public int type;
	
	WorldMapNode(String areaName, int type){
		this.areaName = areaName;
		this.type = type; // 0 - null, 1 - grass, 2 - forest, 3 - deep forest, 4 - rocks
	}
}