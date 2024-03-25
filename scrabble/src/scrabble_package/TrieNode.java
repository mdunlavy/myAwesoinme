import java.util.HashMap;

public class TrieNode{
    private char letter;
    private HashMap<Character, TrieNode> children = new HashMap<Character, TrieNode>();
    private boolean isEndOfWord;

    TrieNode(){ }
    
    TrieNode(char letter){
        this.letter = letter;
    }
    public HashMap<Character, TrieNode> getChildren(){
        return children;
    }
    public char getLetter(){
        return letter;
    }
    public boolean isEndOfWord(){
        return isEndOfWord;
    }
    public void setEndOfWord(boolean isEndOfWord){
        this.isEndOfWord = isEndOfWord;
    }
    public void setChildren(HashMap<Character, TrieNode> children){
        this.children = children;
    }
}