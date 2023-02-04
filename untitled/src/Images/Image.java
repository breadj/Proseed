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
            System.out.println("Loaded " + filename);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Cannot load " + filename);
            image = null;
        }
        return image;
    }
}
