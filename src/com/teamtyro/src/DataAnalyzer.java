package com.teamtyro.src;

import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import FileReading.Constants;
import FileReading.MazeMap;
import FileReading.ReadSolutions;

public class DataAnalyzer {
	
	
	public static String 		mapFile = "map1.txt";
	
	private static int[][] 		map;							// Universal map array [x left = 0][y, top = 0] Returns a constant for what is
	private static int[][]		tally = new int[64][4];	//For each situation [0-63] then show us the tally for people who went up [0], down[1], left[2], right[3].
	public static int		 	pX;
	public static int 			pY;
	
	public static ReadSolutions	r;
	public static Random 		generator = new Random();
	
	
	
	
	
	public static void main(String[] args) {
		resetMap();
		printMaze(map);
		r = new ReadSolutions(mapFile);
		r.getRawInputArray();
		begin();
	}
	
	public static void begin(){
		setUpScreen();
		
		
		
		
		
		int counter = 0;					//For replaying purposes.
		resetMap();
		while(counter < solution.length()){
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	// Clears screen and depth buffer	
			render();

			replayGame(solution,counter);
			sleep(frameSpeed);

			Display.update();
			counter += 1;
		}
		sleep(1000);
		/*for(int s = 0; s < r.solutions.length; s++){	//Prints out all of the solutions
			System.out.println(r.solutions[s]);
		}
		
		//Possible solutions: in0:up; in1:down; in2:left; in3:right; in4: lastOutput.
		//in0: 0,1;	in1: 0,1;	in2: 0,1;	in3: 0,1;	in4: 0,1,2,3;	
		int solutionCounter = 0;
		for(int in0 = 0; in0 <= 1; in0++){								//in0	block above
			for(int in1 = 0; in1 <= 1; in1++){							//in1	block below
				for(int in2 = 0; in2 <=1; in2++){						//in2	block left
					for(int in3 = 0; in3 <=1; in3++){					//in3	block right
						for(int in4 = 0; in4 <= 3; in4++){				//in4	last move (up,down,left, or right)
							System.out.println("#"+solutionCounter+": "+in0+" "+in1+" "+in2+" "+in3+" "+in4);
							solutionCounter++;									//Keeps track of what solution key is being printed. Aesthetic only.
							
							if(r.tallyInputs[in0][in1][in2][in3][in4][0] != 0){	
								System.out.println("		Action: UP 		"+r.tallyInputs[in0][in1][in2][in3][in4][0]);
								tally[solutionCounter][0] = r.tallyInputs[in0][in1][in2][in3][in4][0];
							}
							if(r.tallyInputs[in0][in1][in2][in3][in4][1] != 0){	
								System.out.println("		Action: DOWN 	"+r.tallyInputs[in0][in1][in2][in3][in4][1]);
								tally[solutionCounter][0] = r.tallyInputs[in0][in1][in2][in3][in4][1];
							}
							if(r.tallyInputs[in0][in1][in2][in3][in4][2] != 0){	
								System.out.println("		Action: LEFT 	"+r.tallyInputs[in0][in1][in2][in3][in4][2]);
								tally[solutionCounter][0] = r.tallyInputs[in0][in1][in2][in3][in4][2];
							}
							
							if(r.tallyInputs[in0][in1][in2][in3][in4][3] != 0){	
								System.out.println("		Action: RIGHT	"+r.tallyInputs[in0][in1][in2][in3][in4][3]);
								tally[solutionCounter][0] = r.tallyInputs[in0][in1][in2][in3][in4][3];
							}
							
						}
					}
				}
			}
		}
*/
		printGraphics();
		
		
		
	}
	
	
	
	
	
	
	private static void printGraphics(){
		int solutionCounter = 0;
		for(int in0 = 0; in0 <= 1; in0++){								//in0	block above
			for(int in1 = 0; in1 <= 1; in1++){							//in1	block below
				for(int in2 = 0; in2 <=1; in2++){						//in2	block left
					for(int in3 = 0; in3 <=1; in3++){					//in3	block right
						for(int in4 = 0; in4 <= 3; in4++){				//in4	last move (up,down,left, or right)
							boolean actionPerformed = false;			//If there was an action, then actually draw out the situation.
							for(int in5 = 0; in5 <=3; in5++){			//Finds out if something was indeed performed.
								if(r.tallyInputs[in0][in1][in2][in3][in4][in5] != 0){
									actionPerformed = true;
								}
							}		
							if(actionPerformed){					//If an action was indeed performed, continue with the printing of graphics for that situation.
								System.out.println("//////////////////////////////////////////////////////////////");
								System.out.println("#"+solutionCounter+": "+in0+" "+in1+" "+in2+" "+in3+" "+in4);
								
								if(in0 == 1){			//Block above
									System.out.println("                    "+r.tallyInputs[in0][in1][in2][in3][in4][0]);	//Take out
									System.out.println("		   [X]");		//Above
	
								}else{					//Space above
									System.out.println("                    "+r.tallyInputs[in0][in1][in2][in3][in4][0]);
									System.out.println("		   [ ]");		//Above
								}
								
								
								if(in2 == 1){			//Block Left
									System.out.printf("            "+r.tallyInputs[in0][in1][in2][in3][in4][2]);			//Take out
									System.out.printf("	[X]   ");		//Left	//System.out.printf("		[X]   ");		//Left
								}else{					//Space Left
									System.out.printf("            "+r.tallyInputs[in0][in1][in2][in3][in4][2]);
									System.out.printf("	[ ]   ");		//Left
								}
								
								
								if(in3 == 1){			//Block Right
									System.out.printf("[X]");		//Right
									System.out.printf("  "+r.tallyInputs[in0][in1][in2][in3][in4][3]+"\n");					//Take out
								}else{					//Space Right
									System.out.printf("[ ]");		//Right	
									System.out.printf("  "+r.tallyInputs[in0][in1][in2][in3][in4][3]+"\n");
								}
								
								
								if(in1 == 1){			//Block Below
									System.out.println("		   [X]");		//Below
									System.out.println("                    "+r.tallyInputs[in0][in1][in2][in3][in4][1]);	//Take out
								}else{					//Space Below
									System.out.println("		   [ ]");		//Below
									System.out.println("                    "+r.tallyInputs[in0][in1][in2][in3][in4][1]);
								}
							
								
							}
							solutionCounter++;
							
						}
					}
				}
			}
		}
		
		
		
		
		
		
	}
	
