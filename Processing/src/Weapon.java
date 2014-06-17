import processing.core.*;

public class Weapon {
	int x, y;
	int a = -1;
	String type;
	int speedX, speedY, gravity;
	boolean Alive;
	PApplet p;
	int life;
	int count=10;
	public Weapon(PApplet p) {
		this.p = p;
		Alive = true;
		life=100;
		count=10;
	}
	public void setCount(int c){
		count=c;
	}
	public int getCount(){
		return count;
	}
	public void setLife(int x){
		life=x
	}

	public boolean getAlive() {
		return Alive;
	}

	public void setAlive(boolean x) {
		Alive = x;
	}

	public void display() {
		p.stroke(255, 00, 0);
		p.fill(255, 0, 0);
		p.rect(x, y, 10, 10);
	}

	public boolean act() {
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
	public String toString() {return "";}
	public void explode(){}
}