package eldar.game.client.audio;

import java.awt.Component;
import java.io.IOException;

import javax.media.ControllerEvent;
import javax.media.ControllerListener;
import javax.media.Format;
import javax.media.GainControl;
import javax.media.Manager;
import javax.media.MediaLocator;
import javax.media.NoPlayerException;
import javax.media.Player;
import javax.media.PlugInManager;
import javax.media.bean.playerbean.MediaPlayer;
import javax.media.format.AudioFormat;

import eldar.game.client.Game;
import eldar.game.client.Resources;

public class AudioPlayer {
	MediaPlayer musicPlayer;
	MediaPlayer voicesPlayer;
	MediaPlayer effectsPlayer;
	
	int masterVolume, musicVolume, voicesVolume, effectsVolume;
	
	public AudioPlayer(){
		this.masterVolume = Game.gameProperties.masterVolume;
		this.musicVolume = Game.gameProperties.musicVolume;
		this.voicesVolume = Game.gameProperties.voicesVolume;
		this.effectsVolume = Game.gameProperties.effectsVolume;
		
		Format input1 = new AudioFormat(AudioFormat.MPEGLAYER3);
	    Format input2 = new AudioFormat(AudioFormat.MPEG);
	    Format output = new AudioFormat(AudioFormat.LINEAR);
	    PlugInManager.addPlugIn(
	        "com.sun.media.codec.audio.mp3.JavaDecoder",
	        new Format[]{input1, input2},
	        new Format[]{output},
	        PlugInManager.CODEC
	    );
	    musicPlayer = new MediaPlayer();
	    voicesPlayer = new MediaPlayer();
	    effectsPlayer = new MediaPlayer();
	}
	public void playMusic(MediaLocator media){
		musicPlayer.stop();
		musicPlayer.setMediaLocator(media);
		musicPlayer.start();
	}
	public void pauseMusic(){
		musicPlayer.stop();
	}
	public void unpauseMusic(){
		musicPlayer.start();
	}
	public void stop(){
		musicPlayer.stop();
		effectsPlayer.stop();
		voicesPlayer.stop();
	}
}
