package com.mygdx.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.animations.TextureManager;

import static com.mygdx.animations.TextureManager.textureManager;

public class HUD {
    private final Image hudImage;
    public static Image skeletonHudImage;
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

        skeletonHudImage.setPosition(399, 378);
        skeletonHudImage.setWidth(270);
        skeletonHudImage.setHeight(120);

        potionImage.setPosition(0,0);
        potionImage.setWidth(60);
        potionImage.setHeight(60);
    }

    public Image getHudImage(){return this.hudImage;}
    public Image getPotionImage(){return this.potionImage;}
}
