package com.mygdx.game;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen extends ScreenAdapter {
	private ShootingGame shootingGame;
	
	private Texture character1Img;
	 
	private Texture character2Img;
	
    public GameScreen(ShootingGame shootingGame) {
        this.shootingGame = shootingGame;
        character1Img = new Texture("character1.png");
        character2Img = new Texture("character2.png");
    }
    
    @Override
    public void render(float delta) {
        SpriteBatch batch = shootingGame.batch;
        batch.begin();
        batch.draw(character1Img, 100, 100);
        batch.draw(character2Img, 620, 100);
        batch.end();
    }
}
