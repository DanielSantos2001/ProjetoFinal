package com.mygdx.animations;

import com.badlogic.gdx.graphics.Texture;

public class TextureManager {
    private final Texture knightIdleSheet;
    private final Texture skeletonDeathSheet;
    private final Texture skeletonIdleSheet;
    private final Texture knightWalkFrontSheet;
    private final Texture knightWalkBackSheet;
    private final Texture skeletonWalkBackSheet;
    private final Texture knightWalkLeftSheet;
    private final Texture knightWalkRightSheet;
    private final Texture knightAttackFrontSheet;
    private final Texture skeletonAttackFrontSheet;
    private final Texture knightAttackBackSheet;
    private final Texture skeletonAttackBackSheet;
    private final Texture knightAttackRightSheet;
    private final Texture skeletonAttackRightSheet;
    private final Texture knightAttackLeftSheet;
    private final Texture skeletonAttackLeftSheet;
    private final Texture knightHurtSheet;
    private final Texture knightBlockBackSheet;
    private final Texture knightBlockFrontSheet;
    private final Texture knightBlockLeftSheet;
    private final Texture knightBlockRightSheet;
    private final Texture executionerIdleSheet;
    private final Texture loadingAnimationSheet;
    private final Texture moveUpSheet;
    private final Texture moveDownSheet;
    private final Texture moveLeftSheet;
    private final Texture moveRightSheet;
    private final Texture hudSheet;
    private final Texture skeletonHudSheet;
    private final Texture dialogKnightSheet;
    private final Texture dialogKingSheet;
    private final Texture dialogArmorSellerSheet;
    private final Texture dialogWeaponsSellerSheet;
    private final Texture dialogPotionsSellerSheet;
    private final Texture deadSheet;
    private final Texture killedBySheet;
    private final Texture loadingSheet;
    private final Texture kingIdleSheet;
    private final Texture armorSellerSheet;
    private final Texture weaponsSellerSheet;
    private final Texture potionsSellerSheet;
    public static TextureManager textureManager = new TextureManager();
    public TextureManager(){
        this.dialogPotionsSellerSheet = new Texture("Textures/Dialogue/dialogPotionsSeller.png");
        this.dialogWeaponsSellerSheet = new Texture("Textures/Dialogue/dialogWeaponsSeller.png");
        this.dialogArmorSellerSheet = new Texture("Textures/Dialogue/dialogArmorSeller.png");
        this.potionsSellerSheet = new Texture("Textures/NPC/Potions Seller/potionsSellerIdle.png");
        this.weaponsSellerSheet = new Texture("Textures/NPC/Weapons Seller/weaponsSellerIdle.png");
        this.armorSellerSheet = new Texture("Textures/NPC/Armor Seller/armorSellerIdle.png");
        this.kingIdleSheet = new Texture("Textures/NPC/King/kingIdle.png");
        this.dialogKingSheet = new Texture("Textures/Dialogue/dialogKing.png");
        this.loadingSheet = new Texture("Textures/Screens/loading.png");
        this.deadSheet = new Texture("Textures/Screens/dead.png");
        this.killedBySheet = new Texture("Textures/Screens/killedBy.png");
        this.dialogKnightSheet = new Texture("Textures/Dialogue/dialogKnight.png");
        this.skeletonHudSheet = new Texture("Textures/Enemy/Skeleton/hudSkeleton.png");
        this.hudSheet = new Texture("Textures/Char/HUD.png");
        this.moveRightSheet = new Texture("Textures/Tutorial/moveRight.png");
        this.moveLeftSheet = new Texture("Textures/Tutorial/moveLeft.png");
        this.moveDownSheet = new Texture("Textures/Tutorial/moveDown.png");
        this.moveUpSheet = new Texture("Textures/Tutorial/moveUp.png");
        this.executionerIdleSheet = new Texture("Textures/Enemy/Executioner/executionerIdle.png");
        this.loadingAnimationSheet = new Texture("Textures/Screens/loadingAnimation.png");
        this.knightIdleSheet = new Texture("Textures/Char/knightIdle.png");
        this.skeletonIdleSheet = new Texture("Textures/Enemy/Skeleton/skeletonIdle.png");
        this.skeletonDeathSheet = new Texture("Textures/Enemy/Skeleton/skeletonDeath.png");
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
        this.knightBlockBackSheet = new Texture("Textures/Char/knightBlockBack.png");
        this.knightBlockFrontSheet = new Texture("Textures/Char/knightBlockFront.png");
        this.knightBlockLeftSheet = new Texture("Textures/Char/knightBlockLeft.png");
        this.knightBlockRightSheet = new Texture("Textures/Char/knightBlockRight.png");
    }

    public Texture getDialogPotionsSellerSheet(){return this.dialogPotionsSellerSheet;}
    public Texture getDialogWeaponsSellerSheet(){return this.dialogWeaponsSellerSheet;}
    public Texture getDialogArmorSellerSheet(){return this.dialogArmorSellerSheet;}
    public Texture getPotionsSellerSheet(){return this.potionsSellerSheet;}
    public Texture getWeaponsSellerSheet(){return this.weaponsSellerSheet;}
    public Texture getArmorSellerSheet(){return this.armorSellerSheet;}
    public Texture getKingIdleSheet(){return this.kingIdleSheet;}
    public Texture getDialogKingSheet(){return this.dialogKingSheet;}
    public Texture getLoadingSheet(){return this.loadingSheet;}
    public Texture getKilledBySheet(){return this.killedBySheet;}
    public Texture getDeadSheet(){return this.deadSheet;}
    public Texture getDialogKnightSheet(){return this.dialogKnightSheet;}
    public Texture getSkeletonHudSheet(){return this.skeletonHudSheet;}
    public Texture getHudSheet(){return this.hudSheet;}
    public Texture getMoveRightSheet(){return this.moveRightSheet;}
    public Texture getExecutionerIdleSheet(){return this.executionerIdleSheet;}
    public Texture getLoadingAnimationSheet(){return this.loadingAnimationSheet;}
    public Texture getSkeletonDeathSheet(){return this.skeletonDeathSheet;}
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
            case "right":
                return this.knightWalkRightSheet;
            case "left":
                return this.knightWalkLeftSheet;
            case "back":
                return this.knightWalkBackSheet;
            default:
                break;
        }

        return this.knightWalkFrontSheet;
    }

    public Texture getKnightAttackSheet(String attackSheet) {
        switch(attackSheet) {
            case "right":
                return this.knightAttackRightSheet;
            case "left":
                return this.knightAttackLeftSheet;
            case "back":
                return this.knightAttackBackSheet;
            default:
                break;
        }

        return this.knightAttackFrontSheet;
    }

    public Texture getKnightBlockSheet(String blockSheet){
        switch (blockSheet){
            case "right":
                return this.knightBlockRightSheet;
            case "left":
                return this.knightBlockLeftSheet;
            case "back":
                return this.knightBlockBackSheet;
            default:
                break;
        }

        return this.knightBlockFrontSheet;
    }

    public Texture getSkeletonAttackSheet(String attackSheet) {
        switch(attackSheet) {
            case "right":
                return this.skeletonAttackRightSheet;
            case "left":
                return this.skeletonAttackLeftSheet;
            case "back":
                return this.skeletonAttackBackSheet;
            default:
                break;
        }

        return this.skeletonAttackFrontSheet;
    }
}
