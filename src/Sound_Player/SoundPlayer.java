package Sound_Player;

import javafx.scene.media.AudioClip;

public class SoundPlayer {

    public static void playSound(String soundFilePath) {
        try {
            AudioClip sound = new AudioClip(soundFilePath);
            sound.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
