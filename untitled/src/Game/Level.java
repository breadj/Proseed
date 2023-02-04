package Game;

import Images.Sprites;
import Utility.Point;
import static Game.Constants.*;

import java.awt.*;

public class Level {

    public final Sprites sprites;

    private Game game;
    public Player player;
    public boolean reset = false;           // whether the level needs to be reset
    public boolean completed = false;        // whether the Player has completed the level

    public Tile[][] board;      // 2D array of Tiles for the board layout
    private final Point start;        // position of the Tile where the Player starts
    private final Point exit;          // position of the Tile where the Player finishes the Level

    public Level(Game game, Tile[][] board, Point start, Point exit, Sprites sprites) {
        this.game = game;
        this.board = board;
        this.start = start;
        this.exit = exit;

        player = new Player(start, this);

        this.sprites = sprites;

        for (Tile[] column : board) {
            for (Tile tile : column) {
                tile.level = this;
            }
        }
    }


    public void update() {
        if (player.position() == exit)
            completed = true;
    }

    public void draw(Graphics2D g) {
        for (Tile[] column : board)
            for (Tile tile : column)
                tile.draw(g);

        // light
        g.drawImage(sprites.light, (exit.x * TILE_SIZE) + 20, exit.y * TILE_SIZE, ITEM_SIZE, ITEM_SIZE, null);

        player.draw(g);
    }


    public void reset() {
        reset = true;
    }

    public Tile at(Point coord) {
        return board[coord.x][coord.y];
    }



    /// PRE-MADE LEVELS FOR DEMO ///

    // kind of tutorial level
    public static Level LevelOne(Game game, Sprites sprites) {
        Tile[][] board = new Tile[3][5];

        // overground
        for (int i = 0; i < 3; i++) {
            board[i][0] = new Tile(new Point(i, 0), null, null, sprites.sky);
            board[i][1] = new Tile(new Point(i, 1), null, null, sprites.grass);
        }

        board[0][2] = new Tile(new Point(0, 2), null, null, sprites.mud);
        Barrier[] barriers12 = { Barrier.PASSABLE, Barrier.CLOSED, Barrier.CLOSED, Barrier.PASSABLE };
        board[1][2] = new Tile(new Point(1, 2), null, barriers12, sprites.mud);
        Barrier[] barriers22 = { Barrier.PASSABLE, Barrier.CLOSED, Barrier.PASSABLE, Barrier.CLOSED };
        board[2][2] = new Tile(new Point(2, 2), null, barriers22, sprites.mud);

        Barrier[] barriers03 = { Barrier.PASSABLE, Barrier.CLOSED, Barrier.CLOSED, Barrier.PASSABLE };
        board[0][3] = new Tile(new Point(0, 3), null, barriers03, sprites.mud);
        Barrier[] barriers13 = { Barrier.CLOSED, Barrier.PASSABLE, Barrier.PASSABLE, Barrier.CLOSED };
        board[1][3] = new Tile(new Point(1, 3), Item.Snail, barriers13, sprites.mud);
        Barrier[] barriers23 = { Barrier.PASSABLE, Barrier.CLOSED, Barrier.PASSABLE, Barrier.PASSABLE };
        board[2][3] = new Tile(new Point(2, 3), null, barriers23, sprites.mud);

        Barrier[] barriers04 = { Barrier.CLOSED, Barrier.BREAKABLE, Barrier.CLOSED, Barrier.CLOSED };
        board[0][4] = new Tile(new Point(0, 4), Item.Snail, barriers04, sprites.mud);
        Barrier[] barriers14 = { Barrier.PASSABLE, Barrier.BREAKABLE, Barrier.CLOSED, Barrier.BREAKABLE };
        board[1][4] = new Tile(new Point(1, 4), Item.Water, barriers14, sprites.mud);
        Barrier[] barriers24 = { Barrier.PASSABLE, Barrier.CLOSED, Barrier.CLOSED, Barrier.BREAKABLE };
        board[2][4] = new Tile(new Point(2, 4), null, barriers24, sprites.mud);

        Point start = new Point(2, 4);
        Point exit = new Point(2, 2);

        return new Level(game, board, start, exit, sprites);
    }

    // level included in design brief
    public static Level LevelTwo(Game game, Sprites sprites) {
        return null;
    }

    // harder, bigger Level
    // probably final one for the Game Jam demo
    public static Level LevelThree(Game game, Sprites sprites) {
        return null;
    }
}
