package com.akumainc.game.platform;

import java.util.LinkedList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import com.akumainc.game.Play;
import com.akumainc.game.entities.PlatLever;

public class LeverPlat extends Platform{
	
	PlatLever lever;
	
	public LeverPlat(float x, float y, float width, float height, float xDistance, 
			float speed, int type) {
		super(x, y, width, height, type);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.type = type;
		collidable = true;
		moving = false;
		this.speed = speed;
		xd = xDistance;
		try {
			sheet = new SpriteSheet("res/untitledGameTextures.png", 32, 32);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		lever = new PlatLever(0, 0, 0, 0, 0, false);

		tile1 = sheet.getSubImage(0, 0);
		tile2 = sheet.getSubImage(1, 0);
		gearL1 = sheet.getSubImage(2, 0);
		gearR1 = sheet.getSubImage(3, 0);
		wall1 = sheet.getSubImage(0, 1);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		if(collidable && type == 1) {
			tile1.draw((int)x, (int)y);
		}
		else if(!collidable && type == 2) {
			tile2.draw((int)x, (int)y);
		}
		else if(type == 0) {
			wall1.draw((int)x, (int)y);
		}
		else if(type == 3) {
			gearL1.draw((int)x, (int)y);
		}
		else if(type == 4) {
			gearR1.draw((int)x, (int)y);
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {		
		
		x += velX;
		y += velY;
		
		move(Play.platLever);
		
		if(type == 1) {
			collidable = true;
		}
		else if (type == 2) {
			collidable = false;
		}
		else if (type == 3 || type == 4) 
			collidable = false;
		else
			collidable = true;
		
		if(moving) {
			if(xd > 0)
				velX = speed;
			traveled += speed;
			if(xd > 0) {
				if(traveled >= xd || traveled <= -xd) {
					speed = -speed;
					traveled = 0;
				}
			}
		}
		
		
	}
	


	public boolean isCollidable() {
		return collidable;
	}

	public void setCollidable(boolean collidable) {
		this.collidable = collidable;
	}

	public void makeTypeOne() {
		type = 1;
	}
	
	public void makeTypeTwo() {
		type = 2;
	}
	
	public Rectangle getBounds() {
		return new Rectangle((int)x, (int)y, (int)width, (int)height);
	}

	public boolean isMoving() {return moving;}
	public void setMoving(boolean moving) {this.moving = moving;}
	
	private void move(LinkedList<PlatLever> levers) {
		for(PlatLever e : levers) {
			if(e.getClass() == lever.getClass()) {
				if(type == e.getType()) {
					if(e.isOn()) {
						collidable = false;
					}
					else
						collidable = true;
				}
			}
		}
	}

}
