import processing.core.*;

public class Weapon {
	int x, y, c;
	int type;
	int speedX, speedY;
	boolean hitWhite;

	PApplet p;
	int life;

	public Weapon(PApplet p) {
		hitWhite = false;
		c = 10;
		this.p = p;
		life=100;
	}

	public void display() {
		p.stroke(255, 00, 0);
		p.fill(255, 0, 0);
		p.rect(x, y, 10, 10);
	}
	
	public int[] addInt(int[] sent, int count) {
		sent[count] = type;
		sent[count+1] = x;
		sent[count+2] = y;
		return sent;
	}
	
	public int getType() {
		return type;
	}

	public boolean check(int pX, int pY) {
		int dist = (int)Math.sqrt((x-pX)*(x-pX) + (y-pY)*(y-pY));
		return dist < 8;
	}
	
	public boolean checkWall() {
		p.loadPixels();
		boolean valid = false;
		for (int i=-8; i<8; i++)
			for (int j=-8; j<8; j++)
				if (p.pixels[(y+i)*p.width + x+j] < -10 && (p.pixels[(y+i)*p.width + x+j] != -16777216 ^ hitWhite)) {
					//&& !(x > 1080 || x < 720) && !(y > 480 || y < 120)) {
					return true;
				}
				else if (p.pixels[(y+i)*p.width + x+j] == -16777216)
					valid = true;
		
		if (!valid)
			hitWhite = true;
		return false;
	}
	
	public int act(int pX, int pY) {return 0;}
	
	public String toString() {return "";}
	
	public boolean explode() {
		type = 6;
		c--;
		if (c <= 0)
			return true;
		
		p.stroke(200, 10, 10);
   		p.fill(200,10,10);
    	p.ellipse(x,y,10,10);
    	return false;
  	}

	public void set(int pX, int pY) {}
}