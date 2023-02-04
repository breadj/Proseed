package Game;

import Utility.Point;

import java.awt.*;
import java.util.Arrays;

public class Tile {

    private final Point pos;        // position/coordinates of Tile on board grid
    private Item item;              // whether there's Water, an Enemy (e.g. snail) on this tile.
                                    // Default should be null
    public Barrier[] barriers;     // Barrier array of length 4 directions go [0] = north, [1] = east, [2] = south, [3] = west

    public Tile(Point position, Item item, Barrier[] barriers) {
        pos = position;
        this.item = item;
        this.barriers = Arrays.copyOf(barriers, 4);     // making sure to fill all 4 directions
    }

    public Point position() {
        return pos;
    }

    public Item item() {
        return item;
    }


    // METHODS

    public void moveOntoTile(Player player) {
        if (item != null) {
            player.interact(item);
            item = null;
        }
    }


    public void draw(Graphics2D g) {
        // TODO: Draw this, THEN the item
    }
}
