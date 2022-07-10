package com.mygdx.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.animations.AnimationManager;
import com.mygdx.animations.TextureManager;

import static com.mygdx.screens.MainGameScreen.*;

public class Skeleton extends GameEntity {
    private final Rectangle skeletonBounds;
    private final TextureManager textureManager;
    private final AnimationManager animationManager;
    public static TextureRegion currentSkeletonFrame;

    public Skeleton(float width, float height, float x, float y) {
        super(width, height, x, y);
        skeletonBounds = new Rectangle(x, y,width, height);
        this.speed = 0.5f;
        animationManager = new AnimationManager();
        textureManager = new TextureManager();
    }

    @Override
    public void create() {
        animationManager.skeletonIdleAnimation();
        animationManager.skeletonWalkBackAnimation(textureManager.getSkeletonWalkBackSheet());
        animationManager.skeletonAttackAnimation("front", textureManager.getSkeletonAttackSheet("front"));
        animationManager.skeletonAttackAnimation("back", textureManager.getSkeletonAttackSheet("back"));
        animationManager.skeletonAttackAnimation("left", textureManager.getSkeletonAttackSheet("left"));
        animationManager.skeletonAttackAnimation("right", textureManager.getSkeletonAttackSheet("right"));
    }

    @Override
    public void update(float x, float y, boolean verifyDirection, boolean verifyCoordinate) {

    }

    @Override
    public void render(float delta) {
        currentSkeletonFrame = animationManager.getSkeletonIdleAnimation().getKeyFrame(stateTime, true);
    }

    public Rectangle getSkeletonBounds(){
        return this.skeletonBounds;
    }

    public float getSkeletonWidth(){
        return this.skeletonBounds.width;
    }

    public float getSkeletonHeight(){
        return this.skeletonBounds.height;
    }
    public float getSkeletonX(){
        return this.skeletonBounds.x;
    }

    public float getSkeletonY(){
        return this.skeletonBounds.y;
    }
    public void setSkeletonY(float skeletonY){
        skeletonBounds.y += skeletonY;
    }

    public void setSkeletonX(float skeletonX){
        skeletonBounds.x += skeletonX;
    }

    public void skeletonFollowPlayer(float knightX,float knightY,float stateTime) {
        int MoveToX;
        int MoveToY;
        float angle;
        int diffX;
        int diffY;

        if ( knightX > 834.8696f &&  knightX < 1008.2352f && knightY > 886.01495f && knightY < 1025.3497f) {
            MoveToX = (int) knightX;
            MoveToY = (int) knightY;

            stage.addActor(skeletonHudImage);
            stage.addActor(skeletonHealthBar);

        } else {
            MoveToX = (int) 855.60596f;
            MoveToY = (int) 977.9982f;

            skeletonHudImage.remove();
            skeletonHealthBar.remove();

            animationManager.skeletonWalkBackAnimation(textureManager.getSkeletonWalkBackSheet());
            currentSkeletonFrame = animationManager.getSkeletonWalkBackAnimation().getKeyFrame(stateTime, true);
        }

        diffX = (int) (MoveToX - skeletonBounds.x);
        diffY = (int) (MoveToY - skeletonBounds.y);
        angle = (float) Math.atan2(diffY, diffX);

        if (diffX == 0 && diffY == 0) {
            this.speed = 0.0f;
            animationManager.skeletonIdleAnimation();
            currentSkeletonFrame = animationManager.getSkeletonIdleAnimation().getKeyFrame(stateTime, true);

        } else {
            this.speed = 0.5f;
        }

        this.setSkeletonX((float) ((this.speed) * Math.cos(angle)));
        this.setSkeletonY((float) ((this.speed) * Math.sin(angle)));
    }

    public void verifySkeletonAttackState(String state) {
        currentSkeletonFrame = animationManager.getSkeletonAttackAnimation(state).getKeyFrame(stateTime, true);
    }
}
