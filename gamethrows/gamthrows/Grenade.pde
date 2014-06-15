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
  void arrow(int x1, int y1, int x2, int y2) {
    line(x1,y1,x2,y2);
    pushMatrix();
    translate(x2, y2);
    float a = atan2(x1-x2, y2-y1);
    rotate(a);
    line(0, 0, -10, -10);
    line(0, 0, 10, -10);
    popMatrix();
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
  void guide(int x1, int y1, int x2, int y2){
    stroke(255,0,0);
    arrow(x1,y1,x2,y2);
  }
  void explode(){}
  
  
  
}
