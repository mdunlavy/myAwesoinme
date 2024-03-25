import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
  
    // public Scene possibleMode(Board tileBoard){
    //     GridPane BoardtoUse = updateBoard(tileBoard);
    //     VBox racktoUse = initializeRack(rack);
        
    //      racktoUse.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
    //     {
    //         Node node = (Node) (event.getPickResult().getIntersectedNode());
    //         int rowSelect = GridPane.getRowIndex(node);
    //         int colSelect = GridPane.getColumnIndex(node);
    //         System.out.println("Selected: (" +rowSelect + ", " + colSelect+ ")");
    //         selectedTile = rack.get(colSelect);

    //     });

    //     BoardtoUse.addEventHandler(MouseEvent.MOUSE_CLICKED, event2 ->
    //     {
    //         Node node = (Node) (event2.getPickResult().getIntersectedNode());
    //         int row = GridPane.getRowIndex(node);
    //         int col = GridPane.getColumnIndex(node);
    //         System.out.println("Board: (" +row + ", " + col+ ")");
    //         if (selectedTile != null && tileBoard.getTileArr()[row][col].getLetter() == ' ') playTile(selectedTile, rack, row, col, tileBoard, BoardtoUse);
    //         racktoUse.getChildren().clear();
    //         rack.remove(selectedTile);
    //         racktoUse.getChildren().add(initializeRack(rack));
    //         selectedTile = null;
    //     });
    //     return new Scene(new VBox(racktoUse, BoardtoUse), 500, 500);
    // }
    // public GridPane updateBoard(Board board){
    //     GridPane boardPane = new GridPane();
    //     boardPane.setPadding(new Insets(10, 10, 10, 10));
    //     boardPane.setHgap(1);
    //     boardPane.setVgap(1);
    //     boardPane.setGridLinesVisible(true);
    //     boardPane.setAlignment(Pos.CENTER);
        
    //     for (int i = 0; i < BOARDSIZE; i++){
    //         for (int j = 0; j < BOARDSIZE; j++){
    //             GUITile tile = new GUITile(board.getTileArr()[i][j].getLetter(), i, j);
    //             if(board.getTileArr()[i][j].getLetter() != ' '){
    //                 tile.setLetterLabel(board.getTileArr()[i][j].getLetter());
    //             }
    //             else if (i == 7 && j == 7){
    //                 tile.makeStar();
    //             }
    //             else if (Multipliers.getInstance().letterMultiplier(j, i) == 2){
    //                 tile.setLabel("Double \nLetter \nScore");
    //                 tile.setColor(Color.LIGHTBLUE);
    //             }else if (Multipliers.getInstance().letterMultiplier(j, i) == 3){
    //                 tile.setLabel("Triple \nLetter \nScore");
    //                 tile.setColor(Color.LIGHTCORAL);
    //             }else if (Multipliers.getInstance().wordMultiplier(j, i) == 2){
    //                 tile.setLabel("Double \nWord \nScore");
    //                 tile.setColor(Color.LIGHTGREEN);
    //             }else if (Multipliers.getInstance().wordMultiplier(j, i) == 3){
    //                 tile.setLabel("Triple \nWord \nScore");
    //                 tile.setColor(Color.LIGHTPINK);
    //             }
    //             else {
    //                 tile.setLabel("");
    //             }
    //             boardPane.add(tile, i, j);
    //         }
    //     }
    //     return boardPane;
    // }
    // public VBox initializeRack(ArrayList<Tile> tiles){
    //     GridPane rack = new GridPane();
    //     //HBox rack= new HBox();
    //     //rack.setSpacing(10);
    //     //rack.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

    //     rack.setAlignment(Pos.CENTER);
    //     rack.setHgap(20);
    //     ArrayList<GUITile> tileList = new ArrayList<GUITile>();
        
    //     for (int i = 0; i < 6; i++){
    //         GUITile tile = new GUITile(tiles.get(i).getLetter(), 0, i);
    //         tile.setLabel(tiles.get(i).getLetter() + "");
    //         tile.setColor(Color.FLORALWHITE);
    //         Rectangle rectangle = new Rectangle(50, 50, Color.TRANSPARENT);
    //         tile.setTop(rectangle);
    //         GridPane.setRowIndex(rectangle, 0); 
    //         GridPane.setColumnIndex(rectangle, i);
    //         tileList.add(tile);
    //     }

    //     for(GUITile tile : tileList){
    //         rack.add(tile, tile.getCol(), tile.getRow());
    //     }
    //     VBox innerRack = new VBox(rack);
    //     innerRack.setAlignment(Pos.CENTER);
    //     innerRack.setSpacing(10);
    //     innerRack.setPrefHeight(80);
    //     innerRack.setPrefWidth(50);
    //     innerRack.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

    //     return innerRack;
    // }
   

    //needs to score word with letter and word multipliers
   
}