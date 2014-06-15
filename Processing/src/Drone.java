import processing.core.*;


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
  void guide(int x1,int y1,int x2,int y2){
    stroke(255,0,0);
    arrow(x1,y1,x2,y2);
  }
  void arrow(int x1, int y1, int x2, int y2) {
    line(x1, y1, x2, y2);
    pushMatrix();
    translate(x2, y2);
    float a = atan2(x1-x2, y2-y1);
    rotate(a);
    line(0, 0, -10, -10);
    line(0, 0, 10, -10);
    popMatrix();
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