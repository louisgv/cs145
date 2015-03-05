
import java.util.*;
import java.awt.*;

/**
 * A Manager for the Tile Class for the HW1
 *
 * <title> TileManager.java
 * <meta name="description" content="Tile Manager">
 * <ul>
 * <li> Class: Java 145
 * <li> Instructor: Ken Hang
 * <li> Date: Jan 17 2015
 * </ul>
 *
 * @author  Hai H Nguyen (Bill)
 * @version Winter 2015
 */
public class TileManager {

    private ArrayList<Tile> tileList;

    /**
     * Constructor.
     * Called every time a new tile manager object is created.
     * Instantiate the tileList with nothing
     */
    public TileManager(){
        tileList = new ArrayList<Tile>();
    }

    /**
     * Add a tile into the end of the tileList
     * @param rect     The rectangle to Add
     */
    public void addTile(Tile rect){
        tileList.add(rect);
    }

    /**
     * Each Tile object has a draw method that it can use to draw itself.
     * Draw the tiles from bottom (start) to top (end) of the manager's list.
     * @param g     The Graphic object to Draw
     */
    public void drawAll(Graphics g){
        for (Tile tile : tileList){
            tile.draw(g);
        }
    }

    /**
     * Check if user has Clicked on a Tile
     * @param x         X coordinate of a Click
     * @param y         Y coordinate of a Click
     * @param t         Tile to Check
     * @return          True if X and Y are within tile. False otherwise.
     */
    private boolean tileClicked(int x, int y, Tile t){
        return (x >= t.getX() &&
                
                x <= t.getX() + t.getWidth() &&
                
                y >= t.getY() &&
                
                y <= t.getY() + t.getHeight());
    }

    /**
     * Goes through the tileList and Check if Clicked
     * @param x         X coordinate of a Click
     * @param y         Y coordinate of a Click
     * @return          Index of Clicked Tile.
     */
    private int checkTileListIndex(int x, int y) {
        for (int i = tileList.size() - 1; i >= 0; --i) {
            if (tileClicked(x, y, tileList.get(i))){
                return i;
            }
        }
        
        return (-9);
    }

    /**
     * Called when the user left-clicks.
     *  It passes the x/y coordinates the user clicked.
     *  If these coordinates touch any tiles, 
     *  move the top most of these tiles to the very top (end) of the list.
     * @param x     Horizontal Position
     * @param y     Vertical Position
     */
    public void raise(int x, int y){
        
        int i = checkTileListIndex(x, y);
        
        if (i != -9){
            
            Tile tmp = tileList.get(i);
            
            tileList.remove(i);
            
            tileList.add(tmp);
        }
    }

    /**
     * Called when the user Shift-left-clicks.
     * If these coordinates touch any tiles, move the topmost of these tiles to
     * the very bottom (beginning) of the list.
     * @param x     Horizontal Position
     * @param y     Vertical Position
     */
    public void lower(int x, int y){
        
        int i = checkTileListIndex(x, y);
        
        if (i != -9){
            
            Tile tmp = tileList.get(i);
            
            tileList.remove(i);
            
            tileList.add(0, tmp);
        }
    }

    /**
     * Called when the user right-clicks.
     * If these coordinates touch any tiles, delete the topmost from the list.
     * @param x     Horizontal Position
     * @param y     Vertical Position
     */
    public void delete(int x, int y){
        
        int i = checkTileListIndex(x, y);
        
        if (i != -9){
            tileList.remove(i);
        }
    }

    /**
     * Called when the user Shift-right-clicks.
     * If these coordinates touch any tiles, delete all such tiles from the list.
     * @param x     Horizontal Position
     * @param y     Vertical Position
     */
    public void deleteAll(int x, int y){
        for( int i = 0; i < tileList.size(); ++i ){
            if(tileClicked(x,y,tileList.get(i))) {
                
                tileList.remove( i );
                
                --i;
            }
        }
    }

    /**
     * Called when the user types S.
     * This method should perform two actions:
     *  (1) reordering the tiles in the list into a random order
     *  (2) moving every tile on the screen to a new random x/y pixel position.
     * @param width         Window's width
     * @param height        Window's height
     */
    public void shuffle(int width, int height){

        Collections.shuffle( tileList );

        Random randomVault = new Random();

        for( Tile t : tileList ) {

            t.setX(randomVault.nextInt(width - t.getWidth()));

            t.setY(randomVault.nextInt(height - t.getHeight()));
        }
    }
}
