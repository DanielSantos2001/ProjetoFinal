package com.mygdx.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.collisions.MapCollisions;
import com.mygdx.game.HealthBar;
import com.mygdx.game.KnightsOath;
import com.mygdx.screens.LoadingScreen;
import com.mygdx.screens.MainGameScreen;
import com.mygdx.screens.PauseMenuScreen;

import static com.mygdx.animations.AnimationManager.animationManager;
import static com.mygdx.animations.TextureManager.textureManager;
import static com.mygdx.screens.MainGameScreen.*;
import static com.mygdx.systems.EnemySystem.*;

public class Knight extends GameEntity {
    private final KnightsOath mainGame;
    private final Rectangle knightBounds;
    private String state;
    private MapCollisions mapCollisions;
    private final HealthBar healthBar;
    private TextureRegion currentKnightFrame;
    private final MainGameScreen mainGameScreen;

    public Knight(float knightWidth, float knightHeight, float knightX, float knightY, KnightsOath mainGame, MainGameScreen mainGameScreen) {
        super(knightWidth, knightHeight, knightX, knightY);
        knightBounds = new Rectangle(knightX, knightY, knightWidth, knightHeight);
        healthBar = new HealthBar(142, 20);
        this.speed = 80f;
        this.mainGame = mainGame;
        this.mainGameScreen = mainGameScreen;
    }

    @Override
    public void create() {
        animationManager.knightIdleAnimation();
        animationManager.knightHurtAnimation();
        animationManager.skeletonDeathAnimation();
        animationManager.knightWalkAnimation("front", textureManager.getKnightWalkSheet("front"));
        animationManager.knightBlockAnimation("front", textureManager.getKnightBlockSheet("front"));
        animationManager.knightAttackAnimation("front", textureManager.getKnightAttackSheet("front"));
        animationManager.skeletonAttackAnimation("front", textureManager.getSkeletonAttackSheet("front"));
        mapCollisions = new MapCollisions(this.mainGameScreen.getCameraManager());
    }

    @Override
    public void update(float knightX, float knightY, boolean verifyDirection, boolean verifyCoordinate) {
        if (verifyCoordinate) {
            this.setKnightX(knightX, verifyDirection);
        } else {
            this.setKnightY(knightY, verifyDirection);
        }
    }

    @Override
    public void render(float delta) {
        enemySystem.create();
        enemySystem.render(delta);
        this.mainGameScreen.getCameraManager().moveCamera(this.getKnightBounds());

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        currentKnightFrame = animationManager.getKnightIdleAnimation().getKeyFrame(stateTime, true);

        this.playerMovement();
        setUpEnemy();
        this.mapTransitions();

        this.mainGameScreen.getCameraManager().getCamera().update();
        this.mainGameScreen.getCameraManager().getMapRenderer().setView(this.mainGameScreen.getCameraManager().getCamera());
        this.mainGameScreen.getCameraManager().getMapRenderer().render();
        mainGame.batch.setProjectionMatrix(this.mainGameScreen.getCameraManager().getCamera().combined);

        verifySkeletonMap();
        mainGame.batch.draw(currentKnightFrame, this.getKnightX() - 15, this.getKnightY() - 5, this.getKnightWidth(), this.getKnightHeight());
    }

    public HealthBar getHealthBar() {
        return this.healthBar;
    }

    public String getState() {
        return this.state;
    }

    public float getKnightSpeed() {
        return this.speed;
    }

    public Rectangle getKnightBounds() {
        return this.knightBounds;
    }

    public float getKnightWidth() {
        return this.knightBounds.width;
    }

    public float getKnightHeight() {
        return this.knightBounds.height;
    }

    public float getKnightX() {
        return this.knightBounds.x;
    }

    public float getKnightY() {
        return this.knightBounds.y;
    }

    public void setKnightX(float knightX, boolean verifyDirection) {
        if (verifyDirection) {
            this.knightBounds.x += knightX;
        } else {
            this.knightBounds.x -= knightX;
        }

    }

    public void setKnightY(float knightY, boolean verifyDirection) {
        if (verifyDirection) {
            this.knightBounds.y += knightY;
        } else {
            this.knightBounds.y -= knightY;
        }

    }

