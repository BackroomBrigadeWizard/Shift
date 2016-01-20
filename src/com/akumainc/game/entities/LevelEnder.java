package com.akumainc.game.entities;

import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import com.akumainc.game.Play;
import com.akumainc.game.utils.StopWatch;

public class LevelEnder extends Entity {
	
	private Player p;
	private boolean hasPlayer;
	private boolean canEndLevel, showBounds;
	private boolean endLevel;
	private float px, py, pw, ph;
	private Image endlvltxt;
	
	public LevelEnder(float x, float y, float width, float height, int type){
		super(x, y, width, height, type);
		type = -1;
		canEndLevel = false;
		showBounds = false;
		hasPlayer = false;
		endLevel = false;
		p = new Player(0, 0, 0, 0, 0, 0, 0);
		px = 0; py = 0; pw = 0; ph = 0;
		try {
			endlvltxt = new Image("res/endlvltxt.png");
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		if(showBounds) {
			g.setColor(Color.red);
			g.drawRect(x, y, width, height);
		}
		
		if(hasPlayer) {
			endlvltxt.draw(px - 80, py - 18);
		}
		
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		Input input = gc.getInput();	
		
		hasPlayer(Play.entity);
		setPlayerPos(Play.entity);
		
		if(hasPlayer) {
			if(canEndLevel) {
				if(input.isKeyPressed(Input.KEY_E)) {
					endLevel = true;
				}
			}
		}
		
		if(endLevel) {
			System.out.println("Move to next stage");
			StopWatch.stop();
			try {
				sbg.getState(2).init(gc, sbg);
			} catch (SlickException e) {
				e.printStackTrace();
			}
			sbg.enterState(2);
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

	private void hasPlayer(LinkedList<Entity> entity) {
		for(int i = 0; i < entity.size(); i++) {
			Entity e = entity.get(i);
			if(e.getClass() == p.getClass()) {
				if(getAllBounds().intersects(e.getAllBounds())){
					hasPlayer = true;
					canEndLevel = true;
				}
				else{
					hasPlayer = false;
					canEndLevel = false;
				}
			}
		}
	}

	public boolean isHasPlayer() {
		return hasPlayer;
	}

	public void setHasPlayer(boolean hasPlayer) {
		this.hasPlayer = hasPlayer;
	}

	public boolean isCanEndLevel() {
		return canEndLevel;
	}

	public void setCanEndLevel(boolean canEndLevel) {
		this.canEndLevel = canEndLevel;
	}

	public boolean isShowBounds() {
		return showBounds;
	}

	public void setShowBounds(boolean showBounds) {
		this.showBounds = showBounds;
	}

	public boolean isEndLevel() {
		return endLevel;
	}

	public void setEndLevel(boolean endLevel) {
		this.endLevel = endLevel;
	}

	private void setPlayerPos(LinkedList<Entity> e){
		for(int i = 0; i < e.size(); i++) {
			Entity ent = e.get(i);
			if(ent.getClass() == p.getClass()) {
				p = (Player) ent;
			}
		}
		px = p.getX(); py = p.getY();
		pw = p.getWidth(); ph = p.getHeight();
	}
	
}
