import processing.core.*;

public class Weapon {
	int x, y;
	int a = -1;
	String type;
	int speedX, speedY, gravity;
	boolean midThrow;
	PApplet p;
	
	public Weapon(PApplet p) {
		this.p = p;
	}

	public boolean getMidThrow() {
		return midThrow;
	}

	public void setMidThrow(boolean x) {
		midThrow = x;
	}

	public void guide(int x1, int y1, int x2, int y2) {
		p.stroke(255, 0, 0);
		arrow(x1, y1, x2, y2);
	}

	public void arrow(int x1, int y1, int x2, int y2) {
		p.line(x1, y1, x2, y2);
		p.pushMatrix();
		p.translate(x2, y2);
		float a = p.atan2(x1 - x2, y2 - y1);
		p.rotate(a);
		p.line(0, 0, -10, -10);
		p.line(0, 0, 10, -10);
		p.popMatrix();
	}

	public void display() {
		p.stroke(255, 00, 0);
		p.fill(255, 0, 0);
		p.rect(x, y, 10, 10);
	}
	
	public void act() {}
	public String toString() {return "";}
}