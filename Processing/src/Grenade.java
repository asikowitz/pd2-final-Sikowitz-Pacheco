import processing.core.*;

public class Grenade extends Weapon {
	boolean grounded;
	boolean blocked;
	int life;
	double a;

	public Grenade(int x, int y, int sx, int sy, PApplet p) {
		super(p);
		life = 150;
		this.x = x;
		this.y = y;
		speedX = sx;
		speedY = sy;
		blocked = false;
		type = 0;
		double a = 0.1;
	}

	public String toString() {
		return "Grenade";
	}

	public int act(int pX, int pY) {
		life--;

		if (y > 550)
			return 1;
		
		if (life <= 0) {
			explode();
			return 1;
		}
		else if (check(pX, pY)) {
			explode();
			return 0;
		}
		else if (checkWall()) {
			return 2;
		}

		a = a + 0.1;
		x = x + speedX;
		y = y + speedY;
		speedY = speedY + (int) a;
		if ((int) a >= 1)
			a = 0.1;
		return 2;
	}

	public void display() {
		p.stroke(0, 100, 0);
		p.fill(0, 100, 0);
		p.ellipse(x, y, 10, 15);
	}
}