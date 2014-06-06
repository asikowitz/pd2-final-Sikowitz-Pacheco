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

		if (!grounded && a > 0) {
			if (y+4+a < p.height && !(p.pixels[(y+4+(int)a)*p.width + x] < -100000)) {
				y = (int)Math.round(y+a);
				a = a+0.15;
			}
			else
				a = a/3;
		}
		else if (!grounded) {
			if (y-4+a > 0 && !(p.pixels[(y-4+(int)a)*p.width + x] < -100000)) {
				y = (int)Math.round(y+a);
				a = a+0.15;
			}
			else
				a = 0.5;
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
			for (int i=4; i>=-6; i--) {
				if (p.pixels[(y-i)*p.width + x + sign] < -100000) {
					x = x + sign;
					y = y - i - 3;
					return;
				}
			}
			
			//If no places to move, fall
			x = x + sign;
		}
	}
	
	public boolean jump() {
		if (grounded) {
			y = y-2;
			a = -4;
			return true;
		}
		return false;
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
