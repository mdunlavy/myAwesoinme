Scrabble 

What Works: 
- Dictionary saved in trie structure
    - Adding, deleting, searching (words & prefixes) 
- Finding all words currently on the board, making sure they exist in the dictionary
- Input Processing, can take in and process different size boards, find all words, find differences between two boards
- Confirming Plays that move in one direction 
- Scoring plays that do not create multiple words at once - if just one new word is played, the scoring is accurate
- Computer will play valid words that are among the highest scoring for the given board configuration
- Computer uses anchoring method to find best words available, plays the best one and updates board
- GUI initializes, displays board, you can place letters down and try to play the word if you think it is valid


What Doesn't Work: 
- There can be gaps in the board between words, but the words can still be played in some cases. 
- anchoring method I used will not select an empty tile as an anchor, so some test cases are not being passed 
    - I tried to change my logic for this, but it ended up breaking too much of my algorithm
- Taking tiles back off the board once they're placed doesn't work
- Can't resolve wild card tiles for command line IO
- GUI 
- if multiple words are created by one legal play, the scoring doesn't accurately count the overlapping tiles to be used

