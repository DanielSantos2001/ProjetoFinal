package com.mygdx.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class ButtonManager {
	private Texture startTexture;
	private Texture continueTexture;
	private Texture optionsTexture;
	private Texture musicTexture;
	private Texture noMusicTexture;
	private Texture backTexture;
	private Texture saveTexture;
	private Texture exitTexture;
	private Texture creditsTexture;
	private Texture backSquareTexture;
	private Drawable musicDrawable;
	private Drawable startDrawable;
	private Drawable continueDrawable;
	private Drawable optionsDrawable;
	private Drawable noMusicDrawable;
	private Drawable backDrawable;
	private Drawable saveDrawable;
	private Drawable exitDrawable;
	private Drawable creditsDrawable;
	private Drawable backSquareDrawable;
	private ImageButton musicButton;
	private ImageButton startButton;
	private ImageButton continueButton;
	private ImageButton optionsButton;
	private ImageButton noMusicButton;
	private ImageButton backButton;
	private ImageButton saveButton;
	private ImageButton exitButton;
	private ImageButton creditsButton;
	private ImageButton backSquareButton;
	private static ButtonManager button_instance = null;
	
	private ButtonManager() {
		startTexture = new Texture("Textures/startButton.png");
		continueTexture = new Texture("Textures/continueButton.png");
		optionsTexture = new Texture("Textures/optionsButton.png");
		musicTexture = new Texture("Textures/musicButton.png");
		noMusicTexture = new Texture("Textures/noMusicButton.png");
		backTexture = new Texture("Textures/backButton.png");
		saveTexture = new Texture("Textures/saveButton.png");
		exitTexture = new Texture("Textures/exitButton.png");
		creditsTexture = new Texture("Textures/creditsButton.png");
		backSquareTexture = new Texture("Textures/backSquareButton.png");
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
