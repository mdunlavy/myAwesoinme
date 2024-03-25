

public class Multipliers {
	

	public static int[][] Arr;
	
	private static final Multipliers instance;
	
	static{ instance = new Multipliers();}
	
	public static Multipliers getInstance(){
		return instance;
	}

	
	public static int letterMultiplier(int row, int col){
		if (check(row, col) == 1) return 2;
		if (check(row, col) == 2) return 3;
		return 1;
	}
	
	public static int wordMultiplier(int row, int col){
		if (check(row, col) == 3) return 2;
		if (check(row, col) == 4) return 3;
		return 1;
	}
	
	public static void RemovePlayedBonuses(){
		Tile[][] tileArr = Board.getInstance().VALIDCharBoard;
		
		
		for (int row = 0 ; row < Board.size ; row++){
			for (int col = 0 ; col < Board.size ; col++){
				if(tileArr[row][col].getLetter() != ' ') {
					Arr[row][col] = 0;
				}
			}
		}
	}
	
	public Multipliers() {
		
		Arr = new int[15][15];

		for(int i = 1 ; i < Board.size - 1 ; i++){
			Arr[i][i] = 3;
			Arr[Board.size - i - 1][i] = 3; 
		}
		//manually setting the multipliers for the board
		Arr[6][6] =  1;
		Arr[6][8] =  1;
		Arr[8][6] =  1;
		Arr[8][8] =  1;
		
		Arr[11][7] =  1;
		Arr[7][11] =  1;
		Arr[3][7] =  1;
		Arr[7][3] =  1;
		
		Arr[2][6] =  1;
		Arr[6][2] =  1;
		Arr[2][8] =  1;
		Arr[8][2] =  1;
		
		Arr[12][6] =  1;
		Arr[6][12] =  1;
		Arr[12][8] =  1;
		Arr[8][12] =  1;
		
		Arr[11][0] =  1;
		Arr[0][11] =  1;
		Arr[11][14] =  1;
		Arr[14][11] =  1;
		
		Arr[3][0] =  1;
		Arr[0][3] =  1;
		Arr[3][14] =  1;
		Arr[14][3] =  1;
		
		Arr[5][1] =  2;
		Arr[1][5] =  2;
		Arr[5][13] =  2;
		Arr[13][5] =  2;
		
		Arr[9][1] =  2;
		Arr[1][9] =  2;
		Arr[9][13] =  2;
		Arr[13][9] =  2;
		
		Arr[5][5] =  2;
		Arr[5][9] =  2;
		Arr[9][5] =  2;
		Arr[9][9] =  2;
		

		
		Arr[0][0] = 4;
		Arr[14][14] = 4;
		Arr[0][14] = 4;
		Arr[14][0] = 4;
		
		Arr[7][0] = 4;
		Arr[14][7] = 4;
		Arr[7][14] = 4;
		Arr[0][7] = 4;
		
	}
	
	public static Integer check(int row, int col){
		return Arr[row][col];
	}
	
}




	

