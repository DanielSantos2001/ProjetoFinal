package com.mygdx.UI;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.animations.TextureManager;

public class HUD {
    private final Image hudImage;
    public static Image skeletonHudImage;
    public HUD(){
        TextureManager textureManager = new TextureManager();
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
    }

    public Image getHudImage(){return this.hudImage;}
}
