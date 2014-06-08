PApplet p;
boolean grounded;
boolean blocked;
public class Grenade extends Weapon{
  public Grenade(int x, int y, int sx,int sy){
    setX(x);
    setY(y);
    setSX(sx);
    setSY(sy);
    blocked=false;
  }
  String toString(){
    return "Grenade";
  }
  void act(){
    //for (int i=5; i<8; i++) {
   //   if (p.pixels[(y-i)*p.width + x+sign] < -100000)
   //     blocked = true;
    //}
    if(!blocked){
      x=x+speedX;
      y=y+speedY;
      speedY = speedY-a;}
  }
  void display(){
      stroke(0,100,0);
      fill(0,100,0);
      ellipse(x,y,10,15);
  }

}
