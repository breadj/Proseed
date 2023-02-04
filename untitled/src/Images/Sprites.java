package Images;

import java.awt.image.BufferedImage;

public class Sprites {

    public final BufferedImage player, water, snail, roots, mud, sky, sun, sprout;

    public Sprites(String playerImgFilename, String waterImgFilename, String snailImgFilename, String rootsImgFilename,
                   String mudImgFilename, String skyImgFilename, String sunImgFilename, String sproutImgFilename) {
        player = Image.read(playerImgFilename);
        water = Image.read(waterImgFilename);
        snail = Image.read(snailImgFilename);
        roots = Image.read(rootsImgFilename);
        mud = Image.read(mudImgFilename);
        sky = Image.read(skyImgFilename);
        sun = Image.read(sunImgFilename);
        sprout = Image.read(sproutImgFilename);
    }
}
