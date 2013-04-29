package com.teamtyro.src;

import java.math.BigDecimal;
import java.util.Random;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import FileReading.Constants;
import FileReading.MazeMap;
import FileReading.ReadSolutions;

public class DataAnalyzer {
	
	//New Variables, specific for the data analyzing aspects of this program.//
	//public static int[][][][][][] tally = 		new int[2][2][2][2][4][4];		
	//public static double[][][][][][] percent = 	new double[2][2][2][2][4][4];	//Instead of holding the total people who turned x direction, it tells you what percent.		
	public static int decimalsToDisplay = 		10;
	private static ReadSolutions r;
	
	//Original COLORMAZEGAME variables//
	private static int[][] 		map;										// Universal map array [x left = 0][y, top = 0] Returns a constant for what is
	public static String 		mapFile = 	"map1.txt";		
	public static String		genetic = 	"geneticalgorithmsolutions.txt";
	public static String		human = 	"humansolutions.txt";
	public static String		neural = 	"neuralnetworksolutions.txt";
	public static int frameSpeed = 0;										//How  many miliseconds to wait between frames.
	public static int		 	pX;
	public static int 			pY;
	public static Random 		generator = new Random();
	
	
	
	
	
	public static void main(String[] args) {
		resetMap();
		printMaze(map);
		r = new ReadSolutions(mapFile, human, genetic, neural);
		begin();
	}
	
	public static void begin(){
		//setUpScreen();
		
		//getTally();
		
		//printGraphics();
		System.out.println("Genetic Algorithm solutions compared to Human Solutions:");
		printPercents(r.humanSolutions, r.geneticSolutions);
		
		System.out.println("\nNeural Network solutions compared to Human Solutions:");
		printPercents(r.humanSolutions, r.humanSolutions);
		
		//printPercents(genetic, neural);
	}
	
	
	
