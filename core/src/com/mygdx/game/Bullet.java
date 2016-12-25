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
	
	public static void checkBulletHitCharacter1(Bullet bulletCharacter2, Character character1,float delta){
    	if(character1.bulletHit == false && (bulletCharacter2.x + 20 >= character1.x) && (bulletCharacter2.x <= character1.x + 44) && (bulletCharacter2.y + 20 >= character1.y) && (bulletCharacter2.y <= character1.y + 60)){
    		character1.hP -= 1;
    		character1.bulletHit = true;
    		bulletCharacter2.checkShoot = false;
    	}
    }
	
    public static void checkBulletHitCharacter2(Bullet bulletCharacter1, Character character2,float delta){
    	if(character2.bulletHit == false && (bulletCharacter1.x + 20 >= character2.x) && (bulletCharacter1.x <= character2.x + 44) && (bulletCharacter1.y + 20 >= character2.y) && (bulletCharacter1.y <= character2.y + 60)){
    		character2.hP -= 1;
    		character2.bulletHit = true;
    		bulletCharacter1.checkShoot = false;
    	}
    }
    
}


