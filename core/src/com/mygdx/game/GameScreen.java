package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {
	private ShootingGame shootingGame;
	

	
	private static Texture character1RightImg;
	private static Texture character2RightImg;
	private static Texture character1LeftImg;
	private static Texture character2LeftImg;
	private static Texture bulletImg;
	private static Texture backgroundImg;
	private static Texture heartImg;
	private static Texture character1WinsImg;
	private static Texture character2WinsImg;
	
	public static Character character1 = new Character();
	public static Character character2 = new Character();
	public static Bullet bulletCharacter1 = new Bullet();
	public static Bullet bulletCharacter2 = new Bullet();
	
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
        Character.initCharacter(character1, character2);
    }
    
    @Override
    public void render(float delta) {
    	character1.updateCharacter1(delta);
    	character2.updateCharacter2(delta);
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
        gameOverScene(character1, character2, batch);
        batch.end();
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
    
    public static void gameOverScene(Character character1, Character character2, SpriteBatch batch){
    	if(character1.hP <= 0){
        	batch.draw(character2WinsImg, 0, 0);
        }
        if(character2.hP <= 0){
        	batch.draw(character1WinsImg, 0, 0);
        }
    }
    
}