	private static void printPercents(String[] firstSet, String[] secondSet){
		//tally = r.getRawInputArray(firstSet);
		int[][][][][][] tallyOne = r.getRawInputArray(firstSet); 
		int[][][][][][] tallyTwo = r.getRawInputArray(secondSet);
		double[][][][][][] percentOne = getPercent(tallyOne);
		double[][][][][][] percentTwo = getPercent(tallyTwo);			//[2][2][2][2][4][4] [up][down[left][right][lastOutput][move]
		double totalPercent = 0;										//Gets each averaged percent of the database, then averages by how many databases there even are.
		double databasesUsed = 0;											//How many databases got used. (In case you are testing a single solution, which wont have a percent for every single database. In a large database, this should be equal to around 35.
		
		for(int in0 = 0; in0 <= 1; in0++){								//in0	block above
			for(int in1 = 0; in1 <= 1; in1++){							//in1	block below
				for(int in2 = 0; in2 <=1; in2++){						//in2	block left
					for(int in3 = 0; in3 <=1; in3++){					//in3	block right
						for(int in4 = 0; in4 <= 3; in4++){				//in4	last move (up,down,left, or right)
							double whatToDivideBy = 0;					//Is only a double to avoid issues when dividing another double by an int. Just makin sure stuff works:)
							double totalPercentTemporary = 0;
							
							for(int in5 = 0; in5 <= 3; in5++){
								
								if(percentOne[in0][in1][in2][in3][in4][in5] != 0 || percentTwo[in0][in1][in2][in3][in4][in5] != 0){	
									whatToDivideBy += 1;
									//System.out.println(whatToDivideBy);
									double total = percentOne[in0][in1][in2][in3][in4][in5]+percentTwo[in0][in1][in2][in3][in4][in5];	//Gets total
									System.out.println(percentOne[in0][in1][in2][in3][in4][in5]+" "+percentTwo[in0][in1][in2][in3][in4][in5]);
									System.out.println(tallyOne[in0][in1][in2][in3][in4][in5]+" "+tallyTwo[in0][in1][in2][in3][in4][in5]+"\n");
									totalPercentTemporary += percentTwo[in0][in1][in2][in3][in4][in5]/total;
								}

							}
							if(whatToDivideBy != 0){ 
								databasesUsed += 1;
								totalPercent += totalPercentTemporary/whatToDivideBy;
							}
							
							
						}
					}
				}
			}
		}
		System.out.println(totalPercent+" "+databasesUsed);
		System.out.println("Average: "+round(totalPercent/databasesUsed, decimalsToDisplay, BigDecimal.ROUND_HALF_UP));
/*		double allPercentTotals = 0;
		double allHighestPossiblePercentTotals = 0;
		for(int i = 0; i < secondSet.length; i++){						//Goes through each solution in the txt
			
			String test = secondSet[i];									//What string to test
			//System.out.println(secondSet[i]);								//Prints the solution
			double percentTotal = 0;											//Will store the added percent of humanity for each solution, so that we can find the average later.
			double highestPossiblePercentTotal = 0;								//Will store the added percent of humanity that each solution COULD POSSIBLY HAVE HAD so that I can find the average later.
			
			for(int j = 0; j < secondSet[i].length(); j++){				//Goes through each character of the solution	
			
				int[] sit = r.getSituation(test, j);
				percentTotal += percent[sit[0]][sit[1]][sit[2]][sit[3]][sit[4]][getNumericalOutput(test.charAt(j))];					//Adds the percent of choice from that move to the total.
				
				double highestPercent = -1;
				for(int k = 0; k <= 3; k++){									//Goes through each move for that situation, and gets the highest percent.
					if(percent[sit[0]][sit[1]][sit[2]][sit[3]][sit[4]][k] > highestPercent){	highestPercent = percent[sit[0]][sit[1]][sit[2]][sit[3]][sit[4]][k];} 
				}
				
				highestPossiblePercentTotal += highestPercent;
				//System.out.println(percent[sit[0]][sit[1]][sit[2]][sit[3]][sit[4]][getNumericalOutput(test.charAt(j))]+ " "+percentTotal);
			
			}
			
			//System.out.println("Percent for run: "+percentTotal/(test.length())+"     Possible percent for run: "+highestPossiblePercentTotal/(test.length()));						//Prints the percent of human'ness for that particular solutioon.
			allPercentTotals += (percentTotal/(test.length()));
			allHighestPossiblePercentTotals += (highestPossiblePercentTotal/(test.length()));
			
		}
		
		double finalAveragePercent = allPercentTotals/secondSet.length;
		double finalAveragePossiblePercent = allHighestPossiblePercentTotals/secondSet.length;
		System.out.println("Average: "+round(finalAveragePercent, decimalsToDisplay, BigDecimal.ROUND_HALF_UP)+"%     Average Possible: "+round(finalAveragePossiblePercent, decimalsToDisplay, BigDecimal.ROUND_HALF_UP)+"%     Average/Possible: "+round(finalAveragePercent/finalAveragePossiblePercent*100, decimalsToDisplay, BigDecimal.ROUND_HALF_UP)+"%");*/
	}
	
