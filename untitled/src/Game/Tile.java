package Game;

import Utility.Point;
import static Game.Constants.*;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Tile {

    public Level level;
    private final BufferedImage texture;

    private final Point pos;        // position/coordinates of Tile on board grid
    private Item item;              // whether there's Water, an Enemy (e.g. snail) on this tile.
                                    // Default should be null
    public Barrier[] barriers;     // Barrier array of length 4 directions go [0] = north, [1] = east, [2] = south, [3] = west

    public Tile(Point position, Item item, Barrier[] barriers, BufferedImage texture) {
        pos = position;
        this.item = item;
        this.barriers = barriers;     // should only be 4 elements, for the 4 directions (or null for overground)
        this.texture = texture;
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
        g.drawImage(texture, pos.x * TILE_SIZE, pos.y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);

        if (barriers != null) {
            double wallCentreX = level.sprites.wall.getWidth() / 2d;
            double wallCentreY = level.sprites.wall.getHeight() / 2d;

            // north barrier
            if (barriers[0] != Barrier.PASSABLE) {
                AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(90), wallCentreX, wallCentreY);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

                if (barriers[0] == Barrier.BREAKABLE) {
                    g.drawImage(op.filter(level.sprites.breakwall, null),
                            pos.x * TILE_SIZE, pos.y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                } else if (barriers[0] == Barrier.CLOSED) {
                    g.drawImage(op.filter(level.sprites.wall, null),
                            pos.x * TILE_SIZE, pos.y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                }
            }

            // east barrier
            if (barriers[1] != Barrier.PASSABLE) {
                AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(180), wallCentreX, wallCentreY);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

                if (barriers[1] == Barrier.BREAKABLE) {
                    g.drawImage(op.filter(level.sprites.breakwall, null),
                            pos.x * TILE_SIZE, pos.y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                } else if (barriers[1] == Barrier.CLOSED) {
                    g.drawImage(op.filter(level.sprites.wall, null),
                            pos.x * TILE_SIZE, pos.y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                }
            }

            // south barrier
            if (barriers[2] != Barrier.PASSABLE) {
                AffineTransform tx = AffineTransform.getRotateInstance(Math.toRadians(270), wallCentreX, wallCentreY);
                AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

                if (barriers[2] == Barrier.BREAKABLE) {
                    g.drawImage(op.filter(level.sprites.breakwall, null),
                            pos.x * TILE_SIZE, pos.y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                } else if (barriers[2] == Barrier.CLOSED) {
                    g.drawImage(op.filter(level.sprites.wall, null),
                            pos.x * TILE_SIZE, pos.y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
                }
            }

            // west barrier
            if (barriers[3] != Barrier.PASSABLE) {
                if (barriers[3] == Barrier.BREAKABLE) {
                    g.drawImage(level.sprites.breakwall, pos.x * TILE_SIZE, pos.y * TILE_SIZE,
                            TILE_SIZE, TILE_SIZE, null);
                } else if (barriers[3] == Barrier.CLOSED) {
                    g.drawImage(level.sprites.wall, pos.x * TILE_SIZE, pos.y * TILE_SIZE,
                            TILE_SIZE, TILE_SIZE, null);
                }
            }
        }

        // item
        if (item == Item.Water) {
            g.drawImage(level.sprites.water, (pos.x * TILE_SIZE) + ITEM_MARGIN, (pos.y * TILE_SIZE) + ITEM_MARGIN,
                    ITEM_SIZE, ITEM_SIZE, null);
        } else if (item == Item.Snail) {
            g.drawImage(level.sprites.enemy, (pos.x * TILE_SIZE) + ITEM_MARGIN, (pos.y * TILE_SIZE) + ITEM_MARGIN,
                    ITEM_SIZE, ITEM_SIZE, null);
        }
    }
}
