package com.teamtyro.src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import com.teamtyro.etc.Constants;
import com.teamtyro.etc.MazeMap;

public class DataAnalyzer {
	private static TestSubject subjects[];
	private static int map[][];
	private static MazeMap maze;
	
	private static int maxDensity;
	
	public static void main(String[] args) {
		System.out.printf("DataAnalyzer V 0.0.1\n");
		
		subjects = new TestSubject [50];
		parseTextyText("game_first_run.txt");
		
		map = new int[16][16];
		maze = new MazeMap();
		maze.loadConstMap("cbbbccccccccbbbbcccccbbbbbbcbbbbcbbbcbsbccbcbbbbcccbccc" +
				"bccccbbbbcbcbbbcbbbcbbbbbcbcbbbcbcccbbbbbcccccccccbbbbbbbcbbbbbbb" +
				"bbbcbbbbccccccccccbcbbbbbbbbbcbbbcccbbbbcccbbcccccccbbbbcbccccbcc" +
				"cbcbbbbcbbbbbbcbcbcbbbbcbwcccbcbcbcbbbbcbbbbcbbbcbcbbbbcccccccccc" +
				"ccbbbb");
		for(int i=0; i<10; i++) {
			maze.loadDensity(subjects[i]);
		}
		
		for(int x=0; x<Constants.MAP_WIDTH; x++) {
			for(int y=0; y<Constants.MAP_HEIGHT; y++) {
				map[x][y] = maze.getSpace(x,y);
			}
		}
		
		printMaze(map);
		
		begin();
	}
	
	private static void parseTextyText(String filename) {
		BufferedReader textFile;
	    try {
	    	textFile = new BufferedReader(new FileReader(filename));
	    } catch(FileNotFoundException ex) {
	    	System.out.printf("ERROR: Cannot load Texty Text\n");
	    	return;
	    }
	    
	    String line;
	    try {
	    	line = textFile.readLine();
	    } catch(IOException ex) { return; }
	    
	    int charsRead = 1;
	    for(int j=0; j<50; j++) {
	    	String input[] = new String [10];
	    	for(int i=0; i<10; i++) {
	    		input[i] = "";
		    	while(line.charAt(charsRead) != '|' && charsRead < line.length()-1) {
		    		input[i] = input[i] + line.charAt(charsRead);
		    		charsRead++;
		    	}
		    	
		    	charsRead++;
	    	}
	    	
	    	try { line = textFile.readLine(); } catch (IOException ex) { return; }
	    	
	    	System.out.printf("%s\n%s\n%s\n%s\n\n", input[7], input[6], input[5], input[9]);
	    	
	    	subjects[j] = new TestSubject(input[7], input[6], input[5], input[9], 1);
	    	System.out.printf("%d\n%d\n%d\n\n", subjects[j].getGender(), subjects[j].getAge(), subjects[j].getEthnicity());
	    	charsRead = 1;
	    }
	    
	    try {
	    	textFile.close();
	    } catch(IOException ex) {}
	}
	
	private static void begin() {
		try {
			Display.setDisplayMode(new DisplayMode(640,640));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}

		// Init OpenGL
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(0, 640, 0, 640, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
		maxDensity = maze.getMaxDensity();
		
		System.out.printf("VALUE: %d\n", maxDensity);

		// Start main loop
		while(!Display.isCloseRequested()) {
			// Clears screen and depth buffer
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);

			// Rendering
			render();
			checkKeys();

			Display.update();
		}

		Display.destroy();
	}
	