	private static void printGraphics(){										//Prints out all of the results from the data analysis.
		int solutionCounter = 0;
		for(int in0 = 0; in0 <= 1; in0++){								//in0	block above
			for(int in1 = 0; in1 <= 1; in1++){							//in1	block below
				for(int in2 = 0; in2 <=1; in2++){						//in2	block left
					for(int in3 = 0; in3 <=1; in3++){					//in3	block right
						for(int in4 = 0; in4 <= 3; in4++){				//in4	last move (up,down,left, or right)
							boolean actionPerformed = false;			//If there was an action, then actually draw out the situation.
							for(int in5 = 0; in5 <=3; in5++){			//Finds out if something was indeed performed.
								if(tally[in0][in1][in2][in3][in4][in5] != 0){
									actionPerformed = true;
								}
							}		
							if(actionPerformed){					//If an action was indeed performed, continue with the printing of graphics for that situation.
								System.out.println("//////////////////////////////////////////////////////////////");
								System.out.println("#"+solutionCounter+": "+in0+" "+in1+" "+in2+" "+in3+" "+in4);
								System.out.println("UP: "+percent[in0][in1][in2][in3][in4][0]+"%     DOWN: "+percent[in0][in1][in2][in3][in4][1]+"%     LEFT: "+percent[in0][in1][in2][in3][in4][2]+"%     RIGHT: "+percent[in0][in1][in2][in3][in4][3]+"%\n");
								
								if(in0 == 1){			//Block above
									System.out.println("                    "+tally[in0][in1][in2][in3][in4][0]);	//Take out
									System.out.println("		   [X]");		//Above
	
								}else{					//Space above
									System.out.println("                    "+tally[in0][in1][in2][in3][in4][0]);
									System.out.println("		   [ ]");		//Above
								}
								
								
								if(in2 == 1){			//Block Left
									System.out.printf("            "+tally[in0][in1][in2][in3][in4][2]);			//Take out
									System.out.printf("	[X]   ");		//Left	//System.out.printf("		[X]   ");		//Left
								}else{					//Space Left
									System.out.printf("            "+tally[in0][in1][in2][in3][in4][2]);
									System.out.printf("	[ ]   ");		//Left
								}
								
								
								if(in3 == 1){			//Block Right
									System.out.printf("[X]");		//Right
									System.out.printf("  "+tally[in0][in1][in2][in3][in4][3]+"\n");					//Take out
								}else{					//Space Right
									System.out.printf("[ ]");		//Right	
									System.out.printf("  "+tally[in0][in1][in2][in3][in4][3]+"\n");
								}
								
								
								if(in1 == 1){			//Block Below
									System.out.println("		   [X]");		//Below
									System.out.println("                    "+tally[in0][in1][in2][in3][in4][1]);	//Take out
								}else{					//Space Below
									System.out.println("		   [ ]");		//Below
									System.out.println("                    "+tally[in0][in1][in2][in3][in4][1]);
								}
								
								System.out.printf("Last Move: ");
								switch(in4){
								
								case 0:
									System.out.println("UP\n");
									break;
								case 1:
									System.out.println("DOWN\n");
									break;
								case 2:
									System.out.println("LEFT\n");
									break;
								case 3:
									System.out.println("RIGHT\n");
									break;
								}
							}
							solutionCounter++;
							
						}
					}
				}
			}
		}
		
		
		
		
		
		
	}
	
	public static double[][][][][][] getPercent(int[][][][][][] tally){											//Fills up the percent array with processed data from the tally array.
		double[][][][][][] percent = new double[2][2][2][2][4][4];
		
		for(int in0 = 0; in0 <= 1; in0++){								//in0	block above
			for(int in1 = 0; in1 <= 1; in1++){							//in1	block below
				for(int in2 = 0; in2 <=1; in2++){						//in2	block left
					for(int in3 = 0; in3 <=1; in3++){					//in3	block right
						for(int in4 = 0; in4 <= 3; in4++){				//in4	last move (up,down,left, or right)
							
							double total = 0;
							for(int in5 = 0; in5 <= 3; in5++){			//Finds the total tallies, in order to divide each possible solution's tally by total tallys.
								percent[in0][in1][in2][in3][in4][in5] = 0;
								total += tally[in0][in1][in2][in3][in4][in5];
							}
							if(total != 0){								//So that I don't divide by zero...
								for(int in5 = 0; in5 <= 3; in5++){		//Goes through each out, and sets it as the percent of the total. Cool simple code, me gusta! p.s. I am so tired. It is finals week, and I should not be coding. Well. Toodleoo! To the next line I go...
									//percent[in0][in1][in2][in3][in4][in5] = round((double) tally[in0][in1][in2][in3][in4][in5]/total*100,2,BigDecimal.ROUND_HALF_UP);
									percent[in0][in1][in2][in3][in4][in5] = (tally[in0][in1][in2][in3][in4][in5]/total)*100;
								}
							}
							
							
						}
					}
				}
			}
		}
		return percent;
	}
	
/*	public static void getTally(){												//Fills up the tally array with the correct amount of tallys for each situation.
		int counter = 0;					//For replaying purposes.
		resetMap();
		while(counter < r.solutions.length){
			//System.out.println(r.solutions[counter]);
			for(int i = 0; map[pX][pY] != Constants.MAP_WIN; i++){
				if(frameSpeed > 0){															//If framespeed is actually not zero, then waste time to display all the pretty visuals.
					GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);		// Clears screen and depth buffer	
					render();
					sleep(frameSpeed);
					Display.update();
				}
				int[] sol = getSituation(r.solutions[counter],i);
				tally[sol[0]][sol[1]][sol[2]][sol[3]][sol[4]][getNumericalOutput(r.solutions[counter].charAt(i))] += 1;					
				
				replayGame(r.solutions[counter],i);
				if(map[pX][pY] == Constants.MAP_BLOCK){									//If the move ran it into a block.
					System.out.println("BLOODY MURDER! INVALID SOLUTION!!!!!");
					sleep(10000);
				}
				
			}
			resetMap();
			counter += 1;
			
		}
	}*/
	
