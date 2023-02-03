package Game;

import Utilities.Point;

import java.util.Arrays;

public class Tile {

    private final Point<Integer> pos;     // position/coordinates of Tile on board grid
    private Item item;              // whether there's Water, an Enemy (e.g. snail) on this tile.
                                    // Default should be null
    //private final Barrier north, east, south, west;
    private Barrier[] barriers;       // Barrier array of length 4 directions go [0] = north, [1] = east, [2] = south, [3] = west

    public Tile(Point<Integer> position, Item item, Barrier[] barriers) {
        pos = position;
        this.item = item;
        /*north = n;
        east = e;
        south = s;
        west = w;*/
        this.barriers = Arrays.copyOf(barriers, 4);     // making sure to fill all 4 directions
    }

    public Point<Integer> position() {
        return pos;
    }

    public Item item() {
        return item;
    }


    // METHODS

    public void moveOntoTile(Player player) {
        // TODO: Make item interact with player (e.g., grow roots by +1 if Item is Water) WITHIN player class methods

        item = null;
    }
}
