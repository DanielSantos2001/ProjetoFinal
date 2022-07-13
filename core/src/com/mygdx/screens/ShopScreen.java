package com.mygdx.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ButtonManager;
import com.mygdx.game.KnightsOath;


import static com.mygdx.animations.TextureManager.textureManager;

public class ShopScreen implements Screen {
    private final KnightsOath mainGame;
    private final Screen parentScreen;
    private final Stage stage;
    private final Image shopImage;
    private final Image buyPotionImage;
    private final ButtonManager buttonManager;
    private String sellerType;
    public static int potionCount = 0;

    public ShopScreen(KnightsOath game, Screen parent,String sellerType){
        this.sellerType = sellerType;
        mainGame = game;
        parentScreen = parent;
        buttonManager = ButtonManager.getInstance();
        stage = new Stage(new ScreenViewport());
        shopImage = new Image(textureManager.getShopScrollSheet()) ;
        buyPotionImage = new Image(textureManager.getBuyPotionSheet());
    }

    @Override
    public void show() {
        addActors();
        setShopImage();
        verifySellerType();
        addListener();
    }

    @Override
    public void render(float delta) {
        mainGame.batch.begin();
        Gdx.input.setInputProcessor(stage);
        stage.act();
        stage.draw();
        mainGame.batch.end();
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

    private void setShopImage(){
        shopImage.setHeight(350);
        shopImage.setPosition((float)(Gdx.graphics.getWidth() / 2.1 - shopImage.getWidth() / 2), (float)(Gdx.graphics.getHeight()/1.3 - shopImage.getHeight()));
        buttonManager.getExitSquareButton().setWidth(30);
        buttonManager.getExitSquareButton().setHeight(30);
        buttonManager.getExitSquareButton().setPosition((float)(Gdx.graphics.getWidth()/1.42 - buttonManager.getExitSquareButton().getWidth()), (float)(Gdx.graphics.getHeight()/1.65 - buttonManager.getExitSquareButton().getHeight()));
    }
    private void addActors(){
        stage.addActor(shopImage);
        stage.addActor(buttonManager.getExitSquareButton());
    }

    private void setPotionSellerShop(){
        stage.addActor(buyPotionImage);
        stage.addActor(buttonManager.getBuyButton());
        buttonManager.getBuyButton().setPosition((float)(Gdx.graphics.getWidth()/1.095 - buttonManager.getBuyButton().getWidth()/1.02), (float)(Gdx.graphics.getHeight()/1.4 - buttonManager.getBuyButton().getHeight()));
        buttonManager.getBuyButton().setWidth(65);
        buttonManager.getBuyButton().setHeight(65);
        buyPotionImage.setPosition((float)(Gdx.graphics.getWidth()/2.8 - buttonManager.getBuyButton().getWidth()), (float)(Gdx.graphics.getHeight()/2.3 - buttonManager.getBuyButton().getHeight()));
        buyPotionImage.setWidth(65);
        buyPotionImage.setHeight(65);
    }

    private void verifySellerType(){
        switch (sellerType){
            case "potionsSeller":
                setPotionSellerShop();
                break;
            case "weaponsSeller":
                break;
            case "armorSeller":
                break;
            default:
        }
    }
    private void addListener(){
        buttonManager.getExitSquareButton().addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                mainGame.setScreen(parentScreen);
                return false;
            }
        });

        buttonManager.getBuyButton().addListener(new InputListener() {
            @Override
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                potionCount++;
                return false;
            }
        });
    }

}
