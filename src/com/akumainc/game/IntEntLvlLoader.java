package com.akumainc.game;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;

import com.akumainc.game.entities.PlatLever;

public class IntEntLvlLoader {


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
				
				//plat levers t0
				if(red == 255 && blue == 0 && green == 0) {
					Play.platLever.add(new PlatLever(xx*32, yy*32, 32, 32, 0, false));
				}
				
				//plat levers t1
				if(red == 255 && blue == 255 && green == 0) {
					Play.platLever.add(new PlatLever(xx*32, yy*32, 32, 32, 1, false));
				}
				
				//plat levers t2
				if(red == 255 && blue == 0 && green == 255) {
					Play.platLever.add(new PlatLever(xx*32, yy*32, 32, 32, 2, false));
				}
				
			}
		}
	}

}
