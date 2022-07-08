package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.mygdx.game.KnightsOath;

public class DeathScreen implements Screen {
    private final KnightsOath mainGame;
    private final Image deadImage;
    private final Image killedByImage;
    private Image enemyImage;
    private final String enemyType;
    private final Stage stage;
    private float elapsedTime;

    public DeathScreen(KnightsOath game, String enemyType) {
        mainGame = game;
        Texture deadTexture = new Texture("Textures/Buttons/dead.png");
        Texture killedByTexture = new Texture("Textures/Buttons/killedBy.png");
        deadImage = new Image(deadTexture);
        killedByImage = new Image(killedByTexture);
        stage = new Stage();
        this.enemyType = enemyType;
    }

    @Override
    public void show() {
        this.verifyEnemy();
        this.addActors();
        this.setImagePosition();
    }

    @Override
    public void render(float delta) {
        mainGame.batch.begin();
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        elapsedTime += delta;

        if(elapsedTime > 2.0){
            mainGame.setScreen(new MainMenuScreen(mainGame));
        }

        stage.act();
        stage.draw();
        mainGame.batch.end();
    }

    private void setImagePosition() {
        deadImage.setPosition(Gdx.graphics.getWidth() / 2 - deadImage.getWidth() / 2, (float)(Gdx.graphics.getHeight()/1.3 - deadImage.getHeight()));
        enemyImage.setPosition((float)(Gdx.graphics.getWidth()/1.2 - enemyImage.getWidth() / 2),(float)(Gdx.graphics.getHeight()/2.2 - enemyImage.getHeight()));
        killedByImage.setPosition((float)(Gdx.graphics.getWidth() / 2.2 - killedByImage.getWidth() / 2),Gdx.graphics.getHeight()/2 - killedByImage.getHeight());
    }

    private void addActors() {
        stage.addActor(deadImage);
        stage.addActor(enemyImage);
        stage.addActor(killedByImage);
    }

    private void verifyEnemy() {
        switch (this.enemyType) {
            case "skeleton":
                Texture enemyTexture = new Texture("Textures/Portraits/skeletonPortrait.png");
                enemyImage = new Image(enemyTexture);
            default:
        }
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
    }
}
