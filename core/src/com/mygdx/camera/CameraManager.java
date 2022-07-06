package com.mygdx.camera;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapRenderer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.math.Rectangle;


public class CameraManager {
	private OrthographicCamera camera;
	private TiledMap map;
	private TiledMapRenderer renderer;
	private AssetManager assetManager;
	private OrthoCamController cameraController;

	public CameraManager() {
		camera = new OrthographicCamera(150,150);
		assetManager = new AssetManager();
		cameraController = new OrthoCamController(camera);
	}

	public void mapRendering() {
		camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0);
		Gdx.input.setInputProcessor(cameraController);
		
		assetManager.setLoader(TiledMap.class, new TmxMapLoader(new InternalFileHandleResolver()));
		assetManager.load("Maps/map1.tmx", TiledMap.class);
		assetManager.finishLoading();
		
		map = assetManager.get("Maps/map1.tmx");
		renderer = new OrthogonalTiledMapRenderer(map, 1f);
	}
	
	public void moveCamera(Rectangle bounds) {
		camera.position.x = bounds.x;
		camera.position.y = bounds.y;
	}
	
	public OrthographicCamera getCamera() {
		return camera;
	}
	
	public TiledMapRenderer getMapRenderer() {
		return renderer;
	}

	public TiledMap getMap() {return map;}
}