	public static int getNumericalOutput(char s){										//Returns 0 for up, 1 for down, 2 for left, 3 for right
		int numericalOutput = -1;
		if(s == 'u'){ numericalOutput = 0;}
		if(s == 'd'){ numericalOutput = 1;}
		if(s == 'l'){ numericalOutput = 2;}
		if(s == 'r'){ numericalOutput = 3;}
		return numericalOutput;
	}
	
	public static int[] getSituation(String solution, int move){				//Gets the inputs at the time that a particular move was performed, in the String solution.

		int[] situation = new int[5];	//Order: [0] = up, [1] = down, [2] = left, [3] = right, [4] = lastOutput. 0 = open block, 1 = filled block.

		
		if(pY + 1 < Constants.MAP_HEIGHT){			//If you're not at the bottom of the map.
			situation[1] = map[pX][pY+1];			//below you
		}else{ 
			situation[1] = Constants.MAP_BLOCK;	
		}	

		
		if(pY - 1 >= 0){								//If you're not at the top of the map.
			situation[0] = map[pX][pY-1];			//above you
			if(situation[0] == Constants.MAP_START){ 
				situation[0] = Constants.MAP_SPACE; 
			}
		}else{	
			situation[0] = Constants.MAP_BLOCK;
		}	

		
		if(pX + 1 < Constants.MAP_WIDTH){			//If you're not at the right edge of the map.
			situation[3] = map[pX+1][pY];			//right of you
		}else{	
			situation[3] = Constants.MAP_BLOCK;
		}	

		
		if(pX - 1 >= 0){								//If you're not at the left edge of the map.	
			situation[2] = map[pX-1][pY];			//left of you
			if(situation[2] == Constants.MAP_WIN){ 
				situation[2] = Constants.MAP_SPACE; 
			}
		}else{	
			situation[2] = Constants.MAP_BLOCK;
		}	



		if(move > 0){								//Finds the last move. Is recorded as: NO LAST MOVE = 0; 0 =u; 1/3=d; 2/3=l; 1=r
			situation[4] = solution.charAt(move-1);
			if(solution.charAt(move-1) == 'u'){	situation[4] = Constants.DIR_UP;}
			if(solution.charAt(move-1) == 'd'){	situation[4] = Constants.DIR_DOWN;}
			if(solution.charAt(move-1) == 'l'){	situation[4] = Constants.DIR_LEFT;}
			if(solution.charAt(move-1) == 'r'){	situation[4] = Constants.DIR_RIGHT;}
		}else{
			situation[4] = Constants.DIR_UP;
		}

		return situation;
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
	
	private static void sleep(int time){										//stops the frame for 'time' miliseconds. 
		
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

	public static double round(double unrounded, int precision, int roundingMode)
	{
	    BigDecimal bd = new BigDecimal(unrounded);
	    BigDecimal rounded = bd.setScale(precision, roundingMode);
	    return rounded.doubleValue();
	}
}
