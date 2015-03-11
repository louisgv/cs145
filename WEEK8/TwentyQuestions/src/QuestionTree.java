import java.io.*;
import java.util.*;

/**
 * This class Search for Anagrams of a Word given a Dictionary.
 *
 *  It first store a dictionary for word reference.
 *  Then pre-process data from the dictionary, forming an
 *  Anagram Dictionary for faster anagram look-up.
 *
 *  When Given a word, the print function looks for
 *  all anagrams from the Dictionary
 *  and print anagrams from the Anagram Dictionary
 * *
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

public class QuestionTree {

    private QuestionNode root;

    private UserInterface uInter;

    private int games;

    private int wins;

    /**
     * Constructor which initialize the QuestionNode
     *
     * @param ui            The passed User Interface
     */
    public QuestionTree(UserInterface ui){
        root = new QuestionNode("computer");

        uInter = ui;

        games = 0;

        wins = 0;
    }

    public void learn(QuestionNode node){
        uInter.print("I lose. What is your object? ");

        QuestionNode answer = new QuestionNode(uInter.nextLine());

        uInter.print("Type a yes/no question to distinguish " +
                "your item from " + node + ":");

        String query = uInter.nextLine();

        uInter.print("And what is the answer for your object?");

        root = uInter.nextBoolean() ?
                new QuestionNode(answer, node, query):
                new QuestionNode(node, answer, query);
    }

    /**
     * Play a complete guessing game
     */
    public void play() {
        play(root);

        ++games;
    }

    private void play(QuestionNode node){
        if (node.isAnswer()) {
            uInter.print("Would your object happen to be " + node + "?");

            if (uInter.nextBoolean()) {
                uInter.println("I win!");

                ++wins;
            } else {
                learn(node);
            }
        } else {
            uInter.print(node.toString());

            play(uInter.nextBoolean() ? node.yes : node.no);
        }
    }

    /**
     * Invoke the Save Recursion
     * @param output        Output object to Stream data
     */
    public void save (PrintStream output){
        save(output, root);
    }

    private void save(PrintStream out, QuestionNode node){
        if (node.isAnswer()){
            out.println("A:" + node);
        } else {
            out.println("Q:" + node);

            save(out, node.yes);

            save(out, node.no);
        }
    }

    /**
     * Invoke the Load recursion
     * @param input         Scanner Object to get Data
     */
    public void load (Scanner input){
        root = loadedNode (input);
    }

    private QuestionNode loadedNode (Scanner input){
        QuestionNode node = null;

        if (input.hasNext()){
            String[] data = input.nextLine().split(":",2);

            if (data[0].equals("A")){
                node = new QuestionNode(data[1]);
            } else {
                node = new QuestionNode (data[1]);

                node.yes = loadedNode(input);

                node.no = loadedNode (input);
            }
        }

        return node;
    }

    /**
     * @return      How many games had been played
     */
    public int totalGames(){
        return games;
    }

    /**
     * @return      How many games the machine had won
     */
    public int gamesWon(){
        return wins;
    }
}
