package com.mygdx.UI;

import com.badlogic.gdx.graphics.g2d.NinePatch;

public class Dialogue {
    private NinePatch ninePatch;
    private String message;

    public Dialogue(NinePatch ninePatch,String message){
        this.ninePatch = ninePatch;
        this.message = message;
    }

    public NinePatch getNinePatch(){return this.ninePatch;}

    public String getMessage(){return this.message;}
}
