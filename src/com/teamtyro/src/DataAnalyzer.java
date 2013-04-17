package com.teamtyro.src;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class DataAnalyzer {
	private static TestSubject subjects[];
	
	public static void main(String[] args) {
		System.out.printf("DataAnalyzer V 0.0.1\n");
		
		subjects = new TestSubject [10];
		parseTextyText("/home/xavi/Desktop/game_first_run.txt");
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
	    for(int j=0; j<10; j++) {
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
	
	private void render() {
		
	}
}
