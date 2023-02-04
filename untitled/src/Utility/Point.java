package Utility;

public class Point {

    public int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }


    public void set(Point p) {
        x = p.x;
        y = p.y;
    }

    public void add(int x, int y) {
        this.x += x;
        this.y += y;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof Point p)
            return this.x == p.x && this.y == p.y;
        else return false;
    }
}
