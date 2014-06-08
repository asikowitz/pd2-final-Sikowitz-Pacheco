int throwX;
int throwY;
boolean midGuide;
boolean midDraw;
String item="Drone";
ArrayList<Weapon> weapons = new ArrayList<Weapon>();
void setup() {
  size(600,600);
  background(100);
  fill(255);
  rect(150,150,300,300);
  midGuide=false;
  midDraw=false;
}

void draw() {
  stroke(100);
  size(600,600);
  background(100);
  fill(255);
  rect(150,150,300,300);
  if(!(midGuide)){
    if(mouseX>150 && mouseY>150 && mouseX<450 && mouseY<450){
      if (mousePressed == true) {
        line(mouseX, mouseY, pmouseX, pmouseY);
        midDraw=true;
      }else{
        midDraw=false;
      }
    }else{
     if (mousePressed==true){
       throwX=mouseX;
       throwY=mouseY;
       midGuide=true;}}
   }else{
     if(mousePressed && !(midDraw)){
       stroke(255,0,0);
       arrow(throwX,throwY,mouseX,mouseY);
     }
    }
  weaponsAct();
  }
void weaponsAct(){
  for(int x=0;x<weapons.size();x++){
    if((weapons.get(x)).getMidThrow()){
      (weapons.get(x)).act();
    }else{
      weapons.set(x,null);
    }
  }
  for(int x=0;x<weapons.size();x++){
    if(weapons.get(x)==null){
      weapons.remove(x);
    }else{
      (weapons.get(x)).display();
    }
  }
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
int calculateSpeedX(int x1,int x2){
  return (x2-x1)/10;
}

int calculateSpeedY(int y1,int y2){
  return (y2-y1)/10;
}

void mouseReleased(){
    if(midGuide && !(midDraw)){
      stroke(255,0,0);
      fill(255,0,0);
      Weapon n;
      if(item.equals("Drone")){
        n = new Drone(throwX,throwY,calculateSpeedX(throwX,mouseX),calculateSpeedY(throwY,mouseY));
      }else{
        n = new Grenade(throwX,throwY,calculateSpeedX(throwX,mouseX),calculateSpeedY(throwY,mouseY));
      }
      n.setMidThrow(true);
      weapons.add(n);
      n.display();
      }
      midGuide=false;
}
