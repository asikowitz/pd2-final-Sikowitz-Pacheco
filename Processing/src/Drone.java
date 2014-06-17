import processing.core.*;

public class Drone extends Weapon {
	public Drone(int x, int y, int sx, int sy, PApplet p) {
		super(p);
		this.x = x;
		this.y = y;
		speedX = sx;
		speedY = sy;
		setAlive(true);
		setLife(100);
	}

	public String toString() {
		return "Drone";
	}

	public boolean act() {
		x = x + speedX;
		y = y + speedY;
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
		p.stroke(50);
		p.fill(50);
		p.ellipse(x, y, 25, 10);
		p.ellipse(x, y + 5, 5, 10);
		p.ellipse(x, y - 5, 5, 10);
	}
	public void explode(){
   		p.fill(200,10,10);
   		p.ellipse(x,y,100,100);
  	}

}