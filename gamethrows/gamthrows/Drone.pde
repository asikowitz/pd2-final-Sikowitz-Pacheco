
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
  void act(){
   // Double ratioX = new Double(speedX);
   // Double ratioY= new Double(speedY);
    //Double ratio = ratioY/ratioX;
   // ratioY=ratio*2;
   // int newY=ratioY.intValue();
   // x=x+2;
   // y=y+newY;
     x=x+speedX;
     y=y+speedY;
    }
  void display(){
      stroke(50);
      fill(50);
      ellipse(x,y,10,15);
  }

}
