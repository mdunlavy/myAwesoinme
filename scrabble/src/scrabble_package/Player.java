import java.util.ArrayList;

public class Player {

    
    private ArrayList<Tile> rack;

    public Player(ArrayList<Tile> rack){
        this.rack = rack;
    }
    
    public ArrayList<Tile> getRack(){return rack;}

    public void swapTiles(){}
    
    public void removeLetter(Tile tile){rack.remove(tile);}
    //more methods I'm missing I think
    
}