	private static void resetMap(){
		map = new int [Constants.MAP_WIDTH][Constants.MAP_HEIGHT];		//sets array to map size
		//mapCount = new int[Constants.MAP_WIDTH][Constants.MAP_HEIGHT];	//sets array to map size
		//moveCount = 0;
		//ranIntoWall = false;
		MazeMap maze = new MazeMap();//loads map from text file
		maze.loadMap(mapFile);
		
		for(int x=0; x<Constants.MAP_WIDTH; x++) {
			for(int y=0; y<Constants.MAP_HEIGHT; y++) {
				map[x][y] = maze.getSpace(x,y);
				//mapCount[x][y] = 0;
				if(map[x][y] == Constants.MAP_START) {
					pX = x;
					pY = y;
				}
			}
		}	
	}
	
	private static void sleep(int time){			//stops the frame for 'time' miliseconds. 
		
		try {			//slows down the thread
			Thread.sleep(time);
		} catch(InterruptedException ex) {
			Thread.currentThread().interrupt();
		}
		
	}

	private static void replayGame(String mapSolution, int currAction) {
		switch(mapSolution.charAt(currAction)) {
		case 'd':
			pY++;
			break;
		case 'u':
			pY--;
			break;
		case 'r':
			pX++;
			break;
		case 'l':
			pX--;
			break;
		}
	}

