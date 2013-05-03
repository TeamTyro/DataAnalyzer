package com.teamtyro.src;

import org.lwjgl.opengl.GL11;

public class DrawText {
	String text;
	double x, y;
	
	public DrawText() {
		
	}
	
	public DrawText(String s_text) {
		text = s_text;
		x=y=0;
	}
	
	public void draw() {
		GL11.glColor3f(1, 1, 1);
		for(int i=0; i<text.length(); i++) {
			switch(text.charAt(i)) {
			case '0':
				GL11.glBegin(GL11.GL_LINE_LOOP);
					GL11.glVertex2d(x+0.0, y+0.0);
					GL11.glVertex2d(x+0.5, y+0.0);
					GL11.glVertex2d(x+0.5, y+1.0);
					GL11.glVertex2d(x+0.0, y+1.0);
				GL11.glEnd();
				break;
			case '1':
				GL11.glBegin(GL11.GL_LINE_STRIP);
					GL11.glVertex2d(x+0.0 , y+0.0 );
					GL11.glVertex2d(x+0.5 , y+0.0 );
					GL11.glVertex2d(x+0.25, y+0.0 );
					GL11.glVertex2d(x+0.25, y+1.0 );
					GL11.glVertex2d(x+0.12, y+0.88);
				GL11.glEnd();
				break;
			case '2':
				GL11.glBegin(GL11.GL_LINE_STRIP);
					GL11.glVertex2d(x+0.5, y+0.0);
					GL11.glVertex2d(x+0.0, y+0.0);
					GL11.glVertex2d(x+0.0, y+0.5);
					GL11.glVertex2d(x+0.5, y+0.5);
					GL11.glVertex2d(x+0.5, y+1.0);
					GL11.glVertex2d(x+0.0, y+1.0);
				GL11.glEnd();
				break;
			case '3':
				GL11.glBegin(GL11.GL_LINE_STRIP);
					GL11.glVertex2d(x+0.0, y+0.0);
					GL11.glVertex2d(x+0.5, y+0.0);
					GL11.glVertex2d(x+0.5, y+0.5);
					GL11.glVertex2d(x+0.0, y+0.5);
					GL11.glVertex2d(x+0.5, y+0.5);
					GL11.glVertex2d(x+0.5, y+1.0);
					GL11.glVertex2d(x+0.0, y+1.0);
				GL11.glEnd();
				break;
			case '4':
				GL11.glBegin(GL11.GL_LINE_STRIP);
					GL11.glVertex2d(x+0.5, y+0.0);
					GL11.glVertex2d(x+0.5, y+0.5);
					GL11.glVertex2d(x+0.0, y+0.5);
					GL11.glVertex2d(x+0.0, y+1.0);
					GL11.glVertex2d(x+0.0, y+0.5);
					GL11.glVertex2d(x+0.5, y+0.5);
					GL11.glVertex2d(x+0.5, y+1.0);
				GL11.glEnd();
				break;
			case '5':
				GL11.glBegin(GL11.GL_LINE_STRIP);
					GL11.glVertex2d(x+0.0, y+0.0);
					GL11.glVertex2d(x+0.5, y+0.0);
					GL11.glVertex2d(x+0.5, y+0.5);
					GL11.glVertex2d(x+0.0, y+0.5);
					GL11.glVertex2d(x+0.0, y+1.0);
					GL11.glVertex2d(x+0.5, y+1.0);
				GL11.glEnd();
				break;
			case '6':
				GL11.glBegin(GL11.GL_LINE_STRIP);
					GL11.glVertex2d(x+0.5, y+1.0);
					GL11.glVertex2d(x+0.0, y+1.0);
					GL11.glVertex2d(x+0.0, y+0.5);
					GL11.glVertex2d(x+0.0, y+0.0);
					GL11.glVertex2d(x+0.5, y+0.0);
					GL11.glVertex2d(x+0.5, y+0.5);
					GL11.glVertex2d(x+0.0, y+0.5);
				GL11.glEnd();
				break;
			case '7':
				GL11.glBegin(GL11.GL_LINE_STRIP);
					GL11.glVertex2d(x+0.25, y+0.0);
					GL11.glVertex2d(x+0.25, y+0.5);
					GL11.glVertex2d(x+0.5, y+1.0);
					GL11.glVertex2d(x+0.0, y+1.0);
				GL11.glEnd();
				break;
			case '8':
				GL11.glBegin(GL11.GL_LINE_LOOP);
					GL11.glVertex2d(x+0.0, y+0.0);
					GL11.glVertex2d(x+0.5, y+0.0);
					GL11.glVertex2d(x+0.5, y+0.5);
					GL11.glVertex2d(x+0.0, y+0.5);
				GL11.glEnd();
				GL11.glBegin(GL11.GL_LINE_LOOP);
					GL11.glVertex2d(x+0.0, y+0.5);
					GL11.glVertex2d(x+0.5, y+0.5);
					GL11.glVertex2d(x+0.5, y+1.0);
					GL11.glVertex2d(x+0.0, y+1.0);
				GL11.glEnd();
				break;
			case '9':
				GL11.glBegin(GL11.GL_LINE_STRIP);
					GL11.glVertex2d(x+0.5, y+0.0);
					GL11.glVertex2d(x+0.5, y+0.5);
					GL11.glVertex2d(x+0.5, y+1.0);
					GL11.glVertex2d(x+0.0, y+1.0);
					GL11.glVertex2d(x+0.0, y+0.5);
					GL11.glVertex2d(x+0.5, y+0.5);
				GL11.glEnd();
				break;
			case 'r':
				GL11.glBegin(GL11.GL_LINE_STRIP);
					GL11.glVertex2d(x+0.0, y+0.5);
					GL11.glVertex2d(x+0.5, y+0.5);
				GL11.glEnd();
				GL11.glBegin(GL11.GL_LINE_STRIP);
					GL11.glVertex2d(x+0.3, y+0.7);
					GL11.glVertex2d(x+0.5, y+0.5);
					GL11.glVertex2d(x+0.3, y+0.3);
				GL11.glEnd();
				break;
			case 'l':
				GL11.glBegin(GL11.GL_LINE_STRIP);
					GL11.glVertex2d(x+0.0, y+0.5);
					GL11.glVertex2d(x+0.5, y+0.5);
				GL11.glEnd();
				GL11.glBegin(GL11.GL_LINE_STRIP);
					GL11.glVertex2d(x+0.2, y+0.7);
					GL11.glVertex2d(x+0.0, y+0.5);
					GL11.glVertex2d(x+0.2, y+0.3);
				GL11.glEnd();
				break;
			case 'u':
				GL11.glBegin(GL11.GL_LINE_STRIP);
					GL11.glVertex2d(x+0.25, y+0.0);
					GL11.glVertex2d(x+0.25, y+1.0);
				GL11.glEnd();
				GL11.glBegin(GL11.GL_LINE_STRIP);
					GL11.glVertex2d(x+0.1, y+0.7);
					GL11.glVertex2d(x+0.25, y+1.0);
					GL11.glVertex2d(x+0.4, y+0.7);
				GL11.glEnd();
				break;
			case 'd':
				GL11.glBegin(GL11.GL_LINE_STRIP);
					GL11.glVertex2d(x+0.25, y+0.0);
					GL11.glVertex2d(x+0.25, y+1.0);
				GL11.glEnd();
				GL11.glBegin(GL11.GL_LINE_STRIP);
					GL11.glVertex2d(x+0.1, y+0.3);
					GL11.glVertex2d(x+0.25, y+0.0);
					GL11.glVertex2d(x+0.4, y+0.3);
				GL11.glEnd();
			}
			
			x+=0.70;
		}
		
		x = 0;
	}
}
 