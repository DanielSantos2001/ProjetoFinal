package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ButtonManager;
import com.mygdx.game.KnightsOath;

public class PauseMenuScreen implements Screen {
	private final KnightsOath mainGame;
	private final Screen parentScreen;
	private Stage stage;
	private ButtonManager buttonManager;
	
	public PauseMenuScreen(KnightsOath game, Screen parent) {
		mainGame = game;
		parentScreen = parent;
		stage = new Stage(new ScreenViewport());
		buttonManager = ButtonManager.getInstance();
	}


	@Override
	public void show() {
		this.addStageActors();
		this.setImagePosition();
		this.addListeners();
	}

	@Override
	public void render(float delta) {
		mainGame.batch.begin();
		Gdx.input.setInputProcessor(stage);
		
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
		stage.dispose();
	}

	private void addStageActors() {
		stage.addActor(buttonManager.getContinueButton());
		stage.addActor(buttonManager.getSaveButton());
		stage.addActor(buttonManager.getExitButton());
	}


	private void setImagePosition() {
		buttonManager.getContinueButton().setPosition(Gdx.graphics.getWidth()/2 - buttonManager.getContinueButton().getWidth()/2, (float) (Gdx.graphics.getHeight()/1.1 - buttonManager.getContinueButton().getHeight()));
		buttonManager.getSaveButton().setPosition(Gdx.graphics.getWidth()/2 - buttonManager.getSaveButton().getWidth()/2, (float) (Gdx.graphics.getHeight()/1.55 - buttonManager.getSaveButton().getHeight()));
		buttonManager.getExitButton().setPosition(Gdx.graphics.getWidth()/2 - buttonManager.getExitButton().getWidth()/2, (float) (Gdx.graphics.getHeight()/2.65 - buttonManager.getExitButton().getHeight()));
	}

	private void addListeners() {
		buttonManager.getContinueButton().addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				mainGame.setScreen(parentScreen);
				return false;
			}
		});
		
		buttonManager.getExitButton().addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				mainGame.setScreen(new MainMenuScreen(mainGame));
				return false;
			}
		});
	}
}
