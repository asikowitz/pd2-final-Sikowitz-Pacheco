import processing.core.*;

public class Atomic extends Weapon{
  public Atomic(int x, int y, int sx,int sy){
    setX(x);
    setY(y);
    setSX(sx);
    setSY(sy);
  }
  String toString(){
    return "Atomic";
  }
  void guide(int x1, int y1, int x2,int y2){
    stroke(255,0,0);
    arrow(x1,y1,x1,y1+50);
  }
  void act(){
     x=x+speedX;
     y=y+speedY;
    }
  void display(){
      stroke(50);
      fill(50);
      ellipse(x,y,20,30);
      rect(x-10,y-25,20,10);
  }

}