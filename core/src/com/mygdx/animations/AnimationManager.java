package com.mygdx.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import static com.mygdx.animations.TextureManager.textureManager;

public class AnimationManager {
    private Animation<TextureRegion> skeletonDeathAnimation;
    private Animation<TextureRegion> skeletonIdleAnimation;
    private Animation<TextureRegion> knightIdleAnimation;
    private Animation<TextureRegion> knightWalkFrontAnimation;
    private Animation<TextureRegion> knightWalkBackAnimation;
    private Animation<TextureRegion> skeletonWalkBackAnimation;
    private Animation<TextureRegion> knightWalkLeftAnimation;
    private Animation<TextureRegion> knightWalkRightAnimation;
    private Animation<TextureRegion> knightAttackFrontAnimation;
    private Animation<TextureRegion> skeletonAttackFrontAnimation;
    private Animation<TextureRegion> knightAttackBackAnimation;
    private Animation<TextureRegion> skeletonAttackBackAnimation;
    private Animation<TextureRegion> knightAttackRightAnimation;
    private Animation<TextureRegion> skeletonAttackRightAnimation;
    private Animation<TextureRegion> knightAttackLeftAnimation;
    private Animation<TextureRegion> skeletonAttackLeftAnimation;
    private Animation<TextureRegion> knightHurtAnimation;
    private Animation<TextureRegion> knightBlockBackAnimation;
    private Animation<TextureRegion> knightBlockFrontAnimation;
    private Animation<TextureRegion> knightBlockRightAnimation;
    private Animation<TextureRegion> knightBlockLeftAnimation;
    private Animation<TextureRegion> loadingAnimation;
    private Animation<TextureRegion> executionerIdleAnimation;
    private Animation<TextureRegion> kingIdleAnimation;
    private Animation<TextureRegion> moveUpAnimation;
    private Animation<TextureRegion> moveDownAnimation;
    private Animation<TextureRegion> moveLeftAnimation;
    private Animation<TextureRegion> moveRightAnimation;
    private TextureRegion[][] tmp;
    private TextureRegion[] idleFrames;
    public static AnimationManager animationManager = new AnimationManager();
    public AnimationManager() {}

    public void kingIdleAnimation(){
        tmp = TextureRegion.split(textureManager.getKingIdleSheet(),textureManager.getKingIdleSheet().getWidth()/4,textureManager.getKingIdleSheet().getHeight());

        idleFrames = new TextureRegion[4];

        int index = 0;

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 4; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }

