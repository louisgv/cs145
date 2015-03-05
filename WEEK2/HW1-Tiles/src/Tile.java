// CSE 143, Homework 1, Tiles
// This class implements a new type of objects representing rectangular tiles.

import java.awt.Color;
import java.awt.Graphics;

/** Each Tile object represents a 2D rectangular tile with a
    top-left x/y coordinate, width, height, and fill color. */
public class Tile {
    private int x;          // top-left x/y
    private int y;
    private int width;
    private int height;
    private Color color;    // fill color
    
    /** Constructs a new tile with the given coordinates, size, and color. */
    public Tile(int x, int y, int w, int h, Color c) {
        this.x = x;
        this.y = y;
        this.width = w;
        this.height = h;
        this.color = c;
    }
    
    /** Draws this tile using the given graphics pen. */
    public void draw(Graphics g) {
        g.setColor(color);
        g.fillRect(x, y, width, height);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, width, height);
    }

    /** Returns this tile's leftmost x coordinate. */
    public int getX() {
        return x;
    }
    
    /** Returns this tile's topmost y coordinate. */
    public int getY() {
        return y;
    }
    
    /** Returns this tile's width. */
    public int getWidth() {
        return width;
    }
    
    /** Returns this tile's height. */
    public int getHeight() {
        return height;
    }
    
    /** Returns this tile's color. */
    public Color getColor() {
        return color;
    }

    /** Sets this tile's leftmost x-coordinate to be the given value. */
    public void setX(int x) {
        this.x = x;
    }

    /** Sets this tile's topmost y-coordinate to be the given value. */
    public void setY(int y) {
        this.y = y;
    }

    /** Returns a text representation of this tile, such as "(x=57,y=148,w=26,h=53)". */
    public String toString() {
        return "(x=" + x + ",y=" + y + "),w=" + width + ",h=" + height + ")";
    }
}
