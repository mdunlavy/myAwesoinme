import java.util.ArrayList;
import java.util.Random;

public class Tilebag {
	ArrayList<Tile> TileSet;
	
	
	public Tilebag(){
		TileSet = new ArrayList<Tile>();
		AddMultipleTiles('A',1,9);
		AddMultipleTiles('B',3,2);
		AddMultipleTiles('C',3,2);
		AddMultipleTiles('D',2,4);
		AddMultipleTiles('E',1,12);
		AddMultipleTiles('F',4,2);
		AddMultipleTiles('G',2,3);
		AddMultipleTiles('H',4,2);
		AddMultipleTiles('I',1,9);
		AddMultipleTiles('J',8,1);
		AddMultipleTiles('K',5,1);
		AddMultipleTiles('L',1,4);
		AddMultipleTiles('M',3,2);
		AddMultipleTiles('N',1,6);
		AddMultipleTiles('O',1,8);
		AddMultipleTiles('P',3,2);
		AddMultipleTiles('Q',10,1);
		AddMultipleTiles('R',1,6);
		AddMultipleTiles('S',1,4);
		AddMultipleTiles('T',1,6);
		AddMultipleTiles('U',1,4);
		AddMultipleTiles('V',4,2);
		AddMultipleTiles('W',4,2);
		AddMultipleTiles('X',8,1);
		AddMultipleTiles('Y',4,2);
		AddMultipleTiles('Z',10,1);
	}
	
	public Tile takeOutTile(){
		Random rand = new Random();
		int n = rand.nextInt(TileSet.size());
		Tile tile = TileSet.get(n);
		TileSet.remove(n);
		return tile;
	}

	public ArrayList<Tile> takeOutTiles(int numTiles){
		ArrayList<Tile> tiles = new ArrayList<Tile>();
		Random rand = new Random();
		for (int i = 0; i < numTiles; i++){
			int n = rand.nextInt(TileSet.size());
			tiles.add(TileSet.get(n));
			TileSet.remove(n);
		}
		return tiles;
	}
	
	public Tile takeOutTile(char letter){
		for (int i = 0; i < TileSet.size(); i++){
			if (TileSet.get(i).getLetter() == letter){
				Tile tile = TileSet.get(i);
				TileSet.remove(i);
				return tile;
			}
		}
		return null;
	}
	
	public void AddMultipleTiles(char letter, int points ,int quantity){
		for (int i = 0 ; i < quantity ; i++){
			TileSet.add(new Tile(letter));
		}
	}
	
	public boolean isEmpty(){
		return (TileSet.size() == 0);
	}
	
}