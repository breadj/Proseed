package Game;


import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;



public class Sound {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        File file = new File("Sound/Minimalism.wav");//https://soundcloud.com/flat-cap-tiger/minimalism-take-2-re-mastered
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-7.0f); // reduce volume by 7db
        clip.start();
        File file2 = new File("Sound/birdambient.wav"); //https://freesound.org/people/bajko/sounds/385279/
        AudioInputStream audioStream2 = AudioSystem.getAudioInputStream(file2);
        Clip clip2 = AudioSystem.getClip();
        clip2.open(audioStream2);
        FloatControl gainControl2 = (FloatControl) clip2.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl2.setValue(-15.0f); // reduce volume by 15db
        clip2.start();
    }
public static class soundWater{
    public static void main() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
        File filew = new File("Sound/Splash.wav");//https://freesound.org/people/soundscalpel.com/sounds/110393/
        AudioInputStream audioStream = AudioSystem.getAudioInputStream(filew);
        Clip clip = AudioSystem.getClip();
        clip.open(audioStream);
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(-15.0f); // reduce volume by 15db
        clip.start();
    }
}

    public static class soundStretch{
        public static void main() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
            File filee = new File("Sound/stretch.wav");//https://freesound.org/people/aabbccddee123/sounds/469429/
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(filee);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(+6f); // increase volume by 6
            clip.start();
        }
    }
    public static class soundHit{
        public static void main() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
            File fileh = new File("Sound/hitHurt.wav");//https://sfxr.me/
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(fileh);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10); // reduce by 10
            clip.start();
        }
    }

    public static class soundWall{
        public static void main() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
            File fileWa = new File("Sound/wallBreak.wav");//https://sfxr.me/
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(fileWa);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-10); // reduce by 10
            clip.start();
        }
    }

    public static class soundPass{
        public static void main() throws LineUnavailableException, UnsupportedAudioFileException, IOException {
            File filep = new File("Sound/pass.wav");//https://sfxr.me/
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(filep);
            Clip clip = AudioSystem.getClip();
            clip.open(audioStream);
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(-15); // reduce by 15
            clip.start();
        }
    }
}