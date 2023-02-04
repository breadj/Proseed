package Images;

import java.awt.image.BufferedImage;

public class Sprites {

    public final BufferedImage player, water, snail, roots;

    public Sprites(String playerImgFilename, String waterImgFilename, String snailImgFilename, String rootsImgFilename) {
        player = Image.read(playerImgFilename);
        water = Image.read(waterImgFilename);
        snail = Image.read(snailImgFilename);
        roots = Image.read(rootsImgFilename);
    }
}
