package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ButtonManager {
	private final Texture startTexture;
	private final Texture continueTexture;
	private final Texture optionsTexture;
	private final Texture musicTexture;
	private final Texture noMusicTexture;
	private final Texture backTexture;
	private final Texture saveTexture;
	private final Texture exitTexture;
	private final Texture creditsTexture;
	private final Texture backSquareTexture;
	private final Texture exitSquareTexture;
	private final Texture buyTexture;
	private final Drawable buyDrawable;
	private final Drawable exitSquareDrawable;
	private final Drawable musicDrawable;
	private final Drawable startDrawable;
	private final Drawable continueDrawable;
	private final Drawable optionsDrawable;
	private final Drawable noMusicDrawable;
	private final Drawable backDrawable;
	private final Drawable saveDrawable;
	private final Drawable exitDrawable;
	private final Drawable creditsDrawable;
	private final Drawable backSquareDrawable;
	private final ImageButton buyButton;
	private final ImageButton exitSquareButton;
	private final ImageButton musicButton;
	private final ImageButton startButton;
	private final ImageButton continueButton;
	private final ImageButton optionsButton;
	private final ImageButton noMusicButton;
	private final ImageButton backButton;
	private final ImageButton saveButton;
	private final ImageButton exitButton;
	private final ImageButton creditsButton;
	private final ImageButton backSquareButton;
	private static ButtonManager button_instance = null;
	
	private ButtonManager() {
		buyTexture = new Texture("Textures/Buttons/buyButton.png");
		exitSquareTexture = new Texture("Textures/Buttons/exitSquareButton.png");
		startTexture = new Texture("Textures/Buttons/startButton.png");
		continueTexture = new Texture("Textures/Buttons/continueButton.png");
		optionsTexture = new Texture("Textures/Buttons/optionsButton.png");
		musicTexture = new Texture("Textures/Buttons/musicButton.png");
		noMusicTexture = new Texture("Textures/Buttons/noMusicButton.png");
		backTexture = new Texture("Textures/Buttons/backButton.png");
		saveTexture = new Texture("Textures/Buttons/saveButton.png");
		exitTexture = new Texture("Textures/Buttons/exitButton.png");
		creditsTexture = new Texture("Textures/Buttons/creditsButton.png");
		backSquareTexture = new Texture("Textures/Buttons/backSquareButton.png");
		buyDrawable = new TextureRegionDrawable(new TextureRegion(buyTexture));
		exitSquareDrawable = new TextureRegionDrawable(new TextureRegion(exitSquareTexture));
		noMusicDrawable = new TextureRegionDrawable(new TextureRegion(noMusicTexture));
		musicDrawable = new TextureRegionDrawable(new TextureRegion(musicTexture));
		startDrawable = new TextureRegionDrawable(new TextureRegion(startTexture));
		continueDrawable = new TextureRegionDrawable(new TextureRegion(continueTexture));
		optionsDrawable = new TextureRegionDrawable(new TextureRegion(optionsTexture));
		backDrawable = new TextureRegionDrawable(new TextureRegion(backTexture));
		saveDrawable = new TextureRegionDrawable(new TextureRegion(saveTexture));
		exitDrawable = new TextureRegionDrawable(new TextureRegion(exitTexture));
		creditsDrawable = new TextureRegionDrawable(new TextureRegion(creditsTexture));
		backSquareDrawable = new TextureRegionDrawable(new TextureRegion(backSquareTexture));
		buyButton = new ImageButton(buyDrawable);
		exitSquareButton = new ImageButton(exitSquareDrawable);
		startButton = new ImageButton(startDrawable);
		continueButton = new ImageButton(continueDrawable);
		optionsButton = new ImageButton(optionsDrawable);
		musicButton = new ImageButton(musicDrawable);
		noMusicButton = new ImageButton(noMusicDrawable);
		backButton = new ImageButton(backDrawable);
		saveButton = new ImageButton(saveDrawable);
		exitButton = new ImageButton(exitDrawable);
		creditsButton = new ImageButton(creditsDrawable);
		backSquareButton = new ImageButton(backSquareDrawable);
	}
	
	public static ButtonManager getInstance() {

		if(button_instance == null) 
			button_instance = new ButtonManager();


		return button_instance;
	}

	public ImageButton getBuyButton(){return buyButton;}
	public ImageButton getExitSquareButton(){return exitSquareButton;}
	public ImageButton getMusicButton() {
		return musicButton;
	}
	
	public ImageButton getNoMusicButton() {
		return noMusicButton;
	}
	
	public ImageButton getStartButton() {
		return startButton;
	}
	
	public ImageButton getContinueButton() {
		return continueButton;
	}
	
	public ImageButton getOptionsButton() {
		return optionsButton;
	}
	
	public ImageButton getBackButton() {
		return backButton;
	}
	
	public ImageButton getSaveButton() {
		return saveButton;
	}
	
	public ImageButton getExitButton() {
		return exitButton;
	}
	
	public ImageButton getCreditsButton() {
		return creditsButton;
	}
	
	public ImageButton getBackSquareButton() {
		return backSquareButton;
	}
}
