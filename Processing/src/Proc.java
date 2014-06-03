import processing.core.*;

public class Proc extends PApplet {
	int px = 100;
	int py = 99;
	boolean valid;

	public void setup() {
	  size(300, 300);
	  background(255);
	  smooth();
	  line(98, 98, 102, 98);
	  loadPixels();
	}

	public void draw() {
	  stroke(0);
	  strokeWeight(4);
	  // Draw a line from previous mouse location to current mouse location.
	  if (mousePressed)
	    line(pmouseX, pmouseY, mouseX, mouseY);
	  noFill();
	  stroke(255, 0, 0);
	  line(px, py, px, py);
	}

	public void keyPressed() {
	  switch(key) {
	    case('a'):
	      move(true);
	      break;
	    case('d'):
	      move(false);
	      break;
	  }
	  loadPixels();
	  for (int i=0; i<width*height; i++)
		  if (pixels[i] == color(255, 0, 0) || pixels[i] > -70000)
			  pixels[i] = color(255);
	  updatePixels();
	}

	public void move(boolean left) {
	  if (left) {
	     valid = true;
	     for (int i=10; i<30; i++) {
	       if (get(px-6, py+i) == -16777216)
	         valid = false;
	     }
	     if (valid) {
	       for (int i=20; i>-20; i--) {
	         for (int j=0; j<3; j++) {
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
	     if (valid) {
	       for (int i=20; i>-20; i--) {
	         for (int j=0; j<3; j++) {
	            if (get(px+j, py+i) == -16777216) {
	               px = px+1;
	               py = py+i-3;
	            }
	         }
	       }
	     }
	  }
	}
	
	public static void main(String[] args) {
		PApplet.main(new String[] { "--present", "Proc" });
	}
}