package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;

public class MusicManager {
	private static MusicManager music_instance = null;

	private Music music1;
	private Music music2;
	
	private MusicManager() {
		music1 = Gdx.audio.newMusic(Gdx.files.internal("Soundtracks/churchofsaints.wav"));
		music2 =  Gdx.audio.newMusic(Gdx.files.internal("Soundtracks/crowpeak.wav"));
	}

	public static MusicManager getInstance() {

		if(music_instance == null) 
			music_instance = new MusicManager();


		return music_instance;
	}
	
	public Music getMusic1() {
		return music1;
	}
	
	public Music getMusic2() {
		return music2;
	}
}
