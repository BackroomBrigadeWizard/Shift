package com.akumainc.game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.akumainc.game.utils.StopWatch;

public class GameOver extends BasicGameState{
	
	public GameOver(int id) {}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sgb, Graphics g)
			throws SlickException {
		g.drawString("You Completed the level in " + StopWatch.getClockTime() + " seconds!!!!", (float)(UntitledMain.getWidth()/2 - 
				128-64), (float)(UntitledMain.getHeight()/2 - 128));
		g.drawString("Press 'Enter' to return to the main menu!", (float)(UntitledMain.getWidth()/2)-128-32, 
				(float)(UntitledMain.getHeight()/2 + 16));
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		
		if(input.isKeyPressed(Input.KEY_ENTER)) {
			sbg.enterState(0);
		}
	}

	@Override
	public int getID() {
		return 2;
	}

}
