package com.mygdx.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationManager {
	private Animation<TextureRegion> idleAnimation;
	private Animation<TextureRegion> walkFrontAnimation;
	private Animation<TextureRegion> walkBackAnimation;
	private Animation<TextureRegion> walkLeftAnimation;
	private Animation<TextureRegion> walkRightAnimation;
	private Animation<TextureRegion> attackFrontAnimation;
	private Animation<TextureRegion> attackBackAnimation;
	private Animation<TextureRegion> attackRightAnimation;
	private Animation<TextureRegion> attackLeftAnimation;
	private TextureManager textureManager;
	public AnimationManager() {
		textureManager = new TextureManager();
	}


	public void idleAnimation(float stateTime) {
		TextureRegion[][] tmp = TextureRegion.split(textureManager.getKnightIdleSheet(),textureManager.getKnightIdleSheet().getWidth()/2, textureManager.getKnightIdleSheet().getHeight());

		TextureRegion[] idleFrames = new TextureRegion[2];

		int index = 0;

		for(int i = 0;i<1;i++) {
			for(int j = 0;j<2;j++) {
				idleFrames[index++] = tmp[i][j];
			}
		}

		idleAnimation = new Animation<TextureRegion>(0.5f,idleFrames);

		stateTime = 0f;
	}

	public void walkAnimation(float stateTime, String walkState,Texture walkSheet) {
		TextureRegion[][] tmp = TextureRegion.split(walkSheet,walkSheet.getWidth()/5, walkSheet.getHeight());

		TextureRegion[] idleFrames = new TextureRegion[5];

		int index = 0;

		for(int i = 0;i<1;i++) {
			for(int j = 0;j<5;j++) {
				idleFrames[index++] = tmp[i][j];
			}
		}

		switch(walkState) {
		case "walkLeft":
			walkLeftAnimation = new Animation<TextureRegion>(0.15f,idleFrames);
			break;
		case "walkRight":
			walkRightAnimation = new Animation<TextureRegion>(0.15f,idleFrames);
			break;
		case "walkFront":
			walkFrontAnimation = new Animation<TextureRegion>(0.15f,idleFrames);
			break;
		case "walkBack":
			walkBackAnimation = new Animation<TextureRegion>(0.15f,idleFrames);
			break;
		default:

		}

		stateTime = 0f;
	}

	public void attackAnimation(float stateTime, String attackState,Texture attackSheet) {
		TextureRegion[][] tmp = TextureRegion.split(attackSheet,attackSheet.getWidth()/3, attackSheet.getHeight());

		TextureRegion[] idleFrames = new TextureRegion[3];

		int index = 0;

		for(int i = 0;i<1;i++) {
			for(int j = 0;j<3 ;j++) {
				idleFrames[index++] = tmp[i][j];
			}
		}

		switch(attackState) {
		case "attackLeft":
			attackLeftAnimation = new Animation<TextureRegion>(0.15f,idleFrames);
			break;
		case "attackRight":
			attackRightAnimation = new Animation<TextureRegion>(0.15f,idleFrames);
			break;
		case "attackFront":
			attackFrontAnimation = new Animation<TextureRegion>(0.15f,idleFrames);
			break;
		case "attackBack":
			attackBackAnimation = new Animation<TextureRegion>(0.15f,idleFrames);
			break;
		default:

		}

		stateTime = 0f;
	}

	public Animation<TextureRegion> getIdleAnimation(){
		return idleAnimation;
	}
	public Animation<TextureRegion> getWalkAnimation(String walkAnimation){

		switch(walkAnimation) {
		case "walkRight":
			return this.walkRightAnimation;
		case "walkLeft":
			return this.walkLeftAnimation;
		case "walkBack":
			return this.walkBackAnimation;
		default:
			break;
		}

		return walkFrontAnimation;
	}

	public Animation<TextureRegion> getAttackAnimation(String attackAnimation){

		switch(attackAnimation) {
		case "attackRight":
			return this.attackRightAnimation;
		case "attackLeft":
			return this.attackLeftAnimation;
		case "attackBack":
			return this.attackBackAnimation;
		default:
			break;
		}

		return this.attackFrontAnimation;
	}
}
