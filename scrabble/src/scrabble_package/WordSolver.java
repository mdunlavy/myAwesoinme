import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class WordSolver {
    private static Scanner scanner = new Scanner(System.in);
    private static BufferedReader reader;
    private static Trie dictionary = new Trie();
    private static boolean endOfInput = false;
    private static ArrayList<Tile> tray = new ArrayList<>();
    private static Tilebag tilebag = new Tilebag();
    public static void main(String[] args) throws IOException {
        dictionary = createDictionary();
               
            while(!endOfInput){
            int boardSize = scanner.nextInt();
            Tile[][] board1 = scanBoard(boardSize);
            Board board = new Board(board1);
            String trayScan = scanner.nextLine();
            
            for (int i = 0; i < trayScan.length(); i++){
                tray.add(new Tile(trayScan.charAt(i)));
            }
            Computer computer = new Computer(new Player(tray), dictionary, board, tray);

            System.out.println("Input Board: ");
            printBoard(board1, boardSize);
            System.out.println("Tray: "+ trayScan);

            computer.makeMove();

            System.out.println("solution "  + computer.getBestWord() + "  has a score of " + computer.getMaxScore() + " points");

            System.out.println("Solution Board: ");
            printBoard(board.getTileArr(), boardSize);

            System.out.println("Would you like to test another board? (y/n)");
            String response = scanner.next();
            if (response.equals("n")) {
                endOfInput = true;
            }
        } 

        System.out.println(tilebag.isEmpty());
        System.out.println(tilebag.takeOutTile().getLetter());
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
                    System.out.print(letterMultiplier + wordMultiplier + " ");
                    }
                    else {
                        System.out.print(" " + board[i][j].getLetter() + " ");
                    }
                    
                }
                System.out.println("\n");
            }
        }
        public static Trie createDictionary() throws IOException{
            Trie dictionary = new Trie();
            reader = new BufferedReader(new FileReader("sowpods.txt"));
            String line = reader.readLine();
            while(line != null){
                dictionary.addWord(line); //add each word to trie
                line = reader.readLine();
            }
            reader.close();
            return dictionary;
        }
        
    }