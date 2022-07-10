package com.mygdx.systems;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.models.GameEntity;
import com.mygdx.models.Skeleton;

import static com.mygdx.UI.HUD.skeletonHudImage;
import static com.mygdx.animations.AnimationManager.animationManager;
import static com.mygdx.animations.TextureManager.textureManager;
import static com.mygdx.screens.MainGameScreen.*;
import static com.mygdx.screens.MainGameScreen.skeletonHealthBar;

public class EnemySystem extends GameEntity {
    private final Skeleton skeleton;
    public static TextureRegion currentSkeletonFrame;
    public static EnemySystem enemySystem = new EnemySystem();

    public EnemySystem(){
        skeleton = new Skeleton(25, 25, 856, 977);
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

    public Skeleton getSkeleton(){return this.skeleton;}
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

        diffX = (int) (MoveToX - skeleton.getSkeletonX());
        diffY = (int) (MoveToY - skeleton.getSkeletonY());
        angle = (float) Math.atan2(diffY, diffX);

        if (diffX == 0 && diffY == 0) {
            skeleton.setSkeletonSpeed(0.0f);
            currentSkeletonFrame = animationManager.getSkeletonIdleAnimation().getKeyFrame(stateTime, true);

        } else {
            skeleton.setSkeletonSpeed(0.5f);
        }

        skeleton.setSkeletonX((float) ((skeleton.getSkeletonSpeed()) * Math.cos(angle)));
        skeleton.setSkeletonY((float) ((skeleton.getSkeletonSpeed()) * Math.sin(angle)));
    }

    public void verifySkeletonAttackState(String state) {
        currentSkeletonFrame = animationManager.getSkeletonAttackAnimation(state).getKeyFrame(stateTime, true);
    }
}
