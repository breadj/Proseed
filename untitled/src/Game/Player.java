package Game;

import Utility.Point;

import java.awt.*;

public class Player {

    private Point pos;          // current position on board
    private int rootLength = 1; // always starts at 1
    private boolean isDead = false;

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

    public void move(Point direction) {
        // TODO
    }


    public void update() {
        // TODO
    }

    public void draw(Graphics2D g) {
        // TODO
    }
}
