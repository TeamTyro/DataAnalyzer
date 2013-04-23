package com.teamtyro.src;

import com.teamtyro.etc.Constants;

public class TestSubject {
	private static int ethnicity, age, gender, playCount;
	private static String actions[];
	
	public TestSubject() {
		
	}
	
	public TestSubject(String sEthnicity, String sAge,
	String sGender, String sActions, int sPlayCount) {
		
		if(sEthnicity.equals("American Indian or Alaska Native")) {
			ethnicity =  Constants.ETH_INDIANALASKAN;
		} else if(sEthnicity.equals("Asian")) {
			ethnicity =  Constants.ETH_ASIAN;
		} else if(sEthnicity.equals("Black or African American")) {
			ethnicity =  Constants.ETH_BLACK;
		} else if(sEthnicity.equals("Hispanic or Latino")) {
			ethnicity =  Constants.ETH_HISPANIC;
		} else if(sEthnicity.equals("Native Hawaiian or Pacific Islander")) {
			ethnicity =  Constants.ETH_ISLANDER;
		} else if(sEthnicity.equals("White")) {
			ethnicity =  Constants.ETH_WHITE;
		} else if(sEthnicity.equals("Other")) {
			ethnicity =  Constants.ETH_OTHER;
		} else if(sEthnicity.equals("Prefer not to say")) {
			ethnicity =  Constants.ETH_NONE;
		} else {
			ethnicity =  Constants.ERROR;
		}
		
		if(sAge.equals("Under 11")) {
			age =  Constants.AGE_0TO11;
		} else if(sAge.equals("12-17")) {
			age =  Constants.AGE_12TO17;
		} else if(sAge.equals("18-24")) {
			age =  Constants.AGE_18TO24;
		} else if(sAge.equals("25-34")) {
			age =  Constants.AGE_25TO34;
		} else if(sAge.equals("35-44")) {
			age =  Constants.AGE_35TO44;
		} else if(sAge.equals("45-54")) {
			age =  Constants.AGE_45TO54;
		} else if(sAge.equals("55-64")) {
			age =  Constants.AGE_55TO64;
		} else if(sAge.equals("65-74")) {
			age =  Constants.AGE_65TO74;
		} else if(sAge.equals("75 years or older")) {
			age =  Constants.AGE_75PLUS;
		} else {
			age =  Constants.ERROR;
		}
		
		if(sGender.equals("Male")) {
			gender =  Constants.GEN_MALE;
		} else if(sGender.equals("Female")) {
			gender =  Constants.GEN_FEMALE;
		} else {
			gender =  Constants.ERROR;
		}
		
		playCount = sPlayCount;
		actions = new String[sPlayCount];
		for(int i=0; i<sActions.length(); i++) {
			actions[0] = sActions;
		}
	}
	
	public int getAction(int s_x, int s_play) {
		return actions[s_play].charAt(s_x);
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
	
	public String getActions(int playNumb) {
		return actions[playNumb];
	}
}