	private static void setUpScreen(){
		try {
			Display.setDisplayMode(new DisplayMode(600,600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(0);
		}
		
		// Init OpenGLff
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		GL11.glOrtho(-300, 300, -300, 300, 1, -1);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);
		
	}

	private static void render() {
		int x, y;	// Bottom left corner coordinates (for readability)
		
		// Left box
		x = -300;
		y = -100;
		setColor(pX-1, pY, map);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x    ,y    );
			GL11.glVertex2f(x+200,y  +0);
			GL11.glVertex2f(x+200,y+200);
			GL11.glVertex2f(x  +0,y+200);
		GL11.glEnd();
		
		// Right box
		x = 100;
		y = -100;
		setColor(pX+1, pY, map);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x    ,y    );
			GL11.glVertex2f(x+200,y  +0);
			GL11.glVertex2f(x+200,y+200);
			GL11.glVertex2f(x  +0,y+200);
		GL11.glEnd();
		
		
		// Up box
		x = -100;
		y = 100;
		setColor(pX, pY-1, map);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x    ,y    );
			GL11.glVertex2f(x+200,y  +0);
			GL11.glVertex2f(x+200,y+200);
			GL11.glVertex2f(x  +0,y+200);
		GL11.glEnd();
		
		// Down box
		x = -100;
		y = -300;
		setColor(pX, pY+1, map);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x    ,y    );
			GL11.glVertex2f(x+200,y  +0);
			GL11.glVertex2f(x+200,y+200);
			GL11.glVertex2f(x  +0,y+200);
		GL11.glEnd();
		
		// Top-Left box
		x = -300;
		y = 100;
		setColor(pX-1, pY-1, map);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x    ,y    );
			GL11.glVertex2f(x+200,y  +0);
			GL11.glVertex2f(x+200,y+200);
			GL11.glVertex2f(x  +0,y+200);
		GL11.glEnd();
		
		// Top-Right box
		x = 100;
		y = 100;
		setColor(pX+1, pY-1, map);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x    ,y    );
			GL11.glVertex2f(x+200,y  +0);
			GL11.glVertex2f(x+200,y+200);
			GL11.glVertex2f(x  +0,y+200);
		GL11.glEnd();
		
		// Bottom-Left box
		x = -300;
		y = -300;
		setColor(pX-1, pY+1, map);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x    ,y    );
			GL11.glVertex2f(x+200,y  +0);
			GL11.glVertex2f(x+200,y+200);
			GL11.glVertex2f(x  +0,y+200);
		GL11.glEnd();
		
		// Bottom-Right box
		x = 100;
		y = -300;
		setColor(pX+1, pY+1, map);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x    ,y    );
			GL11.glVertex2f(x+200,y  +0);
			GL11.glVertex2f(x+200,y+200);
			GL11.glVertex2f(x  +0,y+200);
		GL11.glEnd();
		
		// Center box
		x = -100;
		y = -100;
		setColor(pX, pY, map);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x    ,y    );
			GL11.glVertex2f(x+200,y  +0);
			GL11.glVertex2f(x+200,y+200);
			GL11.glVertex2f(x  +0,y+200);
		GL11.glEnd();
		
		// Player
		x = -50;
		y = -50;
		GL11.glColor3f(1,1,1);
		GL11.glBegin(GL11.GL_QUADS);
			GL11.glVertex2f(x    ,y    );
			GL11.glVertex2f(x+100,y  +0);
			GL11.glVertex2f(x+100,y+100);
			GL11.glVertex2f(x  +0,y+100);
		GL11.glEnd();
	}

	private static void setColor(int x, int y, int [][] tmap) {
		if(x<0 || y<0 || x>Constants.MAP_WIDTH-1 || y>Constants.MAP_HEIGHT-1) {
			GL11.glColor3f(1,0,0);
			return;
		}
		
		switch(tmap[x][y]) {
		case Constants.MAP_BLOCK:
			GL11.glColor3f(1,0,0);
			break;
		case Constants.MAP_SPACE:
			GL11.glColor3f(0,0,1);
			break;
		case Constants.MAP_WIN:
			GL11.glColor3f(0,1,0);
			break;
		case Constants.MAP_START:
			GL11.glColor3f(1,1,0);
			break;
		}
	}

	private static int[][] makeMaze() {
		int [][] out = new int [Constants.MAP_WIDTH][Constants.MAP_HEIGHT];
		for(int x=0; x<Constants.MAP_WIDTH; x++) {
			for(int y=0; y<Constants.MAP_HEIGHT; y++) {
				out[x][y] = Constants.MAP_BLOCK;
			}
		}
		
		int x = Constants.MAP_WIDTH/2;
		int y = 0;
		out[x][y] = Constants.MAP_SPACE;
		int lastDir = -1;
		for(int i=0; i<20; i++) {
			int dir = generator.nextInt(4);
			int len = generator.nextInt(4);
			while( (dir==0 && lastDir==3) || (dir==1 && lastDir == 2) || (dir==2 && lastDir==1) || (dir==3 && lastDir==0) ) {
				dir = generator.nextInt(4);
			}
			switch (dir) {
			case 0:		//Go down
				for(int j=0; j<len; j++) {
					if(y < Constants.MAP_WIDTH-1) {
						y+=1;
						out[x][y] = Constants.MAP_SPACE;
					}
				}
				break;
			case 1:		//Go right
				for(int j=0; j<len; j++) {
					if(x < Constants.MAP_HEIGHT-1) {
						x+=1;
						out[x][y] = Constants.MAP_SPACE;
					}
				}
				break;
			case 2:		//Go left
				for(int j=0; j<len; j++) {
					if(x > 0) {
						x-=1;
						out[x][y] = Constants.MAP_SPACE;
					}
				}
				break;
			case 3:		//Go up
				for(int j=0; j<len; j++) {
					if(y>0) {
						y-=1;
						out[x][y] = Constants.MAP_SPACE;
					}
				}
				break;
			default:
				System.out.printf("Error: Unexpected random value in map gen. %d\n", dir);
			}
			lastDir = dir;
		}
		
		out[x][y] = Constants.MAP_WIN;
		
		return out;
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
