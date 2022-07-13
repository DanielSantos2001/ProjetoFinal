package com.mygdx.systems;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.UI.HUD;
import com.mygdx.game.HealthBar;
import com.mygdx.models.GameEntity;
import com.mygdx.models.Skeleton;
import static com.mygdx.animations.AnimationManager.animationManager;
import static com.mygdx.animations.TextureManager.textureManager;
import static com.mygdx.screens.MainGameScreen.*;

public class EnemySystem extends GameEntity {
    private final Skeleton skeleton;
    private final Skeleton tutorialSkeleton;
    public static EnemySystem enemySystem = new EnemySystem();

    public EnemySystem(){
        skeleton = new Skeleton(25, 25, 856, 977,new HealthBar(149, 14),new TextureRegion(), new HUD().getSkeletonHudImage());
        tutorialSkeleton = new Skeleton(25,25,215,90,new HealthBar(149, 14),new TextureRegion(), new HUD().getSkeletonHudImage());
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
        skeleton.setCurrentFrame(animationManager.getSkeletonIdleAnimation().getKeyFrame(stateTime, true));
        tutorialSkeleton.setCurrentFrame(animationManager.getSkeletonIdleAnimation().getKeyFrame(stateTime, true));
    }

    public Skeleton getSkeleton(){return this.skeleton;}
    public Skeleton getTutorialSkeleton(){return this.tutorialSkeleton;}
    public void skeletonFollowPlayer(Skeleton skeleton,float knightX,float knightY,float minX,float minY,float maxX,float maxY,float idleX,float idleY) {
        int MoveToX;
        int MoveToY;
        float angle;
        int diffX;
        int diffY;

        if ( knightX > minX &&  knightX < maxX && knightY > minY && knightY < maxY) {
            MoveToX = (int) knightX;
            MoveToY = (int) knightY;

            stage.addActor(skeleton.getSkeletonHud());
            stage.addActor(skeleton.getHealthBar());

        } else {
            MoveToX = (int) idleX;
            MoveToY = (int) idleY;

            skeleton.getSkeletonHud().remove();
            skeleton.getHealthBar().remove();
        }

        diffX = (int) (MoveToX - skeleton.getSkeletonX());
        diffY = (int) (MoveToY - skeleton.getSkeletonY());
        angle = (float) Math.atan2(diffY, diffX);

        if (diffX == 0 && diffY == 0) {
            skeleton.setSkeletonSpeed(0.0f);
            skeleton.setCurrentFrame(animationManager.getSkeletonWalkBackAnimation().getKeyFrame(stateTime, true));
        } else {
            skeleton.setSkeletonSpeed(0.5f);
        }

        skeleton.setSkeletonX((float) ((skeleton.getSkeletonSpeed()) * Math.cos(angle)));
        skeleton.setSkeletonY((float) ((skeleton.getSkeletonSpeed()) * Math.sin(angle)));
    }

    public void verifySkeletonAttackState(String state) {
        skeleton.setCurrentFrame(animationManager.getSkeletonAttackAnimation(state).getKeyFrame(stateTime, true));
        tutorialSkeleton.setCurrentFrame(animationManager.getSkeletonAttackAnimation(state).getKeyFrame(stateTime, true));
    }
}
