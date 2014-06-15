
public class Drone extends Weapon{
  public Drone(int x, int y, int sx,int sy){
    setX(x);
    setY(y);
    setSX(sx);
    setSY(sy);
  }
  String toString(){
    return "Drone";
  }
  void guide(int x1,int y1, int x2,int y2){
    stroke(255,0,0);
    if(x2<x1){
      arrow(x1,y1,x1-50,y1);
    }else{
      arrow(x1,y1,x1+50,y1);
    }
  }
  void act(){
     x=x+speedX;
     y=y+speedY;
    }
  void display(){
      stroke(50);
      fill(50);
      ellipse(x,y,25,10);
      ellipse(x,y+5,5,10);
      ellipse(x,y-5,5,10);
  }
  void explode(){
    fill(200,10,10);
    ellipse(x,y,100,100);
  }

}
