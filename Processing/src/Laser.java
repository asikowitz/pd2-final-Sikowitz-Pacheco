import processing.core.*;

public class Laser extends Weapon {
	public Laser(int x, int y, int sx, int sy, PApplet p) {
		super(p);
		this.x = x;
		this.y = y;
		speedX = sx;
		speedY = sy;
		type = 5;
	}

	boolean act() {
		return false;
	}

	public void display() {
		p.stroke(255, 0, 0);
		p.fill(255, 0, 0);
		p.rect(275, 500, 50, 50);
		if (p.mousePressed) {
			p.line(300, 500, p.mouseX + 10 * (p.mouseX - x), p.mouseY + 10 * (p.mouseY - y));
		}
	}

}