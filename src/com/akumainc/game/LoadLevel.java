package com.akumainc.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

import com.akumainc.game.entities.LevelEnder;
import com.akumainc.game.entities.Lever;
import com.akumainc.game.entities.Player;
import com.akumainc.game.platform.Door;
import com.akumainc.game.platform.Platform;

public class LoadLevel {
	
	private static int w;
	private static int h;
	
	public static void load(Image image) {
		w = image.getWidth();
		h = image.getHeight();
	for(int xx = 0; xx < w; xx++) {
		for(int yy = 0; yy < h; yy++) {
			Color pixel = image.getColor(xx, yy);
			int red = pixel.getRed();
			int blue = pixel.getBlue();
			int green = pixel.getGreen();
			
			//wall tile1
			if(red == 255 && blue == 255 && green == 255) {
				Play.platforms.add(new Platform(xx*32, yy*32, 32, 32, 0));
			}
			
			//normal tile starts visible
			if(red == 255 && blue == 0 && green == 0) {
				Play.platforms.add(new Platform(xx*32, yy*32, 32, 32, 1));
				
				int x1 = xx + 1;
				if(x1 >= w) {
					x1 = 0;
				}
				Color pixel1 = image.getColor(x1, yy);
				int r1 = pixel1.getRed();
				int g1 = pixel1.getGreen();
				int b1 = pixel1.getBlue();
				if(r1 == 0 && g1 == 0 && b1 == 0)
					Play.platforms.add(new Platform(x1*32, yy*32, 32, 32, 4));
				
				x1 = xx - 1;
				if(x1 >= w) {
					x1 = 0;
				}
				pixel1 = image.getColor(x1, yy);
				r1 = pixel1.getRed();
				g1 = pixel1.getGreen();
				b1 = pixel1.getBlue();
				if(r1 == 0 && g1 == 0 && b1 == 0)
					Play.platforms.add(new Platform(x1*32, yy*32, 32, 32, 3));
			}
			
			//normal tile, starts invis
			if(red == 0 && blue == 0 && green == 255) {
				Play.platforms.add(new Platform(xx*32, yy*32, 32, 32, 2));
				int x1 = xx + 1;
				
				if(x1 >= w) {
					x1 = 0;
				}
				Color pixel1 = image.getColor(x1, yy);
				int r1 = pixel1.getRed();
				int g1 = pixel1.getGreen();
				int b1 = pixel1.getBlue();
				if(r1 == 0 && g1 == 0 && b1 == 0)
					Play.platforms.add(new Platform(x1*32, yy*32, 32, 32, 4));
				
				x1 = xx - 1;
				if(x1 >= w) {
					x1 = 0;
				}
				pixel1 = image.getColor(x1, yy);
				r1 = pixel1.getRed();
				g1 = pixel1.getGreen();
				b1 = pixel1.getBlue();
				if(r1 == 0 && g1 == 0 && b1 == 0)
					Play.platforms.add(new Platform(x1*32, yy*32, 32, 32, 3));
			}
			
			//moving plat on x dist 100 speed 1
			if(red == 255 && blue == 255 && green == 0) {
				Play.platforms.add(new Platform(xx*32, yy*32, 32, 32, 100, 1, 1, true));
				
				int x1 = xx + 1;
				if(x1 >= w) {
					x1 = 0;
				}
				Color pixel1 = image.getColor(x1, yy);
				int r1 = pixel1.getRed();
				int g1 = pixel1.getGreen();
				int b1 = pixel1.getBlue();
				if(r1 == 0 && g1 == 0 && b1 == 0)
					Play.platforms.add(new Platform(x1*32, yy*32, 32, 32, 100, 1, 4, true));
				
				x1 = xx - 1;
				if(x1 >= w) {
					x1 = 0;
				}
				pixel1 = image.getColor(x1, yy);
				r1 = pixel1.getRed();
				g1 = pixel1.getGreen();
				b1 = pixel1.getBlue();
				if(r1 == 0 && g1 == 0 && b1 == 0)
					Play.platforms.add(new Platform(x1*32, yy*32, 32, 32, 100, 1, 3, true));
			}
			
			//levers type 0
			if(red == 0 && blue == 127 && green == 0) {
				int x1 = xx + 1;
				Color pixel1 = image.getColor(x1, yy);
				int r1 = pixel1.getRed();
				int g1 = pixel1.getGreen();
				int b1 = pixel1.getBlue();
				if(r1 != 0 && b1 != 0 && g1 != 0) {
					Play.levers.add(new Lever((xx - 1)*32, yy*32, 32, 32, 0, true));
				}
				
				else
					Play.levers.add(new Lever(xx*32, yy*32, 32, 32, 0, false));
			}
			
			//lever type 1
			if(red == 0 && blue == 255 && green == 100) {
				int x1 = xx + 1;
				Color pixel1 = image.getColor(x1, yy);
				int r1 = pixel1.getRed();
				int g1 = pixel1.getGreen();
				int b1 = pixel1.getBlue();
				if(r1 != 0 && b1 != 0 && g1 != 0) {
					Play.levers.add(new Lever((xx - 1)*32, yy*32, 32, 32, 1, true));
				}
				
				else
					Play.levers.add(new Lever(xx*32, yy*32, 32, 32, 1, false));
			}
			
			//lever type 2
			if(red == 0 && blue == 255 && green == 125) {
				int x1 = xx + 1;
				Color pixel1 = image.getColor(x1, yy);
				int r1 = pixel1.getRed();
				int g1 = pixel1.getGreen();
				int b1 = pixel1.getBlue();
				if(r1 != 0 && b1 != 0 && g1 != 0) {
					Play.levers.add(new Lever((xx - 1)*32, yy*32, 32, 32, 2, true));
				}
				
				else
					Play.levers.add(new Lever(xx*32, yy*32, 32, 32, 2, false));
			}
			
			//lever type 3
			if(red == 0 && blue == 255 && green == 178) {
				int x1 = xx + 1;
				Color pixel1 = image.getColor(x1, yy);
				int r1 = pixel1.getRed();
				int g1 = pixel1.getGreen();
				int b1 = pixel1.getBlue();
				if(r1 != 0 && b1 != 0 && g1 != 0) {
					Play.levers.add(new Lever((xx - 1)*32, yy*32, 32, 32, 3, true));
				}
				
				else
					Play.levers.add(new Lever(xx*32, yy*32, 32, 32, 3, false));
			}
			
			//doors type 0
			if(red == 128 && blue == 128 && green == 128) {
				Play.doors.add(new Door(xx*32, yy*32, 32, 32, 0));
			}
			
			//doors type 1
			if(red == 99 && blue == 99 && green == 99) {
				Play.doors.add(new Door(xx*32, yy*32, 32, 32, 1));
			}
			
			//doors type 2
			if(red == 136 && blue == 136 && green == 136) {
				Play.doors.add(new Door(xx*32, yy*32, 32, 32, 2));
			}
			
			//doors type 3
			if(red == 168 && green == 168 && blue == 168) {
				Play.doors.add(new Door(xx*32, yy*32, 32, 32, 3));
			}
			
			//player
			if(red == 0 && blue == 255 && green == 0) {
				Player player = new Player(xx*32, yy*32, 32, 64, 0, xx*32, yy*32);
				Play.cam.setFirstX(player);
				Play.cam.setFirstY(player);
				Play.entity.add(player);
			}
			
			//levelEnder
			if(red == 175 && green == 0 && blue == 0) {
				Play.lEnds.add(new LevelEnder(xx*32, yy*32, 32, 32, 1));
			}
			
		}
	}
}
	
}
