
import java.util.*;
/**
 * This program is used as a driver program to play the game from the
 * class LostPuppy.
 *
 * A puppy is lost in a multi-floor building represented in the class
 * LostPuppy.class.  Two players will take turns searching the building
 * by selecting a floor and a room where the puppy might be.
 *
 * @author David Schuessler
 * @version Fall 2013
 */


public class PuppyPlay{
  /**
   * @param args may contain file names in an array of type String
   */
  public static void main(String[] args){
    Scanner s = new Scanner(System.in);
    LostPuppy game;
    int totalFloors;
    int totalRooms;
    int floor;
    int room;
    char[] players = {'1', '2'};
    int playerIndex;
    boolean found = false;
    Random rand = new Random();
   
    do {
      System.out.print("To find the puppy, we need to know:\n"
                       + "\tHow many floors are in the building\n"
                       + "\tHow many rooms are on the floors\n\n"
                       + "             Please enter the number of floors: ");
      totalFloors = s.nextInt();
      System.out.print("Please enter the number of rooms on the floors: ");
      totalRooms = s.nextInt();
      s.nextLine();    // Consume previous newline character

      // Start the game
      game = new LostPuppy(totalFloors, totalRooms);

      // Pick starting player
      playerIndex = rand.nextInt(2);

      System.out.println("\nFloor and room numbers start at zero '0'");

      do {

        do {
          System.out.println("\nPlayer " + players[playerIndex]
                             + ", enter floor and room to search separated by a space: ");
          //floor = s.nextInt();
          //room = s.nextInt();

          //for testing, use random generation of floor and room
          floor = rand.nextInt(totalFloors);
          room = rand.nextInt(totalRooms);
        } while (!game.indicesOK(floor, room)
                 || game.roomSearchedAlready(floor, room));

        found = game.searchRoom(floor, room, players[playerIndex]);
        playerIndex = (playerIndex + 1) % 2;
        System.out.println("\n[" + floor + "], [" + room + "]");
        System.out.println(game.toString());
        s.nextLine();
      } while (!found);

      playerIndex = (playerIndex + 1) % 2;
      System.out.println("Great job player " + players[playerIndex] +"!");
      System.out.println("Would you like to find another puppy [Y/N]? ");
    } while (s.nextLine().equalsIgnoreCase("Y"));
  }
}