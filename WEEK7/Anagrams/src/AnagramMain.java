// Stuart Reges
// 5/9/05
//
// AnagramMain contains a main program that prompts a user for the name of a
// dictionary file and then gives the user the opportunity to find anagrams of
// various phrases.  It constructs an AnagramSolver object to do the actual
// search for anagrams that match the user's phrases.

import java.io.*;
import java.util.*;

public class AnagramMain {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println("Welcome to the cse143 anagram solver.");
        System.out.println();

        // open the dictionary file
        Scanner console = new Scanner(System.in);
        System.out.print("What is the name of the dictionary file? ");
        Scanner input = new Scanner(new File(console.nextLine()));

        // read dictionary into an ArrayList
        List<String> dictionary = new ArrayList<String>();
        while (input.hasNextLine())
            dictionary.add(input.nextLine());

        // solve anagrams
        List<String> dictionary2 = Collections.unmodifiableList(dictionary);
        AnagramSolver solver = new AnagramSolver(dictionary2);
        boolean done = false;
        while (!done) {
            System.out.println();
            System.out.print("phrase to scramble (return to quit)? ");
            String phrase = console.nextLine();
            if (phrase.length() == 0)
                done = true;
            else {
                System.out.print("Max words to include (0 for no max)? ");
                int max = console.nextInt();
                console.nextLine();  // to skip newline after int
                solver.print(phrase, max);
            }
        }
    }
}
