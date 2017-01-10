package models;

import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class GameSounds {
	static AudioInputStream backgroundMusicInputStream;
	static AudioInputStream spinningReelsInputStream;
	static Clip backgroundMusicClip;
	static Clip spinningReelsClip;
	
	public GameSounds() {
		
	}
	
	public static void startBackgroundMusic() {
		try {			
			backgroundMusicInputStream = AudioSystem.getAudioInputStream(new File("./Kissed_The_Quaker.wav").getAbsoluteFile());
			backgroundMusicClip = AudioSystem.getClip();
			backgroundMusicClip.open(backgroundMusicInputStream);
			backgroundMusicClip.loop(Clip.LOOP_CONTINUOUSLY);
			backgroundMusicClip.start();
			setBackGroundMusicVolume(-10.0f);
			backgroundMusicInputStream.close();
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
	}
	
	public static void setBackGroundMusicVolume(float level) {	
		FloatControl volume = (FloatControl) backgroundMusicClip.getControl(FloatControl.Type.MASTER_GAIN);
		volume.setValue(level);
	}
	
	public static void pauseBackgroundMusic() {
		backgroundMusicClip.stop();
	}
	
	public static void playBackgroundMusic() {
		backgroundMusicClip.start();
	}
	
	public static void playSpinningReelsSound() {
		try {
			spinningReelsInputStream = AudioSystem.getAudioInputStream(new File("./IrishMusic.wav").getAbsoluteFile());
			spinningReelsClip = AudioSystem.getClip();
			spinningReelsClip.open(spinningReelsInputStream);
			spinningReelsClip.setFramePosition(66400);
			spinningReelsClip.start();
			spinningReelsInputStream.close();
		} catch (Exception ex) {
			System.out.println("Error: " + ex);
		}
	}
	
	public static void pauseSpinningReelsSound() {
		spinningReelsClip.close();
	}
}
