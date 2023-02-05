package Game;

import Images.Sprites;
import Utility.Point;
import static Game.Constants.*;

import java.awt.*;

public class Level {

    public final Sprites sprites;

    public Player player;
    public boolean reset = false;           // whether the level needs to be reset
    public boolean completed = false;        // whether the Player has completed the level

    public Tile[][] board;      // 2D array of Tiles for the board layout
    private final Point exit;          // position of the Tile where the Player finishes the Level

    public Level(Game game, Tile[][] board, Point start, Point exit, Sprites sprites) {
        this.board = board;
        // position of the Tile where the Player starts
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
        player.update();
        if (player.position().equals(exit))
            completed = true;
    }

    public void draw(Graphics2D g) {
        for (Tile[] column : board)
            for (Tile tile : column)
                tile.draw(g);

        // light
        g.drawImage(sprites.light, (exit.x * TILE_SIZE) + ITEM_MARGIN, exit.y * TILE_SIZE, ITEM_SIZE, ITEM_SIZE, null);


        player.draw(g);
        if (completed) {
            Point sproutPos = Point.add(player.position(), Point.NORTH);
            g.drawImage(sprites.sprout, (sproutPos.x * TILE_SIZE) + ITEM_MARGIN, (sproutPos.y * TILE_SIZE), ITEM_SIZE, ITEM_SIZE, null);
        }

        g.drawImage(sprites.sun, (board.length - 1) * TILE_SIZE, 0, TILE_SIZE, TILE_SIZE, null);
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

        // underground
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
        Tile[][] board = new Tile[6][7];

        // overground
        for (int i = 0; i < 6; i++) {
            board[i][0] = new Tile(new Point(i, 0), null, null, sprites.sky);
            board[i][1] = new Tile(new Point(i, 1), null, null, sprites.grass);
        }

        // underground
        Barrier[] barriers02 = { Barrier.CLOSED, Barrier.PASSABLE, Barrier.PASSABLE, Barrier.CLOSED };
        board[0][2] = new Tile(new Point(0, 2), null, barriers02, sprites.mud);
        Barrier[] barriers12 = { Barrier.CLOSED, Barrier.CLOSED, Barrier.PASSABLE, Barrier.PASSABLE };
        board[1][2] = new Tile(new Point(1, 2), null, barriers12, sprites.mud);
        Barrier[] barriers22 = { Barrier.PASSABLE, Barrier.PASSABLE, Barrier.CLOSED, Barrier.CLOSED };
        board[2][2] = new Tile(new Point(2, 2), null, barriers22, sprites.mud);
        Barrier[] barriers32 = { Barrier.CLOSED, Barrier.PASSABLE, Barrier.CLOSED, Barrier.PASSABLE };
        board[3][2] = new Tile(new Point(3, 2), null, barriers32, sprites.mud);
        Barrier[] barriers42 = { Barrier.CLOSED, Barrier.CLOSED, Barrier.PASSABLE, Barrier.PASSABLE };
        board[4][2] = new Tile(new Point(4, 2), null, barriers42, sprites.mud);
        Barrier[] barriers52 = { Barrier.PASSABLE, Barrier.PASSABLE, Barrier.PASSABLE, Barrier.CLOSED };
        board[5][2] = new Tile(new Point(5, 2), null, barriers52, sprites.mud);

        Barrier[] barriers03 = { Barrier.PASSABLE, Barrier.PASSABLE, Barrier.BREAKABLE, Barrier.CLOSED };
        board[0][3] = new Tile(new Point(0, 3), null, barriers03, sprites.mud);
        Barrier[] barriers13 = { Barrier.PASSABLE, Barrier.PASSABLE, Barrier.PASSABLE, Barrier.PASSABLE };
        board[1][3] = new Tile(new Point(1, 3), Item.Water, barriers13, sprites.mud);
        Barrier[] barriers23 = { Barrier.CLOSED, Barrier.CLOSED, Barrier.PASSABLE, Barrier.PASSABLE };
        board[2][3] = new Tile(new Point(2, 3), null, barriers23, sprites.mud);
        Barrier[] barriers33 = { Barrier.CLOSED, Barrier.CLOSED, Barrier.PASSABLE, Barrier.CLOSED };
        board[3][3] = new Tile(new Point(3, 3), Item.Snail, barriers33, sprites.mud);
        Barrier[] barriers43 = { Barrier.PASSABLE, Barrier.CLOSED, Barrier.PASSABLE, Barrier.CLOSED };
        board[4][3] = new Tile(new Point(4, 3), null, barriers43, sprites.mud);
        Barrier[] barriers53 = { Barrier.PASSABLE, Barrier.PASSABLE, Barrier.PASSABLE, Barrier.CLOSED };
        board[5][3] = new Tile(new Point(5, 3), null, barriers53, sprites.mud);

        Barrier[] barriers04 = { Barrier.BREAKABLE, Barrier.BREAKABLE, Barrier.PASSABLE, Barrier.CLOSED };
        board[0][4] = new Tile(new Point(0, 4), null, barriers04, sprites.mud);
        Barrier[] barriers14 = { Barrier.PASSABLE, Barrier.PASSABLE, Barrier.PASSABLE, Barrier.BREAKABLE };
        board[1][4] = new Tile(new Point(1, 4), null, barriers14, sprites.mud);
        Barrier[] barriers24 = { Barrier.PASSABLE, Barrier.CLOSED, Barrier.CLOSED, Barrier.PASSABLE };
        board[2][4] = new Tile(new Point(2, 4), null, barriers24, sprites.mud);
        Barrier[] barriers34 = { Barrier.PASSABLE, Barrier.PASSABLE, Barrier.PASSABLE, Barrier.CLOSED };
        board[3][4] = new Tile(new Point(3, 4), null, barriers34, sprites.mud);
        Barrier[] barriers44 = { Barrier.PASSABLE, Barrier.CLOSED, Barrier.PASSABLE, Barrier.PASSABLE };
        board[4][4] = new Tile(new Point(4, 4), null, barriers44, sprites.mud);
        Barrier[] barriers54 = { Barrier.PASSABLE, Barrier.PASSABLE, Barrier.CLOSED, Barrier.CLOSED };
        board[5][4] = new Tile(new Point(5, 4), null, barriers54, sprites.mud);

        Barrier[] barriers05 = { Barrier.PASSABLE, Barrier.CLOSED, Barrier.PASSABLE, Barrier.CLOSED };
        board[0][5] = new Tile(new Point(0, 5), null, barriers05, sprites.mud);
        Barrier[] barriers15 = { Barrier.PASSABLE, Barrier.BREAKABLE, Barrier.CLOSED, Barrier.CLOSED };
        board[1][5] = new Tile(new Point(1, 5), null, barriers15, sprites.mud);
        Barrier[] barriers25 = { Barrier.CLOSED, Barrier.PASSABLE, Barrier.PASSABLE, Barrier.BREAKABLE };
        board[2][5] = new Tile(new Point(2, 5), Item.Water, barriers25, sprites.mud);
        Barrier[] barriers35 = { Barrier.PASSABLE, Barrier.PASSABLE, Barrier.CLOSED, Barrier.PASSABLE };
        board[3][5] = new Tile(new Point(3, 5), null, barriers35, sprites.mud);
        Barrier[] barriers45 = { Barrier.PASSABLE, Barrier.BREAKABLE, Barrier.CLOSED, Barrier.PASSABLE };
        board[4][5] = new Tile(new Point(4, 5), null, barriers45, sprites.mud);
        Barrier[] barriers55 = { Barrier.CLOSED, Barrier.CLOSED, Barrier.CLOSED, Barrier.BREAKABLE };
        board[5][5] = new Tile(new Point(5, 5), Item.Snail, barriers55, sprites.mud);

        Barrier[] barriers06 = { Barrier.PASSABLE, Barrier.PASSABLE, Barrier.CLOSED, Barrier.CLOSED };
        board[0][6] = new Tile(new Point(0, 6), null, barriers06, sprites.mud);
        Barrier[] barriers16 = { Barrier.CLOSED, Barrier.PASSABLE, Barrier.CLOSED, Barrier.PASSABLE };
        board[1][6] = new Tile(new Point(1, 6), null, barriers16, sprites.mud);
        Barrier[] barriers26 = { Barrier.PASSABLE, Barrier.CLOSED, Barrier.CLOSED, Barrier.PASSABLE };
        board[2][6] = new Tile(new Point(2, 6), null, barriers26, sprites.mud);
        Barrier[] barriers36 = { Barrier.CLOSED, Barrier.PASSABLE, Barrier.PASSABLE, Barrier.CLOSED };
        board[3][6] = new Tile(new Point(3, 6), null, barriers36, sprites.mud);
        Barrier[] barriers46 = { Barrier.CLOSED, Barrier.PASSABLE, Barrier.PASSABLE, Barrier.PASSABLE };
        board[4][6] = new Tile(new Point(4, 6), null, barriers46, sprites.mud);
        Barrier[] barriers56 = { Barrier.CLOSED, Barrier.PASSABLE, Barrier.PASSABLE, Barrier.PASSABLE };
        board[5][6] = new Tile(new Point(5, 6), null, barriers56, sprites.mud);

        Point start = new Point(1, 5);
        Point exit = new Point(2, 2);

        return new Level(game, board, start, exit, sprites);
    }

