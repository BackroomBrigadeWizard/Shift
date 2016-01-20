package com.akumainc.game;

import java.util.LinkedList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import com.akumainc.game.entities.Entity;
import com.akumainc.game.entities.LevelEnder;
import com.akumainc.game.entities.Lever;
import com.akumainc.game.entities.PlatLever;
import com.akumainc.game.entities.Player;
import com.akumainc.game.platform.Door;
import com.akumainc.game.platform.Platform;
import com.akumainc.game.utils.Debug;
import com.akumainc.game.utils.StopWatch;

public class Play extends BasicGameState {
	
	private Player player;
	//private Enemy enemy;
	public static LinkedList<Platform> platforms;
	public static LinkedList<Entity> entity;
	public static LinkedList<Lever> levers;
	public static LinkedList<Door> doors;
	public static LinkedList<LevelEnder> lEnds;
	public static LinkedList<PlatLever> platLever;
	
	public static Camera cam;
	
	public static Image level;
	private int delay, dDelay;
	private boolean showDebug;
	
	public static Color MIKUS_HAIR = new Color(0x7BC5C8);
	public static Color PINK_DANGO = new Color(0xE59CAE);
	
	private StopWatch watch;
	
	private Image bg, bg1;
	
	public Play(int id) {}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		platforms = new LinkedList<Platform>();
		entity = new LinkedList<Entity>();
		levers = new LinkedList<Lever>();
		doors = new LinkedList<Door>();
		lEnds = new LinkedList<LevelEnder>();
		platLever = new LinkedList<PlatLever>();
		
		player = new Player(-100, -100, 0, 0, 0, 0, 0);
		
		level = new Image("res/level.png");
		
		cam = new Camera(0, 0);
		
		LoadLevel.load(level);
		
		watch = new StopWatch();
		
		bg = new Image("res/tempbg.png");
		bg1 = bg.getScaledCopy(5);
		
		showDebug = false;
		
		dDelay = 100;
		delay = dDelay;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		
		bg.draw(0, 0);
		
		g.translate(cam.getX(), cam.getY());
		
		for(Platform p : platforms)
		{
			p.render(gc, sbg, g);
		}
		
		for(Door d : doors) {
			d.render(gc, sbg, g);
		}
		
		
		for(Entity e : entity) {
			e.render(gc, sbg, g);
		}
		
		for(Lever l : levers) {
			l.render(gc, sbg, g);
		}
		
		for(LevelEnder l : lEnds) {
			l.render(gc, sbg, g);
		}
		g.translate(-cam.getX(), -cam.getY());
		
		watch.render(gc, sbg, g);
		
		if(showDebug){
			Debug.showMemory(g, 50, 50);
			Debug.showFPS(gc, sbg, g, 50, 130);
		}
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();

		watch.update(gc, sbg, delta);
		
		for(Platform p : platforms) {
			p.update(gc, sbg, delta);
		}
		
		for(Door d : doors) {
			d.update(gc, sbg, delta);
		}
		
		for(Entity e : entity) {
			e.update(gc, sbg, delta);
			if(e.getClass() == player.getClass()) {
				player = (Player) e;
				cam.update(player);	
			}
			//if(!e.getAlive()) {
				//entity.remove(e);
			//}
		}
		
		for(Lever l : levers) {
			l.update(gc, sbg, delta);
		}
		
		for(LevelEnder l : lEnds) {
			l.update(gc, sbg, delta);
		}
		
		
		delay-=delta;
		
		if(input.isKeyPressed(Input.KEY_LSHIFT)) {
			if(delay <= 0) {
				for(int i = 0; i < platforms.size(); i++) {
					Platform tempPlat = platforms.get(i);
					if(tempPlat.getType() == 1) {
						tempPlat.makeTypeTwo();
					}
					else if(tempPlat.getType() == 2) {
						tempPlat.makeTypeOne();
					}
				}
				delay = dDelay;
			}
		}
		
		if(input.isKeyPressed(Input.KEY_F3)){
			if(showDebug) showDebug = false;
			else if(!showDebug) showDebug = true;
		}
		
		if(input.isKeyPressed(Input.KEY_ESCAPE)) {
			System.exit(0);
		}

		
	}

	@Override
	public int getID() {
		return 1;
	}
	
	public static Image level() {return level;}

}


