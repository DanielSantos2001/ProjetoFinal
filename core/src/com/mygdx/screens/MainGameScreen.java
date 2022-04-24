package com.mygdx.screens;

import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.mygdx.animations.TextureManager;
import com.mygdx.camera.*;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.animations.AnimationManager;
import com.mygdx.game.KnightsOath;

import java.awt.*;

public class MainGameScreen implements Screen {
    private final KnightsOath mainGame;
    private float knightSpeed = 1.5f;
    private float skeletonSpeed = 0.5f;
    private float skeletonX;
    private float skeletonY;
    private Sprite knightSprite;
    private float stateTime;
    private TextureRegion currentKnightFrame;
    private TextureRegion currentSkeletonFrame;
    private AnimationManager animationManager;
    private CameraManager cameraManager;
    private TextureManager textureManager;
    private String attackState;
    private TiledMapTileLayer collisionLayer;
    private Rectangle knightBounds;

    private String blockedKey = "blocked";
    public MainGameScreen(KnightsOath game) {
        mainGame = game;
        animationManager = new AnimationManager();
        cameraManager = new CameraManager();
        textureManager = new TextureManager();
        knightSprite = new Sprite(textureManager.getKnightIdleSheet());
        skeletonX = 855.60596f;
        skeletonY = 977.9982f;
        knightBounds = new Rectangle(480,15,30,30);
    }

    @Override
    public void show() {
        animationManager.knightIdleAnimation();
        animationManager.skeletonIdleAnimation();
        animationManager.knightWalkAnimation("walkFront", textureManager.getKnightWalkSheet("walkFront"));
        animationManager.skeletonWalkBackAnimation(textureManager.getSkeletonWalkBackSheet());
        animationManager.knightAttackAnimation("attackFront", textureManager.getKnightAttackSheet("attackFront"));
        animationManager.skeletonAttackAnimation("attackFront", textureManager.getSkeletonAttackSheet("attackFront"));
        cameraManager.mapRendering();
        collisionLayer = (TiledMapTileLayer) cameraManager.getMap().getLayers().get(0);
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

        cameraManager.getCamera().update();
        cameraManager.getMapRenderer().setView(cameraManager.getCamera());
        cameraManager.getMapRenderer().render();
        mainGame.batch.setProjectionMatrix(cameraManager.getCamera().combined);
        mainGame.batch.begin();

        mainGame.batch.draw(currentKnightFrame,knightBounds.x,knightBounds.y, knightBounds.width,knightBounds.height);
        mainGame.batch.draw(currentSkeletonFrame, skeletonX, skeletonY, 30f, 30f);

        mainGame.batch.end();
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
                knightBounds.y += knightSpeed;
            }

            animationManager.knightWalkAnimation("walkBack", textureManager.getKnightWalkSheet("walkBack"));

            animationManager.knightAttackAnimation("attackBack", textureManager.getKnightAttackSheet("attackBack"));

            animationManager.skeletonAttackAnimation("attackBack", textureManager.getSkeletonAttackSheet("attackBack"));

            currentKnightFrame = animationManager.getKnightWalkAnimation("walkBack").getKeyFrame(stateTime, true);

            this.attackState = "attackBack";
        }

        if (Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S)) {

            if(!collidesBottom()){
                knightBounds.y -= knightSpeed;
            }

            animationManager.knightWalkAnimation("walkFront", textureManager.getKnightWalkSheet("walkFront"));

            animationManager.knightAttackAnimation("attackFront", textureManager.getKnightAttackSheet("attackFront"));

            animationManager.skeletonAttackAnimation("attackFront", textureManager.getSkeletonAttackSheet("attackFront"));

            currentKnightFrame = animationManager.getKnightWalkAnimation("walkFront").getKeyFrame(stateTime, true);

            this.attackState = "attackFront";
        }

        if (Gdx.input.isKeyPressed(Keys.LEFT) || Gdx.input.isKeyPressed(Keys.A)) {

            if(!collidesLeft()){
                knightBounds.x -= knightSpeed;
            }

            animationManager.knightWalkAnimation("walkLeft", textureManager.getKnightWalkSheet("walkLeft"));

            animationManager.knightAttackAnimation("attackLeft", textureManager.getKnightAttackSheet("attackLeft"));

            animationManager.skeletonAttackAnimation("attackLeft", textureManager.getSkeletonAttackSheet("attackLeft"));

            currentKnightFrame = animationManager.getKnightWalkAnimation("walkLeft").getKeyFrame(stateTime, true);

            this.attackState = "attackLeft";
        }

        if (Gdx.input.isKeyPressed(Keys.RIGHT) || Gdx.input.isKeyPressed(Keys.D)) {

            if(!collidesRight()){
                knightBounds.x += knightSpeed;
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

        if (knightBounds.x > 815.9621 && knightBounds.x < 989.7722 && knightBounds.y > 884.583 && knightBounds.y < 1026.9208) {
            MoveToX = knightBounds.x;
            MoveToY = knightBounds.y;

            this.verifySkeletonAttackState();
        } else {
            MoveToX = (int) 855.60596f;
            MoveToY = (int) 977.9982f;

            animationManager.skeletonWalkBackAnimation(textureManager.getSkeletonWalkBackSheet());
            currentSkeletonFrame = animationManager.getSkeletonWalkBackAnimation().getKeyFrame(stateTime, true);
        }

        diffX = (int) (MoveToX - skeletonX);
        diffY = (int) (MoveToY - skeletonY);
        angle = (float) Math.atan2(diffY, diffX);

        if(diffX == 0 && diffY == 0 ){
            skeletonSpeed = 0.0f;
            animationManager.skeletonIdleAnimation();
            currentSkeletonFrame = animationManager.getSkeletonIdleAnimation().getKeyFrame(stateTime, true);
        }else{
            skeletonSpeed = 0.5f;
        }

        skeletonX += (skeletonSpeed) * Math.cos(angle);
        skeletonY += (skeletonSpeed) * Math.sin(angle);
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
