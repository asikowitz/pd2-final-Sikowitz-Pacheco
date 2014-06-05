import processing.core.*;

public class Wall {
	int px;
	int py;
	int x;
	int y;
	int life;
	PApplet p;
	
	public Wall(int px, int py, int x, int y, PApplet p) {
		this.px = px;
		this.py = py;
		this.x = x;
		this.y = y;
		this.p = p;
		life = 0;
	}
	
	public void display() {
		if (life < 256) { //How to destroy object...
			p.noSmooth();
			p.stroke(life, life, life);
			p.strokeWeight(8);
			p.line(px, py, x, y);
			life++;
		}
	}
}
