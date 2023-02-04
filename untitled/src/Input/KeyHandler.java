package Input;

import Game.Player;
import Game.Level;
import Utility.Point;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {

    private Player player;
    private Level level;


    // only does action once the key has been released
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP -> player.move(new Point(0, -1));
            case KeyEvent.VK_RIGHT -> player.move(new Point(1, 0));
            case KeyEvent.VK_DOWN -> player.move(new Point(0, 1));
            case KeyEvent.VK_LEFT -> player.move(new Point(-1, 0));
            case KeyEvent.VK_R -> level.reset();
        }
    }
}
