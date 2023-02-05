package Game;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;


public class Sound {
    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("Sound/Minimalism.wav");//https://soundcloud.com/flat-cap-tiger/minimalism-take-2-re-mastered
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-15.0f); // reduce volume by 15db
        clip.start();
        File file2 = new File("Sound/birdambient.wav"); //https://freesound.org/people/bajko/sounds/385279/
        AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(file2);
        Clip clip2 = AudioSystem.getClip();
        clip2.open(audioStream2);
        FloatControl gainControl2 = (FloatControl) clip2.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl2.setValue(-15.0f); // reduce volume by 15db
        clip2.start();
    }// splash https://freesound.org/people/soundscalpel.com/sounds/110393/


}