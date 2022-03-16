package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ButtonManager;
import com.mygdx.game.KnightsOath;
import com.mygdx.game.MusicManager;

public class MainMenuScreen implements Screen {
	private KnightsOath mainGame;
	private Stage stage;
	private MusicManager musicManager;
	private ButtonManager buttonManager;
	
	public MainMenuScreen(KnightsOath game) {
		mainGame = game;
		stage = new Stage(new ScreenViewport());
		musicManager = MusicManager.getInstance();
		buttonManager = ButtonManager.getInstance();
	}

	@Override
	public void show() {
		
		if(musicManager.getMusic1().getVolume() == 0) {
			buttonManager.getNoMusicButton().setVisible(true);
		}else {
			buttonManager.getNoMusicButton().setVisible(false);
		}
		
		musicManager.getMusic1().play();
		
		this.addStageActors();
		this.setImagePosition();
		this.addListeners();
	}

	@Override
	public void render(float delta) {
		mainGame.batch.begin();

		Gdx.input.setInputProcessor(stage);
		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		stage.act();
		stage.draw();
		mainGame.batch.end();

	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub

	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub

	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		musicManager.getMusic1().dispose();
		musicManager.getMusic2().dispose();
		stage.dispose();
	}
	
	private void addStageActors() {
		stage.addActor(buttonManager.getStartButton());
		stage.addActor(buttonManager.getContinueButton());
		stage.addActor(buttonManager.getOptionsButton());
		stage.addActor(buttonManager.getMusicButton());
		stage.addActor(buttonManager.getNoMusicButton());
		stage.addActor(buttonManager.getCreditsButton());
	}

	private void setImagePosition() {
		buttonManager.getStartButton().setPosition(Gdx.graphics.getWidth()/2 - buttonManager.getStartButton().getWidth()/2, Gdx.graphics.getHeight() - buttonManager.getStartButton().getHeight());
		//buttonManager.getStartButton().setX(170);
		//buttonManager.getStartButton().setY(375);
		//buttonManager.getContinueButton().setX(170);
		//buttonManager.getContinueButton().setY(255);
		buttonManager.getStartButton().setPosition(Gdx.graphics.getWidth()/2 - buttonManager.getStartButton().getWidth()/2, Gdx.graphics.getHeight() - buttonManager.getStartButton().getHeight());
		buttonManager.getOptionsButton().setX(170);
		buttonManager.getOptionsButton().setY(135);
		buttonManager.getCreditsButton().setX(170);
		buttonManager.getCreditsButton().setY(15);
		buttonManager.getMusicButton().setX(535);
		buttonManager.getMusicButton().setY(0);
		buttonManager.getNoMusicButton().setX(535);
		buttonManager.getNoMusicButton().setY(0);
	}

	private void addListeners() {
		buttonManager.getStartButton().addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				mainGame.setScreen(new MainGameScreen(mainGame));
				return false;
			}
		});

		buttonManager.getContinueButton().addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {

				return false;
			}
		});

		buttonManager.getOptionsButton().addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				mainGame.setScreen(new OptionsScreen(mainGame));
				return false;
			}
		});

		musicManager.getMusic1().setOnCompletionListener(new Music.OnCompletionListener() {

			@Override
			public void onCompletion(Music music) {
				musicManager.getMusic2().play();
			}
		});

		buttonManager.getMusicButton().addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				if(musicManager.getMusic1().isPlaying()) {
					musicManager.getMusic1().setVolume(0);
					musicManager.getMusic2().setVolume(0);
					buttonManager.getMusicButton().setVisible(false);
					buttonManager.getNoMusicButton().setVisible(true);

				}else {
					if(musicManager.getMusic2().isPlaying()) {
						musicManager.getMusic2().setVolume(0);
						buttonManager.getMusicButton().setVisible(false);
						buttonManager.getNoMusicButton().setVisible(true);
					}
				}

				return false;
			}
		});

		buttonManager.getNoMusicButton().addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				if(musicManager.getMusic1().isPlaying()) {
					musicManager.getMusic1().setVolume(1);
					buttonManager.getMusicButton().setVisible(true);
					buttonManager.getNoMusicButton().setVisible(false);

				}else {
					if(musicManager.getMusic2().isPlaying()) {
						musicManager.getMusic2().setVolume(1);
						buttonManager.getMusicButton().setVisible(true);
						buttonManager.getNoMusicButton().setVisible(false);
					}
				}

				return false;
			}
		});
		
		buttonManager.getCreditsButton().addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				mainGame.setScreen(new CreditsScreen(mainGame));
				return false;
			}
		});

	}

}
