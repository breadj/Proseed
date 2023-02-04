package Game;

import Utility.Point;
import static Game.Constants.*;

import java.awt.*;

public class Level {

    private Game game;
    private Player player;
    public boolean reset = false;           // whether the level needs to be reset
    public boolean completed = false;        // whether the Player has completed the level

    public Tile[][] board;      // 2D array of Tiles for the board layout
    private final Point start;        // position of the Tile where the Player starts
    private final Point exit;          // position of the Tile where the Player finishes the Level

    public Level(Game game, Tile[][] board, Point start, Point exit) {
        this.game = game;
        this.board = board;
        this.start = start;
        this.exit = exit;

        player = new Player(start, this);
    }


    public void update() {
        if (player.position() == exit)
            completed = true;
    }

    public void draw(Graphics2D g) {
        drawOverground(g);

        for (Tile[] column : board)
            for (Tile tile : column)
                tile.draw(g);

        player.draw(g);
    }

    public void drawOverground(Graphics2D g) {
        // TODO: Draw the blue sky, the sun, and the opening
    }

    public void reset() {
        reset = true;
    }



    /// PRE-MADE LEVELS FOR DEMO ///

    // kind of tutorial level
    public static Level LevelOne() {
        return null;
    }

    // level included in design brief
    public static Level LevelTwo() {
        return null;
    }

    // harder, bigger Level
    // probably final one for the Game Jam demo
    public static Level LevelThree() {
        return null;
    }
}
