package com.mydgx.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationManager {
	private Texture knightIdleSheet;
	private Texture knightWalkFrontSheet;
	private Texture knightWalkBackSheet;
	private Texture knightWalkLeftSheet;
	private Texture knightWalkRightSheet;
	private Texture knightAttackFrontSheet;
	private Texture knightAttackBackSheet;
	private Texture knightAttackRightSheet;
	private Texture knightAttackLeftSheet;
	private Animation<TextureRegion> idleAnimation;
	private Animation<TextureRegion> walkFrontAnimation;
	private Animation<TextureRegion> walkBackAnimation;
	private Animation<TextureRegion> walkLeftAnimation;
	private Animation<TextureRegion> walkRightAnimation;
	private Animation<TextureRegion> attackFrontAnimation;
	private Animation<TextureRegion> attackBackAnimation;
	private Animation<TextureRegion> attackRightAnimation;
	private Animation<TextureRegion> attackLeftAnimation;

	public AnimationManager() {
		this.knightIdleSheet = new Texture("Textures/Char/knightIdle.png");
		this.knightWalkFrontSheet =  new Texture("Textures/Char/knightWalkFront.png");
		this.knightWalkBackSheet =  new Texture("Textures/Char/knightWalkBack.png");
		this.knightWalkLeftSheet =  new Texture("Textures/Char/knightWalkLeft.png");
		this.knightWalkRightSheet =  new Texture("Textures/Char/knightWalkRight.png");
		this.knightAttackFrontSheet = new Texture("Textures/Char/knightAttackFront.png");
		this.knightAttackBackSheet = new Texture("Textures/Char/knightAttackBack.png");
		this.knightAttackLeftSheet = new Texture("Textures/Char/knightAttackLeft.png");
		this.knightAttackRightSheet = new Texture("Textures/Char/knightAttackRight.png");
	}


	public void idleAnimation(float stateTime) {
		TextureRegion[][] tmp = TextureRegion.split(knightIdleSheet,knightIdleSheet.getWidth()/2, knightIdleSheet.getHeight());

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

	public Texture getKnightIdleSheet() {
		return this.knightIdleSheet;
	}

	public Animation<TextureRegion> getIdleAnimation(){
		return idleAnimation;
	}

	public Texture getWalkSheet(String walkSheet) {
		switch(walkSheet) {
		case "walkRight":
			return this.knightWalkRightSheet;
		case "walkLeft":
			return this.knightWalkLeftSheet;
		case "walkBack":
			return this.knightWalkBackSheet;
		default:
			break;
		}

		return this.knightWalkFrontSheet;
	}

	public Texture getAttackSheet(String walkSheet) {
		switch(walkSheet) {
		case "attackRight":
			return this.knightAttackRightSheet;
		case "attackLeft":
			return this.knightAttackLeftSheet;
		case "attackBack":
			return this.knightAttackBackSheet;
		default:
			break;
		}

		return this.knightAttackFrontSheet;
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
