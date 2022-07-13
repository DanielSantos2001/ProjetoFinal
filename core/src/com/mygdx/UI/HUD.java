package com.mygdx.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.animations.TextureManager;
import com.mygdx.models.Skeleton;

import static com.mygdx.animations.TextureManager.textureManager;
import static com.mygdx.systems.EnemySystem.enemySystem;

public class HUD {
    private final Image hudImage;
    private Image skeletonHudImage;
    private final Image potionImage;
    public HUD(){
        potionImage = new Image(textureManager.getPotionSheet());
        hudImage = new Image(textureManager.getHudSheet());
        skeletonHudImage = new Image(textureManager.getSkeletonHudSheet());
    }

    public void setHUD(){
        hudImage.setPosition(0, Gdx.graphics.getHeight() - 109);
        hudImage.setWidth(250);
        hudImage.setHeight(120);

        potionImage.setPosition(0,0);
        potionImage.setWidth(60);
        potionImage.setHeight(60);
    }

    public void setSkeletonHUD(Skeleton skeleton){
        skeleton.getSkeletonHud().setPosition(399, 378);
        skeleton.getSkeletonHud().setWidth(270);
        skeleton.getSkeletonHud().setHeight(120);
    }

    public Image getSkeletonHudImage(){return this.skeletonHudImage;}
    public Image getHudImage(){return this.hudImage;}
    public Image getPotionImage(){return this.potionImage;}
}
