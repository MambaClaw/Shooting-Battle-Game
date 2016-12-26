package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;

public class Character {
	public int hP = 7;
	public float x, y;
	public float vx = 7, vy = 0;
	public int vector;
	public boolean checkJump = false;
	public boolean bulletHit = false;
	private static final int LEFT = -1;
	private static final int RIGHT = 1;
	private static final int JUMP = 9;
	private static final int STAND = 0;
	private static final float g = 0.25f;
	private static final float FLOOR = 100;
	private static final float SIZE_OF_CHARACTER_IMAGE = 44;
	

	void updateCharacter1(float delta) {
		if (Gdx.input.isKeyPressed(Keys.A) && this.x >= 0) {
			this.x -= this.vx;
			this.vector = LEFT;
		}

		if (Gdx.input.isKeyPressed(Keys.D) && this.x <= GameScreen.GAME_WIDTH - this.SIZE_OF_CHARACTER_IMAGE) {
			this.x += this.vx;
			this.vector = RIGHT;
		}

		if (Gdx.input.isKeyPressed(Keys.W)) {
			if (this.checkJump == false) {
				this.vy = JUMP;
				this.checkJump = true;

			}
		}

		if (this.checkJump == true) {
			this.vy -= g;
			this.y += this.vy;
			if (this.y <= FLOOR) {
				this.checkJump = false;

				this.vy = STAND;
			}
		}

		if (Gdx.input.isKeyPressed(Keys.SPACE) && GameScreen.bulletCharacter1.checkShoot == false) {
			shoot(GameScreen.character1, GameScreen.character2, GameScreen.bulletCharacter1);
		}

	}

	void updateCharacter2(float delta) {

		if (Gdx.input.isKeyPressed(Keys.LEFT) && this.x >= 0) {
			this.x -= this.vx;
			this.vector = LEFT;
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT) && this.x <= GameScreen.GAME_WIDTH - this.SIZE_OF_CHARACTER_IMAGE) {
			this.x += this.vx;
			this.vector = RIGHT;
		}

		if (Gdx.input.isKeyPressed(Keys.UP)) {
			if (this.checkJump == false) {
				this.vy = JUMP;
				this.checkJump = true;
			}
		}

		if (this.checkJump == true) {
			this.vy -= g;
			this.y += this.vy;
			if (this.y <= FLOOR) {
				this.checkJump = false;
				this.vy = STAND;
			}
		}

		if (Gdx.input.isKeyPressed(Keys.ENTER) && GameScreen.bulletCharacter2.checkShoot == false) {
			shoot(GameScreen.character2, GameScreen.character1, GameScreen.bulletCharacter2);
		}
	}

	public static void shoot(Character character, Character anotherCharacter, Bullet bullet) {
		bullet.checkShoot = true;
		bullet.x = character.x;
		bullet.y = character.y;
		bullet.vx = 7;
		bullet.vy = 7 + character.vy;
		bullet.vector = character.vector;
		anotherCharacter.bulletHit = false;
	}

	public static void initCharacter(Character character1, Character character2) {
		character1.x = 100;
		character1.y = 100;
		character1.vector = RIGHT;
		character2.x = 860;
		character2.y = 100;
		character2.vector = LEFT;
	}

}
