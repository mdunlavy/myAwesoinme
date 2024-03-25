

public class Tile {
        private char letter;
        private int points;
        private int letterMultiplier = 1;
        private int wordMultiplier = 1;
        private boolean canUseMultiplier = true;
        private boolean isWild = false;

      

      public Tile (char letter){
        this.letter = letter;
        switch (Character.toLowerCase(letter)){
          case 'a':
          case 'e':
          case 'i':
          case 'o':
          case 'n':
          case 'r':
          case 't': 
          case 'l':
          case 's':
          case 'u': points = 1; break;
          case 'd':
          case 'g': points = 2; break;
          case 'b':
          case 'c':
          case 'm':
          case 'p': points = 3; break;
          case 'f':
          case 'h':
          case 'v':
          case 'w':
          case 'y': points = 4; break;
          case 'k': points = 5; break;
          case 'j':
          case 'x': points = 8; break;
          case 'q':
          case 'z': points = 10; break;
          default: points = 0; break;
        }
      }
      public Tile (int wordMultiplier, int letterMultiplier){
        this.letter = ' ';
        this.letterMultiplier = letterMultiplier;
        this.wordMultiplier = wordMultiplier;
      }
      
        public int getLetterMultiplier(){
          return letterMultiplier;
        }
        public int getWordMultiplier(){
          return wordMultiplier;
        }
        public boolean canUseMultiplier(){
          return canUseMultiplier;
      }
      public void useMultiplier(){
        canUseMultiplier = false;
      }
      public void setWild(){isWild = true;}
      public boolean isWild(){return isWild;}
        
     public char getLetter(){return letter;}
     public int getPoints(){return points * letterMultiplier;}
     
      
    } // Add this closing curly brace
