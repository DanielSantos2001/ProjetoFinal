package com.mygdx.screens;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.animations.AnimationManager;
import com.mygdx.animations.TextureManager;
import com.mygdx.camera.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.HealthBar;
import com.mygdx.game.KnightsOath;
import com.mygdx.models.Knight;

public class MainGameScreen implements Screen {
    private final KnightsOath mainGame;
    private final CameraManager cameraManager;
    private final TextureManager textureManager;
    private final AnimationManager animationManager;
    private final Knight knight;
    public static float stateTime;
    private Stage stage;
    private HealthBar healthBar;
    private double timer = 0;
    public static boolean doAnimation = false;

    public MainGameScreen(KnightsOath game) {
        mainGame = game;
        cameraManager = new CameraManager();
        textureManager = new TextureManager();
        animationManager = new AnimationManager();
        knight = new Knight(25,25,480,15,mainGame,this);
    }

    @Override
    public void show() {
        animationManager.knightHurtAnimation();
        cameraManager.mapRendering();

        stage = new Stage();

        healthBar = new HealthBar(200, 100);
        healthBar.setPosition(0, Gdx.graphics.getHeight() - 20);
        stage.addActor(healthBar);
    }

    @Override
    public void render(float delta) {
        stateTime += Gdx.graphics.getDeltaTime();
        knight.create();
        knight.render(delta);

        invincibilityTime(delta);

        stage.draw();
        stage.act();
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
        textureManager.getKnightIdleSheet().dispose();
        textureManager.getSkeletonIdleSheet().dispose();
        textureManager.getKnightHurtSheet().dispose();
        textureManager.getKnightWalkSheet("walkBack").dispose();
        textureManager.getKnightWalkSheet("walkFront").dispose();
        textureManager.getKnightWalkSheet("walkRight").dispose();
        textureManager.getKnightWalkSheet("walkLeft").dispose();
        textureManager.getKnightAttackSheet("attackFront").dispose();
        textureManager.getKnightAttackSheet("attackBack").dispose();
        textureManager.getKnightAttackSheet("attackLeft").dispose();
        textureManager.getKnightAttackSheet("attackRight").dispose();
        textureManager.getSkeletonAttackSheet("attackFront").dispose();
        textureManager.getSkeletonAttackSheet("attackBack").dispose();
        textureManager.getSkeletonAttackSheet("attackLeft").dispose();
        textureManager.getSkeletonAttackSheet("attackRight").dispose();
        mainGame.batch.dispose();
    }

    public CameraManager getCameraManager(){
        return this.cameraManager;
    }

    private void invincibilityTime(float delta){
        if (timer > 0) {
            timer -= delta;
        } else if (timer < 0) timer = 0;

        doAnimation = false;
        if (timer == 0 && knight.getKnightBounds().overlaps(knight.getSkeleton().getSkeletonBounds())) {
            doAnimation = true;
            healthBar.setValue(healthBar.getValue() - 0.1f);

            if (healthBar.getValue() == 0.0f) {
                this.dispose();
                mainGame.setScreen(new MainMenuScreen(mainGame));

            }

            timer = 3.0;
        }
    }
}
