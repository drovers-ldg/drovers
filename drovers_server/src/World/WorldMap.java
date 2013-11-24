package World;

import java.io.Externalizable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.Scanner;

public class WorldMap implements Externalizable{
	private static final long serialVersionUID = 201311182029L;
	
	public static WorldMapNode [][] map;
	public static int sizeX;
	public static int sizeY;
	
	public WorldMap() throws FileNotFoundException{
		loadMap();
	}
	
	private static void loadMap() throws FileNotFoundException{
		Scanner in = new Scanner(new File("maps\\allworld.map"));
		
		sizeX = in.nextInt();
		sizeY = in.nextInt();
		map = new WorldMapNode[sizeX][sizeY];
		
		for(int i = 0; i < sizeX; ++i){
			for(int j = 0; j < sizeY; ++j){
				map[i][j] = new WorldMapNode(in.next(), in.nextInt(), in.nextInt());
			}
		}
		
		in.close();
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
		// void
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {
		out.writeInt(sizeX);
		out.writeInt(sizeY);
		for(int i = 0; i < sizeX; ++i){
			for(int j = 0; j < sizeY; ++j){
				out.writeUTF(map[i][j].areaName);
				out.writeInt(map[i][j].id);
				out.writeInt(map[i][j].type);
			}
		}
		out.flush();
	}
}

class WorldMapNode{
	public int id;
	public String areaName;
	public int type;
	public boolean movable;
	
	WorldMapNode(String areaName, int id, int type){
		this.id = id;
		this.areaName = areaName;
		this.type = type; // 0 - null, 1 - grass, 2 - forest, 3 - deep forest, 4 - rocks
		
		switch(type){
			case 0:
				this.movable = true;
				break;
			case 1: 
				this.movable = true;
				break;
			case 2:
				this.movable = true;
				break;
			case 3:
				this.movable = true;
				break;
			case 4: 
				this.movable = true;
				break;
			case 5: 
				this.movable = true;
				break;
			case 6: 
				this.movable = true;
				break;
			case 7: 
				this.movable = true;
				break;
			default:
				this.movable = true;
		}
		
	}
}