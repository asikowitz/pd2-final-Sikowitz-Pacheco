import processing.core.*;
import java.util.*;

public class Proc extends PApplet {
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private Player p;
	private int energy;
	
	//These are to solve processing's issue with multiple keys pressed simultaneously
	private int stillMoving;
	private int keysDown;
	private char prevKey;
	private char bypass; //Prevents glitches when a specific key is hit and let go twice while still holding down a or d

	public void setup() {
	  size(600, 600);
	  background(255);
	  noSmooth();
	  strokeWeight(8);
	  stroke(0);
	  textFont(loadFont("Font.vlw"));
	  walls.add(new Wall(290, 310, 310, 310, this));
	  p = new Player(300, 300, this);
	  energy = 300;
	}

	public void draw() {
		//Clear, redraw bottom, draw and write energy
		
		background(255);
		stroke(0);
		strokeWeight(8);
		smooth();
		line(0, 600, 600, 600);
		stroke(0, 0, 255);
		fill(0);
		text("Energy", 400, 35);
		line(400, 50, 400+energy/2, 50);
		noSmooth();
		
		if (energy < 300)
			energy++;
		
		// Draw a line from previous mouse location to current mouse location.
		if (mousePressed && (!between(pmouseX, mouseX, p.getX()) || !between(pmouseY, mouseY, p.getY()))) {
			int dist = (pmouseX-mouseX)*(pmouseX-mouseX) + (pmouseY-mouseY)*(pmouseY-mouseY);
			if (energy > dist/100) {
				walls.add(new Wall(pmouseX, pmouseY, mouseX, mouseY, this));
				energy = energy - dist/100;
			}
		}
		for (Wall w : walls)
			w.display();
		if (keyPressed) { //void keyPressed() is not called often enough
			if (key != prevKey || key == bypass) { 
				prevKey = key;
				bypass = '|';
				keysDown++;
			}
			switch(key) {
			case('a'):
				p.move(-2);
				stillMoving = -2;
				break;
			case('d'):
				p.move(2);
				stillMoving = 2;
				break;
			case('w'):
				if (stillMoving != 0) {
					p.move(stillMoving);
					if (keysDown == 1)
						bypass = key;
				}
				if(p.jump() && energy > 30)
					energy = energy - 30;
				break;
			default:
				if (stillMoving != 0) {
					p.move(stillMoving);
					if (keysDown == 1)
						bypass = key;
				}
				break;
			}
		}
		else if (keysDown == 0)
			stillMoving = 0;
		else if (stillMoving != 0) {
			p.move(stillMoving);
			if (keysDown == 1)
				bypass = key;
		}
		
		p.run();
	}
	
	public void keyReleased() {
		keysDown--;
		if (keysDown <= 0) {
			keysDown = 0;
			stillMoving = 0;
		}
	}
	
	private boolean between(int a, int b, int v) {
		return (a < v+7 && b > v-7) || (a > v-7 && b < v+7);
	}

	public static void main(String[] args) {
		PApplet.main(new String[] { "--present", "Proc" });
	}
}