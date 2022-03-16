package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.mygdx.game.KnightsOath;

public class MainGameScreen implements Screen {
	private KnightsOath mainGame;
	private Texture img;
	private static final float speed = 120;
	private float x;
	private float y;

	public MainGameScreen(KnightsOath game){
		mainGame = game;
		img = new Texture(("Textures/knight.png"));
	}

	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		if(Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W)) {
			y += speed * Gdx.graphics.getDeltaTime();
		}

		if(Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S)) {
			y -= speed * Gdx.graphics.getDeltaTime();
		}

		if(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
			x -= speed * Gdx.graphics.getDeltaTime();
		}

		if(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
			x += speed * Gdx.graphics.getDeltaTime();
		}
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			mainGame.setScreen(new PauseMenuScreen(mainGame));
		}

		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		mainGame.batch.begin();
		mainGame.batch.draw(img,x,y);
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
		mainGame.batch.dispose();
	}
}