        kingIdleAnimation = new Animation<>(0.5f, idleFrames);
    }

    public void moveRightAnimation(){
        tmp = TextureRegion.split(textureManager.getMoveRightSheet(),textureManager.getMoveRightSheet().getWidth()/5,textureManager.getMoveRightSheet().getHeight()/8);

        idleFrames = new TextureRegion[40];

        int index = 0;

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 5; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }

        moveRightAnimation = new Animation<>(0.075f, idleFrames);
    }
    public void loadingAnimation(){
        tmp = TextureRegion.split(textureManager.getLoadingAnimationSheet(),textureManager.getLoadingAnimationSheet().getWidth()/5,textureManager.getLoadingAnimationSheet().getHeight()/5);

        idleFrames = new TextureRegion[25];

        int index = 0;

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }

        loadingAnimation = new Animation<>(0.065f, idleFrames);
    }
    public void knightIdleAnimation() {
        tmp = TextureRegion.split(textureManager.getKnightIdleSheet(), textureManager.getKnightIdleSheet().getWidth() / 2, textureManager.getKnightIdleSheet().getHeight());

        idleFrames = new TextureRegion[2];

        int index = 0;

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 2; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }

        knightIdleAnimation = new Animation<>(0.5f, idleFrames);
    }

    public void executionerIdleAnimation(){
        tmp = TextureRegion.split(textureManager.getExecutionerIdleSheet(),textureManager.getExecutionerIdleSheet().getWidth()/4,textureManager.getExecutionerIdleSheet().getHeight()/2);

        idleFrames = new TextureRegion[8];

        int index = 0;

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }

        executionerIdleAnimation = new Animation<>(0.15f,idleFrames);
    }

    public void knightHurtAnimation() {
        tmp = TextureRegion.split(textureManager.getKnightHurtSheet(), textureManager.getKnightHurtSheet().getWidth() / 2, textureManager.getKnightHurtSheet().getHeight());

        idleFrames = new TextureRegion[2];

        int index = 0;

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 2; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }

        knightHurtAnimation = new Animation<>(0.25f, idleFrames);
    }

    public void skeletonIdleAnimation() {
        tmp = TextureRegion.split(textureManager.getSkeletonIdleSheet(), textureManager.getSkeletonIdleSheet().getWidth() / 2, textureManager.getSkeletonIdleSheet().getHeight());

        idleFrames = new TextureRegion[2];

        int index = 0;

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 2; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }

        skeletonIdleAnimation = new Animation<>(0.5f, idleFrames);
    }

    public void skeletonDeathAnimation() {
        tmp = TextureRegion.split(textureManager.getSkeletonDeathSheet(), textureManager.getSkeletonDeathSheet().getWidth() / 4, textureManager.getSkeletonDeathSheet().getHeight());

        idleFrames = new TextureRegion[4];

        int index = 0;

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 4; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }

        skeletonDeathAnimation = new Animation<>(0.5f,idleFrames);

    }

    public void skeletonWalkBackAnimation(Texture walkSheet) {
        tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / 5, walkSheet.getHeight());

        idleFrames = new TextureRegion[5];

        int index = 0;

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 5; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }

        skeletonWalkBackAnimation = new Animation<>(0.15f, idleFrames);
    }

    public void knightWalkAnimation(String walkState, Texture walkSheet) {
        tmp = TextureRegion.split(walkSheet, walkSheet.getWidth() / 5, walkSheet.getHeight());

        idleFrames = new TextureRegion[5];

        int index = 0;

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 5; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }

        switch (walkState) {
            case "left":
                knightWalkLeftAnimation = new Animation<>(0.15f, idleFrames);
                break;
            case "right":
                knightWalkRightAnimation = new Animation<>(0.15f, idleFrames);
                break;
            case "front":
                knightWalkFrontAnimation = new Animation<>(0.15f, idleFrames);
                break;
            case "back":
                knightWalkBackAnimation = new Animation<>(0.15f, idleFrames);
                break;
            default:
        }

    }

    public void knightBlockAnimation(String blockState, Texture blockSheet) {
        tmp = TextureRegion.split(blockSheet, blockSheet.getWidth() / 5, blockSheet.getHeight());

        idleFrames = new TextureRegion[5];

        int index = 0;

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 5; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }

        switch (blockState) {
            case "left":
                knightBlockLeftAnimation = new Animation<>(0.15f, idleFrames);
                break;
            case "right":
                knightBlockRightAnimation = new Animation<>(0.15f, idleFrames);
                break;
            case "front":
                knightBlockFrontAnimation = new Animation<>(0.15f, idleFrames);
                break;
            case "back":
                knightBlockBackAnimation = new Animation<>(0.15f, idleFrames);
                break;
            default:
        }

    }

    public void knightAttackAnimation(String attackState, Texture attackSheet) {
        tmp = TextureRegion.split(attackSheet, attackSheet.getWidth() / 3, attackSheet.getHeight());

        idleFrames = new TextureRegion[3];

        int index = 0;

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 3; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }

        switch (attackState) {
            case "left":
                knightAttackLeftAnimation = new Animation<>(0.15f, idleFrames);
                break;
            case "right":
                knightAttackRightAnimation = new Animation<>(0.15f, idleFrames);
                break;
            case "front":
                knightAttackFrontAnimation = new Animation<>(0.15f, idleFrames);
                break;
            case "back":
                knightAttackBackAnimation = new Animation<>(0.15f, idleFrames);
                break;
            default:

        }
    }

    public void skeletonAttackAnimation(String attackState, Texture attackSheet) {
        tmp = TextureRegion.split(attackSheet, attackSheet.getWidth() / 3, attackSheet.getHeight());

        idleFrames = new TextureRegion[3];

        int index = 0;

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 3; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }

        switch (attackState) {
            case "left":
                skeletonAttackLeftAnimation = new Animation<>(0.15f, idleFrames);
                break;
            case "right":
                skeletonAttackRightAnimation = new Animation<>(0.15f, idleFrames);
                break;
            case "front":
                skeletonAttackFrontAnimation = new Animation<>(0.15f, idleFrames);
                break;
            case "back":
                skeletonAttackBackAnimation = new Animation<>(0.15f, idleFrames);
                break;
            default:

        }
    }

    public Animation<TextureRegion> getKingIdleAnimation(){return this.kingIdleAnimation;}
    public Animation<TextureRegion> getMoveRightAnimation(){return this.moveRightAnimation;}
    public Animation<TextureRegion> getExecutionerIdleAnimation(){return this.executionerIdleAnimation;}
    public Animation<TextureRegion> getLoadingAnimation(){return this.loadingAnimation;}
    public Animation<TextureRegion> getSkeletonDeathAnimation(){return skeletonDeathAnimation;}
    public Animation<TextureRegion> getKnightIdleAnimation() {
        return knightIdleAnimation;
    }

    public Animation<TextureRegion> getKnightHurtAnimation() {
        return knightHurtAnimation;
    }

    public Animation<TextureRegion> getSkeletonIdleAnimation() {
        return skeletonIdleAnimation;
    }

    public Animation<TextureRegion> getSkeletonWalkBackAnimation() {
        return skeletonWalkBackAnimation;
    }

    public Animation<TextureRegion> getKnightWalkAnimation(String walkAnimation) {

        switch (walkAnimation) {
            case "right":
                return this.knightWalkRightAnimation;
            case "left":
                return this.knightWalkLeftAnimation;
            case "back":
                return this.knightWalkBackAnimation;
            default:
                break;
        }

        return knightWalkFrontAnimation;
    }

    public Animation<TextureRegion> getKnightAttackAnimation(String attackAnimation) {

        switch (attackAnimation) {
            case "right":
                return this.knightAttackRightAnimation;
            case "left":
                return this.knightAttackLeftAnimation;
            case "back":
                return this.knightAttackBackAnimation;
            default:
                break;
        }

        return this.knightAttackFrontAnimation;
    }

    public Animation<TextureRegion> getKnightBlockAnimation(String blockAnimation) {

        switch (blockAnimation) {
            case "right":
                return this.knightBlockRightAnimation;
            case "left":
                return this.knightBlockLeftAnimation;
            case "back":
                return this.knightBlockBackAnimation;
            default:
                break;
        }

        return this.knightBlockFrontAnimation;
    }

    public Animation<TextureRegion> getSkeletonAttackAnimation(String attackAnimation) {
        switch (attackAnimation) {
            case "right":
                return this.skeletonAttackRightAnimation;
            case "left":
                return this.skeletonAttackLeftAnimation;
            case "back":
                return this.skeletonAttackBackAnimation;
            default:
                break;
        }

        return this.skeletonAttackFrontAnimation;
    }
}
