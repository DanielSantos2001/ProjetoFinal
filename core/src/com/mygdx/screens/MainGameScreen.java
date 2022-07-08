package com.mygdx.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.animations.AnimationManager;
import com.mygdx.camera.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.game.HealthBar;
import com.mygdx.game.KnightsOath;
import com.mygdx.models.Knight;


public class MainGameScreen implements Screen {
    private final KnightsOath mainGame;
    private final CameraManager cameraManager;
    private final AnimationManager animationManager;
    public static Knight knight;
    public static float stateTime;
    public static HealthBar skeletonHealthBar;
    public static Stage stage;
    private double skeletonAttackTimer = 0;
    private double knightAttackTimer = 0;
    public static boolean doAnimation = false;
    public static boolean isDead = false;
    private final Image hudImage;
    public static Image skeletonHudImage;

    public MainGameScreen(KnightsOath game) {
        mainGame = game;
        cameraManager = new CameraManager();
        animationManager = new AnimationManager();
        knight = new Knight(25, 25, 515, 1250, mainGame, this);
        Texture hudTexture = new Texture("Textures/Char/HUD.png");
        hudImage = new Image(hudTexture);
        Texture skeletonHudTexture = new Texture("Textures/Enemy/Skeleton/hudSkeleton.png");
        skeletonHudImage = new Image(skeletonHudTexture);
    }

    @Override
    public void show() {
        animationManager.knightHurtAnimation();

        cameraManager.mapRendering();

        stage = new Stage();

        setHUD();
    }

    @Override
    public void render(float delta) {
        mainGame.batch.begin();
        stateTime += delta;
        knight.create();
        knight.render(delta);

        if (!isDead) {
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
    }

    public CameraManager getCameraManager() {
        return this.cameraManager;
    }

    private void setHUD(){
        skeletonHealthBar = new HealthBar(149, 14);
        skeletonHealthBar.setPosition(488, 466);

        skeletonHudImage.setPosition(399, 378);
        skeletonHudImage.setWidth(270);
        skeletonHudImage.setHeight(120);

        hudImage.setPosition(0, Gdx.graphics.getHeight() - 109);
        hudImage.setWidth(250);
        hudImage.setHeight(120);

        knight.getHealthBar().setPosition(80, Gdx.graphics.getHeight() - 25);

        stage.addActor(knight.getHealthBar());
        stage.addActor(hudImage);
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
                mainGame.setScreen(new DeathScreen(mainGame, "skeleton"));

            }

            skeletonAttackTimer = 2.0;
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

                skeletonHudImage.setVisible(false);
                skeletonHealthBar.remove();
            }

            knightAttackTimer = 1.0;
        }
    }
}
