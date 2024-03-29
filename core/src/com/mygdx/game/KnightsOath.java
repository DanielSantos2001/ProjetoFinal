package com.mygdx.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.mygdx.screens.MainMenuScreen;

public class KnightsOath extends Game {
	public SpriteBatch batch;
	private Viewport viewport;

	@Override
	public void create () {
		batch = new SpriteBatch();
		viewport = new ScreenViewport();
		viewport.apply();
		this.setScreen(new MainMenuScreen(this));
	}
	
	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose () {
		super.dispose();
	}
}
