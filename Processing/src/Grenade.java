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
		setAlive(true);
		setLife(100);
	}

	public String toString() {
		return "Grenade";
	}

	public boolean act() {
		if (!blocked) {
			x = x + speedX;
			y = y + speedY;
			speedY = speedY - a;
		}
		if(x<0 || y<0 || x>600 || y>600){
      			life=0;
    		}else{
    			life=life-1;
    		}
		if(life<=0){
			setAlive(false);
		}
    		if(!getAlive()){
      			return true;	
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