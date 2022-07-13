package com.mygdx.models;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Circle;

public class Sign {
    private final Texture signTexture;
    private final Circle signCircle;

    public Sign(Texture signTexture, Circle signCircle){
        this.signTexture = signTexture;
        this.signCircle = signCircle;
    }

    public Texture getSignTexture(){return this.signTexture;}
    public Circle getSignCircle(){return this.signCircle;}
}
