package com.jordanweschler.trie;

/**
 * The goal of this is to be the interface that works with the Trie. Information from the keyboard will be passed
 *  to this object and then this object will return the "predictions"
 */
public class TrieController {

    private Trie trie;
    private StringBuilder currentWord;

    public TrieController() {
        trie = new Trie();
        currentWord = new StringBuilder();
    }

    /**
     *
     * @param character technically doesn't need to be a single character
     * @return
     */
    public String[] addCharacter(String character) {
        currentWord.append(character);

        return predictions();
    }

    public String[] newWord() {
        trie.insert(currentWord.toString());
        currentWord = new StringBuilder();

        return predictions();
    }

    private String[] predictions() {
        return trie.getPredictions(currentWord.toString(), 3);
    }
}
