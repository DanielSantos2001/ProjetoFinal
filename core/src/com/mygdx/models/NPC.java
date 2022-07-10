package com.mygdx.models;

public class NPC{
    private final float width;
    private final float height;
    private final float x;
    private final float y;

    public NPC(float width, float height, float x, float y){
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public float getWidth(){return this.width;}
    public float getHeight(){return this.height;}
    public float getX(){return this.x;}
    public float getY(){return this.y;}
}
