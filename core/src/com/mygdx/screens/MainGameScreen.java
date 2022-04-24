package com.mygdx.screens;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.mygdx.animations.TextureManager;
import com.mygdx.camera.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.animations.AnimationManager;
import com.mygdx.game.HealthBar;
import com.mygdx.game.KnightsOath;


public class MainGameScreen implements Screen {
    private final KnightsOath mainGame;
    private float knightSpeed = 80f;
    private float skeletonSpeed = 0.5f;
    private Sprite knightSprite;
    private float knightX;
    private float knightY;
    private float stateTime;
    private TextureRegion currentKnightFrame;
    private TextureRegion currentSkeletonFrame;
    private AnimationManager animationManager;
    private CameraManager cameraManager;
    private TextureManager textureManager;
    private String attackState;
    private TiledMapTileLayer collisionLayer;
    private Rectangle knightBounds;
    private Rectangle skeletonBounds;

    private String blockedKey = "blocked";
    private long t0;
    private long timer;
    private Stage stage;
    private HealthBar healthBar;
    public MainGameScreen(KnightsOath game) {
        mainGame = game;
        animationManager = new AnimationManager();
        cameraManager = new CameraManager();
        textureManager = new TextureManager();
        knightSprite = new Sprite(textureManager.getKnightIdleSheet());
        knightX = 480;
        knightY = 15;
        knightBounds = new Rectangle(480,15,30,30);
        skeletonBounds = new Rectangle(856,977,30,30);
    }

    @Override
    public void show() {
        animationManager.knightIdleAnimation();
        animationManager.skeletonIdleAnimation();
        animationManager.knightHurtAnimation();
        animationManager.knightWalkAnimation("walkFront", textureManager.getKnightWalkSheet("walkFront"));
        animationManager.skeletonWalkBackAnimation(textureManager.getSkeletonWalkBackSheet());
        animationManager.knightAttackAnimation("attackFront", textureManager.getKnightAttackSheet("attackFront"));
        animationManager.skeletonAttackAnimation("attackFront", textureManager.getSkeletonAttackSheet("attackFront"));
        cameraManager.mapRendering();
        collisionLayer = (TiledMapTileLayer) cameraManager.getMap().getLayers().get(0);

        stage = new Stage();

        healthBar = new HealthBar(100, 40);
        healthBar.setPosition(10, Gdx.graphics.getHeight() - 20);
        stage.addActor(healthBar);
    }

    @Override
    public void render(float delta) {
        cameraManager.moveCamera(knightBounds);

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stateTime += Gdx.graphics.getDeltaTime();
        currentKnightFrame = animationManager.getKnightIdleAnimation().getKeyFrame(stateTime, true);
        currentSkeletonFrame = animationManager.getSkeletonIdleAnimation().getKeyFrame(stateTime, true);

        this.playerMovement();
        this.enemyFollowPlayer();

        if(knightBounds.overlaps(skeletonBounds)){
            this.verifySkeletonAttackState();
            currentKnightFrame = animationManager.getKnightHurtAnimation().getKeyFrame(stateTime,true);
            healthBar.setValue(healthBar.getValue() - 0.1f);
        }

        cameraManager.getCamera().update();
        cameraManager.getMapRenderer().setView(cameraManager.getCamera());
        cameraManager.getMapRenderer().render();
        mainGame.batch.setProjectionMatrix(cameraManager.getCamera().combined);
        mainGame.batch.begin();

        mainGame.batch.draw(currentKnightFrame,knightBounds.x,knightBounds.y, knightBounds.width,knightBounds.height);
        mainGame.batch.draw(currentSkeletonFrame, skeletonBounds.x, skeletonBounds.y, skeletonBounds.width, skeletonBounds.height);

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

    private void playerMovement() {
        this.attackState = "attackIdle";

        if (Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W)) {

            if(!collidesTop()){
                knightBounds.y += knightSpeed * Gdx.graphics.getDeltaTime();
            }

            animationManager.knightWalkAnimation("walkBack", textureManager.getKnightWalkSheet("walkBack"));

            animationManager.knightAttackAnimation("attackBack", textureManager.getKnightAttackSheet("attackBack"));

            animationManager.skeletonAttackAnimation("attackBack", textureManager.getSkeletonAttackSheet("attackBack"));

            currentKnightFrame = animationManager.getKnightWalkAnimation("walkBack").getKeyFrame(stateTime, true);

            this.attackState = "attackBack";
        }

        if (Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S)) {

            if(!collidesBottom()){
                knightBounds.y -= knightSpeed * Gdx.graphics.getDeltaTime();
            }

            animationManager.knightWalkAnimation("walkFront", textureManager.getKnightWalkSheet("walkFront"));

            animationManager.knightAttackAnimation("attackFront", textureManager.getKnightAttackSheet("attackFront"));

            animationManager.skeletonAttackAnimation("attackFront", textureManager.getSkeletonAttackSheet("attackFront"));

            currentKnightFrame = animationManager.getKnightWalkAnimation("walkFront").getKeyFrame(stateTime, true);

            this.attackState = "attackFront";
        }

