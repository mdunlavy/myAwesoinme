import java.lang.reflect.Array;
import java.util.ArrayList;

public class Computer {
	
    private Player computer;
	private int maxScore ;
	private ArrayList<Tile> bestWord;
	private Anchor currentAnchor;
	private Trie dictionary;
	private Board board;
	private ArrayList<Tile> rack;

	public Computer(Player computer, Trie dictionary, Board board, ArrayList<Tile> rack){
		this.computer = computer;
		this.dictionary = dictionary;
		this.board = board;
		this.rack = rack;
	}

	public boolean makeMove(){

		System.out.println("make move");
		maxScore = 0;
		bestWord = new ArrayList<Tile>();
		//this is breaking 
		for (Anchor anchor : findAnchors()){
			ArrayList<Tile> inputTiles = new ArrayList<Tile>(rack);
			inputTiles.add(anchor.getAnchorTile());
			findHighestScoringWord(inputTiles, new ArrayList<Tile>(), "", 0, anchor);
		}
		if (bestWord == null || bestWord.size() == 0){
			System.out.println("best word is null");
		} else {
		
			int startCol;
			int startRow;
			if (currentAnchor.across){
				startCol = currentAnchor.col - getAnchorPosition(currentAnchor, bestWord);
				startRow = currentAnchor.row;
			} else {
				startCol = currentAnchor.col;
				startRow = currentAnchor.row - getAnchorPosition(currentAnchor, bestWord);
			}
			
			System.out.print("best word: ");
			for (Tile tile: bestWord){
				System.out.print(tile.getLetter());
			}
			System.out.println("\n");
			Move move = new Move(bestWord , startRow , startCol ,currentAnchor.across, maxScore, computer);
			move.execute(board.getTileArr());
			
		}
		return true;
	}

	public String getBestWord(){
		StringBuilder word = new StringBuilder();
		for (Tile tile: bestWord){
			word.append(tile.getLetter());
		}
		return word.toString();
	}
	public int getMaxScore() {
		return maxScore;
	}
	
	private int getAnchorPosition(Anchor anchor, ArrayList<Tile> word){
		for (int c = 0 ; c < word.size() ; c++){
			if (word.get(c).getLetter() == anchor.anchorTile.getLetter()){
				return c;
			}
		}
		return -1000;
	}
	
	private boolean fitsOnBoard(Anchor anchor, ArrayList<Tile> word){
		//check if word would cause spilling off the edge of the board
		int anchorPos = getAnchorPosition(anchor, word);
		int prefixLength = anchorPos;
		int posfixLength = word.size() - anchorPos - 1 ;
		
		if (anchor.prefixCap >= prefixLength && anchor.postfixCap >= posfixLength){
			return true;
		} else {
			return false;
		}
	}
	
	private void  findHighestScoringWord(ArrayList<Tile> inputTiles, ArrayList<Tile> tilesToBeUsed, String currentWord, int score, Anchor anchor){
		for (int tileNo = 0 ; tileNo < inputTiles.size() ; tileNo++){
			Tile curTile = inputTiles.get(tileNo);
			if (isValidPrefix(currentWord + curTile.getLetter())){
			
				ArrayList<Tile> remainingTiles = new ArrayList<Tile>(inputTiles);
				ArrayList<Tile> tilesInWord = new ArrayList<Tile>(tilesToBeUsed);

				remainingTiles.remove(tileNo);
				tilesInWord.add(curTile);
				findHighestScoringWord(remainingTiles, tilesInWord ,currentWord  + curTile.getLetter(), score + curTile.getPoints() , anchor);
				
				if (currentWord.length() >= 7){
					score += 50;
				}
				
				
				
				//need to check if anchor is in the word before we propose it as an answer
				if (tilesToBeUsed.contains(anchor.anchorTile) || curTile.equals(anchor.anchorTile)){
					if (isValidWord(currentWord + curTile.getLetter())){
						if (fitsOnBoard(anchor, tilesInWord)){
							if (maxScore < score + curTile.getPoints()){
								maxScore =  (score + curTile.getPoints()) * curTile.getWordMultiplier();
								bestWord = tilesInWord;
								currentAnchor = anchor;
							}	
						}
					}
				}
			}
		}
	}
	
	public boolean isValidPrefix(String prefix){
		return(dictionary.findPrefix(prefix));
	}
	
