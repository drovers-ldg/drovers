package World;

import java.util.HashMap;

public class World {
	public HashMap<String, Area_Map> world_map;
	
	public World(){
		world_map = new HashMap<String, Area_Map>();
		world_map.put("null", new Area_Map(3, 2));
	}
}