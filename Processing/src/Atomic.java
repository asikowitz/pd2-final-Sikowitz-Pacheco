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
		if (x < 700 || x > 1100)
			return 1;
		
		if (checkWall()) {
			explode();
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
	
	public boolean explode() {
		type = 10;
		c--;
		if (c <= 0)
			return true;
		
		p.stroke(255);
   		p.fill(255);
    	p.ellipse(x,y,50,50);
    	return false;
  	}
}