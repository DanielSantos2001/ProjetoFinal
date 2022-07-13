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
    private Animation<TextureRegion> armorSellerIdleAnimation;
    private Animation<TextureRegion> potionsSellerIdleAnimation;
    private Animation<TextureRegion> weaponsSellerIdleAnimation;
    private Animation<TextureRegion> greyKnightIdleAnimation;
    private Animation<TextureRegion> villager1IdleAnimation;
    private Animation<TextureRegion> villager2IdleAnimation;
    private Animation<TextureRegion> villager3IdleAnimation;
    private Animation<TextureRegion> villager4IdleAnimation;
    private Animation<TextureRegion> villager5IdleAnimation;
    private Animation<TextureRegion> villager6IdleAnimation;
    private Animation<TextureRegion> caveWomanIdleAnimation;
    private Animation<TextureRegion> moveUpAnimation;
    private Animation<TextureRegion> moveDownAnimation;
    private Animation<TextureRegion> moveLeftAnimation;
    private Animation<TextureRegion> moveRightAnimation;
    private Animation<TextureRegion> attackAnimation;
    private Animation<TextureRegion> blockAnimation;
    private Animation<TextureRegion> usePotionAnimation;
    private Animation<TextureRegion> chestAnimation;
    private Animation<TextureRegion> interactObjectsAnimation;
    private Animation<TextureRegion> interactVillagersAnimation;
    private TextureRegion[][] tmp;
    private TextureRegion[] idleFrames;
    public static AnimationManager animationManager = new AnimationManager();
    public AnimationManager() {}

    public void interactVillagersAnimation(){
        tmp = TextureRegion.split(textureManager.getInteractVillagersSheet(),textureManager.getInteractVillagersSheet().getWidth()/5,textureManager.getInteractVillagersSheet().getHeight()/7);

        idleFrames = new TextureRegion[35];

        int index = 0;


        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }

        interactVillagersAnimation = new Animation<>(0.075f, idleFrames);
    }

    public void interactObjectsAnimation(){
        tmp = TextureRegion.split(textureManager.getInteractObjectsSheet(),textureManager.getInteractObjectsSheet().getWidth()/5,textureManager.getInteractObjectsSheet().getHeight()/7);

        idleFrames = new TextureRegion[35];

        int index = 0;


        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }

        interactObjectsAnimation = new Animation<>(0.075f, idleFrames);
    }
    public void chestAnimation(){
        tmp = TextureRegion.split(textureManager.getChestOpeningSheet(),textureManager.getChestOpeningSheet().getWidth()/5,textureManager.getChestOpeningSheet().getHeight());

        idleFrames = new TextureRegion[5];

        int index = 0;


        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 5; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }

        chestAnimation = new Animation<>(0.075f, idleFrames);
    }
    public void attackAnimation(){
        tmp = TextureRegion.split(textureManager.getAttackSheet(),textureManager.getAttackSheet().getWidth()/5,textureManager.getAttackSheet().getHeight()/5);

        idleFrames = new TextureRegion[25];

        int index = 0;


        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }

        attackAnimation = new Animation<>(0.075f, idleFrames);
    }

    public void blockAnimation(){
        tmp = TextureRegion.split(textureManager.getBlockSheet(),textureManager.getBlockSheet().getWidth()/5,textureManager.getBlockSheet().getHeight()/4);

        idleFrames = new TextureRegion[20];

        int index = 0;


        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }

        blockAnimation = new Animation<>(0.075f, idleFrames);
    }

    public void usePotionAnimation(){
        tmp = TextureRegion.split(textureManager.getUseHealthSheet(),textureManager.getUseHealthSheet().getWidth()/5,textureManager.getUseHealthSheet().getHeight()/6);

        idleFrames = new TextureRegion[30];

        int index = 0;


        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 5; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }

        usePotionAnimation = new Animation<>(0.075f, idleFrames);
    }
    public void npcIdleAnimation(String npc,Texture npcIdleSheet){
        tmp = TextureRegion.split(npcIdleSheet,npcIdleSheet.getWidth()/4,npcIdleSheet.getHeight());

        idleFrames = new TextureRegion[4];

        int index = 0;

        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 4; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }

        switch (npc) {
            case "king":
                kingIdleAnimation = new Animation<>(0.5f, idleFrames);
                break;
            case "armorSeller":
                armorSellerIdleAnimation = new Animation<>(0.5f, idleFrames);
                break;
            case "potionsSeller":
                potionsSellerIdleAnimation = new Animation<>(0.5f, idleFrames);
                break;
            case "weaponsSeller":
                weaponsSellerIdleAnimation = new Animation<>(0.5f, idleFrames);
                break;
            case "greyKnight":
                greyKnightIdleAnimation = new Animation<>(0.5f, idleFrames);
                break;
            case "villager1":
                villager1IdleAnimation = new Animation<>(0.5f, idleFrames);
                break;
            case "villager2":
                villager2IdleAnimation = new Animation<>(0.5f, idleFrames);
                break;
            case "villager3":
                villager3IdleAnimation = new Animation<>(0.5f, idleFrames);
                break;
            case "villager4":
                villager4IdleAnimation = new Animation<>(0.5f, idleFrames);
                break;
            case "villager5":
                villager5IdleAnimation = new Animation<>(0.5f, idleFrames);
                break;
            case "villager6":
                villager6IdleAnimation = new Animation<>(0.5f, idleFrames);
                break;
            case "caveWoman":
                caveWomanIdleAnimation = new Animation<>(0.5f,idleFrames);
                break;
            default:
        }
    }

    public void moveAnimations(String animation, Texture moveAnimationSheet){
        tmp = TextureRegion.split(moveAnimationSheet,moveAnimationSheet.getWidth()/5,moveAnimationSheet.getHeight()/7);

        idleFrames = new TextureRegion[35];

        int index = 0;

        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 5; j++) {
                idleFrames[index++] = tmp[i][j];
            }
        }

        switch (animation){
            case "moveUp":
                moveUpAnimation = new Animation<>(0.075f, idleFrames);
                break;
            case "moveDown":
                moveDownAnimation = new Animation<>(0.075f, idleFrames);
                break;
            case "moveLeft":
                moveLeftAnimation = new Animation<>(0.075f, idleFrames);
                break;
            default:
        }

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

    public Animation<TextureRegion> getInteractVillagersAnimation(){return this.interactVillagersAnimation;}
    public Animation<TextureRegion> getInteractObjectsAnimation(){return this.interactObjectsAnimation;}
    public Animation<TextureRegion> getChestAnimation(){return this.chestAnimation;}
    public Animation<TextureRegion> getUsePotionAnimation(){return this.usePotionAnimation;}
    public Animation<TextureRegion> getBlockAnimation(){return this.blockAnimation;}
    public Animation<TextureRegion> getAttackAnimation(){return this.attackAnimation;}
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

    public Animation<TextureRegion> getNpcIdleAnimation(String npcAnimation){

        switch (npcAnimation) {
            case "king":
                return kingIdleAnimation;
            case "armorSeller":
                return armorSellerIdleAnimation;
            case "potionsSeller":
                return potionsSellerIdleAnimation;
            case "weaponsSeller":
                return weaponsSellerIdleAnimation;
            case "greyKnight":
                return greyKnightIdleAnimation;
            case "villager1":
                return villager1IdleAnimation;
            case "villager2":
                return villager2IdleAnimation;
            case "villager3":
                return villager3IdleAnimation;
            case "villager4":
                return villager4IdleAnimation;
            case "villager5":
                return villager5IdleAnimation;
            case "villager6":
                return villager6IdleAnimation;
            case "caveWoman":
                return caveWomanIdleAnimation;
            default:
                break;
        }
        return null;
    }

    public Animation<TextureRegion> getMoveAnimations(String moveAnimation){
        switch (moveAnimation){
            case "moveUp":
                return moveUpAnimation;
            case "moveDown":
                return moveDownAnimation;
            case "moveLeft":
                return moveLeftAnimation;
            default:
                break;
        }

        return null;
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