	public boolean isValidWord(String word){
		return dictionary.searchDictionary(word);
	}
	
	
	public ArrayList<Anchor> findAnchors(){
		ArrayList<Anchor> anchors = new ArrayList<Anchor>();
		Tile[][] tileArr =  board.getTileArr();
		for (int row = 0 ; row < tileArr.length ; row ++){
			for (int col = 0 ; col < tileArr[0].length ; col ++){
				if (tileArr[row][col].getLetter() != ' '){
					//System.out.println("current tile : " + tileArr[row][col].getLetter());
					int startCol = col;
					int endCol = col;
					
					//check how far left the word can go without collisions
					if (col > 0 && tileArr[row][col - 1].getLetter() == ' '){
						//System.out.println("going left is an option");
						while (startCol > 0){
							if (row != tileArr.length - 1 && tileArr[row + 1][startCol - 1].getLetter() != ' '){
								break;
							}
							if ( row != 0 && tileArr[row - 1][startCol - 1].getLetter() != ' '){
								break;
							}
							if (startCol == 1){
								startCol--;
								break;
							}
							if (tileArr[row ][startCol -2].getLetter() != ' '){
								break;
							}
							startCol--;
						}
						//System.out.println("start col " + startCol + " col " + col);
					}
					
					//check how far right the word can go without collisions
					if (col < tileArr.length - 1 && tileArr[row][col + 1].getLetter() == ' '){
						//System.out.println("going right is an option");
						while (endCol < tileArr.length -1 ){
							
							if (row != tileArr.length - 1 && tileArr[row + 1][endCol + 1].getLetter() != ' '){
								break;
							}		
							if ( row != 0 && tileArr[row - 1][endCol + 1].getLetter() != ' '){
								break;
							}
							if (endCol == tileArr.length - 2){
								endCol++;
								break;
							}
									
							if (tileArr[row][endCol + 2].getLetter() != ' '){
								break;
							}
							
							endCol++;
						}
						//System.out.println("end col " + endCol + " col " + col);
					}
					
					//add the horizontal anchors
					//System.out.println(" col " + col + "start col " + startCol + " end col " + endCol );

					if (col - startCol > 0 && endCol - col > 0){ // words that can go left or right
						anchors.add(new Anchor(row, col, tileArr[row][col], col - startCol, endCol - col , true));
					} else {
						//if only one then we need to do additional checks
						if (col - startCol > 0){
							//System.out.println(tileArr.length - 1 + ", " + tileArr[row][col + 1].getLetter());
							
							if (col < tileArr.length - 1 && tileArr[row][col + 1].getLetter() == ' '){  // words that can only go left
								anchors.add(new Anchor(row, col, tileArr[row][col], col - startCol, endCol - col , true));
							}
						}
						if (endCol - col > 0){
							if (col > 0 && tileArr[row][col - 1 ].getLetter() == ' '){ // words that can only go right
								anchors.add(new Anchor(row, col, tileArr[row][col], col - startCol, endCol - col , true));
							}
						}
					}				
					
					//check not at edges - have to re-think algorithm for edges.
					int startRow = row;
					int endRow = row;
					
					//check how high the word can go without collisions
					if (row > 0 && tileArr[row - 1][col].getLetter() == ' '){
						//System.out.println("going up is an option");
						while (startRow > 0){
							if (col < tileArr.length - 1 && tileArr[startRow - 1][col + 1].getLetter() != ' '){
								break;
							}
							if (col > 0 && tileArr[startRow - 1][col - 1].getLetter() != ' '){
								break;
							}
							if (startRow == 1){
								startRow--;
								break;
							}
							if (tileArr[startRow - 2][col].getLetter() != ' '){
								break;
							}
							startRow--;
						}
						
					}
					
					//check how low the word can go without collisions
					if (row < tileArr.length - 1 && tileArr[row + 1][col].getLetter() == ' '){
						//System.out.println("going down is an option");
						while (endRow < tileArr.length -1){
							if (col < tileArr.length - 1 && tileArr[endRow + 1][col + 1].getLetter() != ' '){
								break;
							}
							if (col > 0 &&	tileArr[endRow + 1][col - 1].getLetter() != ' '){
								break;
							}
							if (endRow == tileArr.length - 2){
								endRow++;
								break;
							}
							if(tileArr[endRow + 2][col].getLetter() != ' '){
								break;
							}
								
							endRow++;
						}
					}
				

					//System.out.println("start row " + startRow + " end row " + endRow + " row " + row + " col " + col);
					if (row - startRow > 0 && endRow - row > 0){;
						anchors.add(new Anchor(row, col, tileArr[row][col], row - startRow, endRow - row, false));
					} else{//if only one then we need to do additional checks
						if (row - startRow > 0){ //words that can only go up
							if (row < tileArr.length-1 && tileArr[row+1][col].getLetter() == ' '){
								anchors.add(new Anchor(row, col, tileArr[row][col], row - startRow, endRow - row, false));
							}
						}
						if (endRow - row > 0){ //words that can only go down
							if (row > 0 && tileArr[row-1][col].getLetter() == ' '){
								anchors.add(new Anchor(row, col, tileArr[row][col], row - startRow, endRow - row, false));
							}
						}
					}			
				}
			}
		}
		return anchors;
	}
}
