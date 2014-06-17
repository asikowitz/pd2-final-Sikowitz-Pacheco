
public class Laser extends Weapon{
  public Laser(int x, int y, int sx,int sy){
    setX(x);
    setY(y);
    setSX(sx);
    setSY(sy);
    blocked=false;
  }
  String toString(){
    return "Laser";
  }
  void act(){
  }
  void display(){
      stroke(255,0,0);
      fill(255,0,0);
      rect(275,500,50,50);
      if(mousePressed){
        line(300,500,mouseX+10*(mouseX-x),mouseY+10*(mouseY-y));
      }
  }
  void guide(int x1, int y1, int x2, int y2){
  
  }
  void explode(){}
  
  
  
}