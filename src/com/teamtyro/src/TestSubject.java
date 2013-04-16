package com.teamtyro.src;

import com.teamtyro.etc.Constants;

public class TestSubject {
	Constants con;
	String name;
	int ethnicity, age, gender, playCount;
	int actions[][];
	public TestSubject() {
		
	}
	
	public TestSubject(String sName, String sEthnicity, String sAge,
	String sGender, String a, int sPlayCount) {
		name = sName;
		
		if(sEthnicity.equals("American Indian or Alaska Native")) {
			ethnicity = con.ETH_INDIANALASKAN;
		} else if(sEthnicity.equals("Asian")) {
			ethnicity = con.ETH_ASIAN;
		} else if(sEthnicity.equals("Black or African American")) {
			ethnicity = con.ETH_BLACK;
		} else if(sEthnicity.equals("Hispanic or Latino")) {
			ethnicity = con.ETH_HISPANIC;
		} else if(sEthnicity.equals("Native Hawaiian or Pacific Islander")) {
			ethnicity = con.ETH_ISLANDER;
		} else if(sEthnicity.equals("White")) {
			ethnicity = con.ETH_WHITE;
		} else if(sEthnicity.equals("Other")) {
			ethnicity = con.ETH_OTHER;
		} else if(sEthnicity.equals("Prefer not to say")) {
			ethnicity = con.ETH_NONE;
		} else {
			ethnicity = con.ERROR;
		}
		
		if(sAge.equals("Under 11")) {
			age = con.AGE_0TO11;
		} else if(sAge.equals("12-17")) {
			age = con.AGE_12TO17;
		} else if(sAge.equals("18-24")) {
			age = con.AGE_18TO24;
		} else if(sAge.equals("25-34")) {
			age = con.AGE_25TO34;
		} else if(sAge.equals("35-44")) {
			age = con.AGE_35TO44;
		} else if(sAge.equals("45-54")) {
			age = con.AGE_45TO54;
		} else if(sAge.equals("55-64")) {
			age = con.AGE_55TO64;
		} else if(sAge.equals("65-74")) {
			age = con.AGE_65TO74;
		} else if(sAge.equals("75 years or older")) {
			age = con.AGE_75PLUS;
		} else {
			age = con.ERROR;
		}
		
		if(sGender.equals("Male")) {
			gender = con.GEN_MALE;
		} else if(sGender.equals("Female")) {
			gender = con.GEN_FEMALE;
		} else {
			gender = con.ERROR;
		}
		
		playCount = sPlayCount;
		actions = new int[sPlayCount][a.length()];
		for(int i=0; i<a.length(); i++) {
			actions[0][i] = a.charAt(i);
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
	
	public String getName() {
		return name;
	}
	
	public int[] getActions(int playNumb) {
		return actions[playNumb];
	}
}
