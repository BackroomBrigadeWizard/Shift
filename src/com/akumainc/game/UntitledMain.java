package com.akumainc.game;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class UntitledMain extends StateBasedGame {
	
	private static double width = 1280; 
	private static double height = 720;
	public static final int menu = 0;
	public static final int play = 1;
	public static final int gOver = 2;
	public static final String name = "Shift alpha 4.13.15";
	
	public UntitledMain(String name) {
		super(name);
		this.addState(new Menu(menu));
		this.addState(new Play(play));
		this.addState(new GameOver(gOver));
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menu).init(gc,  this);
		this.enterState(menu);
	}
	
	public static void main(String args[]) {
		AppGameContainer container;
		try {
			container = new AppGameContainer(new UntitledMain(name));
			container.setDisplayMode((int)width, (int)height, false);
			container.setTargetFrameRate(60);
			container.setVSync(false);
			container.setUpdateOnlyWhenVisible(true);
			container.setShowFPS(false);
			container.setSmoothDeltas(true);
			container.start();
			
		}catch(SlickException e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	public static double getWidth() {return width;}
	public static double getHeight() {return height;}
}