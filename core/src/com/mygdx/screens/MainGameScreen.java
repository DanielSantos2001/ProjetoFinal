package com.mygdx.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
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
    public static Knight knight;
    public static float stateTime;
    public static HealthBar skeletonHealthBar;
    public static Stage stage;
    private double skeletonAttackTimer = 0;
    private double knightAttackTimer = 0;
    public static boolean doAnimation = false;
    public static boolean isDead = false;

    public MainGameScreen(KnightsOath game) {
        mainGame = game;
        cameraManager = new CameraManager();
        textureManager = new TextureManager();
        animationManager = new AnimationManager();
        knight = new Knight(25,25,500,1250,mainGame,this);
    }

    @Override
    public void show() {
        animationManager.knightHurtAnimation();
        cameraManager.mapRendering();
        stage = new Stage();
        skeletonHealthBar = new HealthBar(100, 10);
        skeletonHealthBar.setPosition(540, 400);
        knight.getHealthBar().setPosition(0, Gdx.graphics.getHeight() - 20);
        stage.addActor(knight.getHealthBar());
    }

    @Override
    public void render(float delta) {
        mainGame.batch.begin();
        stateTime += delta;
        knight.create();
        knight.render(delta);

        if(!isDead){
            invincibilityTime(delta);
            knightCanAttack(delta);
        }

        mainGame.batch.end();
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
        textureManager.getKnightWalkSheet("back").dispose();
        textureManager.getKnightWalkSheet("front").dispose();
        textureManager.getKnightWalkSheet("right").dispose();
        textureManager.getKnightWalkSheet("left").dispose();
        textureManager.getKnightAttackSheet("front").dispose();
        textureManager.getKnightAttackSheet("back").dispose();
        textureManager.getKnightAttackSheet("left").dispose();
        textureManager.getKnightAttackSheet("right").dispose();
        textureManager.getSkeletonAttackSheet("front").dispose();
        textureManager.getSkeletonAttackSheet("back").dispose();
        textureManager.getSkeletonAttackSheet("left").dispose();
        textureManager.getSkeletonAttackSheet("right").dispose();
        textureManager.getKnightBlockSheet("left").dispose();
        textureManager.getKnightBlockSheet("right").dispose();
        textureManager.getKnightBlockSheet("back").dispose();
        textureManager.getKnightBlockSheet("front").dispose();
        mainGame.batch.dispose();
    }

    public CameraManager getCameraManager() {
        return this.cameraManager;
    }

    private void invincibilityTime(float delta) {
        if (skeletonAttackTimer > 0) {
            skeletonAttackTimer -= delta;
        } else if (skeletonAttackTimer < 0) skeletonAttackTimer = 0;

        doAnimation = false;
        if (skeletonAttackTimer == 0 && knight.getKnightBounds().overlaps(knight.getSkeleton().getSkeletonBounds())) {
            doAnimation = true;

            if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
                knight.getHealthBar().setValue(knight.getHealthBar().getValue() - 0.05f);
            } else {
                knight.getHealthBar().setValue(knight.getHealthBar().getValue() - 0.1f);
            }

            if (knight.getHealthBar().getValue() == 0.0f) {
                this.dispose();
                mainGame.setScreen(new MainMenuScreen(mainGame));

            }

            skeletonAttackTimer = 3.0;
        }

    }

    private void knightCanAttack(float delta) {
        if (knightAttackTimer > 0) {
            knightAttackTimer -= delta;
        } else if (knightAttackTimer < 0) knightAttackTimer = 0;

        if (knightAttackTimer == 0 && knight.getKnightBounds().overlaps(knight.getSkeleton().getSkeletonBounds())) {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                skeletonHealthBar.setValue(skeletonHealthBar.getValue() - 0.3f);
            }

            if (skeletonHealthBar.getValue() == 0.0f) {
                isDead = true;
            }

            knightAttackTimer = 1.0;
        }
    }
}
