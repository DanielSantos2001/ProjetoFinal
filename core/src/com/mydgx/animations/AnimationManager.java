package com.mydgx.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationManager {
	private Texture knightIdleSheet;
	private Texture knightWalkSheet;
	private Texture knightSlashSheet;
	private Animation<TextureRegion> idleAnimation;
	private Animation<TextureRegion> walkAnimation;
	private Animation<TextureRegion> slashAnimation;

	public AnimationManager() {
		this.knightIdleSheet = new Texture("Textures/knightIdle.png");
		this.knightWalkSheet =  new Texture("Textures/knightRun.png");
		this.knightSlashSheet = new Texture("Textures/knightSlash.png");
	}


	public void idleAnimation(float stateTime) {
		TextureRegion[][] tmp = TextureRegion.split(knightIdleSheet,knightIdleSheet.getWidth()/8, knightIdleSheet.getHeight());

		TextureRegion[] idleFrames = new TextureRegion[8];

		int index = 0;

		for(int i = 0;i<1;i++) {
			for(int j = 0;j<8;j++) {
				idleFrames[index++] = tmp[i][j];
			}
		}

		idleAnimation = new Animation<TextureRegion>(0.15f,idleFrames);

		stateTime = 0f;
	}

	public void walkAnimation(float stateTime) {
		TextureRegion[][] tmp = TextureRegion.split(knightWalkSheet,knightWalkSheet.getWidth()/10, knightWalkSheet.getHeight());

		TextureRegion[] idleFrames = new TextureRegion[10];

		int index = 0;

		for(int i = 0;i<1;i++) {
			for(int j = 0;j<10;j++) {
				idleFrames[index++] = tmp[i][j];
			}
		}

		walkAnimation = new Animation<TextureRegion>(0.15f,idleFrames);

		stateTime = 0f;
	}

	public void slashAnimation(float stateTime) {
		TextureRegion[][] tmp = TextureRegion.split(knightSlashSheet,knightSlashSheet.getWidth()/6, knightSlashSheet.getHeight());

		TextureRegion[] idleFrames = new TextureRegion[6];

		int index = 0;

		for(int i = 0;i<1;i++) {
			for(int j = 0;j<6;j++) {
				idleFrames[index++] = tmp[i][j];
			}
		}

		slashAnimation = new Animation<TextureRegion>(0.15f,idleFrames);

		stateTime = 0f;
	}
	
	public Texture getKnightIdleSheet() {
		return this.knightIdleSheet;
	}

	public Animation<TextureRegion> getIdleAnimation(){
		return idleAnimation;
	}

	public Animation<TextureRegion> getWalkAnimation(){
		return walkAnimation;
	}
	
	public Animation<TextureRegion> getSlashAnimation(){
		return slashAnimation;
	}
}
