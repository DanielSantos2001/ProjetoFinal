package com.mygdx.models;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.HealthBar;

public class Skeleton{
    private final Rectangle skeletonBounds;
    private float speed;
    private HealthBar healthBar;
    private boolean isDead;
    private TextureRegion currentFrame;
    private Image skeletonHud;

    public Skeleton(float width, float height, float x, float y,HealthBar healthBar,TextureRegion currentFrame,Image skeletonHud) {
        skeletonBounds = new Rectangle(x, y,width, height);
        this.speed = 0.5f;
        this.healthBar = healthBar;
        isDead = false;
        this.currentFrame = currentFrame;
        this.skeletonHud = skeletonHud;
    }

    public Image getSkeletonHud(){return this.skeletonHud;}
    public void setIsDead(boolean isDead){this.isDead = isDead;}
    public void setCurrentFrame(TextureRegion currentFrame){this.currentFrame = currentFrame;}
    public TextureRegion getCurrentFrame(){return this.currentFrame;}
    public boolean getIsDead(){return this.isDead;}
    public HealthBar getHealthBar(){return this.healthBar;}
    public void setSkeletonSpeed(float speed){this.speed = speed;}
    public float getSkeletonSpeed(){return this.speed;}
    public Rectangle getSkeletonBounds(){return this.skeletonBounds;}
    public float getSkeletonWidth(){
        return this.skeletonBounds.width;
    }

    public float getSkeletonHeight(){
        return this.skeletonBounds.height;
    }
    public float getSkeletonX(){
        return this.skeletonBounds.x;
    }

    public float getSkeletonY(){
        return this.skeletonBounds.y;
    }
    public void setSkeletonY(float skeletonY){
        skeletonBounds.y += skeletonY;
    }

    public void setSkeletonX(float skeletonX){
        skeletonBounds.x += skeletonX;
    }

}
