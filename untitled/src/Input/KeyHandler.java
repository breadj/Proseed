package Input;

import Game.Game;
import Game.Player;
import Game.Level;
import Utility.Point;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyHandler extends KeyAdapter {

    private Game game;

    public KeyHandler(Game game) {
        this.game = game;
    }

    // only does action once the key has been released
    @Override
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();
        switch (key) {
            case KeyEvent.VK_UP -> game.currentLevel.player.move(Point.NORTH);
            case KeyEvent.VK_RIGHT -> game.currentLevel.player.move(Point.EAST);
            case KeyEvent.VK_DOWN -> game.currentLevel.player.move(Point.SOUTH);
            case KeyEvent.VK_LEFT -> game.currentLevel.player.move(Point.WEST);
            case KeyEvent.VK_R -> game.currentLevel.reset();
        }
    }
}
