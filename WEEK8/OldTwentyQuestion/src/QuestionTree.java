import java.io.*;
import java.util.*;

/**
 * This class models a Binary Question Tree.
 *
 *  It first initialize an empty Question Node as its root.
 *  If a previous game data exist, user can have it loaded into the tree
 *  When a game is finished, user can have the tree save its structure
 *  When a game is played, the tree expands per lost game
 *  using data given by user
 *
 * <ul>
 * <li> Name: QuestionTree.java
 * <li> Description: Question Tree
 * <li> Class: Java 145
 * <li> Instructor: Ken Hang && Janet Ash
 * <li> Date: March 16 2015
 * </ul>
 * @author  Hai H Nguyen (Bill)
 * @version Winter 2015
 */

public class QuestionTree {
    private QuestionNode root;

    private Scanner console;

    private int games;

    private int wins;

    /**
     * Constructor which initialize the QuestionNode
     * and the console Scanner
     */
    public QuestionTree(){
        root = new QuestionNode("computer");

        console = new Scanner(System.in);

        games = 0;

        wins = 0;
    }

    /**
     * Play a complete guessing game
     * and rebuild the tree as necessary
     */
    public void askQuestions() {
        root = playedNode(root);

        ++games;
    }

    private QuestionNode playedNode(QuestionNode node){
        if (node.isAnswer()) { // If a node is an Answer node
            if (yesTo ("Would your object happen to be " + node + "?")) {
                debugLog("Great, I got it right!"); // If user accepts the answer
                // Increase the amount of time the machine's won
                ++wins;
            } else { // Else the machine learn a new question node
                node = learnedNode(node);
            }
        } else { // If node is a Question node
            if (yesTo (node.toString())) {
                node.yes = playedNode(node.yes);
            } else {
                node.no = playedNode(node.no);
            }
        }
        return node;
    }

    private QuestionNode learnedNode(QuestionNode node){
        debugLog("What is the name of your object? ");
        // Make new answer node for the new object
        QuestionNode newNode = new QuestionNode (console.nextLine());

        debugLog("Please give me a yes/no question that\n" +
                 "distinguishes between your object\n" +
                 "and mine--> ");
        // Get user's question
        String query = console.nextLine();

        debugLog("And what is the answer for your object? (y/n)?");
        // Make new Question Node per user's command
        return yesTo("And what is the answer for your object? (y/n)? ") ?
                new QuestionNode ( newNode, node, query ):
                new QuestionNode ( node, newNode, query );
    }

    /**
     * Invoke the Save Recursion and output
     * the Question Tree into the output Stream
     * @param output        Output object to Stream data
     */
    public void write (PrintStream output){
        save(output, root);
    }

    private void save(PrintStream out, QuestionNode node){
        if (node.isAnswer()){
            out.print("A:\n" + node);
        } else {
            out.println("Q:\n" + node);

            save(out, node.yes);

            out.println();
            // Avoid new line at the end
            save(out, node.no);
        }
    }

    /**
     * Invoke the Load recursion and build
     * the Question Tree in a binary fashion
     * @param input         Scanner Object to get Data
     */
    public void read (Scanner input){
        root = loadedNode (input);
    }

    private QuestionNode loadedNode (Scanner input){
        QuestionNode node = null; // Initialise a null node.

        if (input.hasNext()){
            String[] data = input.nextLine().split(":",2); // Get Data and Split Once
            // If Begins with A
            if (data[0].equals("A")){// Create Answer node
                node = new QuestionNode(data[1]);
            } else { // Else Create Question node
                node = new QuestionNode (   loadedNode(input),
                        loadedNode(input),
                        data[1]);
            }
        }

        return node;
    }

    private void debugLog(Object o){
        if(o!= null) {
            System.out.println(o.toString());
        }
    }

    /**
     * Asks the user a question, forcing an answer of "y " or "n";
     * @param prompt    Message to print first
     * @return          True if the answer was yes, false otherwise
     */
    public boolean yesTo (String prompt){
        System.out.print(prompt + " (y/n)? ");
        String response = console.nextLine().trim().toLowerCase();
        while (!response.equals("y") && !response.equals("n")) {
            System.out.println("Please answer y or n.");
            System.out.print(prompt + " (y/n)? ");
            response = console.nextLine().trim().toLowerCase();
        }
        return response.equals("y");
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
}//IS29