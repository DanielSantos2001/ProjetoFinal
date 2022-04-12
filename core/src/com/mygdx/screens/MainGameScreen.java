package com.mygdx.screens;

import com.mygdx.camera.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mydgx.animations.AnimationManager;
import com.mygdx.game.KnightsOath;

public class MainGameScreen implements Screen {
	private final KnightsOath mainGame;
	private static final float speed = 1;
	private float x;
	private float y;
	private Sprite sprite;
	private float stateTime;
	private TextureRegion currentFrame;
	private AnimationManager animationManager;
	private CameraManager cameraManager;

	public MainGameScreen(KnightsOath game){
		mainGame = game;
		animationManager = new AnimationManager();
		cameraManager = new CameraManager();
		sprite = new Sprite(animationManager.getKnightIdleSheet());
		x = 5f;
		y = 5f;
	}

	@Override
	public void show() {
		animationManager.idleAnimation(stateTime);
		animationManager.walkAnimation(stateTime);
		animationManager.slashAnimation(stateTime);
	}

	@Override
	public void render(float delta) {

		cameraManager.mapRendering();

		cameraManager.moveCamera(sprite);

		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		stateTime += Gdx.graphics.getDeltaTime();
		currentFrame = animationManager.getIdleAnimation().getKeyFrame(stateTime,true);

		this.playerMovement();

		cameraManager.getCamera().update();
		cameraManager.getMapRenderer().setView(cameraManager.getCamera());
		cameraManager.getMapRenderer().render();
		mainGame.batch.setProjectionMatrix(cameraManager.getCamera().combined);
		mainGame.batch.begin();

		mainGame.batch.draw(currentFrame,x,y,0.5f,0.5f);
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
		animationManager.getKnightIdleSheet().dispose();
		animationManager.getKnightWalkSheet().dispose();
		animationManager.getKnightSlashSheet().dispose();
		mainGame.batch.dispose();
	}

	private void playerMovement() {
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
			currentFrame = animationManager.getWalkAnimation().getKeyFrame(stateTime,true);
		}

		if(Gdx.input.isKeyPressed(Keys.SPACE)) {
			currentFrame = animationManager.getSlashAnimation().getKeyFrame(stateTime,true);
		}

		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			mainGame.setScreen(new PauseMenuScreen(mainGame,this));
		}


		sprite.setX(x);
		sprite.setY(y);
	}
}
