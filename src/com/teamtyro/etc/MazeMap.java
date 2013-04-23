package com.teamtyro.etc;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import com.teamtyro.src.TestSubject;

public class MazeMap {
	private static int map [] [];
	private static int dMap[][];
	private static int sX, sY;
	
	public MazeMap() {
		map = new int [Constants.MAP_WIDTH][Constants.MAP_HEIGHT];
		dMap = new int [Constants.MAP_WIDTH][Constants.MAP_HEIGHT];
	}

	public int getSpace(int s_x, int s_y) {
		return map[s_x][s_y];
	}
	
	public int getDensity(int s_x, int s_y) {
		return dMap[s_x][s_y];
	}

	public void loadConstMap(String sMap) {
		for(int y=0; y<Constants.MAP_HEIGHT; y++) {
			for(int x=0; x<Constants.MAP_WIDTH; x++) {
				switch(sMap.charAt(x+(y*Constants.MAP_WIDTH))) {
				case 'b':
					map[x][y] = Constants.MAP_BLOCK;
					break;
				case 'c':
					map[x][y] = Constants.MAP_SPACE;
					break;
				case 's':
					map[x][y] = Constants.MAP_START;
					sX = x;
					sY = y;
					break;
				case 'w':
					map[x][y] = Constants.MAP_WIN;
					break;
				}
			}
		}
	}
	
	public void loadDensity(TestSubject subject) {
		int x = sX;
		int y = sY;
		int inc = 0;
		
		
		String actions = subject.getActions(0);
		for(int i=0; i<actions.length(); i++) {
			switch(actions.charAt(i)) {
			case 'l':
				x--;
				break;
			case 'r':
				x++;
				break;
			case 'u':
				y--;
				break;
			case 'd':
				y++;
				break;
			}
			
			if(map[x][y] == Constants.MAP_BLOCK) {
				System.out.printf("OH GOD OH NO! NO PLEASE GOD NO GOD PLEASE NO!\n");
			}
			dMap[x][y]++;
		}
	}

	public boolean loadMap(URL s_fileURL) {
		boolean success = false;
		try {			
			s_fileURL.openConnection();
			InputStream reader = s_fileURL.openStream();

			int x = 0;
			int y = 0;

			int section = 0;
			while ((section = reader.read()) != 0 && y < Constants.MAP_HEIGHT) {
				switch(section) {
				case 'b':
					map[x][y] = Constants.MAP_BLOCK;
					break;
				case 'c':
					map[x][y] = Constants.MAP_SPACE;
					break;
				case 's':
					map[x][y] = Constants.MAP_START;
					break;
				case 'w':
					map[x][y] = Constants.MAP_WIN;
					break;
				}
				if(x < Constants.MAP_WIDTH-1) {
					x++;
				} else {
					x=0;
					y++;
				}
			}

			success = true;
		} catch(IOException ex) {
			System.out.printf("ERROR: Couldn't load map\n");
			success = false;
		}

		return success;
	}
}