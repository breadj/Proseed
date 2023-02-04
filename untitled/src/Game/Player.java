package Game;

import Utility.Point;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player {

    public BufferedImage playerSprite;
    public BufferedImage rootsSprite;

    private Point pos;          // current position on board
    private int rootLength = 1; // always starts at 1
    private boolean isDead = false;
    private boolean isMoving = false;
    private Move moving = null;

    private Level level;

    public Player(Point startingPos, Level level) {
        pos = startingPos;
        this.level = level;
    }

    public Point position() {
        return pos;
    }

    public int rootLength() {
        return rootLength;
    }


    public void interact(Item item) {
        switch (item) {
            case Water -> rootLength += 1;
            case Snail -> isDead = true;
        }
    }

    // direction is the direction the player is PUSHING in
    public void move(Point direction) {
        if (!isMoving) {
            isMoving = true;
            moving = new Move(rootLength, pos, direction, this);
        }
    }


    public void update() {
        if (isMoving) {
            boolean completed = moving.push();

            if (completed) {
                isMoving = false;
                moving = null;      // just a safety measure in case it tries moving after it's done
            }
        }
    }

    public void draw(Graphics2D g) {
        if (isMoving) {
            moving.drawRoots(g);
        }
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

        }

        boolean push() {
            if (rootLengthLeft <= 0)
                return true;        // completed the move

            if (direction == Point.NORTH) {
                pushNorth();
            } else if (direction == Point.EAST) {
                pushEast();
            } else if (direction == Point.SOUTH) {
                pushSouth();
            } else if (direction == Point.WEST) {
                pushWest();
            }

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
                rootEnd.add(Point.NORTH);
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
