
import java.util.*;
import java.io.*;

/**
 * Melody controls Nodes and Play Songs.
 *
 * <ul>
 * <li> Name: Melody.java
 * <li> Description: Melody
 * <li> Class: Java 145
 * <li> Instructor: Ken Hang
 * <li> Date: Feb 4 2015
 * </ul>
 *
 * @author  Hai H Nguyen (Bill)
 * @version Winter 2015
 */

public class Melody {
    private Queue<Note> notes;

    private double length;

    private int size;

    public static int repeat = 1;   // How many time all melody should
                                    // rewind a repeat section

    /**
     * Main Constructor
     * Initialize Node Queue and Length
     */
    public Melody(){
        notes = new LinkedList<Note>();

        length = 0;

        size = 0;
    }

    /**
     * Scan melody files
     * @param in       Scanner object with path to file
     */
    public void input (Scanner in){
        while (in.hasNext()){
            double duration = in.nextDouble();

            Pitch node = Pitch.valueOf(in.next());

            Note nextNote = (node.equals(Pitch.R)) ?
                    (new Note (duration, node, in.next().equals("true"))):
                    (new Note (duration, node, in.nextInt(),
                            Accidental.valueOf(in.next()),
                            in.next().equals("true")));

            notes.add (nextNote);

            length += nextNote.getDuration();
        }

        size = notes.size();
    }

    /**
     *  Print the Song, one note per line
     * @param out       PrintStream object to print out
     */
    public void output (PrintStream out){
        for (int i = 0 ; i < size; ++i){
            notes.add(notes.peek());

            out.println(notes.remove().toString());
        }
    }

    /**
     * Change The tempo of the Melody by a Factor
     * @param tempo     Factor to alter the Current tempo
     */
    public void changeTempo (double tempo){
        length *= tempo;

        for (int i = 0; i < size; ++i){
            notes.peek().setDuration(notes.peek().getDuration() * tempo);

            notes.add(notes.remove());
        }
    }

    /**
     * Get the Length of the Melody
     * @return      Length of the Melody
     */
    public double getLength (){
        return length;
    }

    /**
     * Reverse the Order of nodes in the Melody
     */
    public void reverse(){
        Stack<Note> noteStack = new Stack<Note>();

        while (!notes.isEmpty()) {
            noteStack.push(notes.remove());
        }

        while (!noteStack.isEmpty()){
            notes.add(noteStack.pop());
        }
    }

    /**
     * Append the given other Melody to the Current melody
     * @param other     Melody to be appended
     */
    public void append (Melody other){
        Queue<Note> noteQueue = new LinkedList<Note>(other.notes); // Preserve

        for (int i = 0; i < other.size; ++i){
            notes.add(noteQueue.remove());
        }

        length += other.length;

        size = notes.size();
    }

    /**
     * Play the melody
     */
    public void play(){
        Queue<Note> noteQueue = new LinkedList<Note>(notes); // Preserve main Queue

        play(noteQueue, repeat);
    }

    /**
     * Play the melody at a given moment
     * @param time      The time to start playback the melody
     */
    public void play (double time){
        Queue<Note> noteQueue = new LinkedList<Note>(notes); // Preserve main Queue

        while (time > 0){
            time -= noteQueue.remove().getDuration();
        }

        play(noteQueue, repeat);
    }

    /**
     * Play method which accept a queue of node and
     * a number which indicate how many time to repeat a loop
     * @param noteQueue     Queue of Node to Play
     * @param repeat        How many time to repeat the loop
     */
    private void play (Queue<Note> noteQueue, int repeat){
        while(!noteQueue.isEmpty()){ // Preserve User's Input
            if (noteQueue.peek().isRepeat()) {
                Queue<Note> repeatQueue = new LinkedList<Note>();

                do {        // Add First, then Check Empty and not Repeat
                    repeatQueue.add(noteQueue.remove());
                } while ((!noteQueue.isEmpty()) &&
                        (!noteQueue.peek().isRepeat()));

                if (!noteQueue.isEmpty()) { // Preserve Musician's Input
                    repeatQueue.add(noteQueue.remove());
                }

                for (int i = 0; i <= repeat; ++i){
                    for (int j = 0; j < repeatQueue.size(); ++j) {
                        play(repeatQueue, true);
                    }
                }
            } else {
                play(noteQueue, false);
            }
        }
    }

    /**
     * Play method which rewind if noteQueue is a repeat section
     * and play the first node of the noteQueue
     * @param noteQueue     Queue to Play
     * @param repeating     Indicate if it is repeating or not
     */
    private void play (Queue<Note> noteQueue, boolean repeating){
        if (repeating) {
            noteQueue.add(noteQueue.peek());        //Rewind, Size preserved
        }

        noteQueue.remove().play();
    }
}