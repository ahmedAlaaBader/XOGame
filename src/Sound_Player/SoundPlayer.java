/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Sound_Player;

import javafx.scene.media.AudioClip;

/**
 *
 * @author Hossam
 */
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
