package com.akumainc.game.entities;

import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
//import org.newdawn.slick.Input;
import org.newdawn.slick.state.StateBasedGame;

import com.akumainc.game.Play;
import com.akumainc.game.platform.Platform;

public class Enemy extends Entity{

	float gravity;
	private final float MAX_SPEED;
	

	public Enemy(float x, float y, int width, int height, int type) {
		super(x, y, width, height, type);
		gravity = 0.5f;
		MAX_SPEED = 10;
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		g.setColor(Color.red);
		g.fillRect((int)x, (int)y, this.width, this.height);

	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {		
		x += velX;
		y += velY;
		
		if(falling || jumping) {
			velY += gravity;
			if(velY >= MAX_SPEED) {
				velY = MAX_SPEED;
			}
		}
		
		Collision(Play.platforms);
		
	}
	
	private void Collision(LinkedList<Platform> p) {
		for(int i = 0; i < p.size(); i++) {
			Platform plat = p.get(i);
			
			if(plat.isCollidable()) {
				if(getBounds().intersects(plat.getBounds())) {
					y = plat.getY() - height;
					velY = 0;
					falling = false;
					jumping = false;
				}
				else if(getBoundsTop().intersects(plat.getBounds())) {
					y = plat.getY() + plat.getHeight();
					velY = 1;
				}
				else if(getBoundsRight().intersects(plat.getBounds())) {
					x = plat.getX() - width;
				}
				else if(getBoundsLeft().intersects(plat.getBounds())) {
					x = plat.getX() + plat.getWidth();
				}
				else {
					falling = true;
				}
			}
		}
	}

}
