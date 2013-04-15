package com.teamtyro.src;

public class TestSubject {
	String name
	int ethnicity
	int actions[];
	public TestSubject() {
		
	}
	
	public TestSubject(int[] a) {
		actions = new int[a.length];
		for(int i=0; i<a.length; i++) {
			a[i] = actions[i];
		}
	}
}
