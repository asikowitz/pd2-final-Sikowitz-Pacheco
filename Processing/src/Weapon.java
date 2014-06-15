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
		midThrow = true;
	}

	public boolean getMidThrow() {
		return midThrow;
	}

	public void setMidThrow(boolean x) {
		midThrow = x;
	}

	public void display() {
		p.stroke(255, 00, 0);
		p.fill(255, 0, 0);
		p.rect(x, y, 10, 10);
	}

	public boolean act() {
		return false;
	}
	public String toString() {return "";}
	public void explode(){}
}