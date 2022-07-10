package com.mygdx.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
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
    private final BitmapFont font;
    private final NinePatch ninePatchKnight;

    public MainGameScreen(KnightsOath game) {
        mainGame = game;
        cameraManager = new CameraManager();
        animationManager = new AnimationManager();
        TextureManager textureManager = new TextureManager();
        knight = new Knight(25, 25, 515, 1250, mainGame, this);
        hudImage = new Image(textureManager.getHudSheet());
        skeletonHudImage = new Image(textureManager.getSkeletonHudSheet());
        font = new BitmapFont(Gdx.files.internal("Textures/Skin/ui/font-title-export.fnt"), Gdx.files.internal("Textures/Skin/ui/font-title-export.png"), false);
        ninePatchKnight = new NinePatch(textureManager.getDialogKnightSheet());
    }

    @Override
    public void show() {
        animationManager.knightHurtAnimation();

        cameraManager.mapRendering();

        stage = new Stage(new ScreenViewport());

        setHUD();
        addActors();
    }

    @Override
    public void render(float delta) {
        mainGame.batch.begin();
        stateTime += delta;
        knight.create();
        knight.render(delta);


        if (stateTime < 1.4) {
            setDialog();
        }

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

    private void setDialog() {
        GlyphLayout glyphLayout = new GlyphLayout();
        glyphLayout.setText(font,"Lets start the day !");
        ninePatchKnight.draw(mainGame.batch,475, 1185,80,30);
        font.getData().setScale(0.056f);
        font.draw(mainGame.batch, glyphLayout, 495, 1205);
        font.setColor(Color.BLACK);
    }

    private void setHUD() {
        skeletonHealthBar = new HealthBar(149, 14);
        skeletonHealthBar.setPosition(488, 466);

        skeletonHudImage.setPosition(399, 378);
        skeletonHudImage.setWidth(270);
        skeletonHudImage.setHeight(120);

        hudImage.setPosition(0, Gdx.graphics.getHeight() - 109);
        hudImage.setWidth(250);
        hudImage.setHeight(120);

        knight.getHealthBar().setPosition(80, Gdx.graphics.getHeight() - 25);
    }

    private void addActors() {
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
