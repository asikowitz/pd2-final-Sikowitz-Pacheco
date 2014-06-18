import processing.core.*;

public class Weapon {
	int x, y, c;
	int type;
	int speedX, speedY;
	int hitWhite;

	PApplet p;
	int life;

	public Weapon(PApplet p) {
		hitWhite = 0;
		c = 10;
		this.p = p;
		life=100;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
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
		if ((p.pixels[(y)*p.width + x] < -10 && p.pixels[(y)*p.width + x] != -16777216) || (p.pixels[(y)*p.width + x] == -16777216 && hitWhite > 5)) {
			//&& !(x > 1080 || x < 720) && !(y > 480 || y < 120)) {
			return true;
		}
		else if (p.pixels[(y)*p.width + x] != -16777216)
			hitWhite++;
		
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