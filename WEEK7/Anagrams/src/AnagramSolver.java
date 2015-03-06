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
    private Map<LetterInventory, List<String>> anagramDictionary =
            new HashMap<LetterInventory, List<String>>();
    
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

            debugLog(anagramDictionary);
        }
    }

    private void prepareAnagramDictionary(){
        for (String word : dictionary) {
            // Extract a Letter inventory from word
            LetterInventory key = new LetterInventory(word);

            if (anagramDictionary.containsKey(key)){
                anagramDictionary.get(key).add(word);
            } else {
                List<String> values = new ArrayList<String>();

                values.add(word);

                anagramDictionary.put(key,values);
            }
        }
    }

    public void debugLog(Object o){
        if(o!= null) {
            System.out.println(o.toString());
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

    private void anagramsOf (LetterInventory sLi){


    }

    private void printStack (Stack<String> ans, int max){

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

            List<String> sLs = allAnagramsOf(sLi);

            debugLog(sLs);

            debugLog(sLi);
        }
    }
}

//    procedure bt(c)
//        if reject(P,c) then return
//        if accept(P,c) then output(P,c)
//        s ← first(P,c)
//        while s ≠ Λ do
//        bt(s)
//        s ← next(P,s)

//      Partial candidate c is: The First word!

//        root(P): return the partial candidate at the root of the search tree.
//        reject(P,c): return true only if the partial candidate c is not worth completing.
//        accept(P,c): return true if c is a solution of P, and false otherwise.
//        first(P,c): generate the first extension of candidate c.
//        next(P,s): generate the next alternative extension of a candidate, after the extension s.
//        output(P,c): use the solution c of P, as appropriate to the application.
