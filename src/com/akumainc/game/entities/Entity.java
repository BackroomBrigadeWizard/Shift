package com.akumainc.game.entities;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

//import com.akumainc.game.platform.Platform;

public abstract class Entity {
	
	protected float width, height;
	protected int type;
	protected float y, x, velX, velY;
	protected boolean isAlive;
	protected boolean jumping, falling;
	protected Rectangle bounds;
	protected SpriteSheet sheet;
	
	public Entity(float x, float y, float width, float height, int type) {
		this.x = x; this.y = y;
		this.width = width; this.height = height;
		isAlive = true;
		this.type = type;
		velX = 0; velY = 0;
		falling = true;
		jumping = false;
		try {
			sheet = new SpriteSheet("res/untitledGameTextures.png", 32, 32);
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public Entity() {}
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g){}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {}

	public float getVelX() {return velX;}
	public void setVelX(float velX) {this.velX = velX;}

	public float getVelY() {return velY;}
	public void setVelY(float velY) {this.velY = velY;}

	public boolean isJumping() {return jumping;}
	public void setJumping(boolean jumping) {this.jumping = jumping;}

	public boolean isFalling() {return falling;}
	public void setFalling(boolean falling) {this.falling = falling;}

	public void setX(float x) {this.x = x;}
	public void setY(float y) {this.y = y;}

	public float getX() {return x;}
	public float getY() {return y;}

	public void setWidth(float w) {width = w;}
	public void setHeight(float h) {height = h;}
	
	public float getWidth() {return width;}
	public float getHeight() {return height;}
	
	public void setAlive(boolean isAlive) {this.isAlive = isAlive;}
	public boolean getAlive() {return isAlive;}
	
	//whole object
	public Rectangle getAllBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}
	//bottom
	public Rectangle getBounds() {
		return new Rectangle((int) ((int)x+(width/2) - ((width/2)/2)), (int)y+(height/2), (int)width/2, (int)height/2);
	}
	//left
	public Rectangle getBoundsLeft() {
		return new Rectangle((int)x, (int)y+5, (int)5, (int)height-10);
	}
	//right
	public Rectangle getBoundsRight() {
		return new Rectangle((int) ((int)x+width-5), (int)y+5, (int)5, (int)height-10);
	}
	//top
	public Rectangle getBoundsTop() {
		return new Rectangle((int)x+(width/2) - ((width/2)/2), (int)y, (int)width/2, (int)height/2);
	}

	public int getType() {
		return type;
	}

}
