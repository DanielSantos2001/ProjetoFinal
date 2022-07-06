package com.mygdx.animations;

import com.badlogic.gdx.graphics.Texture;

public class TextureManager {
    private Texture knightIdleSheet;
    private Texture skeletonIdleSheet;
    private Texture knightWalkFrontSheet;
    private Texture knightWalkBackSheet;
    private Texture skeletonWalkBackSheet;
    private Texture knightWalkLeftSheet;
    private Texture knightWalkRightSheet;
    private Texture knightAttackFrontSheet;
    private Texture skeletonAttackFrontSheet;
    private Texture knightAttackBackSheet;
    private Texture skeletonAttackBackSheet;
    private Texture knightAttackRightSheet;
    private Texture skeletonAttackRightSheet;
    private Texture knightAttackLeftSheet;
    private Texture skeletonAttackLeftSheet;
    private Texture knightHurtSheet;

    public TextureManager(){
        this.knightIdleSheet = new Texture("Textures/Char/knightIdle.png");
        this.skeletonIdleSheet = new Texture("Textures/Enemy/Skeleton/skeletonIdle.png");
        this.knightWalkFrontSheet =  new Texture("Textures/Char/knightWalkFront.png");
        this.knightWalkBackSheet =  new Texture("Textures/Char/knightWalkBack.png");
        this.skeletonWalkBackSheet = new Texture("Textures/Enemy/Skeleton/skeletonWalkBack.png");
        this.knightWalkLeftSheet =  new Texture("Textures/Char/knightWalkLeft.png");
        this.knightWalkRightSheet =  new Texture("Textures/Char/knightWalkRight.png");
        this.knightAttackFrontSheet = new Texture("Textures/Char/knightAttackFront.png");
        this.skeletonAttackFrontSheet = new Texture("Textures/Enemy/Skeleton/skeletonAttackFront.png");
        this.knightAttackBackSheet = new Texture("Textures/Char/knightAttackBack.png");
        this.skeletonAttackBackSheet = new Texture("Textures/Enemy/Skeleton/skeletonAttackBack.png");
        this.knightAttackLeftSheet = new Texture("Textures/Char/knightAttackLeft.png");
        this.skeletonAttackLeftSheet = new Texture("Textures/Enemy/Skeleton/skeletonAttackLeft.png");
        this.knightAttackRightSheet = new Texture("Textures/Char/knightAttackRight.png");
        this.skeletonAttackRightSheet = new Texture("Textures/Enemy/Skeleton/skeletonAttackRight.png");
        this.knightHurtSheet = new Texture("Textures/Char/knightHurt.png");
    }

    public Texture getKnightIdleSheet() {
        return this.knightIdleSheet;
    }
    public Texture getKnightHurtSheet(){return this.knightHurtSheet;}
    public Texture getSkeletonIdleSheet(){
        return this.skeletonIdleSheet;
    }
    public Texture getSkeletonWalkBackSheet(){return  this.skeletonWalkBackSheet; }
    public Texture getKnightWalkSheet(String walkSheet) {
        switch(walkSheet) {
            case "walkRight":
                return this.knightWalkRightSheet;
            case "walkLeft":
                return this.knightWalkLeftSheet;
            case "walkBack":
                return this.knightWalkBackSheet;
            default:
                break;
        }

        return this.knightWalkFrontSheet;
    }

    public Texture getKnightAttackSheet(String attackSheet) {
        switch(attackSheet) {
            case "attackRight":
                return this.knightAttackRightSheet;
            case "attackLeft":
                return this.knightAttackLeftSheet;
            case "attackBack":
                return this.knightAttackBackSheet;
            default:
                break;
        }

        return this.knightAttackFrontSheet;
    }

    public Texture getSkeletonAttackSheet(String attackSheet) {
        switch(attackSheet) {
            case "attackRight":
                return this.skeletonAttackRightSheet;
            case "attackLeft":
                return this.skeletonAttackLeftSheet;
            case "attackBack":
                return this.skeletonAttackBackSheet;
            default:
                break;
        }

        return this.skeletonAttackFrontSheet;
    }
}
