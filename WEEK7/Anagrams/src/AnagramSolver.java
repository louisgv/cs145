import java.util.*;

/**
 * This class Search for Anagrams of a Word given a Dictionary.
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
            } else  {
                List<String> values = new ArrayList<String>();

                values.add(word);

                anagramDictionary.put(key.toString(),values);
            }
        }
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


    private void printStack (Stack<String> out, LetterInventory root, List<LetterInventory> choices, int max){
        if (root != null) {

//	        debugLog("Letters to use: " + root);
//
//          debugLog("Choices: " + choices);
//
//          debugLog("Chosen: " + out);

	        if (root.isEmpty()){

		        debugLog("Chosen: " + out);

	        } else {
		        for (LetterInventory choice : choices) {

			        LetterInventory leftOverLetters = root.subtract(choice);

			        List<String> words = anagramDictionary.get(choice.toString());

			        for (String word : words) {
				        out.push(word);

				        printStack(out, leftOverLetters, choices, max);

				        out.pop();
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
            LetterInventory lettersToUse = new LetterInventory(s);

            debugLog(lettersToUse);

            // Implement it as a list of Li instead, and traverse through that list.
            List<LetterInventory> choices = anagramsOf(lettersToUse);

            debugLog("Choices are: " + choices);

            Stack<String> answerStack = new Stack<String>();

            printStack(answerStack, lettersToUse, choices, max);

        }
    }

    private void debugLog(Object o){
        if(o!= null) {
            System.out.println(o.toString());
        }
    }
}

//stackoverflow.com/questions/12477339/finding-anagrams-for-a-given-word