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

	public boolean act() {
		// for (int i=5; i<8; i++) {
		// if (p.pixels[(y-i)*p.width + x+sign] < -100000)
		// blocked = true;
		// }
		if (!blocked) {
			x = x + speedX;
			y = y + speedY;
			speedY = speedY - a;
		}
		return false;
	}

	public void display() {
		p.stroke(0, 100, 0);
		p.fill(0, 100, 0);
		p.strokeWeight(1);
		p.ellipse(x, y, 10, 15);
		p.strokeWeight(8);
	}
	public void explode(){
   		p.fill(200,10,10);
    	p.ellipse(x,y,100,100);
  	}
}