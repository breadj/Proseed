package Utility;

public class Point {

    public static final Point NORTH = new Point(0, -1);
    public static final Point EAST = new Point(1, 0);
    public static final Point SOUTH = new Point(0, 1);
    public static final Point WEST = new Point(-1, 0);

    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }


    public void set(Point p) {
        x = p.x;
        y = p.y;
    }

    public void add(int x, int y) {
        this.x += x;
        this.y += y;
    }

    public void add(Point other) {
        this.x += other.x;
        this.y += other.y;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Point p)
            return this.x == p.x && this.y == p.y;
        else return false;
    }
}