    private void playerMovement() {
        System.out.println(this.getKnightX() + "x");
        System.out.println(this.getKnightY() + "y");
        this.state = "idle";

        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {

            if (!mapCollisions.collidesTop(this.getKnightX(), this.getKnightY())) {
                this.update(this.getKnightX(), this.getKnightSpeed() * Gdx.graphics.getDeltaTime(), true, false);
            }

            this.state = "back";
            initializeAnimations();

        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {

            if (!mapCollisions.collidesBottom(this.getKnightX(), this.getKnightY())) {
                this.update(this.getKnightX(), this.getKnightSpeed() * Gdx.graphics.getDeltaTime(), false, false);
            }


            this.state = "front";
            initializeAnimations();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {

            if (!mapCollisions.collidesLeft(this.getKnightX(), this.getKnightY())) {
                this.update(this.getKnightSpeed() * Gdx.graphics.getDeltaTime(), this.getKnightY(), false, true);
            }

            this.state = "left";
            initializeAnimations();

        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {

            if (!mapCollisions.collidesRight(this.getKnightX(), this.getKnightY())) {
                this.update(this.getKnightSpeed() * Gdx.graphics.getDeltaTime(), this.getKnightY(), true, true);
            }

            this.state = "right";
            initializeAnimations();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            currentKnightFrame = animationManager.getKnightAttackAnimation(this.state).getKeyFrame(stateTime, true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            currentKnightFrame = animationManager.getKnightBlockAnimation(this.state).getKeyFrame(stateTime, true);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            mainGame.setScreen(new PauseMenuScreen(mainGame, mainGameScreen));
        }
    }

    private void initializeAnimations() {
        animationManager.knightWalkAnimation(this.state, textureManager.getKnightWalkSheet(this.state));
        animationManager.knightAttackAnimation(this.state, textureManager.getKnightAttackSheet(this.state));
        animationManager.skeletonAttackAnimation(this.state, textureManager.getSkeletonAttackSheet(this.state));
        animationManager.knightBlockAnimation(this.state, textureManager.getKnightBlockSheet(this.state));
        currentKnightFrame = animationManager.getKnightWalkAnimation(this.state).getKeyFrame(stateTime, true);
    }

    private void setUpEnemy() {
        if (this.mainGameScreen.getCameraManager().getMapPath().equals("Maps/map1.tmx"))
            enemySystem.skeletonFollowPlayer(enemySystem.getSkeleton(), this.getKnightX(), this.getKnightY(), 834.8696f, 886.01495f,
                    1008.2352f, 1025.3497f, 855.60596f, 977.9982f);


        if (this.mainGameScreen.getCameraManager().getMapPath().equals("Maps/map4.tmx")) {
            enemySystem.skeletonFollowPlayer(enemySystem.getTutorialSkeleton(), this.getKnightX(), this.getKnightY(), 163.47707f, 15.948988f,
                    303.82486f, 184.0824f, 230.76222f, 94.94184f);
        }
    }

    private void verifySkeletonMap() {
        if (this.mainGameScreen.getCameraManager().getMapPath().equals("Maps/map1.tmx"))
            verifySkeletonDeath(enemySystem.getSkeleton());

        if (this.mainGameScreen.getCameraManager().getMapPath().equals("Maps/map4.tmx"))
            verifySkeletonDeath(enemySystem.getTutorialSkeleton());
    }

    private void verifySkeletonDeath(Skeleton skeleton) {
        if (!skeleton.getIsDead()) {
            if (doAnimation) {
                enemySystem.verifySkeletonAttackState(this.getState());
                currentKnightFrame = animationManager.getKnightHurtAnimation().getKeyFrame(stateTime, true);
            }

            mainGame.batch.draw(skeleton.getCurrentFrame(), skeleton.getSkeletonX(), skeleton.getSkeletonY(),
                    skeleton.getSkeletonWidth(), skeleton.getSkeletonHeight());
        } else {
            skeleton.getHealthBar().remove();
        }
    }

    private void mapTransitions() {

        if (mapCollisions.tpCastleUp(this.getKnightX(), this.getKnightY())) {
            knightBounds.x = 515;
            knightBounds.y = 1090;
            mainGame.setScreen(new LoadingScreen(mainGame, mainGameScreen, "castleUp", mainGameScreen, knightBounds));
        }

        if (mapCollisions.tpCastleDown(this.getKnightX(), this.getKnightY())) {
            knightBounds.x = 515;
            knightBounds.y = 1200;
            mainGame.setScreen(new LoadingScreen(mainGame, mainGameScreen, "castleDown", mainGameScreen, knightBounds));
        }

        if (mapCollisions.tpHouseUp(this.getKnightX(), this.getKnightY())) {
            mainGame.setScreen(new LoadingScreen(mainGame, mainGameScreen, "houseUp", mainGameScreen, knightBounds));
        }

        if (mapCollisions.tpHouseDown(this.getKnightX(), this.getKnightY())) {
            mainGame.setScreen(new LoadingScreen(mainGame, mainGameScreen, "houseDown", mainGameScreen, knightBounds));
        }

        if (mapCollisions.tpForestUp(this.getKnightX(), this.getKnightY())) {
            mainGame.setScreen(new LoadingScreen(mainGame, mainGameScreen, "forestUp", mainGameScreen, knightBounds));
        }

        if (mapCollisions.tpForestDown(this.getKnightX(), this.getKnightY())) {

            mainGame.setScreen(new LoadingScreen(mainGame, mainGameScreen, "forestDown", mainGameScreen, knightBounds));
        }

        if (mapCollisions.tpCaveUp(this.getKnightX(), this.getKnightY())) {

            mainGame.setScreen(new LoadingScreen(mainGame, mainGameScreen, "caveUp", mainGameScreen, knightBounds));
        }

        if (mapCollisions.tpCaveDown(this.getKnightX(), this.getKnightY())) {

            mainGame.setScreen(new LoadingScreen(mainGame, mainGameScreen, "caveDown", mainGameScreen, knightBounds));
        }
    }
}
