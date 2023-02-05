package Game;

import Images.Sprites;
import Input.KeyHandler;
import Utility.JEasyFrame;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Game {

    public Level currentLevel;
    public static Sprites sprites;


    public static void main(String[] args)
        throws Exception {
        Sound.main(args);
        Game game = new Game();
        GameView view = new GameView(game);
        new JEasyFrame(view, "Proseed").addKeyListener(new KeyHandler(game));

        sprites = new Sprites("seedling_happy.png", "seedling_sad.png",
                "water.png", "enemy.png", "root2.png",
                "root3.png", "root.png", "mud.png",
                "grass.png", "sky.png", "sun.png",
                "winning_sun.png", "grass_girl.png",
                "grass_girl_happy.png", "light.png", "normal_wall.png",
                "broken_wall.png");


        // uses pre-made Levels for the GJ demo
        boolean completed = false;
        while (!completed) {
            game.currentLevel = Level.LevelOne(game, sprites);
            completed = levelLoop(game.currentLevel, view);
        } winSound();
        Thread.sleep(3000);

        completed = false;
        while (!completed) {
            game.currentLevel = Level.LevelTwo(game, sprites);
            completed = levelLoop(game.currentLevel, view);
        } winSound();
        Thread.sleep(3000);

        completed = false;
        while (!completed) {
            game.currentLevel = Level.LevelThree(game, sprites);
            completed = levelLoop(game.currentLevel, view);
        } winSound();
        Thread.sleep(3000);

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

    private static void winSound() {
        try {
            Sound.soundPass.main();
        } catch (LineUnavailableException |
                 UnsupportedAudioFileException |
                 IOException e) {
            throw new RuntimeException(e);
        }
    }
}
