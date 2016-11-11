package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {
	private int XPositionCharacter1;
	private int YPositionCharacter1;
	private int XPositionCharacter2;
	private int YPositionCharacter2;
	private int XPositionBulletCharacter1;
	private int YPositionBulletCharacter1;
	private int vectorOfBulletCharacter1;
	private int XPositionBulletCharacter2;
	private int YPositionBulletCharacter2;
	private int vectorOfBulletCharacter2;
	private int vectorOfCharacter1 = 1;
	private int vectorOfCharacter2 = -1;// 1 = right , -1 = left
	private float vY = 5;
	private float g = 9.8f;
	private float v;
	private boolean checkShootCharacter1 = false;
	private boolean checkShootCharacter2 = false;
	private boolean checkJump = false;
	
	private ShootingGame shootingGame;
	
	private Texture character1RightImg;
	 
	private Texture character2RightImg;

	private Texture character1LeftImg;
	 
	private Texture character2LeftImg;
	
	private Texture dotImg;
	
	private Texture backgroundImg;
	
    public GameScreen(ShootingGame shootingGame) {
        this.shootingGame = shootingGame;
        character1RightImg = new Texture("character1Right.png");
        character2RightImg = new Texture("character2Right.png");
        character1LeftImg = new Texture("character1Left.png");
        character2LeftImg = new Texture("character2Left.png");
        backgroundImg = new Texture("background.jpeg");
        dotImg = new Texture("dot.png");
        XPositionCharacter1 = 100;
        YPositionCharacter1 = 100;
        XPositionCharacter2 = 860;
        YPositionCharacter2 = 100;
    }
    
    @Override
    public void render(float delta) {
    	updateCharacter1(delta);
    	updateCharacter2(delta);
    	updateBulletCharacter1(delta);
    	updateBulletCharacter2(delta);
    	Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        SpriteBatch batch = shootingGame.batch;
        batch.begin();
        batch.draw(backgroundImg, 0, 0);
        if(vectorOfCharacter1 == 1){
        	batch.draw(character1RightImg, XPositionCharacter1, YPositionCharacter1);
        }
        if(vectorOfCharacter1 == -1){
        	batch.draw(character1LeftImg, XPositionCharacter1, YPositionCharacter1);
        }
        if(vectorOfCharacter2 == 1){
        	batch.draw(character2RightImg, XPositionCharacter2, YPositionCharacter2);
        }
        if(vectorOfCharacter2 == -1){
        	batch.draw(character2LeftImg, XPositionCharacter2, YPositionCharacter2);
        }
        if(checkShootCharacter1 == true){
        	batch.draw(dotImg, XPositionBulletCharacter1, YPositionBulletCharacter1);
        }
        if(checkShootCharacter2 == true){
        	batch.draw(dotImg, XPositionBulletCharacter2, YPositionBulletCharacter2);
        }
        batch.end();
    }
    
    private void updateCharacter1(float delta) {
        if(Gdx.input.isKeyPressed(Keys.A)){
        	XPositionCharacter1 -= 5;
        	vectorOfCharacter1 = -1;
        }
        if(Gdx.input.isKeyPressed(Keys.D)){
        	XPositionCharacter1 += 5;
        	vectorOfCharacter1 = 1;
        }
        if(Gdx.input.isKeyPressed(Keys.W)){
        	YPositionCharacter1 += 5;
        }
        if(Gdx.input.isKeyPressed(Keys.S)){
        	YPositionCharacter1 -= 5;
        }
        if(Gdx.input.isKeyPressed(Keys.C)){
        	checkShootCharacter1 = true;
        	XPositionBulletCharacter1 = XPositionCharacter1;
        	YPositionBulletCharacter1 = YPositionCharacter1;
        	vectorOfBulletCharacter1 = vectorOfCharacter1;
        }
    }
    
    private void updateCharacter2(float delta) {
        if(Gdx.input.isKeyPressed(Keys.LEFT)){
        	XPositionCharacter2 -= 5;
        	vectorOfCharacter2 = -1;
        }
        if(Gdx.input.isKeyPressed(Keys.RIGHT)){
        	XPositionCharacter2 += 5;
        	vectorOfCharacter2 = 1;
        }
        if(Gdx.input.isKeyPressed(Keys.UP)){
        	YPositionCharacter2 += 5;
        }
        if(Gdx.input.isKeyPressed(Keys.DOWN)){
        	YPositionCharacter2 -= 5;
        }
        if(Gdx.input.isKeyPressed(Keys.M)){
        	checkShootCharacter2 = true;
        	XPositionBulletCharacter2 = XPositionCharacter2;
        	YPositionBulletCharacter2 = YPositionCharacter2;
        	vectorOfBulletCharacter2 = vectorOfCharacter2;
        }
    }
    
    private void updateBulletCharacter1(float delta) {
        if(vectorOfBulletCharacter1 == 1){
        	XPositionBulletCharacter1 += 10;
        }
        if(vectorOfBulletCharacter1 == -1){
        	XPositionBulletCharacter1 -= 10;
        }
    }
   
    private void updateBulletCharacter2(float delta) {
        if(vectorOfBulletCharacter2 == 1){
        	XPositionBulletCharacter2 += 10;
        }
        if(vectorOfBulletCharacter2 == -1){
        	XPositionBulletCharacter2 -= 10;
        }
    }
}
