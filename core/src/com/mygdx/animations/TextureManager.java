package com.mygdx.animations;

import com.badlogic.gdx.graphics.Texture;

public class TextureManager {
    private Texture knightIdleSheet;
    private Texture knightWalkFrontSheet;
    private Texture knightWalkBackSheet;
    private Texture knightWalkLeftSheet;
    private Texture knightWalkRightSheet;
    private Texture knightAttackFrontSheet;
    private Texture knightAttackBackSheet;
    private Texture knightAttackRightSheet;
    private Texture knightAttackLeftSheet;

    public TextureManager(){
        this.knightIdleSheet = new Texture("Textures/Char/knightIdle.png");
        this.knightWalkFrontSheet =  new Texture("Textures/Char/knightWalkFront.png");
        this.knightWalkBackSheet =  new Texture("Textures/Char/knightWalkBack.png");
        this.knightWalkLeftSheet =  new Texture("Textures/Char/knightWalkLeft.png");
        this.knightWalkRightSheet =  new Texture("Textures/Char/knightWalkRight.png");
        this.knightAttackFrontSheet = new Texture("Textures/Char/knightAttackFront.png");
        this.knightAttackBackSheet = new Texture("Textures/Char/knightAttackBack.png");
        this.knightAttackLeftSheet = new Texture("Textures/Char/knightAttackLeft.png");
        this.knightAttackRightSheet = new Texture("Textures/Char/knightAttackRight.png");
    }

    public Texture getKnightIdleSheet() {
        return this.knightIdleSheet;
    }

    public Texture getWalkSheet(String walkSheet) {
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

    public Texture getAttackSheet(String walkSheet) {
        switch(walkSheet) {
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
}
