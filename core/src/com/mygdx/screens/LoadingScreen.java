package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.animations.AnimationManager;
import com.mygdx.game.KnightsOath;


public class LoadingScreen implements Screen {
    private final KnightsOath mainGame;
    private final Screen parentScreen;
    private float elapsedTime;
    private final AnimationManager animationManager;
    private TextureRegion currentLoadingFrame;
    private Texture loadingTexture;
    private String mapToLoad;

    public LoadingScreen(KnightsOath game, Screen parent, String mapToLoad){
        mainGame = game;
        parentScreen = parent;
        animationManager = new AnimationManager();
        loadingTexture = new Texture("Textures/Buttons/loading.png");
        this.mapToLoad = mapToLoad;
    }

    @Override
    public void show() {
        animationManager.loadingAnimation();
    }

    @Override
    public void render(float delta) {
        mainGame.batch.begin();
        Gdx.gl.glClearColor(0,0,0,0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        elapsedTime += delta;

        currentLoadingFrame = animationManager.getLoadingAnimation().getKeyFrame(elapsedTime,true);

        mainGame.batch.draw(loadingTexture,445,1174,105,40);
        mainGame.batch.draw(currentLoadingFrame,530,1187,14,17);

        if(elapsedTime > 1.5){
            mainGame.setScreen(parentScreen);
        }

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
}
