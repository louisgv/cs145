
import java.util.*;
/**
 * A lost dog need to be located, and this class holds
 * the needed information in order to do so.
 *
 * <title> LostPuppy.java
 * <meta name="description" content="Dog Locator">
 * <ul>
 * <li> Class: Java 145
 * <li> Instructor: Ken Hang
 * <li> Date: Jan 13 2015
 * </ul>
 *
 * @author  Hai H Nguyen (Bill)
 * @version Winter 2015
 */

public class LostPuppy{

    private char[][] hidingPlaces;

    private int floorLocation;

    private int roomLocation;

    private char winner;

    private boolean found;

    /**
     * Constructor. Does the following:
     * <ul>
     * <li> Instantiate the hidingPlaces with @param
     * <li> Initialize hidingPlaces with blank space
     * <li> Set a Random hiding Location for the Dog
     * <li> Set the hidingPlace of the Dog
     * <li> Set Winner to ' '
     * <li> Set Found to false
     * </ul>
     * @param floors     The number of floors in each Room
     * @param rooms      The number of buildings in the Building
     */

    public LostPuppy (int floors, int rooms){

        hidingPlaces = new char[floors][rooms];

        initializeHidingPlaces(floors, rooms);

        setPuppyLocation(floors, rooms);

        hidingPlaces[floorLocation][roomLocation] = 'P';

        winner = ' ';

        found = false;

    }

    /**
     * Initialize hidingPlaces with blank space
     * @param floors     Max floor Index
     * @param rooms      Max room Index
     */

    private void initializeHidingPlaces (int floors, int rooms){
        for (int i = 0; i < floors; ++i){
            for (int j = 0; j < rooms; ++j){
                hidingPlaces[i][j] = ' ';
            }
        }
    }

    /**
     * Set a Random hiding Location for the Dog.
     * @param floors     Max floor Index
     * @param rooms      Max room Index
     */

    private void setPuppyLocation(int floors, int rooms){

        Random randomVault = new Random();

        floorLocation = randomVault.nextInt(floors);

        roomLocation = randomVault.nextInt(rooms);

    }

    /**
     * Check if the room has already been Searched
     * @param floor     Floor to be Checked
     * @param room      Room to be Checked
     * @return          False if place is ' ' or 'P', True otherwise
     */

    public boolean roomSearchedAlready(int floor, int room){
        return indicesOK(floor, room) &&
                hidingPlaces[floor][room] != ' ' &&
                    hidingPlaces[floor][room] != 'P';
    }

    /**
     * Check if the room has the dog
     * @param floor     Floor to be Checked
     * @param room      Room to be Checked
     * @return          True if Dog is there, False otherwise
     */

    public boolean puppyLocation(int floor, int room){
        return (indicesOK(floor, room) &&
                floor == floorLocation &&
                    room == roomLocation);
    }

    /**
     * Check if the location exist in the current Maze
     * @param floor     Floor to be Checked
     * @param room      Room to be Checked
     * @return          True if within range of hidingPlaces, False otherwise
     */

    public boolean indicesOK (int floor, int room){
        return (floor <= numberOfFloors() &&
                    room <= numberOfRooms());
    }

    /**
     * Get the number of floors int the Building, first floor starts at zero.
     * @return          Number of Floor in the Building
     */

    public int numberOfFloors(){
        return hidingPlaces.length - 1 ;
    }

    /**
     * Get the number of floors int the Building, first floor starts at zero.
     * @return          Number of Floor in the Building
     */

    public int numberOfRooms(){
        return hidingPlaces[0].length - 1 ;
    }

    /**
     * Get the number of floors in the Building, first floor starts at zero.
     * @param floor     Floor to be Search
     * @param room      Room to be Search
     * @return          True if Puppy is found, False otherwise.
     */

    public boolean searchRoom(int floor, int room, char player){
        if (indicesOK(floor, room)) {
            if (!puppyLocation(floor, room)) {
                hidingPlaces[floor][room] = player;
                return false;
            } else {
                winner = player;
                return found = true;
            }
        }
        else {
            return false;
        }
    }

    /**
     * Displays the current hidingPlaces array.
     * Show the puppy location only if winner is determined
     * @return          Location Information of the Building.
     */

    public String toString(){
        String out = " ";
        for (int j = numberOfRooms(); j >= 0; --j){
            out += ("___") + (j==0 ? (" ") : ("_"));
        }
        out += ("\n");
        for (int i = numberOfFloors(); i >= 0; --i){
            for (int j = 0; j <= numberOfRooms(); ++j){
                out +=  ("|") +
                        ((found && puppyLocation(i, j)) ?
                                (winner + "P" + " ") :
                                (" " + (hidingPlaces[i][j] == 'P' ?
                                        (" ") :
                                        (hidingPlaces[i][j])) + " "));
            }
            out += ("|\n");
            for (int j = numberOfRooms(); j >= 0; --j){
                out += ("|___");
            }
            out += ("|\n");
        }
        return out;
    }
}