package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ButtonManager;
import com.mygdx.game.KnightsOath;

public class CreditsScreen implements Screen {
	private final KnightsOath mainGame;
	private Texture creditsTexture;
	private Stage stage;
	private int y;
	private int x;
	private float speed;
	private ButtonManager buttonManager;

	public CreditsScreen(KnightsOath game) {
		mainGame = game;
		stage = new Stage(new ScreenViewport());
		creditsTexture = new Texture("Textures/Buttons/credits.png");
		y = -670;
		x = Gdx.graphics.getWidth()/2-creditsTexture.getWidth()/2;
		speed = 10;
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
		// TODO Auto-generated method stub
		mainGame.batch.begin();
		Gdx.input.setInputProcessor(stage);
		Gdx.gl.glClearColor(0,0,0,0);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		
		mainGame.batch.draw(creditsTexture,x,y);
		
		if(y != 84) {
			y += speed * Gdx.graphics.getDeltaTime();
		}else {
			speed = 0;
			
		}
		
		mainGame.batch.end();
		stage.act();
		stage.draw();
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
	
	private void addStageActors() {
		stage.addActor(buttonManager.getBackSquareButton());
	}

	private void setImagePosition() {
		buttonManager.getBackSquareButton().setPosition(0, (float) (Gdx.graphics.getHeight() - buttonManager.getBackButton().getHeight()));
	}
	
	private void addListeners() {
		buttonManager.getBackSquareButton().addListener(new InputListener() {
			@Override
			public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
				mainGame.setScreen(new MainMenuScreen(mainGame));
				return false;
			}
		});
	}
}
