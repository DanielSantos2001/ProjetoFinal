package com.mygdx.animations;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class AnimationManager {
	private Animation<TextureRegion> skeletonIdleAnimation;
 	private Animation<TextureRegion> knightIdleAnimation;
	private Animation<TextureRegion> knightWalkFrontAnimation;
	private Animation<TextureRegion> knightWalkBackAnimation;
	private Animation<TextureRegion> skeletonWalkBackAnimation;
	private Animation<TextureRegion> knightWalkLeftAnimation;
	private Animation<TextureRegion> knightWalkRightAnimation;
	private Animation<TextureRegion> knightAttackFrontAnimation;
	private Animation<TextureRegion> skeletonAttackFrontAnimation;
	private Animation<TextureRegion> knightAttackBackAnimation;
	private Animation<TextureRegion> skeletonAttackBackAnimation;
	private Animation<TextureRegion> knightAttackRightAnimation;
	private Animation<TextureRegion> skeletonAttackRightAnimation;
	private Animation<TextureRegion> knightAttackLeftAnimation;
	private Animation<TextureRegion> skeletonAttackLeftAnimation;
	private Animation<TextureRegion> knightHurtAnimation;
	private TextureManager textureManager;
	public AnimationManager() {
		textureManager = new TextureManager();
	}

	public void knightIdleAnimation() {
		TextureRegion[][] tmp = TextureRegion.split(textureManager.getKnightIdleSheet(),textureManager.getKnightIdleSheet().getWidth()/2, textureManager.getKnightIdleSheet().getHeight());

		TextureRegion[] idleFrames = new TextureRegion[2];

		int index = 0;

		for(int i = 0;i<1;i++) {
			for(int j = 0;j<2;j++) {
				idleFrames[index++] = tmp[i][j];
			}
		}

		knightIdleAnimation = new Animation<>(0.5f, idleFrames);
	}

	public void knightHurtAnimation(){
		TextureRegion[][] tmp = TextureRegion.split(textureManager.getKnightHurtSheet(),textureManager.getKnightHurtSheet().getWidth()/2, textureManager.getKnightHurtSheet().getHeight());

		TextureRegion[] idleFrames = new TextureRegion[2];

		int index = 0;

		for(int i = 0;i<1;i++) {
			for(int j = 0;j<2;j++) {
				idleFrames[index++] = tmp[i][j];
			}
		}

		knightHurtAnimation = new Animation<>(0.5f, idleFrames);
	}

	public void skeletonIdleAnimation() {
		TextureRegion[][] tmp = TextureRegion.split(textureManager.getSkeletonIdleSheet(),textureManager.getSkeletonIdleSheet().getWidth()/2, textureManager.getSkeletonIdleSheet().getHeight());

		TextureRegion[] idleFrames = new TextureRegion[2];

		int index = 0;

		for(int i = 0;i<1;i++) {
			for(int j = 0;j<2;j++) {
				idleFrames[index++] = tmp[i][j];
			}
		}

		skeletonIdleAnimation = new Animation<>(0.5f, idleFrames);
	}

	public void skeletonWalkBackAnimation(Texture walkSheet){
		TextureRegion[][] tmp = TextureRegion.split(walkSheet,walkSheet.getWidth()/5, walkSheet.getHeight());

		TextureRegion[] idleFrames = new TextureRegion[5];

		int index = 0;

		for(int i = 0;i<1;i++) {
			for(int j = 0;j<5;j++) {
				idleFrames[index++] = tmp[i][j];
			}
		}

		skeletonWalkBackAnimation = new Animation<>(0.15f, idleFrames);
	}

	public void knightWalkAnimation(String walkState, Texture walkSheet) {
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
			knightWalkLeftAnimation = new Animation<>(0.15f, idleFrames);
			break;
		case "walkRight":
			knightWalkRightAnimation = new Animation<>(0.15f, idleFrames);
			break;
		case "walkFront":
			knightWalkFrontAnimation = new Animation<>(0.15f, idleFrames);
			break;
		case "walkBack":
			knightWalkBackAnimation = new Animation<>(0.15f, idleFrames);
			break;
		default:
		}

	}

	public void knightAttackAnimation(String attackState, Texture attackSheet) {
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
			knightAttackLeftAnimation = new Animation<>(0.15f, idleFrames);
			break;
		case "attackRight":
			knightAttackRightAnimation = new Animation<>(0.15f, idleFrames);
			break;
		case "attackFront":
			knightAttackFrontAnimation = new Animation<>(0.15f, idleFrames);
			break;
		case "attackBack":
			knightAttackBackAnimation = new Animation<>(0.15f, idleFrames);
			break;
		default:

		}
	}

	public void skeletonAttackAnimation(String attackState, Texture attackSheet){
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
				skeletonAttackLeftAnimation = new Animation<>(0.15f, idleFrames);
				break;
			case "attackRight":
				skeletonAttackRightAnimation = new Animation<>(0.15f, idleFrames);
				break;
			case "attackFront":
				skeletonAttackFrontAnimation = new Animation<>(0.15f, idleFrames);
				break;
			case "attackBack":
				skeletonAttackBackAnimation = new Animation<>(0.15f, idleFrames);
				break;
			default:

		}
	}

	public Animation<TextureRegion> getKnightIdleAnimation(){
		return knightIdleAnimation;
	}
	public Animation<TextureRegion> getKnightHurtAnimation(){return knightHurtAnimation;}
	public Animation<TextureRegion> getSkeletonIdleAnimation(){return  skeletonIdleAnimation;}
	public Animation<TextureRegion> getSkeletonWalkBackAnimation(){return skeletonWalkBackAnimation;}
	public Animation<TextureRegion> getKnightWalkAnimation(String walkAnimation){

		switch(walkAnimation) {
		case "walkRight":
			return this.knightWalkRightAnimation;
		case "walkLeft":
			return this.knightWalkLeftAnimation;
		case "walkBack":
			return this.knightWalkBackAnimation;
		default:
			break;
		}

		return knightWalkFrontAnimation;
	}

	public Animation<TextureRegion> getKnightAttackAnimation(String attackAnimation){

		switch(attackAnimation) {
		case "attackRight":
			return this.knightAttackRightAnimation;
		case "attackLeft":
			return this.knightAttackLeftAnimation;
		case "attackBack":
			return this.knightAttackBackAnimation;
		default:
			break;
		}

		return this.knightAttackFrontAnimation;
	}

	public Animation<TextureRegion> getSkeletonAttackAnimation(String attackAnimation){
		switch(attackAnimation) {
			case "attackRight":
				return this.skeletonAttackRightAnimation;
			case "attackLeft":
				return this.skeletonAttackLeftAnimation;
			case "attackBack":
				return this.skeletonAttackBackAnimation;
			default:
				break;
		}

		return this.skeletonAttackFrontAnimation;
	}
}
