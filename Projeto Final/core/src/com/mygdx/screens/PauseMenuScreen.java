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
	private KnightsOath mainGame;
	private Stage stage;
	private ButtonManager buttonManager;
	
	public PauseMenuScreen(KnightsOath game) {
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
		buttonManager.getContinueButton().setX(170);
		buttonManager.getContinueButton().setY(350);
		buttonManager.getSaveButton().setX(170);
		buttonManager.getSaveButton().setY(200);
		buttonManager.getExitButton().setX(170);
		buttonManager.getExitButton().setY(50);
	}

	private void addListeners() {
		buttonManager.getContinueButton().addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
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
