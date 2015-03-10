// CSE 143 Homework 6: 20 Questions

/**
 * Interface describing abstract user interaction operations.
 * This interface is implemented by the graphical and text UIs for the game.
 * Your QuestionTree interacts with the UI through this interface.
 * @author Marty Stepp
 * @version 2010/02/20
 */
public interface UserInterface {
    /**
     * Waits for the user to input a yes/no answer (by typing, clicking, etc.),
     * and returns that answer as a boolean value.
     * @return the answer typed by the user as a boolean (yes is true, no is false)
     */
    boolean nextBoolean();

    /**
     * Waits for the user to input a text value, and returns that answer as a String.
     * @return the answer typed by the user as a String (empty string if no answer typed)
     */
    String nextLine();
    
    /**
     * Displays the given output message to the user.
     * @param message The message to display.  Assumes not null.
     */
    void print(String message);
    
    /**
     * Displays the given output message to the user.
     * If the UI is a text UI, also inserts a line break (\n).
     * @param message The message to display.  Assumes not null.
     */
    void println(String message);

    // various messages that are output by the user interface
    // (your QuestionTree does not need to refer to these messages)
    final String PLAY_AGAIN_MESSAGE = "Challenge me again?";
    final String SAVE_MESSAGE = "Shall I remember these games?";
    final String LOAD_MESSAGE = "Shall I recall our previous games?";
    final String SAVE_LOAD_FILENAME_MESSAGE = "What is the file name?";
    final String STATUS_MESSAGE = "Games played: %d\nI won: %d";
    final String BANNER_MESSAGE = "Think of an item, and I will guess it.";
}
