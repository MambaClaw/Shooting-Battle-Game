package com.mygdx.game;

public class Bullet {
	public float x,y;
	public float vx = 5, vy = 7;
	public float vector;
	public float g = 0.25f;
	public boolean checkShoot = false;
	
	
	public void updateBullet(float delta){
        if(this.vector == 1){
        	this.x += this.vx;
        }
        
        if(this.vector == -1){
        	this.x -= this.vx;
        }
        this.vy -= g;
        this.y += this.vy;
        
        if(this.x < 0 || this.x > 960 || this.y < 0 || this.y > 640){
        	this.checkShoot = false;
        }

    }
}


