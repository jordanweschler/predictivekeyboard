package com.jordanweschler.trie;

/**
 * Currently the Trie class relies on the default of 26 for the alphabet.
 * There are no plans to change this, and as such storing the specific character
 * is not needed. a-z maps 0-25 and can easily be computed as such with a lighter
 * memory footprint
 */
public class Trie {

    // Contant for all 26 lowercase english letters
    private static final int DEFAULT_ALPHABET_SIZE = 26;

    private static final char[] LOWERCASE_LETTERS =
            {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private int alphabetSize;
    private Trie[] children;
    private int confidence;

    public Trie() {
        this(DEFAULT_ALPHABET_SIZE);
    }

    /**
     * Constructor with variable alphabet size for future proofing
     *  (although real future proofing would include the char needed)
     * @param alphabetSize
     */
    public Trie(int alphabetSize) {
        this.alphabetSize = alphabetSize;
        children = new Trie[alphabetSize];
        confidence = 0;
    }

    /**
     * Move through Trie based off input string, once last character is found
     * add 1 to the confidence. That represents a finished word
     * @param key
     */
    public void insert(String key) {
        int c = ((int) key.charAt(0)) - 97;

        if (children[c] == null) {
            children[c] = new Trie(alphabetSize);
        }

        if (key.length() > 1) {
            children[c].insert(key.substring(1));
        } else {
            children[c].confidence++;
        }
    }

    /**
     * Mainly for debug
     * @return string representation of the Trie
     */
    public String toString() {
        StringBuilder sb = new StringBuilder("");

        for (int i = 0; i < alphabetSize; i++) {
            if (children[i] != null) {
                sb.append(LOWERCASE_LETTERS[i]);
                sb.append(children[i].confidence);
                sb.append(" ");
                sb.append(children[i].toString());
            }
        }

        return sb.toString();
    }
}