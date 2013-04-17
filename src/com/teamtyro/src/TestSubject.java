package com.teamtyro.src;

import com.teamtyro.etc.Constants;

public class TestSubject {
	public final int ETH_WHITE 			= 	1;
	public final int ETH_INDIANALASKAN		=	2;
	public final int ETH_ASIAN				=	3;
	public final int ETH_HISPANIC			=	4;
	public final int ETH_ISLANDER			= 	5;
	public final int ETH_BLACK				=	6;
	public final int ETH_OTHER				=	7;
	public final int ETH_NONE				=	8;
	
	public final int AGE_0TO11				=	1;
	public final int AGE_12TO17			=	2;
	public final int AGE_18TO24			=	3;
	public final int AGE_25TO34			=	4;
	public final int AGE_35TO44			= 	5;
	public final int AGE_45TO54			=	6;
	public final int AGE_55TO64			=	7;
	public final int AGE_65TO74			=	8;
	public final int AGE_75PLUS			=	9;
	
	public final int GEN_MALE				= 	1;
	public final int GEN_FEMALE			=	2;
	
	public final int ERROR					=	69;
	
	private Constants con;
	private int ethnicity, age, gender, playCount;
	private int actions[][];
	public TestSubject() {
		
	}
	
	public TestSubject(String sEthnicity, String sAge,
	String sGender, String sActions, int sPlayCount) {
		
		if(sEthnicity.equals("American Indian or Alaska Native")) {
			ethnicity =  ETH_INDIANALASKAN;
		} else if(sEthnicity.equals("Asian")) {
			ethnicity =  ETH_ASIAN;
		} else if(sEthnicity.equals("Black or African American")) {
			ethnicity =  ETH_BLACK;
		} else if(sEthnicity.equals("Hispanic or Latino")) {
			ethnicity =  ETH_HISPANIC;
		} else if(sEthnicity.equals("Native Hawaiian or Pacific Islander")) {
			ethnicity =  ETH_ISLANDER;
		} else if(sEthnicity.equals("White")) {
			ethnicity =  ETH_WHITE;
		} else if(sEthnicity.equals("Other")) {
			ethnicity =  ETH_OTHER;
		} else if(sEthnicity.equals("Prefer not to say")) {
			ethnicity =  ETH_NONE;
		} else {
			//ethnicity =  ERROR;
		}
		
		if(sAge.equals("Under 11")) {
			age =  AGE_0TO11;
		} else if(sAge.equals("12-17")) {
			age =  AGE_12TO17;
		} else if(sAge.equals("18-24")) {
			age =  AGE_18TO24;
		} else if(sAge.equals("25-34")) {
			age =  AGE_25TO34;
		} else if(sAge.equals("35-44")) {
			age =  AGE_35TO44;
		} else if(sAge.equals("45-54")) {
			age =  AGE_45TO54;
		} else if(sAge.equals("55-64")) {
			age =  AGE_55TO64;
		} else if(sAge.equals("65-74")) {
			age =  AGE_65TO74;
		} else if(sAge.equals("75 years or older")) {
			age =  AGE_75PLUS;
		} else {
			age =  ERROR;
		}
		
		if(sGender.equals("Male")) {
			gender =  GEN_MALE;
		} else if(sGender.equals("Female")) {
			gender =  GEN_FEMALE;
		} else {
			gender =  ERROR;
		}
		
		playCount = sPlayCount;
		actions = new int[sPlayCount][sActions.length()];
		for(int i=0; i<sActions.length(); i++) {
			actions[0][i] = sActions.charAt(i);
		}
	}
	
	public int getGender() {
		return gender;
	}
	
	public int getEthnicity() {
		return ethnicity;
	}
	
	public int getAge() {
		return age;
	}
	
	public int[] getActions(int playNumb) {
		return actions[playNumb];
	}
}
