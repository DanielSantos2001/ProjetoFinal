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
	private KnightsOath mainGame;
	private Stage stage;
	private ButtonManager buttonManager;
	
	public OptionsScreen(KnightsOath game) {
		mainGame = game;
		stage = new Stage(new ScreenViewport());
		buttonManager = ButtonManager.getInstance();
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
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
		buttonManager.getBackButton().setX(170);
		buttonManager.getBackButton().setY(100);
	}

}
