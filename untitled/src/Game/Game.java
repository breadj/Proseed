package Game;

import Images.Sprites;
import Input.KeyHandler;
import Utility.JEasyFrame;

public class Game {

    public Level currentLevel;
    public static Sprites sprites;


    public static void main(String[] args)
        throws Exception {
        Game game = new Game();
        GameView view = new GameView(game);
        new JEasyFrame(view, "Roots").addKeyListener(new KeyHandler(game));

        sprites = new Sprites("seedling_happy.png", "seedling_sad.png",
                "water.png", "enemy.png", "root.png",
                "mud.png", "grass.png", "sky.png", "sun.png",
                "grass_girl.png", "light.png", "normal_wall.png",
                "broken_wall.png");

        // uses pre-made Levels for the GJ demo
        boolean completed = false;
        while (!completed) {
            game.currentLevel = Level.LevelOne(game, sprites);
            completed = levelLoop(game.currentLevel, view);
        } Thread.sleep(3000);

        completed = false;
        while (!completed) {
            game.currentLevel = Level.LevelTwo(game, sprites);
            completed = levelLoop(game.currentLevel, view);
        } Thread.sleep(3000);

        completed = false;
        while (!completed) {
            game.currentLevel = Level.LevelThree(game, sprites);
            completed = levelLoop(game.currentLevel, view);
        } Thread.sleep(3000);

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
