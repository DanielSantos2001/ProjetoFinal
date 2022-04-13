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
	private String state;

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
		animationManager.walkAnimation(stateTime, "walkFront",animationManager.getWalkSheet("walkFront"));
		animationManager.attackAnimation(stateTime,"attackFront",animationManager.getAttackSheet("attackFront"));
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

		mainGame.batch.draw(currentFrame,x,y,1.3f,1.3f);
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
		animationManager.getWalkSheet("walkBack").dispose();
		animationManager.getWalkSheet("walkFront").dispose();
		animationManager.getWalkSheet("walkRight").dispose();
		animationManager.getWalkSheet("walkLeft").dispose();
		animationManager.getAttackSheet("attackFront").dispose();
		animationManager.getAttackSheet("attackBack").dispose();
		animationManager.getAttackSheet("attackLeft").dispose();
		animationManager.getAttackSheet("attackRight").dispose();
		mainGame.batch.dispose();
	}

	private void playerMovement() {
		this.state = "attackIdle";
		
		if(Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W)) {
			y += speed * Gdx.graphics.getDeltaTime();	
			
			animationManager.walkAnimation(stateTime, "walkBack",animationManager.getWalkSheet("walkBack"));	
			
			animationManager.attackAnimation(stateTime, "attackBack", animationManager.getAttackSheet("attackBack"));
			
			currentFrame = animationManager.getWalkAnimation("walkBack").getKeyFrame(stateTime,true);
			
			this.state = "attackBack";
		}

		if(Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S)) {
			y -= speed * Gdx.graphics.getDeltaTime();
			
			animationManager.walkAnimation(stateTime, "walkFront",animationManager.getWalkSheet("walkFront"));	
			
			animationManager.attackAnimation(stateTime, "attackFront", animationManager.getAttackSheet("attackFront"));
			
			currentFrame = animationManager.getWalkAnimation("walkFront").getKeyFrame(stateTime,true);
			
			this.state = "attackFront";
		}

		if(Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) {
			x -= speed * Gdx.graphics.getDeltaTime();
			
			animationManager.walkAnimation(stateTime, "walkLeft",animationManager.getWalkSheet("walkLeft"));	
			
			animationManager.attackAnimation(stateTime, "attackLeft", animationManager.getAttackSheet("attackLeft"));
			
			currentFrame = animationManager.getWalkAnimation("walkLeft").getKeyFrame(stateTime,true);
			
			this.state = "attackLeft";
		}

		if(Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {
			x += speed * Gdx.graphics.getDeltaTime();
			
			animationManager.walkAnimation(stateTime, "walkRight",animationManager.getWalkSheet("walkRight"));	
			
			animationManager.attackAnimation(stateTime, "attackRight", animationManager.getAttackSheet("attackRight"));
			
			currentFrame = animationManager.getWalkAnimation("walkRight").getKeyFrame(stateTime,true);
			
			this.state = "attackRight";

		}

		if(Gdx.input.isKeyPressed(Keys.SPACE)) {
			this.verifyAttackState();
		}

		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			mainGame.setScreen(new PauseMenuScreen(mainGame,this));
		}

		sprite.setX(x);
		sprite.setY(y);
	}

	private void verifyAttackState() {
		switch(this.state) {
		case "attackFront":
			currentFrame = animationManager.getAttackAnimation("attackFront").getKeyFrame(stateTime,true);
			break;
		case "attackLeft":
			currentFrame = animationManager.getAttackAnimation("attackLeft").getKeyFrame(stateTime,true);
			break;
		case "attackRight":
			currentFrame = animationManager.getAttackAnimation("attackRight").getKeyFrame(stateTime,true);
			break;
		case "attackBack":
			currentFrame = animationManager.getAttackAnimation("attackBack").getKeyFrame(stateTime,true);
			break;
		default:
			currentFrame = animationManager.getAttackAnimation("attackFront").getKeyFrame(stateTime,true);
		}
	}
}
