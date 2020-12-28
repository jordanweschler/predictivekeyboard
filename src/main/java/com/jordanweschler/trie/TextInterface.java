package com.jordanweschler.trie;

import java.util.Scanner;

/**
 * Completely for basic testing
 * Requires an "enter" between each letter, empty line input = space
 */
public class TextInterface {

    private Trie history;

    public TextInterface() {
        history = new Trie();
    }

    /**
     * Basically the driver of this
     */
    public void start() {
        Scanner stdin = new Scanner(System.in);

        while (true) {
            String input = stdin.nextLine();

            if (input.equals("exit")) {
                break;
            }

            if (input.length() > 0) {
                history.insert(input);
                System.out.println(history.toString());
            }
        }

        stdin.close();
    }
}