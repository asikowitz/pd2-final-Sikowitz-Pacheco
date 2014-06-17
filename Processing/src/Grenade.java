import processing.core.*;

public class Grenade extends Weapon {
	boolean grounded;
	boolean blocked;
	int life;

	public Grenade(int x, int y, int sx, int sy, PApplet p) {
		super(p);
		life = 200;
		this.x = x;
		this.y = y;
		speedX = sx;
		speedY = sy;
		blocked = false;
		type = 0;
	}

	public String toString() {
		return "Grenade";
	}

	public int act(int pX, int pY) {
		if (check(pX, pY)) {
			explode();
			return 0;
		}
		else if (checkWall()) {
			return 2;
		}
		else if (life <= 0) {
			return 1;
		}
		
		life--;
		x = x + speedX;
		y = y + speedY;
		speedY = speedY + 1;
		return 2;
	}

	public void display() {
		p.stroke(0, 100, 0);
		p.fill(0, 100, 0);
		p.ellipse(x, y, 10, 15);
	}
}