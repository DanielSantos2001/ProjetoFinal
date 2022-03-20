package com.mygdx.screens;

import com.mygdx.camera.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.game.KnightsOath;

public class MainGameScreen implements Screen {
	private final KnightsOath mainGame;
	private Texture img;
	private static final float speed = 1;
	private float x;
	private float y;
	private TiledMap map;
	private TiledMapRenderer renderer;
	private OrthographicCamera camera;
	private OrthoCamController cameraController;
	private AssetManager assetManager;
	private Sprite sprite;
	
	
	public MainGameScreen(KnightsOath game){
		mainGame = game;
		img = new Texture("Textures/knight.png");
		sprite = new Sprite(img);
		x = 5f;
		y = 5f;
	}

	@Override
	public void show() {
		
	}

	@Override
	public void render(float delta) {
		
		this.mapRendering();
		this.playerMovement();
		this.moveCamera();
		
		ScreenUtils.clear(100f / 255f, 100f / 255f, 250f / 255f, 1f);
		camera.update();
		renderer.setView(camera);
		renderer.render();
		mainGame.batch.setProjectionMatrix(camera.combined);
		mainGame.batch.begin();
		
		mainGame.batch.draw(sprite,x,y,0.5f,0.5f);
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
		}
		
		if(Gdx.input.isKeyPressed(Keys.ESCAPE)) {
			mainGame.setScreen(new PauseMenuScreen(mainGame,this));
		}
		
		
		sprite.setX(x);
		sprite.setY(y);
	}
	
	private void mapRendering() {
		camera = new OrthographicCamera(5,5);
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		
		cameraController = new OrthoCamController(camera);
		Gdx.input.setInputProcessor(cameraController);
		
		assetManager = new AssetManager();
		assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		assetManager.load("Maps/map1.tmx", TiledMap.class);
		assetManager.finishLoading();
		map = assetManager.get("Maps/map1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1f /16f);
	}
	
	private void moveCamera() {
		camera.position.x = sprite.getX();
		camera.position.y = sprite.getY();
	}
}
