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
import com.mygdx.models.Skeleton;
import com.mygdx.systems.DialogueSystem;
import com.mygdx.game.KnightsOath;
import com.mygdx.models.Knight;
import com.mygdx.systems.NPCSystem;
import com.mygdx.systems.TutorialSystem;

import static com.mygdx.animations.AnimationManager.animationManager;
import static com.mygdx.animations.TextureManager.textureManager;
import static com.mygdx.screens.ShopScreen.potionCount;
import static com.mygdx.systems.EnemySystem.enemySystem;


public class MainGameScreen implements Screen {
    private final KnightsOath mainGame;
    private final CameraManager cameraManager;
    public static Knight knight;
    public static float stateTime;
    public static Stage stage;
    public static boolean doAnimation = false;
    private final HUD hud;
    private final DialogueSystem dialogueSystem;
    public static int count = 0;
    private int interactCount = 0;
    private double skeletonAttackTimer = 0;
    private double knightAttackTimer = 0;
    private final NPCSystem npcSystem;
    private final TutorialSystem tutorialSystem;

    public MainGameScreen(KnightsOath game) {
        mainGame = game;
        cameraManager = new CameraManager();
        knight = new Knight(25, 25, 515, 1250, mainGame, this);
        hud = new HUD();
        dialogueSystem = new DialogueSystem(this);
        tutorialSystem = new TutorialSystem(this, game);
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
        hud.setSkeletonHUD(enemySystem.getSkeleton());
        hud.setSkeletonHUD(enemySystem.getTutorialSkeleton());
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
        tutorialSystem.create();
        tutorialSystem.render(delta);

        setDialog();
        setSigns();

        if (potionCount != 0) {
            stage.addActor(hud.getPotionImage());
        } else {
            hud.getPotionImage().remove();
        }

        verifySkeletonDeath(delta);

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

    private void verifySkeletonDeath(float delta) {
        if (!enemySystem.getSkeleton().getIsDead()) {
            if (cameraManager.getMapPath().equals("Maps/map1.tmx")) {
                invincibilityTime(delta, enemySystem.getSkeleton());
                knightCanAttack(delta, enemySystem.getSkeleton());
            }
        }

        if (!enemySystem.getTutorialSkeleton().getIsDead()) {
            if (cameraManager.getMapPath().equals("Maps/map4.tmx")) {
                invincibilityTime(delta, enemySystem.getTutorialSkeleton());
                knightCanAttack(delta, enemySystem.getTutorialSkeleton());
            }
        }
    }

    private void clearDialogs() {
        dialogueSystem.getCaveWomanDialog().clear();
        dialogueSystem.getKingDialog().clear();
        dialogueSystem.getArmorSellerDialog().clear();
        dialogueSystem.getPotionsSellerDialog().clear();
        dialogueSystem.getWeaponsSellerDialog().clear();
    }

    private void addDialogs() {
        dialogueSystem.addArmorSellerDialog();
        dialogueSystem.addKingDialog();
        dialogueSystem.addPotionsSellerDialog();
        dialogueSystem.addWeaponsSellerDialog();
        dialogueSystem.addCaveWomanDialog();
    }

    private void setDialog() {
        boolean verifyDialogue = false;
        if (cameraManager.getMapPath().equals("Maps/map1.tmx")) {

            if (Intersector.overlaps(dialogueSystem.getKingCircle(), knight.getKnightBounds())) {
                dialogueSystem.kingDialog(mainGame);
                verifyDialogue = true;
            }

            if (Intersector.overlaps(dialogueSystem.getArmorSellerCircle(), knight.getKnightBounds())) {
                dialogueSystem.armorSellerDialog(mainGame);
                verifyDialogue = true;
            }

            if (Intersector.overlaps(dialogueSystem.getWeaponsSellerCircle(), knight.getKnightBounds())) {
                dialogueSystem.weaponsSellerDialog(mainGame);
                verifyDialogue = true;
            }

            if (Intersector.overlaps(dialogueSystem.getPotionsSellerCircle(), knight.getKnightBounds())) {
                dialogueSystem.potionsSellerDialog(mainGame);
                verifyDialogue = true;
            }

            if (stateTime < 1.0) {
                dialogueSystem.setDialog(new Dialogue(new NinePatch((textureManager.getDialogKnightSheet())), "I have to talk \nwith the king"), mainGame, 475, 1210, 495, 1235);
            }
        }

        if(cameraManager.getMapPath().equals("Maps/map4.tmx")){
            if (Intersector.overlaps(dialogueSystem.getCaveWomanCircle(), knight.getKnightBounds())) {
                dialogueSystem.caveWomanDialog(mainGame);
                tutorialSystem.drawTutorialAnimations("villager");
                verifyDialogue = true;
            }
        }

        if (!verifyDialogue) count = 0;
    }

    private void setSigns() {
        if (cameraManager.getMapPath().equals("Maps/map4.tmx")) {
            if (Intersector.overlaps(tutorialSystem.getMoveUpSign().getSignCircle(), knight.getKnightBounds())) {
                tutorialSystem.drawTutorialAnimations("moveUp");
            }

            if (Intersector.overlaps(tutorialSystem.getMoveDownSign().getSignCircle(), knight.getKnightBounds())) {
                tutorialSystem.drawTutorialAnimations("moveDown");
            }

            if (Intersector.overlaps(tutorialSystem.getMoveLeftSign().getSignCircle(), knight.getKnightBounds())) {
                tutorialSystem.drawTutorialAnimations("moveLeft");
            }

            if (Intersector.overlaps(tutorialSystem.getMoveRightSign().getSignCircle(), knight.getKnightBounds())) {
                tutorialSystem.drawTutorialAnimations("moveRight");
            }

            if (Intersector.overlaps(tutorialSystem.getAttackSign().getSignCircle(), knight.getKnightBounds())) {
                tutorialSystem.drawTutorialAnimations("attack");
            }

            if (Intersector.overlaps(tutorialSystem.getBlockSign().getSignCircle(), knight.getKnightBounds())) {
                tutorialSystem.drawTutorialAnimations("block");
            }

            if (Intersector.overlaps(tutorialSystem.getUsePotionSign().getSignCircle(), knight.getKnightBounds())) {
                tutorialSystem.drawTutorialAnimations("potion");
            }

            if (Intersector.overlaps(tutorialSystem.getChestSign().getSignCircle(), knight.getKnightBounds())) {
                tutorialSystem.drawTutorialAnimations("object");

                if (Gdx.input.isKeyPressed(Input.Keys.E)) {
                    tutorialSystem.drawTutorialAnimations("chest");
                }
            }



        }
    }

    private void setHealthBars() {
        enemySystem.getSkeleton().getHealthBar().setPosition(488, 466);
        enemySystem.getTutorialSkeleton().getHealthBar().setPosition(488, 466);
        knight.getHealthBar().setPosition(80, Gdx.graphics.getHeight() - 25);
    }

    private void addActors() {
        stage.addActor(knight.getHealthBar());
        stage.addActor(hud.getHudImage());
    }

    private void invincibilityTime(float delta, Skeleton skeleton) {
        if (skeletonAttackTimer > 0) {
            skeletonAttackTimer -= delta;
        } else if (skeletonAttackTimer < 0) skeletonAttackTimer = 0;

        doAnimation = false;

        if (skeletonAttackTimer == 0 && knight.getKnightBounds().overlaps(skeleton.getSkeletonBounds())) {
            System.out.println();
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

    private void knightCanAttack(float delta, Skeleton skeleton) {
        if (knightAttackTimer > 0) {
            knightAttackTimer -= delta;
        } else if (knightAttackTimer < 0) knightAttackTimer = 0;

        if (knightAttackTimer == 0 && knight.getKnightBounds().overlaps(skeleton.getSkeletonBounds())) {
            if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
                skeleton.getHealthBar().setValue(skeleton.getHealthBar().getValue() - 0.4f);
            }

            if (skeleton.getHealthBar().getValue() == 0.0f) {
                skeleton.setIsDead(true);

                skeleton.getSkeletonHud().setVisible(false);
                skeleton.getHealthBar().remove();
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

                if (interactCount == 0) {
                    if (keycode == Input.Keys.E) {
                        potionCount++;
                        interactCount++;
                    }
                }


                if (keycode == Input.Keys.R) {
                    if (potionCount <= 0) potionCount = 0;

                    if (potionCount > 0) {
                        if (knight.getHealthBar().getValue() != 1.0f) {
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
