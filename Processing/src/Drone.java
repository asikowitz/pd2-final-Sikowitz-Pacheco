import processing.core.*;

public class Drone extends Weapon {
	public Drone(int x, int y, int sx, int sy, PApplet p) {
		super(p);
		this.x = x;
		this.y = y;
		speedX = sx;
		speedY = sy;
		type = 1;
	}

	public String toString() {
		return "Drone";
	}

	public int act(int pX, int pY) {
		if (check(pX, pY)) {
			explode();
			return 0;
		}
		else if (checkWall()) {
			return 1;
		}
		x = x + speedX;
		y = y + speedY;
		return 2;
	}

	public void display() {
		p.stroke(50);
		p.fill(50);
		p.ellipse(x, y, 25, 10);
		p.ellipse(x, y + 5, 5, 10);
		p.ellipse(x, y - 5, 5, 10);
	}
}