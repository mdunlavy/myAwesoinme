

public class Board{
    public Tile [][] VALIDCharBoard; //board with valid moves made
    //can get the multiplier by calling get multiplier in tile class 

    Trie dictionary;
    private static final Board instance;
    public static int size;

    public Board(int size){
        Board.size = size;
        VALIDCharBoard = new Tile[size][size];
    }
    public void initializeBoard(){
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                VALIDCharBoard[i][j] = new Tile(' ');
            }
        }
    }
    public Board(Tile[][] board){
        VALIDCharBoard = board;
    }
    public void setBoard(Tile[][] board){
        VALIDCharBoard = board;
    }
    
    static{ instance = new Board(size);}
	
	public static Board getInstance(){
		return instance;
	}
    public void setDictionary(Trie dictionary){
        this.dictionary = dictionary;
    }
 
    public Tile[][] getTileArr(){
        return VALIDCharBoard;
    }
}
