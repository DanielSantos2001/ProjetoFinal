package com.mygdx.systems;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.KnightsOath;
import com.mygdx.models.GameEntity;
import com.mygdx.models.NPC;
import com.mygdx.screens.MainGameScreen;

import static com.mygdx.animations.AnimationManager.animationManager;
import static com.mygdx.animations.TextureManager.textureManager;
import static com.mygdx.screens.MainGameScreen.stateTime;

public class NPCSystem extends GameEntity {
    private final NPC king;
    private final NPC armorSeller;
    private final NPC weaponsSeller;
    private final NPC potionsSeller;
    private final NPC greyKnight1;
    private final NPC greyKnight2;
    private final NPC villager1;
    private final NPC villager2;
    private final NPC villager3;
    private final NPC villager4;
    private final NPC villager5;
    private final NPC villager6;
    private final KnightsOath mainGame;
    private final MainGameScreen mainGameScreen;

    public NPCSystem(KnightsOath mainGame, MainGameScreen mainGameScreen) {
        this.mainGame = mainGame;
        this.mainGameScreen = mainGameScreen;
        king = new NPC(13, 13, 506, 1300);
        armorSeller = new NPC(13,13,593,425);
        weaponsSeller = new NPC(13,13,753,425);
        potionsSeller = new NPC(13,13,913,425);
        greyKnight1 = new NPC(13,13,561,1082);
        greyKnight2 = new NPC(13,13,471,1082);
        villager1 = new NPC(13,13,550,335);
        villager2 = new NPC(13,13,430,168);
        villager3 = new NPC(13,13,390,390);
        villager4 = new NPC(13,13,595,168);
        villager5 = new NPC(13,13,508,728);
        villager6 = new NPC(13,13,870,832);
    }

    @Override
    public void create() {
        initializeNpcAnimation();
    }


    @Override
    public void update(float x, float y, boolean verifyDirection, boolean verifyCoordinate) {
    }

    @Override
    public void render(float delta) {
        drawNpcAnimation();
    }

    private void drawNpcAnimation(){
        TextureRegion currentKingFrame = animationManager.getNpcIdleAnimation("king").getKeyFrame(stateTime, true);
        TextureRegion currentWeaponsSellerFrame = animationManager.getNpcIdleAnimation("weaponsSeller").getKeyFrame(stateTime,true);
        TextureRegion currentPotionsSellerFrame = animationManager.getNpcIdleAnimation("potionsSeller").getKeyFrame(stateTime,true);
        TextureRegion currentArmorSellerFrame = animationManager.getNpcIdleAnimation("armorSeller").getKeyFrame(stateTime,true);
        TextureRegion currentGreyKnight1Frame = animationManager.getNpcIdleAnimation("greyKnight").getKeyFrame(stateTime,true);
        TextureRegion currentGreyKnight2Frame = animationManager.getNpcIdleAnimation("greyKnight").getKeyFrame(stateTime,true);
        TextureRegion currentVillager1Frame = animationManager.getNpcIdleAnimation("villager1").getKeyFrame(stateTime,true);
        TextureRegion currentVillager2Frame = animationManager.getNpcIdleAnimation("villager2").getKeyFrame(stateTime,true);
        TextureRegion currentVillager3Frame = animationManager.getNpcIdleAnimation("villager3").getKeyFrame(stateTime,true);
        TextureRegion currentVillager4Frame = animationManager.getNpcIdleAnimation("villager4").getKeyFrame(stateTime,true);
        TextureRegion currentVillager5Frame = animationManager.getNpcIdleAnimation("villager5").getKeyFrame(stateTime,true);
        TextureRegion currentVillager6Frame = animationManager.getNpcIdleAnimation("villager6").getKeyFrame(stateTime,true);

        if (this.mainGameScreen.getCameraManager().getMapPath().equals("Maps/map1.tmx")){
            mainGame.batch.draw(currentKingFrame, king.getX(), king.getY(), king.getWidth(), king.getHeight());
            mainGame.batch.draw(currentArmorSellerFrame, armorSeller.getX(), armorSeller.getY(), armorSeller.getWidth(), armorSeller.getHeight());
            mainGame.batch.draw(currentWeaponsSellerFrame, weaponsSeller.getX(), weaponsSeller.getY(), weaponsSeller.getWidth(), weaponsSeller.getHeight());
            mainGame.batch.draw(currentPotionsSellerFrame, potionsSeller.getX(), potionsSeller.getY(), potionsSeller.getWidth(), potionsSeller.getHeight());
            mainGame.batch.draw(currentGreyKnight1Frame, greyKnight1.getX(), greyKnight1.getY(), greyKnight1.getWidth(), greyKnight1.getHeight());
            mainGame.batch.draw(currentGreyKnight2Frame, greyKnight2.getX(), greyKnight2.getY(), greyKnight2.getWidth(), greyKnight2.getHeight());
            mainGame.batch.draw(currentVillager1Frame, villager1.getX(), villager1.getY(), villager1.getWidth(), villager1.getHeight());
            mainGame.batch.draw(currentVillager2Frame, villager2.getX(), villager2.getY(), villager2.getWidth(), villager2.getHeight());
            mainGame.batch.draw(currentVillager3Frame, villager3.getX(), villager3.getY(), villager3.getWidth(), villager3.getHeight());
            mainGame.batch.draw(currentVillager4Frame, villager4.getX(), villager4.getY(), villager4.getWidth(), villager4.getHeight());
            mainGame.batch.draw(currentVillager5Frame, villager5.getX(), villager5.getY(), villager5.getWidth(), villager5.getHeight());
            mainGame.batch.draw(currentVillager6Frame, villager6.getX(), villager6.getY(), villager6.getWidth(), villager6.getHeight());
        }
    }
    private void initializeNpcAnimation(){
        animationManager.npcIdleAnimation("king",textureManager.getNpcIdleSheet("king"));
        animationManager.npcIdleAnimation("armorSeller",textureManager.getNpcIdleSheet("armorSeller"));
        animationManager.npcIdleAnimation("potionsSeller",textureManager.getNpcIdleSheet("potionsSeller"));
        animationManager.npcIdleAnimation("weaponsSeller",textureManager.getNpcIdleSheet("weaponsSeller"));
        animationManager.npcIdleAnimation("greyKnight",textureManager.getNpcIdleSheet("greyKnight"));
        animationManager.npcIdleAnimation("villager1",textureManager.getNpcIdleSheet("villager1"));
        animationManager.npcIdleAnimation("villager2",textureManager.getNpcIdleSheet("villager2"));
        animationManager.npcIdleAnimation("villager3",textureManager.getNpcIdleSheet("villager3"));
        animationManager.npcIdleAnimation("villager4",textureManager.getNpcIdleSheet("villager4"));
        animationManager.npcIdleAnimation("villager5",textureManager.getNpcIdleSheet("villager5"));
        animationManager.npcIdleAnimation("villager6",textureManager.getNpcIdleSheet("villager6"));
    }
}
