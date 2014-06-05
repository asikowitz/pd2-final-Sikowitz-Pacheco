import processing.core.*;
import java.util.*;
//import java.io.*;

public class Proc extends PApplet {
	private int px = 300;
	private int py = 300;
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private Player p;

	public void setup() {
	  size(600, 600);
	  background(255);
	  noSmooth();
	  p = new Player(300, 300, this);
	}

	public void draw() {
		background(255);
		stroke(0);
		strokeWeight(8);
		// Draw a line from previous mouse location to current mouse location.
		if (mousePressed && (!between(pmouseX, mouseX, p.getX()) || !between(pmouseY, mouseY, p.getY())))
			walls.add(new Wall(pmouseX, pmouseY, mouseX, mouseY, this));
		for (Wall w : walls)
			w.display();
		if (keyPressed) { //void keyPressed() is not called often enough
			  switch(key) {
			    case('a'):
			      p.move(-2);
			      break;
			    case('d'):
			      p.move(2);
			      break;
			  }
		}
		/*loadPixels();
		for (int i=0; i<width*height; i++)
			if (pixels[i] < -100000)
				pixels[i] = -16777216;
		updatePixels();*/
		p.run();
		//noFill();
		//stroke(255, 0, 0);
		//line(px, py, px, py);
	}
	
	/*public void keyPressed() {
	  switch(key) {
	    case('a'):
	      p.move(-5);
	      break;
	    case('d'):
	      p.move(5);
	      break;
	  }
	  /*loadPixels();
	  for (int i=0; i<width*height; i++)
		  if (pixels[i] != -16777216)
			  pixels[i] = color(255);
	  updatePixels();*/
	//}
	
	private boolean between(int a, int b, int v) {
		return (a < v+10 && b > v-10) || (a > v-10 && b < v+10);
	}

	/*public void move(boolean left) {
	  if (left) {
	     valid = true;
	     for (int i=10; i<30; i++) {
	       if (get(px-6, py+i) == -16777216)
	         valid = false;
	     }
	     if (true) {
	       for (int i=10; i>-10; i--) {
	         for (int j=1; j>=-1; j--) {
	            if (get(px-j, py+i) == -16777216) {
	               px = px-1;
	               py = py+i-3;
	            }
	         }
	       }
	     }
	  }
	  else {
	     valid = true;
	     for (int i=10; i<30; i++) {
	       if (get(px+1, py+i) == -16777216)
	         valid = false;
	     }
	     if (true) {
	       for (int i=10; i>-10; i--) {
	         for (int j=1; j>=-1; j--) {
	            if (get(px+j, py+i) == -16777216) {
	               px = px+1;
	               py = py+i-3;
	            }
	         }
	       }
	     }
	  }
	}*/
	
	public static void main(String[] args) {
		PApplet.main(new String[] { "--present", "Proc" });
	}
}