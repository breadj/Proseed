package Images;

import java.awt.image.BufferedImage;

public class Sprites {

    public final BufferedImage player, deadplayer, water, enemy, rootstart, rootmid, rootend;
    public final BufferedImage mud, grass, sky, sun, sprout, light;
    public final BufferedImage wall, breakwall;

    public Sprites(String playerImgFilename, String deadplayerImgFilename, String waterImgFilename, String enemyImgFilename,
                   String rootstartImgFilename, String rootmidImgFilename, String rootendImgFilename,
                   String mudImgFilename, String grassImgFilename, String skyImgFilename, String sunImgFilename,
                   String sproutImgFilename, String lightImgFilename, String wallImgFilename, String breakwallImgFilename) {
        player = Image.read(playerImgFilename);
        deadplayer = Image.read(deadplayerImgFilename);
        water = Image.read(waterImgFilename);
        enemy = Image.read(enemyImgFilename);
        rootstart = Image.read(rootstartImgFilename);
        rootmid = Image.read(rootmidImgFilename);
        rootend = Image.read(rootendImgFilename);

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
