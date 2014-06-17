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
	
	public int[] addInt(int[] sent, int count) {
		sent[count] = life;
		sent[count+1] = px;
		sent[count+2] = py;
		sent[count+3] = x;
		sent[count+4] = y;
		return sent;
	}
	
	public String toString() {
		return (px + "," + py + "," + x + "," + y + "," + life);
	}
}