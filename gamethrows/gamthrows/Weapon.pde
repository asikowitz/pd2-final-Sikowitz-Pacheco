
public class Weapon{
  int x;
  int y;
  int a=-1;
  String type;
  int speedX;
  int speedY;
  int gravity;
  boolean midThrow;
  public boolean getMidThrow(){
    return midThrow;
  }
  public void setMidThrow(boolean x){
    midThrow=x;
  }

  void setX(int x){
    this.x=x;
  }
  void setY(int y){
    this.y=y;
  }
  void setSX(int sx){
    speedX=sx;
  }
  void setSY(int sy){
    speedY=sy;
  }
  int getX(){
    return x;
  }
    int getY(){
    return y;
  }
    int getSX(){
    return speedX;
  }
    int getSY(){
    return speedY;
  }
  
  void display(){
      stroke(255,00,0);
      fill(255,0,0);
      rect(x,y,10,10);
  }
  void act(){}
}
