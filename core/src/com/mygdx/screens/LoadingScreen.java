package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.animations.AnimationManager;
import com.mygdx.game.KnightsOath;

public class LoadingScreen implements Screen {
    private final KnightsOath mainGame;
    private final Screen parentScreen;
    private float elapsedTime;
    private final AnimationManager animationManager;
    private TextureRegion currentLoadingFrame;
    private final Texture loadingTexture;
    private final String mapToLoad;
    private final MainGameScreen mainGameScreen;
    private final Rectangle knightBounds;

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
                mainGame.batch.draw(loadingTexture,460,1090,105,40);
                mainGame.batch.draw(currentLoadingFrame,545,1103,14,17);
                break;
            case "castleUp":
                mainGame.batch.draw(loadingTexture,460,1174,105,40);
                mainGame.batch.draw(currentLoadingFrame,545,1187,14,17);
                break;
            case "houseDown":
                mainGame.batch.draw(loadingTexture,385,380,105,40);
                mainGame.batch.draw(currentLoadingFrame,470,393,14,17);
                break;
            case "houseUp":
                mainGame.batch.draw(loadingTexture,65,0,105,40);
                mainGame.batch.draw(currentLoadingFrame,150,13,14,17);
                break;
            case "forestUp":
                mainGame.batch.draw(loadingTexture,455,0,105,40);
                mainGame.batch.draw(currentLoadingFrame,540,13,14,17);
                break;
            case "forestDown":
                mainGame.batch.draw(loadingTexture,460,1380,105,40);
                mainGame.batch.draw(currentLoadingFrame,545,1393,14,17);
                break;
        }
    }

    private void externalMapsTransitions(){
        switch (this.mapToLoad){
            case "houseDown":
                this.mainGameScreen.getCameraManager().changeMap("Maps/map3.tmx");
                knightBounds.x = 115;
                knightBounds.y =  30;
                break;
            case "houseUp":
                this.mainGameScreen.getCameraManager().changeMap("Maps/map1.tmx");
                knightBounds.x = 515;
                knightBounds.y = 300;
                break;
            case "forestUp":
                this.mainGameScreen.getCameraManager().changeMap("Maps/map2.tmx");
                knightBounds.x = 515;
                knightBounds.y = 1370;
                break;
            case "forestDown":
                this.mainGameScreen.getCameraManager().changeMap("Maps/map1.tmx");
                knightBounds.x = 515;
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
