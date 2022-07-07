package com.mygdx.game;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;

public class HealthBar extends ProgressBar {

    public HealthBar(int width, int height) {
        super(0f, 1f, 0.01f, false, new ProgressBarStyle());
        getStyle().background = Utils.getColoredDrawable(width, height, Color.DARK_GRAY);
        getStyle().knob = Utils.getColoredDrawable(0, height, Color.RED);
        getStyle().knobBefore = Utils.getColoredDrawable(width, height, Color.RED);

        setWidth(width);
        setHeight(height);
        setValue(1f);

        setAnimateDuration(0.25f);
    }
}
