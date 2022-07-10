package com.mygdx.models;

public abstract class GameEntity {
    protected float speed;
    protected float width;
    protected float height;
    protected float x;
    protected float y;

    public GameEntity(float width, float height,float x,float y){
        this.speed = 0f;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
    }

    public GameEntity() {}

    public abstract void create();
    public abstract void update(float x,float y,boolean verifyDirection,boolean verifyCoordinate);
    public abstract void render(float delta);
}
