package com.mygdx.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.mygdx.animations.AnimationManager;
import com.mygdx.animations.TextureManager;
import com.mygdx.screens.MainGameScreen;

import static com.mygdx.screens.MainGameScreen.stateTime;

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
        animationManager.skeletonAttackAnimation("attackFront", textureManager.getSkeletonAttackSheet("attackFront"));
        animationManager.skeletonAttackAnimation("attackBack", textureManager.getSkeletonAttackSheet("attackBack"));
        animationManager.skeletonAttackAnimation("attackLeft", textureManager.getSkeletonAttackSheet("attackLeft"));
        animationManager.skeletonAttackAnimation("attackRight", textureManager.getSkeletonAttackSheet("attackRight"));
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

        if ( knightX > 815.9621f &&  knightX < 989.7722f && knightY > 884.583f && knightY < 1026.9208f) {
            MoveToX = (int) knightX;
            MoveToY = (int) knightY;


        } else {
            MoveToX = (int) 855.60596f;
            MoveToY = (int) 977.9982f;

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

    public void verifySkeletonAttackState(String attackState) {
        switch (attackState) {
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
}
