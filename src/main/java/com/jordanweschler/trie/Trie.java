package com.jordanweschler.trie;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Trie {

    // 128 is the base ASCII table
    private static final int DEFAULT_ALPHABET_SIZE = 128;

    private static final char[] LOWERCASE_LETTERS =
            {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm',
                    'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};

    private int alphabetSize;
    private Trie[] children;
    private int confidence;

    /**
     * Node class for PriorityQueue
     */
    private class Node {
        int confidence;
        String word;

        Node (String word, int confidence) {
            this.word = word;
            this.confidence = confidence;
        }
    }

    private class NodeComparator implements Comparator<Node> {
        public int compare(Node n1, Node n2) {
            return n2.confidence - n1.confidence;
        }
    }

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
     *
     * @param base
     * @param predictionCount
     * @return
     */
    public String[] getPredictions(String base, int predictionCount) {
        Trie current = this;

        for (int stringIndex = 0; stringIndex < base.length(); stringIndex++) {
            int trieIndex = (int) base.charAt(stringIndex);

            if (current.children[trieIndex] != null) {
                current = current.children[trieIndex];
            } else {
                current = null;
                break;
            }
        }

        String[] predictions = new String[predictionCount];

        if (current != null) {
            PriorityQueue<Node> potentialWords = new PriorityQueue<Node>(new NodeComparator());

            current.predictionHelper(potentialWords, base);

            for (int i = 0; i < predictionCount; i++) {
                if (potentialWords.peek() != null && potentialWords.peek().confidence > 0) {
                    predictions[i] = potentialWords.poll().word;
                } else {
                    predictions[i] = "";
                }
            }

        } else {
            for (int i = 0; i < predictionCount; i++) {
                predictions[i] = "";
            }
        }

        return predictions;
    }

    private void predictionHelper(PriorityQueue<Node> predictions, String base) {
        for (int i = 0; i < alphabetSize; i++) {
            if (children[i] != null) {
                predictions.add(new Node(base + (char) i, children[i].confidence));
                children[i].predictionHelper(predictions, base + (char) i);
            }
        }
    }

    /**
     * Move through Trie based off input string, once last character is found
     * add 1 to the confidence. That represents a finished word
     * @param key
     */
    public void insert(String key) {
        int c = (int) key.charAt(0);

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
                sb.append((char) i);
                sb.append(children[i].confidence);
                sb.append(" ");
                sb.append(children[i].toString());
            }
        }

        return sb.toString();
    }
}