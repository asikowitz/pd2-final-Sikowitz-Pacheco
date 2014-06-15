import processing.core.*;

public class Grenade extends Weapon {
	boolean grounded;
	boolean blocked;

	public Grenade(int x, int y, int sx, int sy, PApplet p) {
		super(p);
		this.x = x;
		this.y = y;
		speedX = sx;
		speedY = sy;
		blocked = false;
	}

	public String toString() {
		return "Grenade";
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

	public void act() {
		// for (int i=5; i<8; i++) {
		// if (p.pixels[(y-i)*p.width + x+sign] < -100000)
		// blocked = true;
		// }
		if (!blocked) {
			x = x + speedX;
			y = y + speedY;
			speedY = speedY - a;
		}
	}

	public void display() {
		p.stroke(0, 100, 0);
		p.fill(0, 100, 0);
		p.ellipse(x, y, 10, 15);
	}

	public void guide(int x1, int y1, int x2, int y2) {
		p.stroke(255, 0, 0);
		arrow(x1, y1, x2, y2);
	}

}