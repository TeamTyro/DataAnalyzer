package com.teamtyro.etc;

public class Constants {
	public static final int ETH_WHITE 			= 	1;
	public static final int ETH_INDIANALASKAN	=	2;
	public static final int ETH_ASIAN			=	3;
	public static final int ETH_HISPANIC		=	4;
	public static final int ETH_ISLANDER		= 	5;
	public static final int ETH_BLACK			=	6;
	public static final int ETH_OTHER			=	7;
	public static final int ETH_NONE			=	8;
	
	public static final int AGE_0TO11			=	1;
	public static final int AGE_12TO17			=	2;
	public static final int AGE_18TO24			=	3;
	public static final int AGE_25TO34			=	4;
	public static final int AGE_35TO44			= 	5;
	public static final int AGE_45TO54			=	6;
	public static final int AGE_55TO64			=	7;
	public static final int AGE_65TO74			=	8;
	public static final int AGE_75PLUS			=	9;
	
	public static final int GEN_MALE			= 	1;
	public static final int GEN_FEMALE			=	2;
	
	public static final int ERROR				=	69;
	
	// Directional constants, returned by recActions[]
	public static final int DIR_RIGHT = 3;
	public static final int DIR_LEFT = 2;
	public static final int DIR_UP = 0;
	public static final int DIR_DOWN = 1;
	// Map space constants are returned by the map[] array
	public static final int MAP_START = 4;
	public static final int MAP_SPACE = 1;
	public static final int MAP_BLOCK = 2;
	public static final int MAP_WIN = 3;
	// Map property constants
	public static final int MAP_WIDTH = 16;
	public static final int MAP_HEIGHT = 16;
	public static final int BLOCK_SIZE = 32;
}
