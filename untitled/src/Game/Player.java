package Game;

import Utility.Point;
import static Game.Constants.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Player {


    private Point pos;          // current position on board
    private int rootLength = 1; // always starts at 1
    private boolean isDead = false;
    private Move moving = null;

    private Level level;

    public Player(Point startingPos, Level level) {
        pos = startingPos;
        this.level = level;
    }

    public Point position() {
        return pos;
    }


    public void interact(Item item) {
        switch (item) {
            case Water -> rootLength += 1;
            case Snail -> isDead = true;
        }
    }

    // direction is the direction the player is PUSHING in
    public void move(Point direction) {
        if (isDead)     // cannot move if dead
            return;
        if (moving == null) {
            moving = new Move(rootLength, pos, direction, this);
        }
    }


    private int updateTimer = 0;
    public void update() {
        if ((updateTimer += 20) < 250)      // 250 milliseconds is how long the wait timer is
            return;

        // if the update timer is equal to or larger than the wait time
        if (moving != null) {
            boolean completed = moving.push();      // completed meaning whether the move has been fully done

            if (completed) {
                moving = null;      // just a safety measure in case it tries moving after it's done
                return;
            }
        }
        updateTimer = 0;
    }

    public void draw(Graphics2D g) {
        if (moving != null) {
            moving.drawRoots(g);
        }

        if (isDead)
            g.drawImage(level.sprites.deadplayer, pos.x * TILE_SIZE, pos.y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
        else
            g.drawImage(level.sprites.player, pos.x * TILE_SIZE, pos.y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
    }

    private class Move {
        int rootLengthLeft;
        Point rootEnd;
        Point direction;
        Player player;

        Move(int rootLength, Point playerPos, Point pushDirection, Player player) {
            rootLengthLeft = rootLength;
            rootEnd = new Point(playerPos);     // deep copy so doesn't change player position unintentionally
            direction = pushDirection;
            this.player = player;
        }

        void drawRoots(Graphics2D g) {
            if (rootEnd.equals(pos))
                return;
            System.out.println(pos + " : " + rootEnd);

            // roots texture goes westwards by default
            double rotation = 0;
            Point drawPos = null;
            Point drawTo = null;

            int imageCentreX = level.sprites.rootstart.getWidth() / 2;
            int imageCentreY = level.sprites.rootstart.getHeight() / 2;

            if (direction.equals(Point.NORTH)) {
                drawPos = rootEnd;
                drawTo = Point.add(pos, Point.NORTH);

                rotation = Math.toRadians(90);
            } else if (direction.equals(Point.EAST)) {
                drawPos = Point.add(pos, Point.EAST);
                drawTo = rootEnd;

                rotation = Math.toRadians(180);
            } else if (direction.equals(Point.SOUTH)) {
                drawPos = Point.add(pos, Point.SOUTH);
                drawTo = rootEnd;

                rotation = Math.toRadians(270);
            } else if (direction.equals(Point.WEST)) {
                drawPos = rootEnd;
                drawTo = Point.add(pos, Point.WEST);

                // rotation is already 0 since texture natively faces westwards
            }

            AffineTransform rotate = AffineTransform.getRotateInstance(rotation, imageCentreX, imageCentreY);
            AffineTransformOp op = new AffineTransformOp(rotate, AffineTransformOp.TYPE_BILINEAR);

            g.drawImage(op.filter(level.sprites.rootstart, null), pos.x * TILE_SIZE, pos.y * TILE_SIZE,
                    TILE_SIZE, TILE_SIZE, null);

            for (Point p = Point.add(pos, direction); !p.equals(rootEnd); p.add(direction))
                g.drawImage(op.filter(level.sprites.rootmid, null), p.x * TILE_SIZE, p.y * TILE_SIZE,
                        TILE_SIZE, TILE_SIZE, null);

            g.drawImage(op.filter(level.sprites.rootend, null),
                    rootEnd.x * TILE_SIZE, rootEnd.y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
        }

        boolean push() {
            if (rootLengthLeft <= 0)
                return true;        // completed the move

            if (direction.equals(Point.NORTH)) {
                pushNorth();
            } else if (direction.equals(Point.EAST)) {
                pushEast();
            } else if (direction.equals(Point.SOUTH)) {
                pushSouth();
            } else if (direction.equals(Point.WEST)) {
                pushWest();
            }

            System.out.println("Player position: " + pos);
            System.out.println("End of root position: " + rootEnd);
            rootLengthLeft -= 1;
            return false;
        }

        void pushNorth() {
            Tile currentTile = level.at(rootEnd);
            if (currentTile.barriers[0] == Barrier.BREAKABLE ||
                    currentTile.barriers[0] == Barrier.CLOSED) {
                moveSouth();
            } else if (currentTile.barriers[0] == Barrier.PASSABLE) {
                rootEnd.add(Point.NORTH);
            }
        }

        void pushEast() {
            Tile currentTile = level.at(rootEnd);
            if (currentTile.barriers[1] == Barrier.BREAKABLE ||
                    currentTile.barriers[1] == Barrier.CLOSED) {
                moveWest();
            } else if (currentTile.barriers[1] == Barrier.PASSABLE) {
                rootEnd.add(Point.EAST);
            }
        }

        void pushSouth() {
            Tile currentTile = level.at(rootEnd);
            if (currentTile.barriers[2] == Barrier.BREAKABLE ||
                    currentTile.barriers[2] == Barrier.CLOSED) {
                moveNorth();
            } else if (currentTile.barriers[2] == Barrier.PASSABLE) {
                rootEnd.add(Point.SOUTH);
            }
        }

        void pushWest() {
            Tile currentTile = level.at(rootEnd);
            if (currentTile.barriers[3] == Barrier.BREAKABLE ||
                    currentTile.barriers[3] == Barrier.CLOSED) {
                moveEast();
            } else if (currentTile.barriers[3] == Barrier.PASSABLE) {
                rootEnd.add(Point.WEST);
            }
        }


        void moveNorth() {
            Tile currentTile = level.at(pos);
            if (currentTile.barriers[0] == Barrier.PASSABLE ||
                    currentTile.barriers[0] == Barrier.BREAKABLE) {
                pos.add(Point.NORTH);
                currentTile.barriers[0] = Barrier.PASSABLE;

                currentTile = level.at(pos);    // updates the currentTile with the player's new position
                currentTile.moveOntoTile(player);
                currentTile.barriers[2] = Barrier.PASSABLE;
            }
        }

        void moveEast() {
            Tile currentTile = level.at(pos);
            if (currentTile.barriers[1] == Barrier.PASSABLE ||
                    currentTile.barriers[1] == Barrier.BREAKABLE) {
                pos.add(Point.EAST);
                currentTile.barriers[1] = Barrier.PASSABLE;

                currentTile = level.at(pos);    // updates the currentTile with the player's new position
                currentTile.moveOntoTile(player);
                currentTile.barriers[3] = Barrier.PASSABLE;
            }
        }

        void moveSouth() {
            Tile currentTile = level.at(pos);
            if (currentTile.barriers[2] == Barrier.PASSABLE ||
                    currentTile.barriers[2] == Barrier.BREAKABLE) {
                pos.add(Point.SOUTH);
                currentTile.barriers[2] = Barrier.PASSABLE;

                currentTile = level.at(pos);    // updates the currentTile with the player's new position
                currentTile.moveOntoTile(player);
                currentTile.barriers[0] = Barrier.PASSABLE;
            }
        }

        void moveWest() {
            Tile currentTile = level.at(pos);
            if (currentTile.barriers[3] == Barrier.PASSABLE ||
                    currentTile.barriers[3] == Barrier.BREAKABLE) {
                pos.add(Point.WEST);
                currentTile.barriers[3] = Barrier.PASSABLE;

                currentTile = level.at(pos);    // updates the currentTile with the player's new position
                currentTile.moveOntoTile(player);
                currentTile.barriers[1] = Barrier.PASSABLE;
            }
        }
    }
}
