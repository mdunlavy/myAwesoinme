import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class ScoreChecker{
    private static Scanner scanner = new Scanner(System.in);
    private static BufferedReader reader;
    private static Trie dictionary = new Trie();
    private static boolean endOfInput = false;
    public static void main(String[] args) throws IOException {
    
                reader = new BufferedReader(new FileReader(args[0]));
                String line = reader.readLine();
                while(line != null){
                    dictionary.addWord(line); //add each word to trie
                    line = reader.readLine();
                }
                reader.close();
            
            while(!endOfInput){
            int boardSize = scanner.nextInt();
            Tile[][] board1 = scanBoard(boardSize);
            scanner.nextInt(); //skip the second int indicating size
            Tile[][] board2 = scanBoard(boardSize);
            System.out.println("Original Board: ");
            printBoard(board1, boardSize);
            System.out.println("Result Board: ");
            printBoard(board2, boardSize);
            System.out.println(playCheck(board1, board2));

            System.out.println("Would you like to test another board? (y/n)");
            String response = scanner.next();
            if (response.equals("n")) {
                endOfInput = true;
            }
        }

            
            scanner.close();
    
        }

        public static Tile[][] scanBoard(int boardSize){
            Tile[][] board1 = new Tile[boardSize][boardSize];
            for (int i = 0; i < boardSize; i++){
                for (int j = 0; j < boardSize; j++){
                    String tile = scanner.next();
                    if (tile.length() == 1) board1[i][j] = new Tile(tile.charAt(0));
                    else {
                        int wordMultiplier, letterMultiplier; 
                        if (tile.charAt(0) == '.') {wordMultiplier = 1;}
                        else {wordMultiplier = Character.getNumericValue(tile.charAt(0));}
                        if (tile.charAt(1) == '.') {letterMultiplier = 1;}
                        else {letterMultiplier = Character.getNumericValue(tile.charAt(1));}

                        board1[i][j] = new Tile(wordMultiplier, letterMultiplier);
                    }
                }
            }
            return board1;

        }

        public static void printBoard(Tile [][] board, int boardSize){
            for (int i = 0; i < boardSize; i++){
                for (int j = 0; j < boardSize; j++){
                    String letterMultiplier = Integer.toString(board[i][j].getLetterMultiplier());
                    String wordMultiplier = Integer.toString(board[i][j].getWordMultiplier());

                    if (board[i][j].getLetter() == ' ' && !board[i][j].isWild()){
                    if (board[i][j].getLetterMultiplier() == 1) letterMultiplier = ".";
                    if (board[i][j].getWordMultiplier() == 1) wordMultiplier = ".";
                    System.out.print(wordMultiplier + letterMultiplier + " ");
                    }
                    else {
                        System.out.print(" " + board[i][j].getLetter() + " ");
                    }
                    
                }
                System.out.println("\n");
            }
        }
        public static String playCheck(Tile[][] board1, Tile[][] board2){
            int multiplier = 1;
            String play = "play is ";
            for (int i = 0; i < board1.length; i++){
                for (int j = 0; j < board1[0].length; j++){
                    if (board1[i][j].getLetter() != ' ' && board2[i][j].getLetter() == ' '){
                        play += "not legal";
                        return play;
                    }
                    else if (board1[i][j].getLetter() != ' ' && board2[i][j].getLetter() != ' ' && board1[i][j].getLetter() != board2[i][j].getLetter()){
                        return "Incompatible boards: tile removed at: (" + i + ", " + j + ")";
                    }
                    else if (board1[i][j].getLetter() == ' ' && board2[i][j].getLetter() == ' ' &&
                    (board1[i][j].getLetterMultiplier() != board2[i][j].getLetterMultiplier())){
                        return "Incompatible boards: multiplier mismatch at: (" + i + ", " + j + ")";
                    }
                    else if (board1[i][j].getLetter() != board2[i][j].getLetter()){
                        play += board2[i][j].getLetter() + " at (" + i + ", " + j + "), ";
                        //score += board2[i][j].getPoints() * board1[i][j].getLetterMultiplier();
                        if (board1[i][j].getWordMultiplier() >  multiplier){
                            multiplier = board1[i][j].getWordMultiplier();
                        }
                    }
                }
            }

            
            Words origionalWords = new Words(board1);
            Words resultWords = new Words(board2);

            ArrayList<String> origionalAllWords = origionalWords.getAllWords();
            ArrayList<String> resultAllWords = resultWords.getAllWords();
            int numTotalWords = resultAllWords.size();

            //if the second board does not have gaps or has only one word,
            // then it will not hit this condition and will be a legal play
            if (numTotalWords > 1 && hasGaps(board2)){
                play += "\nnot legal";
                return play;
            }
            if (numTotalWords == 0) {
                play += "empty\nplay is not legal";
                return play;
            }

            if (numTotalWords == 1 && board2[board2.length/2][ board2.length/2].getLetter() == ' '){
                play += "\nnot legal";
                return play;
            }

            for (String word : resultAllWords){
                if (!dictionary.searchDictionary(word)){
                    play += "\nnot legal " + word;
                    return play;
                }
            }

            int finalScore = (resultWords.getScore() - origionalWords.getScore()) * multiplier;

           
            System.out.println("results: " + resultWords.getScore() + " origional: " + origionalWords.getScore() + " multiplier: " + multiplier);
            
            play += "\nplay is legal\nscore is " + finalScore;

            return play;
        }

        public static boolean hasGaps(Tile[][] board){
            //need logic for this
            return false;
        }
        public static int wordScore(String word){
            int score = 0;
            for (int i = 0 ; i < word.length() ; i++){
                switch (Character.toLowerCase(word.charAt(i))){
                    case 'a':
                    case 'e':
                    case 'i':
                    case 'o':
                    case 'n':
                    case 'r':
                    case 't': 
                    case 'l':
                    case 's':
                    case 'u': score += 1; break;
                    case 'd':
                    case 'g': score += 2; break;
                    case 'b':
                    case 'c':
                    case 'm':
                    case 'p': score += 3; break;
                    case 'f':
                    case 'h':
                    case 'v':
                    case 'w':
                    case 'y': score += 4; break;
                    case 'k': score += 5; break;
                    case 'j':
                    case 'x': score += 8; break;
                    case 'q':
                    case 'z': score += 10; break;
                    default: score += 0; break;
                  }
            }
            return score;
        }

    
}
