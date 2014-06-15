import processing.core.*;

public class Atomic extends Weapon {
	public Atomic(int x, int y, int sx, int sy, PApplet p) {
		super(p);
		this.x = x;
		this.y = y;
		speedX = sx;
		speedY = sy;
	}

	public String toString() {
		return "Atomic";
	}

	public void act() {
		x = x + speedX;
		y = y + speedY;
	}

	public void display() {
		p.stroke(50);
		p.fill(50);
		p.ellipse(x, y, 20, 30);
		p.rect(x - 10, y - 25, 20, 10);
	}
	public void explode(){
		p.fill(200,10,10);
    	p.ellipse(x,y,100,100);
  	}

}