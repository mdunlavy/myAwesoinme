public class Anchor {
public 
int row;
int col;
public Tile anchorTile;
int prefixCap;
int postfixCap;
boolean across;

public Anchor(int row, int col, Tile anchorTile, int prefixCap, int postfixCap, boolean across) {
    super();
    this.row = row;
    this.col = col;
    this.anchorTile = anchorTile;
    this.prefixCap = prefixCap;
    this.postfixCap = postfixCap;
    this.across = across;
}
public Tile getAnchorTile() {
    return anchorTile;
    
}
}
