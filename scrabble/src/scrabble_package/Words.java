import java.util.ArrayList;

public class Words {
	private int score;
	private Tile[][] TileArr;

	private int[][] usedTiles;
	private static int BOARD_DIMENSIONS;

	public Words(Tile[][] board){
		TileArr = board;
		BOARD_DIMENSIONS = board.length;

		usedTiles = new int[board.length][board.length];
		for (int i = 0; i < TileArr.length; i++){
			for (int j = 0; j < TileArr[0].length; j++){
				usedTiles[i][j] = 0;
			}
		}
	}

    public ArrayList<String> getVerticalWords(){
		ArrayList<String> vertical = new ArrayList<>();
		StringBuilder curWord = new StringBuilder();
		score = 0;
		for (int col = 0 ; col < BOARD_DIMENSIONS ; col++){
			for (int row = 0 ; row < BOARD_DIMENSIONS ; row++){
				//System.out.println("row: " + row + " col: " + col + " letter: " + TileArr[row][col].getLetter());
				if (TileArr[row][col].getLetter() == ' '){
					if (curWord.length() > 1){
						vertical.add(curWord.toString());
						System.out.println(curWord.toString());
					}
					curWord = new StringBuilder();
				} else {
					score += TileArr[row][col].getPoints() * TileArr[row][col].getLetterMultiplier();
					curWord.append(Character.toLowerCase(TileArr[row][col].getLetter()));
				}
			}
			if (curWord.length() > 1){
				vertical.add(curWord.toString());
				System.out.println(curWord.toString());
			}
			curWord = new StringBuilder();
		}
		return vertical;
	}
	
	public ArrayList<String> getHorizontalWords(){
		ArrayList<String> horizontal = new ArrayList<>();
		StringBuilder curWord = new StringBuilder();
		score = 0;
		for (int row = 0 ; row < BOARD_DIMENSIONS ; row++){
			for (int col = 0 ; col < BOARD_DIMENSIONS ; col++){
				if (TileArr[row][col].getLetter() == ' '){
					System.out.println(curWord.toString());
					if (curWord.length() > 1){
						horizontal.add(curWord.toString());
					}
					curWord = new StringBuilder();
				} else {
					score += TileArr[row][col].getPoints() * TileArr[row][col].getLetterMultiplier();
					curWord.append(Character.toLowerCase(TileArr[row][col].getLetter()));
				}
			}
			if (curWord.length() > 1){
				horizontal.add(curWord.toString());
			}
			curWord = new StringBuilder();
		}
		return horizontal;
	}
	
	public ArrayList<String> getAllWords(){
		ArrayList<String> allWords = new ArrayList<>();
		allWords.addAll(getHorizontalWords());
		allWords.addAll(getVerticalWords());
		return allWords;
	}
	public int getScore(){
		return score;
	}
}
