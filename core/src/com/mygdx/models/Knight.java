package com.mygdx.models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.animations.AnimationManager;
import com.mygdx.animations.TextureManager;
import com.mygdx.collisions.MapCollisions;
import com.mygdx.game.HealthBar;
import com.mygdx.game.KnightsOath;
import com.mygdx.screens.MainGameScreen;
import com.mygdx.screens.MainMenuScreen;
import com.mygdx.screens.PauseMenuScreen;

import static com.mygdx.screens.MainGameScreen.stateTime;

public class Knight extends GameEntity {
    private final KnightsOath mainGame;
    private Rectangle knightBounds;
    private TextureManager textureManager;
    private String attackState;
    private MapCollisions mapCollisions;
    private AnimationManager animationManager;
    private TextureRegion currentKnightFrame;
    private Skeleton skeleton;
    private MainGameScreen mainGameScreen;

    public Knight(float knightWidth, float knightHeight, float knightX, float knightY, KnightsOath mainGame, MainGameScreen mainGameScreen) {
        super(knightWidth, knightHeight, knightX, knightY);
        animationManager = new AnimationManager();
        textureManager = new TextureManager();
        knightBounds = new Rectangle(knightX, knightY, knightWidth, knightHeight);
        skeleton = new Skeleton(25, 25, 856, 977);
        this.speed = 80f;
        this.mainGame = mainGame;
        this.mainGameScreen = mainGameScreen;
    }

    @Override
    public void create() {
        animationManager.knightIdleAnimation();
        animationManager.knightHurtAnimation();
        animationManager.knightWalkAnimation("walkFront", textureManager.getKnightWalkSheet("walkFront"));
        animationManager.knightAttackAnimation("attackFront", textureManager.getKnightAttackSheet("attackFront"));
        animationManager.skeletonAttackAnimation("attackFront", textureManager.getSkeletonAttackSheet("attackFront"));
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
        skeleton.create();
        skeleton.render(delta);
        this.mainGameScreen.getCameraManager().moveCamera(this.getKnightBounds());

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        currentKnightFrame = animationManager.getKnightIdleAnimation().getKeyFrame(stateTime, true);

        this.playerMovement();
        skeleton.skeletonFollowPlayer(this.getKnightX(), this.getKnightY(), stateTime);

        if (MainGameScreen.doAnimation) {
            skeleton.verifySkeletonAttackState(this.getAttackState());
            currentKnightFrame = animationManager.getKnightHurtAnimation().getKeyFrame(stateTime, true);
        }

        this.mainGameScreen.getCameraManager().getCamera().update();
        this.mainGameScreen.getCameraManager().getMapRenderer().setView(this.mainGameScreen.getCameraManager().getCamera());
        this.mainGameScreen.getCameraManager().getMapRenderer().render();
        mainGame.batch.setProjectionMatrix(this.mainGameScreen.getCameraManager().getCamera().combined);
        mainGame.batch.begin();

        mainGame.batch.draw(currentKnightFrame, this.getKnightX(), this.getKnightY(), this.getKnightWidth(), this.getKnightHeight());
        mainGame.batch.draw(Skeleton.currentSkeletonFrame, skeleton.getSkeletonX(), skeleton.getSkeletonY(), skeleton.getSkeletonWidth(), skeleton.getSkeletonHeight());
        mainGame.batch.end();
    }

    public Skeleton getSkeleton() {
        return this.skeleton;
    }

    public String getAttackState() {
        return this.attackState;
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
        this.attackState = "attackIdle";

        if (Gdx.input.isKeyPressed(Input.Keys.UP) || Gdx.input.isKeyPressed(Input.Keys.W)) {

            if (!mapCollisions.collidesTop(this.getKnightX(), this.getKnightY())) {
                this.update(this.getKnightX(), this.getKnightSpeed() * Gdx.graphics.getDeltaTime(), true, false);
            }

            animationManager.knightWalkAnimation("walkBack", textureManager.getKnightWalkSheet("walkBack"));

            animationManager.knightAttackAnimation("attackBack", textureManager.getKnightAttackSheet("attackBack"));

            animationManager.skeletonAttackAnimation("attackBack", textureManager.getSkeletonAttackSheet("attackBack"));

            currentKnightFrame = animationManager.getKnightWalkAnimation("walkBack").getKeyFrame(stateTime, true);

            this.attackState = "attackBack";
        }

        if (Gdx.input.isKeyPressed(Input.Keys.DOWN) || Gdx.input.isKeyPressed(Input.Keys.S)) {

            if (!mapCollisions.collidesBottom(this.getKnightX(), this.getKnightY())) {
                this.update(this.getKnightX(), this.getKnightSpeed() * Gdx.graphics.getDeltaTime(), false, false);
            }

            animationManager.knightWalkAnimation("walkFront", textureManager.getKnightWalkSheet("walkFront"));

            animationManager.knightAttackAnimation("attackFront", textureManager.getKnightAttackSheet("attackFront"));

            animationManager.skeletonAttackAnimation("attackFront", textureManager.getSkeletonAttackSheet("attackFront"));

            currentKnightFrame = animationManager.getKnightWalkAnimation("walkFront").getKeyFrame(stateTime, true);

            this.attackState = "attackFront";
        }

        if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {

            if (!mapCollisions.collidesLeft(this.getKnightX(), this.getKnightY())) {
                this.update(this.getKnightSpeed() * Gdx.graphics.getDeltaTime(), this.getKnightY(), false, true);
            }

            animationManager.knightWalkAnimation("walkLeft", textureManager.getKnightWalkSheet("walkLeft"));

            animationManager.knightAttackAnimation("attackLeft", textureManager.getKnightAttackSheet("attackLeft"));

            animationManager.skeletonAttackAnimation("attackLeft", textureManager.getSkeletonAttackSheet("attackLeft"));

            currentKnightFrame = animationManager.getKnightWalkAnimation("walkLeft").getKeyFrame(stateTime, true);

            this.attackState = "attackLeft";
        }

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {

            if (!mapCollisions.collidesRight(this.getKnightX(), this.getKnightY())) {
                this.update(this.getKnightSpeed() * Gdx.graphics.getDeltaTime(), this.getKnightY(), true, true);
            }

            animationManager.knightWalkAnimation("walkRight", textureManager.getKnightWalkSheet("walkRight"));

            animationManager.knightAttackAnimation("attackRight", textureManager.getKnightAttackSheet("attackRight"));

            animationManager.skeletonAttackAnimation("attackRight", textureManager.getSkeletonAttackSheet("attackRight"));

            currentKnightFrame = animationManager.getKnightWalkAnimation("walkRight").getKeyFrame(stateTime, true);

            this.attackState = "attackRight";
        }

        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
            this.verifyKnightAttackState();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {
            mainGame.setScreen(new PauseMenuScreen(mainGame, mainGameScreen));
        }
    }

    private void verifyKnightAttackState() {
        switch (this.attackState) {
            case "attackLeft":
                currentKnightFrame = animationManager.getKnightAttackAnimation("attackLeft").getKeyFrame(stateTime, true);
                break;
            case "attackRight":
                currentKnightFrame = animationManager.getKnightAttackAnimation("attackRight").getKeyFrame(stateTime, true);
                break;
            case "attackBack":
                currentKnightFrame = animationManager.getKnightAttackAnimation("attackBack").getKeyFrame(stateTime, true);
                break;
            default:
                currentKnightFrame = animationManager.getKnightAttackAnimation("attackFront").getKeyFrame(stateTime, true);
        }
    }
}
