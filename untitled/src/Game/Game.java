package Game;

import Utility.JEasyFrame;

public class Game {

    public Level currentLevel;

    public Game() {

    }


    public static void main(String[] args)
        throws Exception {
        Game game = new Game();
        GameView view = new GameView(game);
        new JEasyFrame(view, "Roots");

        // uses pre-made Levels for the GJ demo
        boolean completed = false;
        while (!completed) {
            game.currentLevel = Level.LevelOne();
            completed = levelLoop(game.currentLevel, view);
        }

        completed = false;
        while (!completed) {
            game.currentLevel = Level.LevelTwo();
            completed = levelLoop(game.currentLevel, view);
        }

        completed = false;
        while (!completed) {
            game.currentLevel = Level.LevelThree();
            completed = levelLoop(game.currentLevel, view);
        }

        // TODO: display 'winner' message
    }

    private static boolean levelLoop(Level level, GameView view)
        throws Exception {
        while (!(level.completed || level.reset)) {
            level.update();
            view.repaint();
            Thread.sleep(20);
        }
        return level.completed;
    }

}
