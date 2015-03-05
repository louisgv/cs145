import sun.security.ssl.Debug;

import java.util.*;

/**
 * This class transforms Grammar rules into Random sentences.
 *
 *  It first read in the rule file, extract data into a map
 *  with non_terminal as key, and values as associated rules
 *  for each non_terminal.
 *
 *  Upon receiving a symbol, the object pre-process the symbol,
 *  strip it from non-rule characters, then compares it with
 *  the set of key to generate the random sentence.
 *
 *  User can generate sentence without the '<' and '>' brackets
 * <ul>
 * <li> Name: AnagramSolver.java
 * <li> Description: Anagram Solver
 * <li> Class: Java 145
 * <li> Instructor: Ken Hang && Janet Ash
 * <li> Date: March 10 2015
 * </ul>
 * @author  Hai H Nguyen (Bill)
 * @version Winter 2015
 */
public class AnagramSolver {

    private List<String> dictionary;

    /**
     * Constructor, given a list, Initialize the dictionary
     * @param dictionary List of words
     * @throws IllegalArgumentException If Dictionary is Empty
     */
    public AnagramSolver(List<String> dictionary) {
        if (dictionary.isEmpty()) {
            throw new IllegalArgumentException("Dictionary is Empty!");
        } else {
            this.dictionary = dictionary;
        }
    }

    public void debugLog(Object o){
        if(o!= null)
            System.out.println(o.toString());
    }

    /**
     * Extract words found from the given String into a Stack
     * @param s         String to Extract word
     */
    public void extractWords(String s){
        LetterInventory sLi = new LetterInventory(s);

        Queue<String> sWs = new LinkedList<String>();

        for (String word : dictionary) {
            // Extract a Letter inventory from word
            LetterInventory wordLi = new LetterInventory(word);
            // Extract a Letter inventory of letters not in passed string
            LetterInventory pLi = sLi.subtract(wordLi);
            // If extracted inventory is not null || the subtraction was a success
            if (pLi != null){
                sWs.add(word);
            }
        }
        debugLog(sWs);
        while (!sWs.isEmpty()) {
            wm(sWs.remove(), s);
        }
    }

    public void wm(String sFs, String sR){
        LetterInventory sLi = new LetterInventory(sR);

        LetterInventory sFsLi = new LetterInventory(sFs);
        LetterInventory pLi = sLi;
        while (pLi != null){

            Stack<String> out = new;
            debugLog(sR);

            pLi = pLi.subtract(sLi);
        }
    }

    public void print(String s, int max) {



    }

/*
    private Stack<String> backtrack(String newWords) {

        Stack<String> words = subset(Stack < String >);

        Stack<String> out = subset(Stack < String >);

        for (word: words) {
            out.push(anagramsOf(word));
        }


        backtrack(word);
    }

    private String anagramsOf(String s) {
        Stack n = new Stack;
        for (comb: s) {
            n.push(dictionary.found(comb));
        }

    }

    public void print(String s, int max) {

        if (max < 0) {
            throw new IllegalArgumentException("Max is Negative?!");
        } else {

            LetterInventory sInventory = new LetterInventory(s);

            for (word in sInventory) {
                print (s, max);
            }
        }
    }*/

}
