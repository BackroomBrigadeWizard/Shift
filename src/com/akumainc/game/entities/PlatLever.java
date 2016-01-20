package com.akumainc.game.entities;

import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

import com.akumainc.game.Play;

public class PlatLever extends Lever{
	
	private Player player;
	private boolean on, hasPlayer, faceLeft;
	private Image lon, loff, l1on, l1off;
	private boolean showBounds;

	public PlatLever(float x, float y, float width, float height, int type,
			boolean faceLeft) {
		super(x, y, width, height, type, faceLeft);
		
		on = false;
		hasPlayer = false;
		this.setFaceLeft(faceLeft);
		player = new Player(0, 0, 0, 0, 0, 0, 0);
		lon = sheet.getSubImage(1, 2);
		loff = sheet.getSubImage(0, 2);
		if(faceLeft) {
			lon = lon.getFlippedCopy(true, false);
			loff = loff.getFlippedCopy(true, false);
		}
		l1on = lon.getScaledCopy(2);
		l1off = loff.getScaledCopy(2);
		
		showBounds = false;
		
	}

	public void render(GameContainer gc, StateBasedGame sgb, Graphics g) {
		if(!on)
			l1off.draw(x, y);
		if(on)
			l1on.draw(x, y);
		
		if(showBounds) {
			g.setColor(Color.red);
			g.draw(getBounds1());
		}
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		
		Input input = gc.getInput();
		
		hasPlayer(Play.entity);
		
		if(hasPlayer) {
			if(input.isKeyPressed(Input.KEY_E)) {
				if(!on)
					on = true;
				else
					on = false;
			}
		}
		
		if(input.isKeyPressed(Input.KEY_B)) {
			if(!showBounds) {
				showBounds = true;
			}
			else if(showBounds) {
				showBounds = false;
			}
		}
		
	}
	
	public boolean isOn() {
		return on;
	}

	public void setOn(boolean on) {
		this.on = on;
	}

	
	public Rectangle getBounds1() {
		return new Rectangle((int) x, (int) y, (int) width * 2, (int) height);
	}
	
	private void hasPlayer(LinkedList<Entity> entity) {
		for(int i = 0; i < entity.size(); i++) {
			Entity e = entity.get(i);
			if(e.getClass() == player.getClass()) {
				if(getBounds1().intersects(e.getAllBounds())){
					hasPlayer = true;
				}
				else{
					hasPlayer = false;
				}
			}
		}
	}

	public boolean isFaceLeft() {
		return faceLeft;
	}

	public void setFaceLeft(boolean faceLeft) {
		this.faceLeft = faceLeft;
	}
	
}
