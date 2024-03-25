import java.util.ArrayList;

public class Human{

     private Player humanPlayer;
	private int maxScore ;
	private Trie dictionary;
	private Board board;
	private ArrayList<Tile> rack;
    private Tile selectedTile;
    private int BOARDSIZE = 15;

	public Human(Player humanPlayer, Trie dictionary, Board board, ArrayList<Tile> rack){
		this.humanPlayer = humanPlayer;
		this.dictionary = dictionary;
		this.board = board;
		this.rack = rack;
	}

    public boolean validMove(){
        return false;
    }
   
}