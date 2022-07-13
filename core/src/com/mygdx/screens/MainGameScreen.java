package com.mygdx.screens;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.g2d.NinePatch;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.UI.Dialogue;
import com.mygdx.UI.HUD;
import com.mygdx.camera.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.mygdx.systems.DialogueSystem;
import com.mygdx.game.HealthBar;
import com.mygdx.game.KnightsOath;
import com.mygdx.models.Knight;
import com.mygdx.systems.NPCSystem;

import static com.mygdx.UI.HUD.skeletonHudImage;
import static com.mygdx.animations.AnimationManager.animationManager;
import static com.mygdx.animations.TextureManager.textureManager;
import static com.mygdx.screens.ShopScreen.potionCount;
import static com.mygdx.systems.EnemySystem.enemySystem;


public class MainGameScreen implements Screen {
    private final KnightsOath mainGame;
    private final CameraManager cameraManager;
    public static Knight knight;
    public static float stateTime;
    public static HealthBar skeletonHealthBar;
    public static Stage stage;
    public static boolean doAnimation = false;
    public static boolean isDead = false;
    private final HUD hud;
    private final DialogueSystem dialogueSystem;
    public static int count = 0;
    private double skeletonAttackTimer = 0;
    private double knightAttackTimer = 0;
    private final NPCSystem npcSystem;

    public MainGameScreen(KnightsOath game) {
        mainGame = game;
        cameraManager = new CameraManager();
        knight = new Knight(25, 25, 515, 1250, mainGame, this);
        hud = new HUD();
        dialogueSystem = new DialogueSystem(this);
        npcSystem = new NPCSystem(game, this);
    }

    @Override
    public void show() {
        animationManager.knightHurtAnimation();

        cameraManager.mapRendering();

        stage = new Stage(new ScreenViewport());

        clearDialogs();
        addDialogs();
        setHealthBars();
        hud.setHUD();
        addActors();
        detectKeys();
    }

    @Override
    public void render(float delta) {
        mainGame.batch.begin();
        stateTime += delta;
        knight.create();
        knight.render(delta);
        npcSystem.create();
        npcSystem.render(delta);

        setStartDialog();
        if(potionCount != 0){
            stage.addActor(hud.getPotionImage());
        }else{
            hud.getPotionImage().remove();
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

    private void clearDialogs(){
        dialogueSystem.getArmorSellerDialog().clear();
        dialogueSystem.getPotionsSellerDialog().clear();
        dialogueSystem.getWeaponsSellerDialog().clear();
    }
    private void addDialogs(){
        dialogueSystem.addArmorSellerDialog();
        dialogueSystem.addKingDialog();
        dialogueSystem.addPotionsSellerDialog();
        dialogueSystem.addWeaponsSellerDialog();
    }
    private void setStartDialog() {
        boolean verifyDialogue = false;
        if (cameraManager.getMapPath().equals("Maps/map1.tmx")) {

                if (Intersector.overlaps(dialogueSystem.getKingCircle(), knight.getKnightBounds())) {
                    dialogueSystem.kingDialog(count, mainGame);
                    verifyDialogue = true;
                }

                if(Intersector.overlaps(dialogueSystem.getArmorSellerCircle(),knight.getKnightBounds())){
                    dialogueSystem.armorSellerDialog(mainGame);
                    verifyDialogue = true;
                }

                if(Intersector.overlaps(dialogueSystem.getWeaponsSellerCircle(),knight.getKnightBounds())){
                    dialogueSystem.weaponsSellerDialog(mainGame);
                    verifyDialogue = true;
                }

                if(Intersector.overlaps(dialogueSystem.getPotionsSellerCircle(),knight.getKnightBounds())){
                    dialogueSystem.potionsSellerDialog(mainGame);
                    verifyDialogue = true;
                }

                if(!verifyDialogue)count = 0;

            if (stateTime < 1.0) {
                dialogueSystem.setDialog(new Dialogue(new NinePatch((textureManager.getDialogKnightSheet())), "I have to talk \nwith the king"), mainGame,475,1210,495,1235);
            }
        }
    }

    private void setHealthBars() {
        skeletonHealthBar = new HealthBar(149, 14);
        skeletonHealthBar.setPosition(488, 466);
        knight.getHealthBar().setPosition(80, Gdx.graphics.getHeight() - 25);
    }

    private void addActors() {
        stage.addActor(knight.getHealthBar());
        stage.addActor(hud.getHudImage());
    }

    private void invincibilityTime(float delta) {
        if (skeletonAttackTimer > 0) {
            skeletonAttackTimer -= delta;
        } else if (skeletonAttackTimer < 0) skeletonAttackTimer = 0;

        doAnimation = false;

        if (skeletonAttackTimer == 0 && knight.getKnightBounds().overlaps(enemySystem.getSkeleton().getSkeletonBounds())) {
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

        if (knightAttackTimer == 0 && knight.getKnightBounds().overlaps(enemySystem.getSkeleton().getSkeletonBounds())) {
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

    private void detectKeys() {
        Gdx.input.setInputProcessor(new InputAdapter() {
            @Override
            public boolean keyDown(int keycode) {
                if (keycode == Input.Keys.X)
                    count++;

                if(keycode == Input.Keys.R){
                    if(potionCount <= 0)potionCount = 0;

                    if(potionCount > 0){
                        if(knight.getHealthBar().getValue() != 1.0f){
                            potionCount--;
                            knight.getHealthBar().setValue(knight.getHealthBar().getValue() + 0.05f);
                        }
                    }
                }

                return true;
            }
        });

    }

}
