package Game;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class Sound {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("Sound/Minimalism.wav");
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-15.0f); // reduce volume by 15db

        clip.start();
    }


}