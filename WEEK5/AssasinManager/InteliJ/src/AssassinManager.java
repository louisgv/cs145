import java.util.ArrayList;

/**
 * This object Manages AssassinNodes
 *
 * <ul>
 * <li> Name: AssassinManager.java
 * <li> Description: Assassin Manager
 * <li> Class: Java 145
 * <li> Instructor: Ken Hang
 * <li> Date: Feb 13 2015
 * </ul>
 *
 * @author  Hai H Nguyen (Bill)
 * @version Winter 2015
 */
public class AssassinManager {

    private AssassinNode frontAssassin;

    private AssassinNode frontGraveyard;

    /**
     * Constructor which Initialize the Assassin Linked list
     * @param name      A List of Assassin's Name
     */
    public AssassinManager (ArrayList<String> name){
        if(name != null && !name.isEmpty()) {
            for (int i = name.size() - 1; i >= 0; --i){
                frontAssassin = new AssassinNode(name.get(i), frontAssassin);
            }
        } else {
            throw new IllegalArgumentException("Name List is Empty or Null");
        }
    }

    /**
     * Show a report of who was killed by who.
     */
    public void printGraveyard(){
        if (frontGraveyard != null) {
            AssassinNode assassinPtr = frontGraveyard;

            printAssassin(assassinPtr, false);
        }
    }

    /**
     * Show a report of who stalking who.
     */
    public void printKillRing() {
        if (!isGameOver()) {
            AssassinNode assassinPtr = frontAssassin;

            printAssassin(assassinPtr, true);
        }
    }

    private void stalkKillStatus(String villain, String hero, boolean isAlive){
        System.out.println( "  " + villain +
                ( isAlive ?
                        (" is stalking ") : (" was killed by ")) + hero);
    }

    private void printAssassin(AssassinNode assassinPtr, boolean isAlive){
        while (assassinPtr != null) {
            if (!isAlive) {
                // Print Graveyard if assassin is Dead
                stalkKillStatus(assassinPtr.name, assassinPtr.killer, false);

                assassinPtr = assassinPtr.next;
            } else if (assassinPtr.next != null) {
                // Print Kill Ring if there is another Assassin ahead
                stalkKillStatus(assassinPtr.name,
                        assassinPtr.next.next == null ?
                                frontAssassin.name : assassinPtr.next.name, true);
                assassinPtr = assassinPtr.next;
            } else {
                assassinPtr = null;
            }
        }
    }

    /**
     * Search for killers
     * @param name  Name of Killer
     * @return      True if found, False otherwise
     */
    public boolean killRingContains(String name){
        AssassinNode assassinPtr = frontAssassin;

        return contains(assassinPtr, name);
    }

    /**
     * Search for victim in the Graveyard.
     * @param name  Name of Victim
     * @return      True if found, False otherwise
     */
    public boolean graveyardContains(String name) {
        if (frontGraveyard != null){
            AssassinNode gravePtr = frontGraveyard;

            return contains(gravePtr, name);
        } else {
            return false;
        }
    }

    private boolean contains(AssassinNode assassinPtr, String name){
        while (assassinPtr != null && assassinPtr.next != null) {
            if (assassinPtr.name.equalsIgnoreCase(name)){
                return true;
            }else {
                assassinPtr = assassinPtr.next;
            }
        }

        return false;
    }

    /**
     * @return      True if all but one is alive, False otherwise.
     */
    public boolean isGameOver(){
        return (frontAssassin.next == null);
    }

    /**
     * @return      Name of the Last man Standing
     */
    public String winner(){
        return isGameOver() ?
                frontAssassin.name : null;
    }

    /**
     * Search for name, and transfer it to the Graveyard
     * If none was found or Game is over, Throw Exceptions
     * @param name          Name of the Victim
     */
    public void kill (String name){
        if (isGameOver()) {
            throw new IllegalStateException("Game Over");
        } else {
            AssassinNode killerPtr = getKiller(name);

            if (frontAssassin.name.equalsIgnoreCase(name)) {
                AssassinNode victim = frontAssassin;

                frontAssassin = victim.next;

                purgatory(victim, killerPtr);
            } else if (killerPtr == null || killerPtr.next == null) {
                throw new IllegalArgumentException("Nobody named " + name);
            } else {
                AssassinNode victim = killerPtr.next;

                killerPtr.next = victim.next;

                purgatory(victim, killerPtr);
            }
        }
    }

    private AssassinNode getKiller(String victim){
        AssassinNode ptr = frontAssassin;

        while ( !(ptr == null) &&
                !(ptr.next == null) &&
                !(ptr.next.name.equalsIgnoreCase(victim))){
            ptr = ptr.next;
        }

        return ptr;
    }

    private void purgatory (AssassinNode victim, AssassinNode killer) {
        victim.killer = killer.name;

        victim.next = frontGraveyard;

        frontGraveyard = victim;
    }
}//IS29