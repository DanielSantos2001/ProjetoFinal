package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.animations.AnimationManager;
import com.mygdx.game.KnightsOath;
import jdk.tools.jaotc.Main;


public class LoadingScreen implements Screen {
    private final KnightsOath mainGame;
    private final Screen parentScreen;
    private float elapsedTime;
    private final AnimationManager animationManager;
    private TextureRegion currentLoadingFrame;
    private Texture loadingTexture;
    private String mapToLoad;
    private MainGameScreen mainGameScreen;
    private Rectangle knightBounds;

    public LoadingScreen(KnightsOath game, Screen parent, String mapToLoad, MainGameScreen mainGameScreen, Rectangle knightBounds){
        mainGame = game;
        parentScreen = parent;
        animationManager = new AnimationManager();
        loadingTexture = new Texture("Textures/Buttons/loading.png");
        this.mapToLoad = mapToLoad;
        this.mainGameScreen = mainGameScreen;
        this.knightBounds = knightBounds;
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

        setCoordinates();

        if(elapsedTime > 1.5){
            mainGame.setScreen(parentScreen);
            externalMapsTransitions();
        }

        mainGame.batch.end();
    }

    private void setCoordinates(){
        switch (this.mapToLoad){
            case "castleDown":
                mainGame.batch.draw(loadingTexture,445,1090,105,40);
                mainGame.batch.draw(currentLoadingFrame,530,1103,14,17);
                break;
            case "castleUp":
                mainGame.batch.draw(loadingTexture,445,1174,105,40);
                mainGame.batch.draw(currentLoadingFrame,530,1187,14,17);
                break;
            case "houseDown":
                mainGame.batch.draw(loadingTexture,375,380,105,40);
                mainGame.batch.draw(currentLoadingFrame,460,393,14,17);
                break;
            case "houseUp":
                mainGame.batch.draw(loadingTexture,60,0,105,40);
                mainGame.batch.draw(currentLoadingFrame,145,13,14,17);
                break;
            case "forestUp":
                mainGame.batch.draw(loadingTexture,443,0,105,40);
                mainGame.batch.draw(currentLoadingFrame,528,13,14,17);
                break;
            case "forestDown":

                break;
        }
    }

    private void externalMapsTransitions(){
        switch (this.mapToLoad){
            case "houseDown":
                this.mainGameScreen.getCameraManager().changeMap("Maps/map3.tmx");
                knightBounds.x = 100;
                knightBounds.y =  30;
                break;
            case "houseUp":
                this.mainGameScreen.getCameraManager().changeMap("Maps/map1.tmx");
                knightBounds.x = 500;
                knightBounds.y = 300;
                break;
            case "forestUp":
                this.mainGameScreen.getCameraManager().changeMap("Maps/map2.tmx");
                knightBounds.x = 500;
                knightBounds.y = 1370;
                break;
            case "forestDown":
                this.mainGameScreen.getCameraManager().changeMap("Maps/map1.tmx");
                knightBounds.x = 500;
                knightBounds.y = 20;
                break;
            default:
        }
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
