package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {
	private float XPositionBulletCharacter1;
	private float YPositionBulletCharacter1;
	private float vectorOfBulletCharacter1;
	private float XPositionBulletCharacter2;
	private float YPositionBulletCharacter2;
	private float vectorOfBulletCharacter2;
	private float vXBulletCharacter1;
	private float vYBulletCharacter1;
	private float vXBulletCharacter2;
	private float vYBulletCharacter2;
	private int vectorOfCharacter1 = 1;
	private int vectorOfCharacter2 = -1;// 1 = right , -1 = left
	private boolean checkShootCharacter1 = false;
	private boolean checkShootCharacter2 = false;
	private ShootingGame shootingGame;
	private Texture character1RightImg;
	private Texture character2RightImg;
	private Texture character1LeftImg;
	private Texture character2LeftImg;
	private Texture dotImg;
	private Texture backgroundImg;
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
        dotImg = new Texture("dot.png");
        initCharacter(character1,character2);
    }
    
    @Override
    public void render(float delta) {
    	updateCharacter1(character1,delta);
    	updateCharacter2(character2,delta);
    	updateBulletCharacter1(delta);
    	updateBulletCharacter2(delta);
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
        	batch.draw(dotImg, XPositionBulletCharacter1, YPositionBulletCharacter1);
        }
        if(checkShootCharacter2 == true){
        	batch.draw(dotImg, XPositionBulletCharacter2, YPositionBulletCharacter2);
        }
        batch.end();
    }
    
    private void updateCharacter1(Character character1,float delta) {
        if(Gdx.input.isKeyPressed(Keys.A)){
        	character1.x -= character1.vx;
        	character1.vector = -1;
        }
        
        if(Gdx.input.isKeyPressed(Keys.D)){
        	character1.x += character1.vx;
        	character1.vector = 1;
        }
        
        if(Gdx.input.isKeyPressed(Keys.W)){
        	if(character1.checkJump== false){
        		character1.vy= 10;
            	character1.checkJump= true;
        	}
        }
        
        if(character1.checkJump == true){
	        character1.vy -= g;
	        character1.y += character1.vy;
	        if(character1.y == 100){
	        	character1.vy = 0;
	        	character1.checkJump = false;
	        }
        }
        
        if(Gdx.input.isKeyPressed(Keys.C)){
        	checkShootCharacter1 = true;
        	XPositionBulletCharacter1 = character1.x;
        	YPositionBulletCharacter1 = character1.y;
        	vXBulletCharacter1 = 4;
        	vYBulletCharacter1 = 10 + character1.vy;
        	vectorOfBulletCharacter1 = vectorOfCharacter1;
        }
    }
    private void updateCharacter2(Character character2,float delta) {
        if(Gdx.input.isKeyPressed(Keys.LEFT)){
        	character2.x -= 7;
        	character2.vector = -1;
        }
        
        if(Gdx.input.isKeyPressed(Keys.RIGHT)){
        	character2.x += 7;
        	character2.vector = 1;
        }
        
        if(Gdx.input.isKeyPressed(Keys.UP)){
        	if(character2.checkJump== false){
        		character2.vy= 10;
            	character2.checkJump= true;
        	}
        }
        
        if(character2.checkJump == true){
	        character2.vy -= g;
	        character2.y += character2.vy;
	        if(character2.y == 100){
	        	character2.checkJump = false;
	        }
        }
        
        if(Gdx.input.isKeyPressed(Keys.M)){
        	checkShootCharacter2 = true;
        	XPositionBulletCharacter2 = character2.x;
        	YPositionBulletCharacter2 = character2.y;
        	vectorOfBulletCharacter2 = character2.vector;
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
    }
   
    private void updateBulletCharacter2(float delta) {
        if(vectorOfBulletCharacter2 == 1){
        	XPositionBulletCharacter2 += 15;
        }
        
        if(vectorOfBulletCharacter2 == -1){
        	XPositionBulletCharacter2 -= 15;
        }
    }
    
    public static void initCharacter(Character character1, Character character2){
    	character1.x = 100;
    	character1.y = 100;
    	character1.vx = 5;
    	character1.vy = 0;
    	character1.vector = 1;
    	character1.checkJump = false;
    	character2.x = 860;
    	character2.y = 100;
    	character2.vector = -1;
    	character2.vx = 5;
    	character2.vy = 0;
    	character2.checkJump = false;
    }
}
