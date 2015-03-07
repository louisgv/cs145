import sun.security.ssl.Debug;

import java.util.*;

/**
 * This class Searchs for Anagrams of a Word given a Dictionary.
 *
 *  It first store a dictionary for reference.
 *  When Given a word, the print function finds in the dictionary
 *  Anagrams which could be extracted.
 *
 *  For every new word query, the program stores available anagrams of
 *  that word into a map. Thus future query of that same word is faster.
 *
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
    // anagrams of queried words, Scalable
    private Map<String, List<String>> anagramDictionary =
            new HashMap<String, List<String>>();

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

            prepareAnagramDictionary();
        }
    }

    private void prepareAnagramDictionary(){
        for (String word : dictionary) {
            // Extract a Letter inventory from word
            LetterInventory key = new LetterInventory(word);

            if (anagramDictionary.containsKey(key.toString())){
                anagramDictionary.get(key.toString()).add(word);
            } else {
                List<String> values = new ArrayList<String>();

                values.add(word);

                anagramDictionary.put(key.toString(),values);
            }
        }
    }

    /**
     * Extract words found from the given String into a Stack
     * @param sLi         String to Extract word
     */
    private List<String> allAnagramsOf(LetterInventory sLi){
        List<String> sWs = new ArrayList<String>();

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

        return sWs;
    }

    private List<LetterInventory> anagramsOf(LetterInventory sLi){
        List<LetterInventory> sLiLiL = new ArrayList<LetterInventory>();

        for (String word : dictionary) {
            // Extract a Letter inventory from word
            LetterInventory wordLi = new LetterInventory(word);
            // Extract a Letter inventory of letters not in passed string
            LetterInventory pLi = sLi.subtract(wordLi);
            // If extracted inventory is not null || the subtraction was a success
            if (pLi != null){
                sLiLiL.add(wordLi);
            }
        }

        return sLiLiL;
    }


    private void printStack (Stack<String> out, LetterInventory key, int max){
        if (out.size() < max){

            List<String> choiceList = anagramDictionary.get(key);

            if (choiceList != null) {
                for (String choice : choiceList) {
                    LetterInventory choiceLi = new LetterInventory(choice);

                    LetterInventory leftOverLi = key.subtract(choiceLi);

                    out.push(choice);

                    if (leftOverLi.isEmpty()) {
                        debugLog(out);
                    } else {
                        printStack(out, leftOverLi, max);
                    }
                }
            }
        }
    }

    /**
     * Print anagrams set of a given words. Each set is
     * restricted to a maximum word.
     * @param s                             String to search for anagram
     * @param max                           Maximum words for each anagram series
     * @throws IllegalArgumentException     If max is smaller than 0
     */
    public void print(String s, int max) {
        if (max < 0){
            throw new IllegalArgumentException("Max < 0");
        } else {
            LetterInventory sLi = new LetterInventory(s);

            // Implement it as a list of Li instead, and traverse through that list.
            List<LetterInventory> sLs = anagramsOf(sLi);

//            debugLog(sLs);
            List<String> sLs = allAnagramsOf(sLi);

            Stack<String> answerStack = new Stack<String>();

            debugLog(anagramDictionary);

            debugLog(anagramDictionary.keySet());

            debugLog(sLs.get(0));

            if (anagramDictionary.containsKey(sLs.get(0))) {
                debugLog("It contains it!");

                debugLog(anagramDictionary.get(sLs.get(0)));
            } else {
                debugLog("It is Null!");
            }
            for (LetterInventory sLsLi : sLs)
                printStack(answerStack, sLsLi, max);

        }
    }

    private void debugLog(Object o){
        if(o!= null) {
            System.out.println(o.toString());
        }
    }
}

//stackoverflow.com/questions/12477339/finding-anagrams-for-a-given-word