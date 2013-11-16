package World;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class World {
	public static HashMap<String, AreaMap> areaMaps;
	public WorldMap worldMap;
	
	public World() throws FileNotFoundException{
		worldMap = new WorldMap();
		areaMaps = new HashMap<String, AreaMap>();
		areaMaps.put("null", new AreaMap(10, 10));
		loadAreas();
		System.out.println("---------------World---------------");
		System.out.println("Areas loaded: " + areaMaps.size());
		System.out.println("-----------------------------------");
	}
	
	public static void loadAreas(){
		for(int i = 0; i < WorldMap.sizeX; ++i){
			for(int j = 0; j < WorldMap.sizeY; ++j){
				try {
					Scanner in = new Scanner(new File("maps\\"+ WorldMap.map[i][j].areaName+".map"));
					areaMaps.put(WorldMap.map[i][j].areaName, new AreaMap(in));
					in.close();
				} catch (FileNotFoundException e) {
					System.out.println("AreaMap name not found: maps\\" + WorldMap.map[i][j].areaName+".map");
				}
			}
		}
	}
}