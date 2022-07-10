package com.mygdx.systems;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.mygdx.game.KnightsOath;
import com.mygdx.models.GameEntity;
import com.mygdx.models.NPC;
import com.mygdx.screens.MainGameScreen;

import static com.mygdx.animations.AnimationManager.animationManager;
import static com.mygdx.screens.MainGameScreen.stateTime;

public class NPCSystem extends GameEntity {
    private final NPC king;
    private final KnightsOath mainGame;
    private final MainGameScreen mainGameScreen;

    public NPCSystem(KnightsOath mainGame, MainGameScreen mainGameScreen) {
        this.mainGame = mainGame;
        this.mainGameScreen = mainGameScreen;
        king = new NPC(13, 13, 506, 1300);
    }

    @Override
    public void create() {
        animationManager.kingIdleAnimation();
    }

    @Override
    public void update(float x, float y, boolean verifyDirection, boolean verifyCoordinate) {
    }

    @Override
    public void render(float delta) {
        TextureRegion currentKingFrame = animationManager.getKingIdleAnimation().getKeyFrame(stateTime, true);

        if (this.mainGameScreen.getCameraManager().getMapPath().equals("Maps/map1.tmx"))
            mainGame.batch.draw(currentKingFrame, king.getX(), king.getY(), king.getWidth(), king.getHeight());
    }
}
