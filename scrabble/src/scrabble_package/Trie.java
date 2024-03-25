import java.util.HashMap;

public class Trie{
 
    private static TrieNode root;

    public Trie(){
        root = new TrieNode();
    }

    public void addWord(String word){
        HashMap<Character, TrieNode> children = root.getChildren();
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            TrieNode node;
            if(children.containsKey(c)) {
                node = children.get(c);
            } else { 
                node = new TrieNode(c);
                children.put(c, node);
            }
            children = node.getChildren();

            if(i == word.length() - 1) {
                node.setEndOfWord(true);
            }
        }
    }
    
     public boolean searchDictionary(String word) {
        HashMap<Character, TrieNode> children = root.getChildren();
        TrieNode node = null;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(children.containsKey(c)) {
                node = children.get(c);
                children = node.getChildren();
            } else { 
                node = null;
                break;
            }
        }
        if(node != null && node.isEndOfWord()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean findPrefix(String word) {
        word = word.toLowerCase();
        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (cur.getChildren().containsKey(c)) {
                cur = cur.getChildren().get(c);
            } else {
                return false;
            }
        }
        return true;
    }

}