	private static void render() {
		float bs = 32;
		GL11.glBegin(GL11.GL_LINE_LOOP);
			GL11.glVertex2f( 3*bs, 3*bs-1);
			GL11.glVertex2f(19*bs, 3*bs-1);
			GL11.glVertex2f(19*bs,19*bs+0);
			GL11.glVertex2f( 3*bs,19*bs+0);
		GL11.glEnd();
		
		for(int y=0; y<Constants.MAP_HEIGHT; y++) {
			for(int x=0; x<Constants.MAP_WIDTH; x++) {
				switch(map[x][y]) {
				case Constants.MAP_BLOCK:
					GL11.glColor3f(1, 0, 0);
					GL11.glBegin(GL11.GL_QUADS);
						GL11.glVertex2f((3*bs)+(x*bs)   , (18*bs)-(y*bs)   );
						GL11.glVertex2f((3*bs)+(x*bs)+bs, (18*bs)-(y*bs)   );
						GL11.glVertex2f((3*bs)+(x*bs)+bs, (18*bs)-(y*bs)+bs);
						GL11.glVertex2f((3*bs)+(x*bs)   , (18*bs)-(y*bs)+bs);
					GL11.glEnd();
					break;
				case Constants.MAP_START:
					GL11.glColor3f(1, 1, 0);
					GL11.glBegin(GL11.GL_QUADS);
						GL11.glVertex2f((3*bs)+(x*bs)   , (18*bs)-(y*bs)   );
						GL11.glVertex2f((3*bs)+(x*bs)+bs, (18*bs)-(y*bs)   );
						GL11.glVertex2f((3*bs)+(x*bs)+bs, (18*bs)-(y*bs)+bs);
						GL11.glVertex2f((3*bs)+(x*bs)   , (18*bs)-(y*bs)+bs);
					GL11.glEnd();
					break;
				case Constants.MAP_WIN:
					GL11.glColor3f(0, 1, 0);
					GL11.glBegin(GL11.GL_QUADS);
						GL11.glVertex2f((3*bs)+(x*bs)   , (18*bs)-(y*bs)   );
						GL11.glVertex2f((3*bs)+(x*bs)+bs, (18*bs)-(y*bs)   );
						GL11.glVertex2f((3*bs)+(x*bs)+bs, (18*bs)-(y*bs)+bs);
						GL11.glVertex2f((3*bs)+(x*bs)   , (18*bs)-(y*bs)+bs);
					GL11.glEnd();
					break;
				case Constants.MAP_SPACE:
					GL11.glColor3f(0, 0, (float)maze.getDensity(x,y)/(float)maxDensity);
					GL11.glBegin(GL11.GL_QUADS);
						GL11.glVertex2f((3*bs)+(x*bs)   , (18*bs)-(y*bs)   );
						GL11.glVertex2f((3*bs)+(x*bs)+bs, (18*bs)-(y*bs)   );
						GL11.glVertex2f((3*bs)+(x*bs)+bs, (18*bs)-(y*bs)+bs);
						GL11.glVertex2f((3*bs)+(x*bs)   , (18*bs)-(y*bs)+bs);
					GL11.glEnd();
					break;
				}
			}
		}
	}
	
	private static void checkKeys() {
		if(Keyboard.isKeyDown(Keyboard.KEY_1)) {
			if(maxDensity > 0) {
				maxDensity--;
			}
		} else if(Keyboard.isKeyDown(Keyboard.KEY_2)) {
			if(maxDensity < maze.getMaxDensity()) {
				maxDensity++;
			}
		}
	}
	
	private static void printMaze(int[][] tmap) {
		for(int x=0; x<Constants.MAP_WIDTH+2; x++) {
			System.out.printf("[-]");
		}
		System.out.println("");
		for (int y = 0; y < Constants.MAP_WIDTH; y++) {
			System.out.printf("[|]");
			for (int x = 0; x < Constants.MAP_HEIGHT; x++) {
				switch (tmap[x][y]) {
				case Constants.MAP_START:
					System.out.printf(" s ");
				case Constants.MAP_BLOCK:
					System.out.printf("[ ]");
					break;
				case Constants.MAP_SPACE:
					System.out.printf("   ");
					break;
				case Constants.MAP_WIN:
					System.out.printf(" w ");
				}
			}
			System.out.printf("[|]");
			System.out.println("");
		}
		for(int x=0; x<Constants.MAP_WIDTH+2; x++) {
			System.out.printf("[-]");
		}

		System.out.printf("\n");
	}
}
