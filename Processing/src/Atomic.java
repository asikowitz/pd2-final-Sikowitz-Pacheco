import processing.core.*;

public class Atomic extends Weapon {
	public Atomic(int x, int y, int sx, int sy, PApplet p) {
		super(p);
		this.x = x;
		this.y = y;
		speedX = sx;
		speedY = sy;
		type = 2;
	}

	public String toString() {
		return "Atomic";
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
		p.ellipse(x, y, 20, 30);
		p.rect(x - 10, y - 25, 20, 10);
	}
}