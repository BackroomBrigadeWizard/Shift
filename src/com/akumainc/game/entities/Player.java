package com.akumainc.game.entities;

import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import com.akumainc.game.Play;
import com.akumainc.game.platform.Door;
import com.akumainc.game.platform.Platform;

public class Player extends Entity {

	Rectangle player;
	private float gravity, sx, sy, accel;
	private float boxX, boxY, boxWidth, boxHeight;
	private final float MAX_SPEED, MAX_RUN_SPEED, PUSH_SPEED;
	private float lastPlatY, lastLastPlatY;
	private boolean moving, reset, canMoveCamUp;
	private float vertDist, speed, friction;
	private Platform plat;
	private boolean showBounds;
	
 	public Player(float x, float y, float width, float height, int type, float sx, float sy) {
		super(x, y, width, height, type);
		gravity = 0.5f;
		accel = 0.5f;
		friction = 0.8f;
		speed = 0;
		
		MAX_SPEED = 10;
		MAX_RUN_SPEED = 7;
		PUSH_SPEED = 3;
		
		moving = false;
		this.sx = sx; this.sy = sy;
		
		boxWidth = width * 10;
		boxHeight = height * 3;
		boxX = x - width;
		boxY = y - height*2 + 16;
		
		lastPlatY = 0;
		lastLastPlatY = 0;
		vertDist = 0;
		reset = true;
		canMoveCamUp = true;
		plat = new Platform();

		showBounds = false;
		
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		g.setColor(Color.green);
		g.fillRect((int)x, (int)y, (int)width, (int)height);
		if(showBounds){
			g.setColor(Color.red);
			g.drawRect(boxX, boxY, boxWidth, boxHeight);
			g.draw(getAllBounds());
			g.draw(getBounds());
			g.draw(getBoundsLeft());
			g.draw(getBoundsRight());
			g.draw(getBoundsTop());
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {		
		
		
		Input input = gc.getInput();

		x += velX;
		y += velY;
		
		if(moveBoxHoriz()) {
			boxX += velX;
		}
		
		if(moveBoxVert()) {
			boxY += velY;
		} 
		
		if(falling || jumping) {
			velY += gravity;
			jumping = true;
			if(velY >= MAX_SPEED) {
				velY = MAX_SPEED;
			}
		}
		
		boxReset();
		Collision(Play.platforms);
		CollisionD(Play.doors);
		
		if(input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_D))
			moving = true;
		
		if(moving)
			Move(input);
		
		if(velX == 0) 
			moving = false;
		
		if(input.isKeyPressed(Input.KEY_SPACE) && !jumping){
			jumping = true;
			falling = true;
			lastLastPlatY = lastPlatY;
			velY = -12;
		}
		
		if(input.isKeyPressed(Input.KEY_B)) {
			if(!showBounds) {
				showBounds = true;
			}
			else if(showBounds) {
				showBounds = false;
			}
		}
		
		if((lastLastPlatY - lastPlatY) >= 32){
			vertDist += lastLastPlatY - lastPlatY;
		}
		
		if(vertDist >= 32){
			if(canMoveCamUp){
				Play.cam.moveCamUp(vertDist);
			}
			vertDist = 0;
			lastLastPlatY = lastPlatY;
		}
		
		if(!isAlive){
			x = sx;
			y = sy;
			isAlive = true;
			Play.cam.setFirstX(this);
		}
		
		if(y >= Play.level.getHeight() * 32) {
			isAlive = false;
		}
	}
	
	private void Collision(LinkedList<Platform> p) { 
		for(int i = 0; i < p.size(); i++) {
			plat = p.get(i);
			
			if(plat.isCollidable()) {
				if(getBounds().intersects(plat.getBounds())) {
					y = plat.getY() - height;
					velY = 0;
					boxY = y - height*2 + 16;
					if(reset)
						lastPlatY = plat.getY();
					falling = false;
					jumping = false;
					if(plat.isMoving()){
						velX = plat.getVelX();
						velY = plat.getVelY();
					}
					else
						velX = 0;
				}
				else if(getBoundsTop().intersects(plat.getBounds())) {
					y = plat.getY() + plat.getHeight();
					velY = 1;
				}
				else if(getBoundsRight().intersects(plat.getBounds())) {
					x = plat.getX() - width;
					if(boxX < x - width - 24) boxX = x - width - 24;
				}
				else if(getBoundsLeft().intersects(plat.getBounds())) {
					x = plat.getX() + plat.getWidth();
					if(boxX > x - 8) boxX = x - 8;
				}
				else {
					falling = true;
					canMoveCamUp = true;
				}
			}
		}
	}
	
	//Collision With The D ;)
	private void CollisionD(LinkedList<Door> p) { 
		for(int i = 0; i < p.size(); i++) {
			Platform plat = p.get(i);
			
			if(plat.isCollidable()) {
				if(getBounds().intersects(plat.getBounds())) {
					y = plat.getY() - height;
					velY = 0;
					//boxY = y - height;
					if(reset)
						lastPlatY = plat.getY();
					falling = false;
					jumping = false;
					if(plat.isMoving()){
						velX = plat.getVelX();
						velY = plat.getVelY();
					}
					else
						velX = 0;
				}
				else if(getBoundsTop().intersects(plat.getBounds())) {
					y = plat.getY() + plat.getHeight();
					velY = 1;
				}
				else if(getBoundsRight().intersects(plat.getBounds())) {
					x = plat.getX() - width;
					boxX = x - width - 24;
				}
				else if(getBoundsLeft().intersects(plat.getBounds())) {
					x = plat.getX() + plat.getWidth();
					boxX = x - 8;
				}
				else {
					falling = true;
				}
			}
		}
	}

	
	private void Move(Input input) {
		if(input.isKeyDown(Input.KEY_D)) {
			speed += accel;
			if(speed >= MAX_RUN_SPEED) {
				speed = MAX_RUN_SPEED;
			}
		}
//		
		else if(input.isKeyDown(Input.KEY_A)) {
			speed -= accel;
			if(speed <= -MAX_RUN_SPEED) {
				speed = -MAX_RUN_SPEED;
			}
		}
//
		else {
			if(speed > 0){
				speed -= friction;
				if(speed <= 0)
					speed = 0;
			}

			if(speed < 0){
				speed += friction;
				if(speed >= 0)
					speed = 0;
			}
		}
		
		velX = speed;
	}


	public boolean moveBoxHoriz() {
		if(x <= boxX) {
			Play.cam.setHorizMovement(true);
			return true;
		}
		else if(x + width >= boxX + boxWidth) {
			Play.cam.setHorizMovement(true);
			return true;
		}
		else {
			Play.cam.setHorizMovement(false);
			return false;
		}
	}

	public boolean moveBoxVert() {
		if(y <= boxY) {
			return true;
		}
		else if(y + height >= boxY + boxHeight) {
			if(y + height >= lastPlatY){
				//System.out.println(canMoveCamUp);
				if(canMoveCamUp)
					Play.cam.setVertMovementDown(true);
			}
			else if(!canMoveCamUp)
				Play.cam.setVertMovementDown(false);
			return true;
		}
		else {
			Play.cam.setVertMovementDown(false);
			Play.cam.setVertMovementUp(false);
			reset = true;
			return false;
		}
	}

	public void setVertDist(float dist) {
		vertDist = dist;
	}

	public float getVertDist() {return vertDist;}

	public boolean isCanMoveCamUp() {
		return canMoveCamUp;
	}

	public void setCanMoveCamUp(boolean canMoveCamUp) {
		this.canMoveCamUp = canMoveCamUp;
	}
	
	private void boxReset() {
		if(boxX + boxWidth < x + width) {
			boxX = x - width;
		}
		if(boxX > x){
			boxX = x - width;
		}
		if(boxY > y) {
			boxY = y - height*2 + 16;
		}
		if(boxY + boxHeight < y + height) {
			boxY = y - height*2 + 16;
		}
	}

	public float getBoxY() {
		return boxY;
	}
	
	public void setBoxY(float boxY) {
		this.boxY = boxY;
	}

	public float getBoxWidth() {
		return boxWidth;
	}
	public float getBoxHeight() {
		return boxHeight;
	}

	
	public float getGravity() {
		return gravity;
	}

	public void setGravity(float gravity) {
		this.gravity = gravity;
	}

	public float getSx() {
		return sx;
	}

	public void setSx(float sx) {
		this.sx = sx;
	}

	public float getSy() {
		return sy;
	}

	public void setSy(float sy) {
		this.sy = sy;
	}

	public float getAccel() {
		return accel;
	}

	public void setAccel(float accel) {
		this.accel = accel;
	}

	public float getBoxX() {
		return boxX;
	}

	public void setBoxX(float boxX) {
		this.boxX = boxX;
	}

	public float getLastPlatY() {
		return lastPlatY;
	}

	public void setLastPlatY(float lastPlatY) {
		this.lastPlatY = lastPlatY;
	}

	public float getLastLastPlatY() {
		return lastLastPlatY;
	}

	public void setLastLastPlatY(float lastLastPlatY) {
		this.lastLastPlatY = lastLastPlatY;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public boolean isReset() {
		return reset;
	}

	public void setReset(boolean reset) {
		this.reset = reset;
	}

	public float getSpeed() {
		return speed;
	}

	public void setSpeed(float speed) {
		this.speed = speed;
	}

	public float getFriction() {
		return friction;
	}

	public void setFriction(float friction) {
		this.friction = friction;
	}

	public Platform getPlat() {
		return plat;
	}

	public void setPlat(Platform plat) {
		this.plat = plat;
	}

	public boolean isShowBounds() {
		return showBounds;
	}

	public void setShowBounds(boolean showBounds) {
		this.showBounds = showBounds;
	}

	public float getMAX_SPEED() {
		return MAX_SPEED;
	}

	public float getMAX_RUN_SPEED() {
		return MAX_RUN_SPEED;
	}

	public float getPUSH_SPEED() {
		return PUSH_SPEED;
	}

	public void setBoxWidth(float boxWidth) {
		this.boxWidth = boxWidth;
	}

	public void setBoxHeight(float boxHeight) {
		this.boxHeight = boxHeight;
	}

	


}
