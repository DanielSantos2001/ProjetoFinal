package com.mygdx.systems;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Circle;
import com.mygdx.game.KnightsOath;
import com.mygdx.models.GameEntity;
import com.mygdx.models.Sign;
import com.mygdx.screens.MainGameScreen;

import static com.mygdx.animations.AnimationManager.animationManager;
import static com.mygdx.animations.TextureManager.textureManager;
import static com.mygdx.screens.MainGameScreen.stateTime;

public class TutorialSystem extends GameEntity {
    private final MainGameScreen mainGameScreen;
    private final KnightsOath mainGame;
    private final Sign moveUpSign;
    private final Sign moveDownSign;
    private final Sign moveLeftSign;
    private final Sign moveRightSign;
    private final Sign attackSign;
    private final Sign blockSign;
    private final Sign usePotionSign;
    private final Sign chestSign;
    public TutorialSystem(MainGameScreen mainGameScreen, KnightsOath mainGame) {
        this.mainGameScreen = mainGameScreen;
        this.mainGame = mainGame;
        moveUpSign = new Sign(textureManager.getTutorialSignSheet(), new Circle(605, 180, 5));
        moveDownSign = new Sign(textureManager.getTutorialSignSheet(), new Circle(605, 120, 5));
        moveLeftSign = new Sign(textureManager.getTutorialSignSheet(), new Circle(605, 60, 5));
        moveRightSign = new Sign(textureManager.getTutorialSignSheet(), new Circle(605, 20, 5));
        attackSign = new Sign(textureManager.getTutorialSignSheet(), new Circle(405, 120, 5));
        blockSign = new Sign(textureManager.getTutorialSignSheet(), new Circle(405, 60, 5));
        usePotionSign = new Sign(textureManager.getTutorialSignSheet(),new Circle(110,180,5));
        chestSign = new Sign(textureManager.getChestSheet(),new Circle(115,110,5));
    }


    @Override
    public void create() {
        initializeAnimations();
    }

    @Override
    public void update(float x, float y, boolean verifyDirection, boolean verifyCoordinate) {

    }

    @Override
    public void render(float delta) {
        if (mainGameScreen.getCameraManager().getMapPath().equals("Maps/map4.tmx")) {
            mainGame.batch.draw(moveUpSign.getSignTexture(), 580, 180, 20, 20);
            mainGame.batch.draw(moveDownSign.getSignTexture(), 580, 120, 20, 20);
            mainGame.batch.draw(moveRightSign.getSignTexture(), 580, 60, 20, 20);
            mainGame.batch.draw(moveLeftSign.getSignTexture(), 580, 0, 20, 20);
            mainGame.batch.draw(attackSign.getSignTexture(), 380, 120, 20, 20);
            mainGame.batch.draw(blockSign.getSignTexture(), 380, 60, 20, 20);
            mainGame.batch.draw(usePotionSign.getSignTexture(),90,180,20,20);
            mainGame.batch.draw(chestSign.getSignTexture(),90,100,20,20);
        }
    }

    public Sign getChestSign(){return this.chestSign;}
    public Sign getUsePotionSign(){return this.usePotionSign;}
    public Sign getMoveDownSign() {
        return this.moveDownSign;
    }

    public Sign getMoveUpSign() {
        return this.moveUpSign;
    }

    public Sign getMoveLeftSign() {
        return this.moveLeftSign;
    }

    public Sign getMoveRightSign() {
        return this.moveRightSign;
    }

    public Sign getAttackSign() {
        return this.attackSign;
    }

    public Sign getBlockSign() {
        return this.blockSign;
    }

    private void initializeAnimations() {
        animationManager.moveAnimations("moveUp", textureManager.getMoveAnimationsSheet("moveUp"));
        animationManager.moveAnimations("moveDown", textureManager.getMoveAnimationsSheet("moveDown"));
        animationManager.moveAnimations("moveLeft", textureManager.getMoveAnimationsSheet("moveLeft"));
        animationManager.moveRightAnimation();
        animationManager.attackAnimation();
        animationManager.blockAnimation();
        animationManager.usePotionAnimation();
        animationManager.chestAnimation();
        animationManager.interactObjectsAnimation();
        animationManager.interactVillagersAnimation();
    }

    public void drawTutorialAnimations(String moveAnimation) {
        switch (moveAnimation) {
            case "moveUp":
                TextureRegion moveUpCurrentFrame = animationManager.getMoveAnimations("moveUp").getKeyFrame(stateTime, true);
                mainGame.batch.draw(moveUpCurrentFrame, 580, 192, 60, 40);
                break;
            case "moveDown":
                TextureRegion moveDownCurrentFrame = animationManager.getMoveAnimations("moveDown").getKeyFrame(stateTime, true);
                mainGame.batch.draw(moveDownCurrentFrame, 580, 132, 60, 40);
                break;
            case "moveLeft":
                TextureRegion moveLeftCurrentFrame = animationManager.getMoveAnimations("moveLeft").getKeyFrame(stateTime, true);
                mainGame.batch.draw(moveLeftCurrentFrame, 580, 72, 60, 40);
                break;
            case "moveRight":
                TextureRegion moveRightCurrentFrame = animationManager.getMoveRightAnimation().getKeyFrame(stateTime, true);
                mainGame.batch.draw(moveRightCurrentFrame, 580, 12, 60, 40);
                break;
            case "attack":
                TextureRegion attackCurrentFrame = animationManager.getAttackAnimation().getKeyFrame(stateTime,true);
                mainGame.batch.draw(attackCurrentFrame,380, 132, 60, 40);
                break;
            case "block":
                TextureRegion blockCurrentFrame = animationManager.getBlockAnimation().getKeyFrame(stateTime,true);
                mainGame.batch.draw(blockCurrentFrame,380,72,60,40);
                break;
            case "potion":
                TextureRegion potionCurrentFrame = animationManager.getUsePotionAnimation().getKeyFrame(stateTime,true);
                mainGame.batch.draw(potionCurrentFrame,90,192,60,40);
                break;
            case "chest":
                TextureRegion chestCurrentFrame = animationManager.getChestAnimation().getKeyFrame(stateTime,true);
                mainGame.batch.draw(chestCurrentFrame,80,90,40,40);
                break;
            case "object":
                TextureRegion interactObjectCurrentFrame = animationManager.getInteractObjectsAnimation().getKeyFrame(stateTime,true);
                mainGame.batch.draw(interactObjectCurrentFrame,90,112,60,40);
                break;
            case "villager":
                TextureRegion interactVillagerCurrentFrame = animationManager.getInteractVillagersAnimation().getKeyFrame(stateTime,true);
                mainGame.batch.draw(interactVillagerCurrentFrame,8,137,60,40);
            default:
        }

    }
}
