package player_data;

import java.awt.Graphics;

public class PlayersOnline{
	public int mapX;
	public int mapY;
	public String playerName;
	
	public PlayersOnline(int mapX, int mapY, String playerName){
		this.mapX = mapX;
		this.mapY = mapY;
		this.playerName = playerName;
	}
	
	public static void draw(Graphics g){
		for(PlayersOnline item: World.playersOnline){
			g.drawImage(World.texture_set.get("char").getImage(), item.mapX*32+380, item.mapY*32, 32, 32, null);
		}
	}
}