package Images;

import java.awt.image.BufferedImage;

public class Sprites {

    public final BufferedImage player, deadplayer, water, enemy, roots;
    public final BufferedImage mud, grass, sky, sun, sprout, light;
    public final BufferedImage wall, breakwall;

    public Sprites(String playerImgFilename, String deadplayerImgFilename, String waterImgFilename, String enemyImgFilename,
                   String rootsImgFilename,
                   String mudImgFilename, String grassImgFilename, String skyImgFilename, String sunImgFilename,
                   String sproutImgFilename, String lightImgFilename, String wallImgFilename, String breakwallImgFilename) {
        player = Image.read(playerImgFilename);
        deadplayer = Image.read(deadplayerImgFilename);
        water = Image.read(waterImgFilename);
        enemy = Image.read(enemyImgFilename);
        roots = Image.read(rootsImgFilename);

        mud = Image.read(mudImgFilename);
        grass = Image.read(grassImgFilename);
        sky = Image.read(skyImgFilename);
        sun = Image.read(sunImgFilename);
        sprout = Image.read(sproutImgFilename);
        light = Image.read(lightImgFilename);

        wall = Image.read(wallImgFilename);
        breakwall = Image.read(breakwallImgFilename);
    }
}
