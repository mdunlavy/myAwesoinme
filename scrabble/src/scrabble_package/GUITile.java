import java.awt.Button;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;

public class GUITile extends StackPane{
    public char letter;
    public int row;
    public int col;
    public boolean hasMultiplier = false;
    private Rectangle top = new Rectangle(50,50, Color.TRANSPARENT);

    public GUITile(char letter, int row, int col){
        this.letter = letter;
        this.row = row;
        this.col = col;
        this.getChildren().add(new Rectangle(50,50, javafx.scene.paint.Color.FLORALWHITE));

    }
    public void setTop(Rectangle top){
        this.top = top;
        this.getChildren().add(top);
    }
    public char getLetter(){
        return letter;
    }
    public int getRow(){
        return row;
    }
    public int getCol(){
        return col;
    }
    public void setLabel(String Label){
        if (!Label.equals("")){
            hasMultiplier = true;
        }
        Label label = new Label(Label);
        label.setFont(new Font("Arial", 10));
        label.setTextFill(javafx.scene.paint.Color.BLACK);
        this.getChildren().addAll(label);
        this.getChildren().add(top);
;        StackPane.setAlignment(label, Pos.CENTER);
    }
    public void makeStar(){
        Rectangle star = new Rectangle(20,20);
        star.rotateProperty().set(45);
        star.setFill(javafx.scene.paint.Color.WHEAT);
        Rectangle star2 = new Rectangle(20,20);
        star2.setFill(javafx.scene.paint.Color.WHEAT);
        star2.rotateProperty().set(90);
        this.getChildren().add(star);
        this.getChildren().add(star2);
        this.getChildren().add(top);
    }
    public void setColor(javafx.scene.paint.Color color){
        ((Rectangle)this.getChildren().get(0)).setFill(color);
        
    }
    public void setLetterLabel(char letter){
        if (hasMultiplier){
            this.getChildren().clear();
        }
        Label label = new Label(letter+"");
        label.setFont(new Font("Arial", 30));
        label.setTextFill(javafx.scene.paint.Color.BLACK);
        this.getChildren().addAll(new Rectangle(50,50, javafx.scene.paint.Color.FLORALWHITE),label);
        StackPane.setAlignment(label, Pos.CENTER);
        this.getChildren().add(top);
    }
    
}
