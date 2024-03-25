import java.util.ArrayList;

public class Player {

    private int score;
    private ArrayList<Tile> rack;

    public Player(ArrayList<Tile> rack){
        this.rack = rack;
        this.score = 0;
    }
    
    public ArrayList<Tile> getRack(){return rack;}

    public void addToRack(ArrayList<Tile> tiles){
        for (Tile tile: tiles){
            rack.add(tile);
        }
    }

    public void swapTiles(){}
    
    public void removeLetter(Tile tile){rack.remove(tile);}
    //more methods I'm missing I think
    
    public int getScore(){return score;}
    public void addToScore(int score){this.score += score;}
    public void updateScore(int score){this.score += score;}

    public int getWordScore(String word, Board board){
        int score = 0;
        int wordMultiplier = 1;
        for (int i = 0; i < word.length(); i++){
            for (int j = 0; j < rack.size(); j++){
                if(board.getTileArr()[i][j].getWordMultiplier() != 1){
                    wordMultiplier *= board.getTileArr()[i][j].getWordMultiplier();
                }
                score += board.getTileArr()[i][j].getPoints();
            }
            
        }
        return score * wordMultiplier;
    }
}
