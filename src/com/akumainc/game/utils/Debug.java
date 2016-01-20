/**
 * @author JDSelsor
 * **/

package com.akumainc.game.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.state.StateBasedGame;

import com.akumainc.game.Play;

public class Debug {
	public static void log (String message){
		Calendar calendar = Calendar.getInstance();
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.YEAR) + " " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE));
		System.out.println("\t" + message);
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
	}
	
	public static void logError(String errorTitle, String message, String errorCode){
		Calendar calendar = Calendar.getInstance();
		
		try{
			File file = new File(System.getProperty("user.home"),"Desktop/crashlog.txt");
			if(!file.exists()){
				file.createNewFile();
			}
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(file.getAbsolutePath()));
			
			bw.write("Error Message: " + message);
			bw.write("Error Code: " + errorCode);
			bw.close();
		}
		catch(IOException ex){
			ex.printStackTrace();
		}
		
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
		System.out.println(calendar.get(Calendar.DAY_OF_MONTH) + " " + calendar.get(Calendar.YEAR) + " " + calendar.get(Calendar.HOUR) + ":" + calendar.get(Calendar.MINUTE));
		System.out.println("Error: " + errorTitle +"\n\t"+message+" " + errorCode);
		System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------");
	}
	
	public static void showFPS(GameContainer gc, StateBasedGame sbg, Graphics g, int x, int y){
		g.setColor(Play.PINK_DANGO);
		int fps = gc.getFPS();
		g.drawString("FPS: " + fps, x, y);
	}
	
	public static void showMemory(Graphics g, int x, int y){
		g.setColor(Play.PINK_DANGO);
		Runtime runtime = Runtime.getRuntime();
		long usedMem = (runtime.maxMemory() - runtime.freeMemory()) / 1024 / 1024;
		long freeMem = runtime.freeMemory() / 1024/ 1024;
		long totalMem = runtime.maxMemory() / 1024/ 1024;
		
		g.drawString("usedMem(Mb): " + usedMem + "\nfreeMem(Mb): " + freeMem + "\nper free(mb): " + ((freeMem/totalMem)*100) + "\nper used(mb): " + ((usedMem / totalMem) *100), x, y);
	}
}