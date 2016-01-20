package com.akumainc.game.platform;

import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import com.akumainc.game.Play;
import com.akumainc.game.entities.Lever;

public class Door extends Platform{

	private Lever lever;

	public Door(float x, float y, float width, float height, int type) {
		super(x, y, width, height, type);
		collidable = true;
		lever = new Lever(0, 0, 0, 0, 0, false);
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		if(!collidable)
			g.setColor(Color.white);
		else if(collidable)
			g.setColor(Color.gray);
		g.fillRect(x, y, width, height);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		open(Play.levers);
	}

	
	private void open(LinkedList<Lever> levers) {
		for(Lever e : levers) {
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
