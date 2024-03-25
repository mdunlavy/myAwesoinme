
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.shape.Rectangle;

/**
 * Main class creates GUI and stores main game information
 * @author maisydunlavy
 */
public class Main extends Application {
    private static final double WIDTH = 1000.0;
    private static final double HEIGHT = 800.0;
    private Stage primaryStage = new Stage();
    private BorderPane backPane = new BorderPane();
    private int BOARDSIZE = 15;
    private Tilebag tilebag = new Tilebag();
    private Trie dictionary;
    private Tile selectedTile;
                


    /**
     * main method lanches GUI
     * @param args default main args
     */
    public static void main(String[] args) {
        launch(args);

    }

    /**
     * start initializes the primary stage
     * @param stage the primary stage for this application, onto which
     * the application scene can be set.
     */
    public void start(Stage stage) {
        primaryStage = stage;
        primaryStage.setTitle("start scene");
        primaryStage.setScene(createSelectionScene());
        primaryStage.show();


    }

    /**
     * create selection scene generates the first screen the user sees - just a basic
     * start screen with a button to start the game
     * if button is pressed, generates Start scene of game
     * @return Scene with button and label to start game
     */
    public Scene createSelectionScene(){
        StackPane startingLayout = new StackPane();
        startingLayout.setBackground(new Background(new BackgroundFill(Color.FLORALWHITE, CornerRadii.EMPTY, Insets.EMPTY)));

        Button startButton = new Button();
        startButton.setText("Start Game");
        startButton.setFont(Font.font("Times New Roman", 20));

        Label myLabel = new Label("Scrabble!");
        myLabel.setFont(Font.font("Times New Roman", 40));
        Label description = new Label("How to play:  ");
        Label description2 = new Label("");
        Label description3 = new Label("");
        Label description4 = new Label("");
        description.setFont(Font.font("Times New Roman", 20));
        description2.setFont(Font.font("Times New Roman", 20));
        description3.setFont(Font.font("Times New Roman", 20));
        description4.setFont(Font.font("Times New Roman", 20));

        VBox startingInner = new VBox();
        startingInner.getChildren().addAll(myLabel, description, description2, description3, description4, startButton);
        startingInner.setSpacing(20.0);
        HBox starting = new HBox(startingInner);
        startingInner.setAlignment(Pos.CENTER);
        starting.setAlignment(Pos.CENTER);
        startingLayout.getChildren().add(starting);

        startButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    primaryStage.setScene(createStartScene());
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });
        return new Scene(startingLayout, WIDTH, HEIGHT);
    }

    /**
     * create StartScene is the main part of the game - generates and displays the board,
     * displays and updates the score, allows user to end game, and checks match for two selected tiles
     *
     * @return Scene with game information
     * @throws IOException 
     */
    public Scene createStartScene() throws IOException {
        backPane.setBackground(new Background(new BackgroundFill(Color.WHITE, CornerRadii.EMPTY, Insets.EMPTY)));
        Board tileBoard = new Board(BOARDSIZE);
        tileBoard.initializeBoard();

        Player computerPlayer = new Player(tilebag.takeOutTiles(6));
        Player newPlayer = new Player(tilebag.takeOutTiles(6));

        dictionary = new Trie();
        BufferedReader reader = new BufferedReader(new FileReader("src/scrabble_package/sowpods.txt"));
        String line = reader.readLine();
        while(line != null){
            dictionary.addWord(line); //add each word to trie
            line = reader.readLine();
        }
        reader.close();


        GridPane BoardtoUse = initializeBoard(tileBoard);
        VBox racktoUse = initializeRack(newPlayer.getRack());

        //buttons! 
        HBox buttons = new HBox();
        Button endGame = new Button("End Game");
        Button pass = new Button("Pass");
        Button exchange = new Button("Exchange");
        Button playWord = new Button("Play Word");
        buttons.getChildren().addAll(endGame, pass, exchange, playWord);
        buttons.setAlignment(Pos.CENTER);
        buttons.setSpacing(10);
        VBox buttonBox = new VBox(buttons);

        VBox holdBoard = new VBox(BoardtoUse, racktoUse, buttonBox);

        holdBoard.setAlignment(Pos.TOP_CENTER);
        holdBoard.setSpacing(10);


        //scoring box

        HBox scoreBox = new HBox();
        VBox scoreBox2 = new VBox(scoreBox);
        scoreBox2.setPadding(new Insets(10, 10, 10, 0));
        scoreBox.setPrefHeight(HEIGHT - 200);
        scoreBox.setPrefWidth(350);
        scoreBox.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY, CornerRadii.EMPTY, Insets.EMPTY)));

        Label scoreLabel = new Label("Score: ");
        Label score = new Label("0");
        scoreBox.getChildren().addAll(scoreLabel, score);

        HBox boardBox = new HBox(holdBoard, scoreBox2);
        boardBox.setAlignment(Pos.TOP_LEFT);

        backPane.setCenter(boardBox);
        // all above is to set initial scene, now create players and handle input/gameflow
        

        Human humanMove = new Human(newPlayer, dictionary, tileBoard, newPlayer.getRack());
        Computer computer = new Computer(computerPlayer, dictionary, tileBoard, computerPlayer.getRack());
    
       
        
         racktoUse.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->
        {
            Node node = (Node) (event.getPickResult().getIntersectedNode());
            int rowSelect = GridPane.getRowIndex(node);
            int colSelect = GridPane.getColumnIndex(node);
            System.out.println("Selected: (" +rowSelect + ", " + colSelect+ ")");
            selectedTile = newPlayer.getRack().get(colSelect);

        });

        BoardtoUse.addEventHandler(MouseEvent.MOUSE_CLICKED, event2 ->
        {
            Node node = (Node) (event2.getPickResult().getIntersectedNode());
            int row = GridPane.getRowIndex(node);
            int col = GridPane.getColumnIndex(node);
            System.out.println("Board: (" +row + ", " + col+ ")");
            if (selectedTile != null && tileBoard.getTileArr()[row][col].getLetter() == ' ') playTile(selectedTile, newPlayer.getRack(), row, col, tileBoard, BoardtoUse);
            racktoUse.getChildren().clear();
            newPlayer.getRack().remove(selectedTile);
            racktoUse.getChildren().add(initializeRack(newPlayer.getRack()));
            BoardtoUse.getChildren().clear();
            BoardtoUse.getChildren().add(initializeBoard(tileBoard));
            selectedTile = null;
            node = null;
        });
        //animation timer that waits for human move then sets scene as human move
        // AnimationTimer  timer = new AnimationTimer(){
        //     @Override
        //     public void handle(long now){
        //         if (!validMove){
        //             Scene possibleMove = humanMove.possibleMode(tileBoard);
        //             primaryStage.setScene(possibleMove);
        //             updateRack(newPlayer.getRack(), racktoUse);

        //             computer.makeMove();
        //             updateBoard(tileBoard, BoardtoUse);
        //             updateRack(computerPlayer.getRack(), racktoUse);
        //         }
        //     }
        // };

        return new Scene(backPane, WIDTH, HEIGHT);
    }



    public void playTile(Tile tile, ArrayList<Tile> rack, int row, int col, Board board, GridPane boardPane){
        board.getTileArr()[row][col] = new Tile(tile.getLetter());
        rack.remove(tile);
    }
    public GridPane initializeBoard (Board tileBoard){
        GridPane board = new GridPane();
        board.setPadding(new Insets(10, 10, 10, 10));
        board.setHgap(1);
        board.setVgap(1);
        board.setGridLinesVisible(true);
        board.setAlignment(Pos.CENTER);
        for (int i = 0; i < BOARDSIZE; i ++) {
            for (int j = 0; j < BOARDSIZE; j++) {
                GUITile tile = new GUITile(tileBoard.getTileArr()[i][j].getLetter(), i, j);
                if(tileBoard.getTileArr()[i][j].getLetter() != ' '){
                    tile.setLetterLabel(tileBoard.getTileArr()[i][j].getLetter());
                }
                else if (i == 7 && j == 7){
                    tile.makeStar();
                }
                else if (Multipliers.getInstance().letterMultiplier(j, i) == 2){
                    tile.setLabel("Double \nLetter \nScore");
                    tile.setColor(Color.LIGHTBLUE);
                }
                else if (Multipliers.getInstance().letterMultiplier(j, i) == 3){
                    tile.setLabel("Triple \nLetter \nScore");
                    tile.setColor(Color.LIGHTCORAL);
                }else if (Multipliers.getInstance().wordMultiplier(j, i) == 2){
                    tile.setLabel("Double \nWord \nScore");
                    tile.setColor(Color.LIGHTGREEN);
                }else if (Multipliers.getInstance().wordMultiplier(j, i) == 3){
                    tile.setLabel("Triple \nWord \nScore");
                    tile.setColor(Color.LIGHTPINK);
                }
                else {
                    tile.setLabel("");
                }
                Rectangle rect = new Rectangle(40, 40, Color.TRANSPARENT);
                tile.setTop(rect);
                GridPane.setRowIndex(rect, i); 
                GridPane.setColumnIndex(rect, j);
                board.add(tile, i, j);
            }
        }
        return board;
    }
    public VBox initializeRack(ArrayList<Tile> tiles){
        GridPane rack = new GridPane();
        //HBox rack= new HBox();
        //rack.setSpacing(10);
        //rack.setBackground(new Background(new BackgroundFill(Color.DARKBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        rack.setAlignment(Pos.CENTER);
        rack.setHgap(20);
        ArrayList<GUITile> tileList = new ArrayList<GUITile>();
        
        for (int i = 0; i < tiles.size(); i++){
            GUITile tile = new GUITile(tiles.get(i).getLetter(), 0, i);
            tile.setLetterLabel(tiles.get(i).getLetter());
            tile.setColor(Color.FLORALWHITE);
            Rectangle rectangle = new Rectangle(40, 40, Color.TRANSPARENT);
            tile.setTop(rectangle);
            GridPane.setRowIndex(rectangle, 0); 
            GridPane.setColumnIndex(rectangle, i);
            tileList.add(tile);
        }

        for(GUITile tile : tileList){
            rack.add(tile, tile.getCol(), tile.getRow());
        }
        HBox innerRack = new HBox(rack);
        innerRack.setAlignment(Pos.CENTER);
        innerRack.setSpacing(10);
        innerRack.setPrefHeight(70);
        VBox outerRack = new VBox(innerRack);
        outerRack.setMaxWidth(tileList.size() * 60);
        outerRack.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
        

        return outerRack;
    }
    public void highLightTile(int index){
        
    }

    //TODO : FIX THIS
    public VBox updateRack(ArrayList<Tile> tiles, VBox outerRack){
        HBox rack = new HBox();
        for (int i = 0; i < tiles.size(); i++){
            GUITile tile = new GUITile(tiles.get(i).getLetter(), 0, i);
            tile.setLabel(tiles.get(i).getLetter() + "");
            tile.setColor(Color.FLORALWHITE);
            rack.getChildren().add(tile);
        }

        outerRack.setAlignment(Pos.CENTER);
        outerRack.setSpacing(10);
        outerRack.setPrefHeight(80);
        outerRack.setPrefWidth(tiles.size() * 60);
        return outerRack;
    }
    /**
     * when user ends game with endGame button, end screen will tell them the final score
     * @return Scene with end of game information
     */
    public Scene createEndScene(Boolean won){
        VBox hold = new VBox();
        HBox holdMessage = new HBox(hold);

        Label endGameMessage = new Label();
        if(won)endGameMessage.setText("You Won!");
        else endGameMessage.setText("Game Ended by User");

        Label scoreLabel = new Label("0");

        scoreLabel.setFont(Font.font("Times New Roman", 20.0));
        endGameMessage.setFont(Font.font("Times New Roman", 30.0));

        hold.getChildren().addAll(endGameMessage, scoreLabel);
        hold.setAlignment(Pos.CENTER);
        holdMessage.setAlignment(Pos.CENTER);
        hold.setSpacing(20);

        StackPane layout = new StackPane();
        layout.getChildren().add(holdMessage);
        layout.setBackground(new Background(new BackgroundFill(Color.FLORALWHITE, CornerRadii.EMPTY, Insets.EMPTY)));
       
        return new Scene(layout, WIDTH, HEIGHT);
    }
    
}
