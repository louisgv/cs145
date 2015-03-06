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
    private Map<String, Set<String>> anagramDictionary = new HashMap<String, Set<String>>();

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
        if(o!= null) {
            System.out.println(o.toString());
        }
    }

    /**
     * Extract words found from the given String into a Stack
     * @param s         String to Extract word
     */
    private Set<String> extractWords(String s){
        LetterInventory sLi = new LetterInventory(s);

        Set<String> sWs = new LinkedHashSet<String>();

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

    public LetterInventory root(String chosen, LetterInventory lettersToUse){
        return lettersToUse = lettersToUse.subtract(new LetterInventory(chosen));
    }

    public boolean accept (){
        return true;
    }

    public String first (String chosen, LetterInventory lettersToUse){
        return "something";
    }

    public void print(String s, int max) {
        if(anagramDictionary.containsKey(s)){
            if (!s.isEmpty()){
                //Recursively pullout the anagrams here!!
                Set<String> choices = anagramDictionary.get(s);

                LetterInventory lettersToUse = new LetterInventory(s);

                LetterInventory chosen = new LetterInventory(choices.iterator().next());

                lettersToUse = lettersToUse.subtract(chosen);

                print(lettersToUse.toString(), max);
            }
        } else{
            anagramDictionary.put(s,extractWords(s));

            print (s,max);
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
