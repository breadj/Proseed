package Images;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Image {
    public static BufferedImage read(String filename) {
        BufferedImage image;
        try {
            image = ImageIO.read(new File("textures\\" + filename));
        } catch (IOException e) {
            e.printStackTrace();
            image = null;
        }
        return image;
    }
}
