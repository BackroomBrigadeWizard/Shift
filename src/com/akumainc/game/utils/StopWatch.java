package com.akumainc.game.utils;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import com.akumainc.game.Play;
import com.akumainc.game.UntitledMain;


public class StopWatch {
	private static Timer timer;
	public static final int ONE_SEC = 1000;
	public static final int TENTH_SEC = 100;
	public static final int HUN_SEC = 10;
	private int clockTick; // number of clock ticks; tick can be 1.0s or .1s
	private double clockTime; // time in seconds
	private static String clockTimeString;
	
	public StopWatch() {
		clockTick = 0;
		clockTime = ((double)clockTick)/10.0;
		
		clockTimeString = new Double(clockTime).toString();
		
		timer = new Timer(TENTH_SEC, new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				clockTick++;
				clockTime = ((double)clockTick)/10.0;
				clockTimeString = new Double(clockTime).toString();
			}			
		});
	}
	
	public void reset() {
		clockTick = 0;
		clockTime = ((double)clockTick)/10.0;
		clockTimeString = new Double(clockTime).toString();
	}
	
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) {
		g.setColor(Play.MIKUS_HAIR);
		g.drawString(clockTimeString, (float) (UntitledMain.getWidth() - 75), 10);
	}
	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) {
		timer.start();
	}	

	public static String getClockTime() {
		return clockTimeString;
	}
	
	public static void stop() {
		timer.stop();
	}
	
}