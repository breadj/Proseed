package Game;

import javax.swing.*;
import java.awt.*;

public class GameView extends JComponent {

    private Game game;

    public GameView(Game game) {
        this.game = game;
    }

    @Override
    public void paintComponent(Graphics gr) {
        Graphics2D g = (Graphics2D)gr;
        g.setColor(Color.BLACK);        // setting default background colour

        g.fillRect(0, 0, getWidth(), getHeight());

        if (game.currentLevel != null)
            game.currentLevel.draw(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return Constants.FRAME_SIZE;
    }
}
