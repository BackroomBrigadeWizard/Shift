package com.akumainc.game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

public class Menu extends BasicGameState{
	
	//private String title;
	private Rectangle startB;
	
	public Menu(int id){}
	
	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		//title = "Untitled Game";
		startB = new Rectangle((int)UntitledMain.getWidth() / 2 - 250, (int)UntitledMain.getHeight() / 2 - 150, 500, 100);
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		//g.drawString(title, 100, 100);
		g.setColor(Color.blue);
		g.fillRect(startB.getX(), startB.getY(), startB.getWidth(), startB.getHeight());
		g.setColor(Color.black);
		g.drawString("Play", (int)UntitledMain.getWidth() / 2 - 50, (int)UntitledMain.getHeight() / 2 - 100);
		g.setColor(Color.white);
		g.drawString("X: " + Mouse.getX() + "Y: " + Mouse.getY(), 50, 50);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		
		if(Mouse.getX() >= ((UntitledMain.getWidth() / 2) - startB.getWidth() / 2) && Mouse.getX() <= ((UntitledMain.getWidth() / 2) + startB.getWidth() / 2)) {
			if(Mouse.getY() >= ((UntitledMain.getHeight() - startB.getY()) - startB.getHeight()) && Mouse.getY() <= UntitledMain.getHeight() - startB.getY()) {
				if(Mouse.isButtonDown(0)) {
					sbg.getState(1).init(gc, sbg);
					sbg.enterState(1);
				}
			}
		}
		
		if(input.isKeyPressed(Input.KEY_ESCAPE)) {
			System.exit(0);
		}
	}

	@Override
	public int getID() {
		return 0;
	}

}
