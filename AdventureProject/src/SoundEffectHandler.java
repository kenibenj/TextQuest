import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;
import java.util.Random;

public class SoundEffectHandler {
    URL buttonMain = getClass().getResource("button.wav");
    URL openInv = getClass().getResource("inventorySound.wav");
    URL openInfo = getClass().getResource("informationSound.wav");
    URL openEquip = getClass().getResource("equipmentSound.wav");
    static float previousVolume = 0;
    static float currentVolume = -10;
    static FloatControl floatControl;
    static boolean mute = false;
    static Clip soundClip = null;
    static Clip crawlSoundClip = null;


    public static void playFile(URL soundFileName) throws IOException, UnsupportedAudioFileException {
        try{
            AudioInputStream sound = AudioSystem.getAudioInputStream(soundFileName);
            soundClip = AudioSystem.getClip();
            soundClip.open(sound);
            floatControl = (FloatControl)soundClip.getControl(FloatControl.Type.MASTER_GAIN);
            floatControl.setValue(currentVolume);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        soundClip.setFramePosition(0);
        soundClip.start();
    }

    public static void playFileRandomStart(URL soundFileName) throws IOException, UnsupportedAudioFileException {
        try{
            AudioInputStream sound = AudioSystem.getAudioInputStream(soundFileName);
            crawlSoundClip = AudioSystem.getClip();
            crawlSoundClip.open(sound);
            floatControl = (FloatControl)crawlSoundClip.getControl(FloatControl.Type.MASTER_GAIN);
            floatControl.setValue(currentVolume);
        }
        catch (LineUnavailableException e) {
            e.printStackTrace();
        }
        crawlSoundClip.setMicrosecondPosition(new Random().nextInt((int) crawlSoundClip.getMicrosecondLength()));
        crawlSoundClip.start();
    }

    public static void stop(){

        if(!(crawlSoundClip == null)) {
            crawlSoundClip.stop();
        }
    }

    public static boolean isRunning(){

        if(!(crawlSoundClip == null)) {
            return crawlSoundClip.isRunning();
        }
        else {
            return false;
        }
    }

    public static void volumeUp(){
        currentVolume += 5.0f;
        if(currentVolume > 6.0f){
            currentVolume =6.0f;
        }
        floatControl.setValue(currentVolume);
    }

    public static void volumeDown(){
        currentVolume -= 5.0f;
        if(currentVolume < -80.0f){
            currentVolume = -80.0f;
        }
        floatControl.setValue(currentVolume);
    }

    public static void volumeMute(){
        if(mute == false){
            previousVolume = currentVolume;
            currentVolume = -80.0f;
            floatControl.setValue(currentVolume);
            mute = true;
        }
        else{
            currentVolume = previousVolume;
            floatControl.setValue(currentVolume);
            mute = false;
        }
    }
}
