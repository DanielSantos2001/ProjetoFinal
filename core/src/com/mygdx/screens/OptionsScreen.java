package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ButtonManager;
import com.mygdx.game.KnightsOath;

public class OptionsScreen implements Screen{
	private final KnightsOath mainGame;
	private final Stage stage;
	private final ButtonManager buttonManager;
	
	public OptionsScreen(KnightsOath game) {
		mainGame = game;
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
		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		stage.act();
		stage.draw();
		mainGame.batch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}

	private void addListeners() {
		buttonManager.getBackButton().addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				mainGame.setScreen(new MainMenuScreen(mainGame));
				return false;
			}
		});
	}

	private void addStageActors() {
		stage.addActor(buttonManager.getBackButton());
	}

	private void setImagePosition() {
		buttonManager.getBackButton().setPosition(Gdx.graphics.getWidth()/2-buttonManager.getBackButton().getWidth()/2,Gdx.graphics.getHeight()/4-buttonManager.getBackButton().getHeight());
	}

}
