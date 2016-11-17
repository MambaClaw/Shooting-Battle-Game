package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {
	private static float XPositionBulletCharacter1;
	private static float YPositionBulletCharacter1;
	private float vectorOfBulletCharacter1;
	private static float XPositionBulletCharacter2;
	private static float YPositionBulletCharacter2;
	private float vectorOfBulletCharacter2;
	private float vXBulletCharacter1;
	private float vYBulletCharacter1;
	private float vXBulletCharacter2;
	private float vYBulletCharacter2;
	private static int TURN_LEFT = -1;
	private static int TURN_RIGHT = 1;
	private static int JUMP_UP = 9;
	private static int STAND = 0;

	private static boolean checkShootCharacter1 = false;
	private static boolean checkShootCharacter2 = false;
	private ShootingGame shootingGame;
	private Texture character1RightImg;
	private Texture character2RightImg;
	private Texture character1LeftImg;
	private Texture character2LeftImg;
	private Texture bulletImg;
	private Texture backgroundImg;
	private Texture heartImg;
	private Texture character1WinsImg;
	private Texture character2WinsImg;
	private static float g = 0.25f;
	private Character character1 = new Character();
	private Character character2 = new Character();
    public GameScreen(ShootingGame shootingGame) {
        this.shootingGame = shootingGame;
        character1RightImg = new Texture("character1Right.png");
        character2RightImg = new Texture("character2Right.png");
        character1LeftImg = new Texture("character1Left.png");
        character2LeftImg = new Texture("character2Left.png");
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
    	updateBulletCharacter1(delta);
    	updateBulletCharacter2(delta);
    	checkBulletHitCharacter1(character1, delta);
    	checkBulletHitCharacter2(character2, delta);
    	Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SpriteBatch batch = shootingGame.batch;
        batch.begin();
        batch.draw(backgroundImg, 0, 0);
        if(character1.vector == 1){
        	batch.draw(character1RightImg, character1.x, character1.y);
        }
        if(character1.vector == -1){
        	batch.draw(character1LeftImg, character1.x, character1.y);
        }
        if(character2.vector == 1){
        	batch.draw(character2RightImg, character2.x, character2.y);
        }
        if(character2.vector == -1){
        	batch.draw(character2LeftImg, character2.x, character2.y);
        }
        if(checkShootCharacter1 == true){
        	batch.draw(bulletImg, XPositionBulletCharacter1, YPositionBulletCharacter1);
        }
        if(checkShootCharacter2 == true){
        	batch.draw(bulletImg, XPositionBulletCharacter2, YPositionBulletCharacter2);
        }
        for(float i = 0 ; i < character2.hP ; i++){
        	batch.draw(heartImg, 900 - i*45, 580);
        }
        for(float i = 0 ; i < character1.hP ; i++){
        	batch.draw(heartImg, 20 + i*45, 580);
        }
        if(character1.hP <= 0){
        	batch.draw(character2WinsImg, 0, 0);
        }
        if(character2.hP <= 0){
        	batch.draw(character1WinsImg, 0, 0);
        }
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
        	/*else if(character1.canDoubleJump = true){
        		character1.vy += 10;
        		character1.checkDoubleJump = true;
        	}*/
        }
        
        /*if(!Gdx.input.isKeyPressed(Keys.W) && character1.checkJump == true){
        	character1.canDoubleJump = true;
        }*/
        
        if(character1.checkJump == true){
	        character1.vy -= g;
	        character1.y += character1.vy;
	        if(character1.y == 100){
	        	character1.vy = STAND;
	        	character1.checkJump = false;
	        	character1.checkDoubleJump = false;
	        }
        }
        
        if(Gdx.input.isKeyPressed(Keys.SPACE) && checkShootCharacter1 == false){
        	checkShootCharacter1 = true;
        	XPositionBulletCharacter1 = character1.x;
        	YPositionBulletCharacter1 = character1.y;
        	vXBulletCharacter1 = 5;
        	vYBulletCharacter1 = 7 + character1.vy;
        	vectorOfBulletCharacter1 = character1.vector;
        	character2.bulletHit = false;
        }
    }
    private void updateCharacter2(Character character2,float delta) {
        if(Gdx.input.isKeyPressed(Keys.LEFT)){
        	character2.x -= character2.vx;
        	character2.vector = TURN_LEFT;
        }
        
        if(Gdx.input.isKeyPressed(Keys.RIGHT)){
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
	        if(character2.y == 100){
	        	character2.checkJump = false;
	        	character2.vy = STAND;
	        }
        }
        
        if(Gdx.input.isKeyPressed(Keys.ENTER) && checkShootCharacter2 == false){
        	checkShootCharacter2 = true;
        	XPositionBulletCharacter2 = character2.x;
        	YPositionBulletCharacter2 = character2.y;
        	vXBulletCharacter2 = 5;
        	vYBulletCharacter2 = 7 + character2.vy;
        	vectorOfBulletCharacter2 = character2.vector;
        	character1.bulletHit = false;
        }
    }
    
    private void updateBulletCharacter1(float delta) {
        if(vectorOfBulletCharacter1 == 1){
        	XPositionBulletCharacter1 += vXBulletCharacter1;
        }
        
        if(vectorOfBulletCharacter1 == -1){
        	XPositionBulletCharacter1 -= vXBulletCharacter1;
        }
        vYBulletCharacter1 -= g;
        YPositionBulletCharacter1 += vYBulletCharacter1;
        if(XPositionBulletCharacter1<0 || XPositionBulletCharacter1>960 || YPositionBulletCharacter1<0 || YPositionBulletCharacter1>640){
        	checkShootCharacter1 = false;
        }
    }
   
    private void updateBulletCharacter2(float delta) {
    	if(vectorOfBulletCharacter2 == 1){
        	XPositionBulletCharacter2 += vXBulletCharacter2;
        }
        
        if(vectorOfBulletCharacter2 == -1){
        	XPositionBulletCharacter2 -= vXBulletCharacter2;
        }
        vYBulletCharacter2 -= g;
        YPositionBulletCharacter2 += vYBulletCharacter2;
        if(XPositionBulletCharacter2<0 || XPositionBulletCharacter2>960 || YPositionBulletCharacter2<0 || YPositionBulletCharacter2>640){
        	checkShootCharacter2 = false;
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
    
    public static void checkBulletHitCharacter2(Character character2,float delta){
    	if(character2.bulletHit == false && (XPositionBulletCharacter1 + 20 >= character2.x) && (XPositionBulletCharacter1 <= character2.x + 44) && (YPositionBulletCharacter1 + 20 >= character2.y) && (YPositionBulletCharacter1 <= character2.y + 60)){
    		character2.hP -= 1;
    		character2.bulletHit = true;
    		checkShootCharacter1 = false;
    	}
    }
    
    public static void checkBulletHitCharacter1(Character character1,float delta){
    	if(character1.bulletHit == false && (XPositionBulletCharacter2 + 20 >= character1.x) && (XPositionBulletCharacter2 <= character1.x + 44) && (YPositionBulletCharacter2 + 20 >= character1.y) && (YPositionBulletCharacter2 <= character1.y + 60)){
    		character1.hP -= 1;
    		character1.bulletHit = true;
    		checkShootCharacter2 = false;
    	}
    }
}