        if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) {

            if(!collidesLeft()){
                knightBounds.x -= knightSpeed * Gdx.graphics.getDeltaTime();
            }

            animationManager.knightWalkAnimation("walkLeft", textureManager.getKnightWalkSheet("walkLeft"));

            animationManager.knightAttackAnimation("attackLeft", textureManager.getKnightAttackSheet("attackLeft"));

            animationManager.skeletonAttackAnimation("attackLeft", textureManager.getSkeletonAttackSheet("attackLeft"));

            currentKnightFrame = animationManager.getKnightWalkAnimation("walkLeft").getKeyFrame(stateTime, true);

            this.attackState = "attackLeft";
        }

        if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {

            if(!collidesRight()){
                knightBounds.x += knightSpeed * Gdx.graphics.getDeltaTime();
            }

            animationManager.knightWalkAnimation("walkRight", textureManager.getKnightWalkSheet("walkRight"));

            animationManager.knightAttackAnimation("attackRight", textureManager.getKnightAttackSheet("attackRight"));

            animationManager.skeletonAttackAnimation("attackRight", textureManager.getSkeletonAttackSheet("attackRight"));

            currentKnightFrame = animationManager.getKnightWalkAnimation("walkRight").getKeyFrame(stateTime, true);

            this.attackState = "attackRight";
        }

        if (Gdx.input.isKeyPressed(Keys.SPACE)) {
            this.verifyKnightAttackState();
        }

        if (Gdx.input.isKeyPressed(Keys.ESCAPE)) {
            mainGame.setScreen(new PauseMenuScreen(mainGame, this));
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

    private void verifySkeletonAttackState() {
        switch (this.attackState) {
            case "attackLeft":
                currentSkeletonFrame = animationManager.getSkeletonAttackAnimation("attackLeft").getKeyFrame(stateTime, true);
                break;
            case "attackRight":
                currentSkeletonFrame = animationManager.getSkeletonAttackAnimation("attackRight").getKeyFrame(stateTime, true);
                break;
            case "attackBack":
                currentSkeletonFrame = animationManager.getSkeletonAttackAnimation("attackBack").getKeyFrame(stateTime, true);
                break;
            default:
                currentSkeletonFrame = animationManager.getSkeletonAttackAnimation("attackFront").getKeyFrame(stateTime, true);
        }
    }

    private void enemyFollowPlayer() {
        int MoveToX;
        int MoveToY;
        float angle;
        int diffX;
        int diffY;

        if (knightBounds.x > 815.9621f && knightBounds.x < 989.7722f && knightBounds.y > 884.583f && knightBounds.y < 1026.9208f) {
            MoveToX = (int) knightBounds.x;
            MoveToY = (int) knightBounds.y;


        } else {
            MoveToX = (int) 855.60596f;
            MoveToY = (int) 977.9982f;

            animationManager.skeletonWalkBackAnimation(textureManager.getSkeletonWalkBackSheet());
            currentSkeletonFrame = animationManager.getSkeletonWalkBackAnimation().getKeyFrame(stateTime, true);
        }

        diffX = (int) (MoveToX - skeletonBounds.x);
        diffY = (int) (MoveToY - skeletonBounds.y);
        angle = (float) Math.atan2(diffY, diffX);

        if(diffX == 0 && diffY == 0 ){
            skeletonSpeed = 0.0f;
            animationManager.skeletonIdleAnimation();
            currentSkeletonFrame = animationManager.getSkeletonIdleAnimation().getKeyFrame(stateTime, true);
        }else{
            skeletonSpeed = 0.5f;
        }

        skeletonBounds.x += (skeletonSpeed) * Math.cos(angle);
        skeletonBounds.y += (skeletonSpeed) * Math.sin(angle);
    }

    public boolean isReadyToGetHit(){
        boolean can;
        long delta = System.currentTimeMillis() - t0;
        timer += delta;

        if(timer > 1000f){
            timer = 0;
            can = true;
        } else {
            can = false;
        }
        t0 = System.currentTimeMillis();
        return can;
    }

    private boolean isCellBlocked(int x, int y) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
    }

    public boolean collidesRight() {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (knightBounds.x / collisionLayer.getTileWidth())+2, (int) (knightBounds.y / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
    }

    public boolean collidesLeft() {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (knightBounds.x / collisionLayer.getTileWidth())+1, (int) (knightBounds.y / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
    }

    public boolean collidesTop() {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (knightBounds.x / collisionLayer.getTileWidth())+2, (int) (knightBounds.y / collisionLayer.getTileHeight())+1);
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
    }

    public boolean collidesBottom() {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (knightBounds.x / collisionLayer.getTileWidth())+1, (int) (knightBounds.y / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null && cell.getTile().getProperties().containsKey("blocked");
    }
}
