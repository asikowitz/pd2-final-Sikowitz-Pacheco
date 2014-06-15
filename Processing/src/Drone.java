import processing.core.*;

public class Drone extends Weapon {
	public Drone(int x, int y, int sx, int sy, PApplet p) {
		super(p);
		this.x = x;
		this.y = y;
		speedX = sx;
		speedY = sy;
	}

	public String toString() {
		return "Drone";
	}

	public void guide(int x1, int y1, int x2, int y2) {
		p.stroke(255, 0, 0);
		if (x2 < x1) {
			arrow(x1, y1, x1 - 50, y1);
		} else {
			arrow(x1, y1, x1 + 50, y1);
		}
	}

	public void act() {
		x = x + speedX;
		y = y + speedY;
	}

	public void display() {
		p.stroke(50);
		p.fill(50);
		p.ellipse(x, y, 25, 10);
		p.ellipse(x, y + 5, 5, 10);
		p.ellipse(x, y - 5, 5, 10);
	}

}