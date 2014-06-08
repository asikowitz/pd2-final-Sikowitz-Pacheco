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
	
	public boolean display() {
		if (life < 256) {
			p.noSmooth();
			p.stroke(life, life, life);
			p.strokeWeight(8);
			p.line(px, py, x, y);
			life++;
			return true;
		}
		else
			return false;
	}
	
	public int[] addInt(int[] walls2, int count) {
		walls2[count*5] = life;
		walls2[count*5+1] = px;
		walls2[count*5+2] = py;
		walls2[count*5+3] = x;
		walls2[count*5+4] = y;
		return walls2;
	}
	
	public String toString() {
		return (px + "," + py + "," + x + "," + y + "," + life);
	}
}
