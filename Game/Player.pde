
public class Player {
	int x;
	int y;
	double a; //Acceleration
	boolean grounded;
	PApplet p;
	
	public Player(int x, int y, PApplet p) {
		this.x = x;
		this.y = y;
		this.p = p;
		a = 0.5;
	}
	
	public void run() {
		p.loadPixels();
		checkGrounded();
		
		if (!grounded) {
			if (!(p.pixels[(y+4+(int)a)*p.width + x] < -100000)) {
				y = (int)Math.round(y+a);
				a = a+0.15;
			}
			else
				a = a/3;
		}
		else
			a = 0.5;
		
		
		p.noSmooth();
		p.stroke(255, 0, 0);
		p.strokeWeight(8);
		p.point(x, y);
	}
	
	public void move(int sign) {
		boolean blocked = false;
		for (int i=5; i<8; i++) {
			if (p.pixels[(y-i)*p.width + x + sign] < -100000)
				blocked = true;
		}
		
		if (!blocked) {
			for (int i=4; i>=-2; i--)
				if (p.pixels[(y-i)*p.width + x + sign] < -100000) {
					x = x + sign;
					y = y - i - 2;
					return;
				}
			
			//If no places to move, fall
			x = x + sign;
		}
	}
	
	public void checkGrounded() {
		for (int i=0; i<5; i++) {
			if (p.pixels[(y+i)*p.width + x] < -100000) {
				grounded = true;
				return;
			}
		}
		grounded = false;
	}
	
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
}

