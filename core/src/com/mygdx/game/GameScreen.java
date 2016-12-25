package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {
	private ShootingGame shootingGame;
	
	private static int TURN_LEFT = -1;
	private static int TURN_RIGHT = 1;
	private static int JUMP_UP = 9;
	private static int STAND = 0;
	private static float g = 0.25f;
	
	private static Texture character1RightImg;
	private static Texture character2RightImg;
	private static Texture character1LeftImg;
	private static Texture character2LeftImg;
	private static Texture bulletImg;
	private static Texture backgroundImg;
	private static Texture heartImg;
	private static Texture character1WinsImg;
	private static Texture character2WinsImg;
	
	private Character character1 = new Character();
	private Character character2 = new Character();
	private Bullet bulletCharacter1 = new Bullet();
	private Bullet bulletCharacter2 = new Bullet();
    public GameScreen(ShootingGame shootingGame) {
        this.shootingGame = shootingGame;
        character1RightImg = new Texture("character1Right.png");
        character2RightImg = new Texture("Character2Right.png");
        character1LeftImg = new Texture("character1Left.png");
        character2LeftImg = new Texture("Character2Left.png");
        backgroundImg = new Texture("background.jpeg");
        bulletImg = new Texture("bullet.png");
        heartImg = new Texture("heart.png");
        character1WinsImg = new Texture("Character1Wins.png");
        character2WinsImg = new Texture("Character2Wins.png");
        initCharacter(character1, character2);
    }
    
    @Override
    public void render(float delta) {
    	updateCharacter1(character1,delta);
    	updateCharacter2(character2,delta);
        bulletCharacter1.updateBullet(delta);
        bulletCharacter2.updateBullet(delta);
    	checkBulletHitCharacter1(bulletCharacter2, character1, delta);
    	checkBulletHitCharacter2(bulletCharacter1, character2, delta);
    	Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SpriteBatch batch = shootingGame.batch;
        batch.begin();
        batch.draw(backgroundImg, 0, 0);
        drawCharacter1(character1, batch);
        drawCharacter2(character2, batch);
        drawBullet(bulletCharacter1, batch);
        drawBullet(bulletCharacter2, batch);
        drawHPCharacter1(character1, batch);
        drawHPCharacter2(character2, batch);
        checkGameOver(character1, character2, batch);
        batch.end();
    }
    
    private void updateCharacter1(Character character1,float delta) {
        if(Gdx.input.isKeyPressed(Keys.A) && character1.x>=0){
        	character1.x -= character1.vx;
        	character1.vector = TURN_LEFT;
        }
        
        if(Gdx.input.isKeyPressed(Keys.D) && character1.x<=916){
        	character1.x += character1.vx;
        	character1.vector = TURN_RIGHT;
        }
        
        if(Gdx.input.isKeyPressed(Keys.W)){
        	if(character1.checkJump == false){
        		character1.vy = JUMP_UP;
            	character1.checkJump = true;

        	}
        }
        
        if(character1.checkJump == true){
	        character1.vy -= g;
	        character1.y += character1.vy;
	        if(character1.y <= 100){
	        	character1.checkJump = false;
	        	character1.vy = STAND;
	        }
        }
        
        
        if(Gdx.input.isKeyPressed(Keys.SPACE) && bulletCharacter1.checkShoot == false){
        	shoot(character1, character2, bulletCharacter1);
        }
    }
    private void updateCharacter2(Character character2,float delta) {
        if(Gdx.input.isKeyPressed(Keys.LEFT) && character2.x>=0){
        	character2.x -= character2.vx;
        	character2.vector = TURN_LEFT;
        }
        
        if(Gdx.input.isKeyPressed(Keys.RIGHT) && character2.x<=916){
        	character2.x += character2.vx;
        	character2.vector = TURN_RIGHT;
        }
        
        if(Gdx.input.isKeyPressed(Keys.UP)){
        	if(character2.checkJump == false){
        		character2.vy = JUMP_UP;
            	character2.checkJump= true;
        	}
        }
        
        if(character2.checkJump == true){
	        character2.vy -= g;
	        character2.y += character2.vy;
	        if(character2.y <= 100){
	        	character2.checkJump = false;
	        	character2.vy = STAND;
	        }
        }
        
        if(Gdx.input.isKeyPressed(Keys.ENTER) && bulletCharacter2.checkShoot == false){
        	shoot(character2, character1, bulletCharacter2);
        }
    }
    
    public static void initCharacter(Character character1, Character character2){
    	character1.x = 100;
    	character1.y = 100;
    	character1.vector = 1;
    	character2.x = 860;
    	character2.y = 100;
    	character2.vector = -1;
    }
    
    public static void checkBulletHitCharacter2(Bullet bulletCharacter1, Character character2,float delta){
    	if(character2.bulletHit == false && (bulletCharacter1.x + 20 >= character2.x) && (bulletCharacter1.x <= character2.x + 44) && (bulletCharacter1.y + 20 >= character2.y) && (bulletCharacter1.y <= character2.y + 60)){
    		character2.hP -= 1;
    		character2.bulletHit = true;
    		bulletCharacter1.checkShoot = false;
    	}
    }
    
    public static void checkBulletHitCharacter1(Bullet bulletCharacter2, Character character1,float delta){
    	if(character1.bulletHit == false && (bulletCharacter2.x + 20 >= character1.x) && (bulletCharacter2.x <= character1.x + 44) && (bulletCharacter2.y + 20 >= character1.y) && (bulletCharacter2.y <= character1.y + 60)){
    		character1.hP -= 1;
    		character1.bulletHit = true;
    		bulletCharacter2.checkShoot = false;
    	}
    }
    
    public static void drawCharacter1(Character character1, SpriteBatch batch){
    	if(character1.vector == 1){
        	batch.draw(character1RightImg, character1.x, character1.y);
        }
        if(character1.vector == -1){
        	batch.draw(character1LeftImg, character1.x, character1.y);
        }
    }
    
    public static void drawCharacter2(Character character2, SpriteBatch batch){
    	if(character2.vector == 1){
        	batch.draw(character2RightImg, character2.x, character2.y);
        }
        if(character2.vector == -1){
        	batch.draw(character2LeftImg, character2.x, character2.y);
        }
    }
    
    public static void drawBullet(Bullet bullet, SpriteBatch batch){
    	if(bullet.checkShoot == true){
        	batch.draw(bulletImg, bullet.x, bullet.y);
        }
    }
    
    public static void drawHPCharacter2(Character character2, SpriteBatch batch){
    	for(float i = 0 ; i < character2.hP ; i++){
        	batch.draw(heartImg, 900 - i*45, 580);
        }
    }
    
    public static void drawHPCharacter1(Character character1, SpriteBatch batch){
    	for(float i = 0 ; i < character1.hP ; i++){
        	batch.draw(heartImg, 20 + i*45, 580);
        }
    }
    
    public static void checkGameOver(Character character1, Character character2, SpriteBatch batch){
    	if(character1.hP <= 0){
        	batch.draw(character2WinsImg, 0, 0);
        }
        if(character2.hP <= 0){
        	batch.draw(character1WinsImg, 0, 0);
        }
    }
    
    public static void shoot(Character character, Character anotherCharacter, Bullet bullet){
    	bullet.checkShoot = true;
    	bullet.x = character.x;
    	bullet.y = character.y;
    	bullet.vx = 7;
    	bullet.vy = 7 + character.vy;
    	bullet.vector = character.vector;
    	anotherCharacter.bulletHit = false;
    }
}
