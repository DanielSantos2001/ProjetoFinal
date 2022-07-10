package com.mygdx.models;

import com.badlogic.gdx.math.Rectangle;

public class Skeleton{
    private final Rectangle skeletonBounds;
    private float speed;
    public Skeleton(float width, float height, float x, float y) {
        skeletonBounds = new Rectangle(x, y,width, height);
        this.speed = 0.5f;
    }

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
