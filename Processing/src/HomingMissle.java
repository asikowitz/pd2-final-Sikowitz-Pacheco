
public class HomingMissle extends Weapon{
  Player n;
  public HomingMissle(int x, int y, int sx,int sy,Player n){
    setX(x);
    setY(y);
    setSX(sx);
    setSY(sy);
    this.n=n;
	setAlive(true);
	setLife(100);
  }
  String toString(){
    return "Homing Missle";
  }
  void guide(int x1,int y1, int x2,int y2){
  }
  int createSpeedX(){
    return (n.getX()-x)/10;
  }
  int createSpeedY(){
    return (n.getY()-y)/10;
  }
  boolean act(){
     if(x>n.getX()-10 && x<n.getX()+10 && y>n.getY()-10 && y<n.getY()-10){
       explode();
       return true;
     }
     x=x+createSpeedX();
     y=y+createSpeedY();
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
  
  void display(){
      stroke(50);
      fill(50);
      ellipse(x,y,10,10);
  }
  void explode(){
    fill(200,10,10);
    ellipse(x,y,100,100);
  }

}