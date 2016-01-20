package com.akumainc.game;

import org.newdawn.slick.Image;

import com.akumainc.game.entities.Player;

public class Camera {
	private float x, y, velY, dist;
	private boolean moveHoriz, moveVertUp, moveVertDown;
	private float lx, ly, lw, lh;
	private Image level;
	private boolean topLock, botLock, leftLock, rightLock;
	private float tol;

	public Camera(float x, float y) {
		this.x = x; this.y = y;
		moveHoriz = false;
		moveVertUp = false;
		moveVertDown = false;
		velY = 2;

		tol = 0;

		level = Play.level();

		lh = (level.getHeight() * 32);
		lw = (level.getWidth() * 32);

		lx = 0; ly = 0;

		topLock = false;
		botLock = false;
		leftLock = false;
		rightLock = false;

	}

	public void update(Player e) {
				
				topLock(e);
				botLock(e);
				leftLock(e);
				rightLock(e);
		
						if(!leftLock && !rightLock) {
							if(moveHoriz)
								x += -e.getVelX();
						}
				
						if(!topLock && !botLock){
							if(moveVertDown) 
								y += -e.getVelY();
						}
				
						if(!topLock && !botLock){
							if(moveVertUp){
								if(dist > 0){
									dist -= 2;
									e.setVertDist(dist);
									//e.setBoxY(e.getBoxY() - velY);
									y += velY;
								}
								else{
									dist = 0;
									moveVertUp = false;
								}
							}
						}

	}

	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}



	public void setFirstX(Player e) {
		x = -e.getX() + (int)UntitledMain.getWidth() / 2 - 32;
	}

	public void setFirstY(Player e) {
		y = -e.getY() + (int)UntitledMain.getHeight() - 128 + 16;
	}

	public void setHorizMovement(boolean m) {
		moveHoriz = m;
	}

	public void setVertMovementUp(boolean b) {
		moveVertUp = b;
	}

	public void setVertMovementDown(boolean b) {
		moveVertDown = b;
	}

	public boolean getVertMoveUp() {return moveVertUp;}

	public void moveCamUp(float dist) {
		this.dist = dist;
		if(dist >= 32) {
			moveVertUp = true;
		}
	}

	public float getDist() {
		return dist;
	}


	private void botLock(Player p) {
		tol = p.getBoxWidth();
		if(p.getY() + (UntitledMain.getHeight()/2) - (tol/2) >= ly + lh) {
			y = (float) -((ly + lh) - UntitledMain.getHeight());
			botLock = true;
		}
		else
			botLock = false;
	}

	public void topLock(Player p) {
		if(ly <= y) {
			if(p.getY() + p.getHeight() <= 32*19){
				y = -ly;
				topLock = true;
			}
			else {
				topLock = false;
			}
		}
		else topLock = false;

	}

	public void leftLock(Player p) {
		if(-x <= lx) {
			if(p.getX() <= (lx + UntitledMain.getWidth() / 2)) {
				x = -lx;
				leftLock = true;
			}
			else
				leftLock = false;
		}

		else
			leftLock = false;
	}

	public void rightLock(Player p) {
		if(-x + UntitledMain.getWidth() >= lx + lw) {
			if(p.getX() >= lx + lw - (UntitledMain.getWidth() / 2)){
				x = (float) -(lx + lw - UntitledMain.getWidth());
				rightLock = true;
			}
			else{
				rightLock = false;
			}
		}

		else
			rightLock = false;
	}

}