    // harder, bigger Level
    // probably final one for the Game Jam demo
    public static Level LevelThree(Game game, Sprites sprites) {
        Tile[][] board = new Tile[7][9];

        // overground
        for (int i = 0; i < 7; i++){
            board[i][0] = new Tile(new Point(i, 0), null, null, sprites.sky);
            board[i][1] = new Tile(new Point(i, 1), null, null, sprites.grass);
        }

        // underground
        board[0][2] = new Tile(new Point(0, 2), null, null, sprites.mud);
        board[1][2] = new Tile(new Point(1, 2), null, null, sprites.mud);
        Barrier[] barriers22 = { Barrier.CLOSED, Barrier.CLOSED, Barrier.PASSABLE, Barrier.CLOSED };
        board[2][2] = new Tile(new Point(2, 2), null, barriers22, sprites.mud);
        board[3][2] = new Tile(new Point(3, 2), null, null, sprites.mud);
        board[4][2] = new Tile(new Point(4, 2), null, null, sprites.mud);
        board[5][2] = new Tile(new Point(5, 2), null, null, sprites.mud);
        Barrier[] barriers62 = { Barrier.PASSABLE, Barrier.CLOSED, Barrier.PASSABLE, Barrier.CLOSED };
        board[6][2] = new Tile(new Point(6, 2), null, barriers62, sprites.mud);

        Barrier[] barriers03 = { Barrier.CLOSED, Barrier.CLOSED, Barrier.BREAKABLE, Barrier.CLOSED };
        board[0][3] = new Tile(new Point(0, 3), Item.Snail, barriers03, sprites.mud);
        board[1][3] = new Tile(new Point(1, 3), null, null, sprites.mud);
        Barrier[] barriers23 = { Barrier.PASSABLE, Barrier.CLOSED, Barrier.PASSABLE, Barrier.CLOSED };
        board[2][3] = new Tile(new Point(2, 3), Item.Snail, barriers23, sprites.mud);
        board[3][3] = new Tile(new Point(3, 3), null, null, sprites.mud);
        board[4][3] = new Tile(new Point(4, 3), null, null, sprites.mud);
        board[5][3] = new Tile(new Point(5, 3), null, null, sprites.mud);
        Barrier[] barriers63 = { Barrier.PASSABLE, Barrier.CLOSED, Barrier.PASSABLE, Barrier.CLOSED };
        board[6][3] = new Tile(new Point(6, 3), null, barriers63, sprites.mud);

        Barrier[] barriers04 = { Barrier.BREAKABLE, Barrier.PASSABLE, Barrier.BREAKABLE, Barrier.CLOSED };
        board[0][4] = new Tile(new Point(0, 4), null, barriers04, sprites.mud);
        Barrier[] barriers14 = { Barrier.CLOSED, Barrier.BREAKABLE, Barrier.BREAKABLE, Barrier.PASSABLE };
        board[1][4] = new Tile(new Point(1, 4), null, barriers14, sprites.mud);
        Barrier[] barriers24 = { Barrier.PASSABLE, Barrier.CLOSED, Barrier.BREAKABLE, Barrier.BREAKABLE };
        board[2][4] = new Tile(new Point(2, 4), Item.Water, barriers24, sprites.mud);
        board[3][4] = new Tile(new Point(3, 4), null, null, sprites.mud);
        board[4][4] = new Tile(new Point(4, 4), null, null, sprites.mud);
        board[5][4] = new Tile(new Point(5, 4), null, null, sprites.mud);
        Barrier[] barriers64 = { Barrier.PASSABLE, Barrier.CLOSED, Barrier.PASSABLE, Barrier.CLOSED };
        board[6][4] = new Tile(new Point(6, 4), null, barriers64, sprites.mud);

        Barrier[] barriers05 = { Barrier.BREAKABLE, Barrier.BREAKABLE, Barrier.CLOSED, Barrier.CLOSED};
        board[0][5] = new Tile(new Point(0, 5), Item.Water, barriers05, sprites.mud);
        Barrier[] barriers15 = { Barrier.BREAKABLE, Barrier.BREAKABLE, Barrier.BREAKABLE, Barrier.BREAKABLE };
        board[1][5] = new Tile(new Point(1, 5), null, barriers15, sprites.mud);
        Barrier[] barriers25 = { Barrier.BREAKABLE, Barrier.CLOSED, Barrier.BREAKABLE, Barrier.BREAKABLE };
        board[2][5] = new Tile(new Point(2, 5), null, barriers25, sprites.mud);
        board[3][5] = new Tile(new Point(3, 5), null, null, sprites.mud);
        board[4][5] = new Tile(new Point(4, 5), null, null, sprites.mud);
        board[5][5] = new Tile(new Point(5, 5), null, null, sprites.mud);
        Barrier[] barriers65 = { Barrier.PASSABLE, Barrier.CLOSED, Barrier.PASSABLE, Barrier.CLOSED };
        board[6][5] = new Tile(new Point(6, 5), null, barriers65, sprites.mud);

        board[0][6] = new Tile(new Point(0, 6), null, null, sprites.mud);
        Barrier[] barriers16 = { Barrier.BREAKABLE, Barrier.BREAKABLE, Barrier.PASSABLE, Barrier.CLOSED };
        board[1][6] = new Tile(new Point(1, 6), null, barriers16, sprites.mud);
        Barrier[] barriers26 = { Barrier.BREAKABLE, Barrier.PASSABLE, Barrier.BREAKABLE, Barrier.BREAKABLE };
        board[2][6] = new Tile(new Point(2, 6), null, barriers26, sprites.mud);
        Barrier[] barriers36 = { Barrier.CLOSED, Barrier.PASSABLE, Barrier.CLOSED, Barrier.PASSABLE };
        board[3][6] = new Tile(new Point(3, 6), null, barriers36, sprites.mud);
        Barrier[] barriers46 = { Barrier.CLOSED, Barrier.PASSABLE, Barrier.CLOSED, Barrier.PASSABLE };
        board[4][6] = new Tile(new Point(4, 6), null, barriers46, sprites.mud);
        Barrier[] barriers56 = { Barrier.CLOSED, Barrier.PASSABLE, Barrier.CLOSED, Barrier.PASSABLE };
        board[5][6] = new Tile(new Point(5, 6), null, barriers56, sprites.mud);
        Barrier[] barriers66 = { Barrier.PASSABLE, Barrier.CLOSED, Barrier.CLOSED, Barrier.PASSABLE };
        board[6][6] = new Tile(new Point(6, 6), null, barriers66, sprites.mud);

        board[0][7] = new Tile(new Point(0, 7), null, null, sprites.mud);
        Barrier[] barriers17 = { Barrier.PASSABLE, Barrier.BREAKABLE, Barrier.CLOSED, Barrier.CLOSED };
        board[1][7] = new Tile(new Point(1, 7), null, barriers17, sprites.mud);
        Barrier[] barriers27 = { Barrier.BREAKABLE, Barrier.BREAKABLE, Barrier.PASSABLE, Barrier.BREAKABLE };
        board[2][7] = new Tile(new Point(2, 7), Item.Water, barriers27, sprites.mud);
        Barrier[] barriers37 = { Barrier.CLOSED, Barrier.CLOSED, Barrier.CLOSED, Barrier.BREAKABLE };
        board[3][7] = new Tile(new Point(3, 7), Item.Snail, barriers37, sprites.mud);
        board[4][7] = new Tile(new Point(4, 7), null, null, sprites.mud);
        board[5][7] = new Tile(new Point(5, 7), null, null, sprites.mud);
        board[6][7] = new Tile(new Point(6, 7), null, null, sprites.mud);

        board[0][8] = new Tile(new Point(0, 8), null, null, sprites.mud);
        board[1][8] = new Tile(new Point(1, 8), null, null, sprites.mud);
        Barrier[] barriers28 = { Barrier.PASSABLE, Barrier.CLOSED, Barrier.CLOSED, Barrier.CLOSED };
        board[2][8] = new Tile(new Point(2, 8), Item.Snail, barriers28, sprites.mud);
        board[3][8] = new Tile(new Point(3, 8), null, null, sprites.mud);
        board[4][8] = new Tile(new Point(4, 8), null, null, sprites.mud);
        board[5][8] = new Tile(new Point(5, 8), null, null, sprites.mud);
        board[6][8] = new Tile(new Point(6, 8), null, null, sprites.mud);

        Point start = new Point(1, 5);
        Point exit = new Point(6, 2);

        return new Level(game, board, start, exit, sprites);
    }
}
