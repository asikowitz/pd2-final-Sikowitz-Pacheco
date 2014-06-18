import processing.core.*;

public class Homing extends Weapon {
	int pX;
	int pY;
	int life;
	
	public Homing(int x, int y, int sx, int sy, int px, int py, PApplet p) {
		super(p);
		life = 100;
		this.x = x;
		this.y = y;
		pX = px;
		pY = py;
		speedX = sx;
		speedY = sy;
		type = 4;
	}

	public String toString() {
		return "Homing";
	}
	
	public void set(int pX, int pY) {
		this.pX = pX;
		this.pY = pY;
	}

	public int act(int pX, int pY) {
		if (check(pX, pY)) {
			explode();
			return 0;
		}
		else if (checkWall()) {
			return 1;
		}
		else if (life <= 0) {
			return 1;
		}
		
		life --;
		x = x + (pX - x) / 5;
		y = y + (pY - y) / 5;
		return 2;
	}

	public void display() {
		p.stroke(0,0,200);
		p.fill(0,0,200);
		p.ellipse(x, y, 10, 10);
		p.line(x+5,y-2,x+5,y+12);
		p.line(x-2,y+5,x-2,y+12);
	}
}