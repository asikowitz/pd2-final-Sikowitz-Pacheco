int throwX;
int throwY;
boolean midGuide;
boolean midDraw;
String item="Drone";
boolean midFlight;
Weapon n;
ArrayList<Weapon> weapons = new ArrayList<Weapon>();
void setup() {
  size(600,600);
  background(100);
  fill(255);
  rect(150,150,300,300);
  midGuide=false;
  midDraw=false;
  if(item.equals("Drone")){
     n = new Drone(throwX,throwY,calculateSpeedX(throwX,mouseX),calculateSpeedY(throwY,mouseY));
  }else if(item.equals("Atomic")){
     n = new Atomic(throwX,throwY,calculateSpeedX(throwX,mouseX),calculateSpeedY(throwY,mouseY));
  }else{
     n = new Grenade(throwX,throwY,calculateSpeedX(throwX,mouseX),calculateSpeedY(throwY,mouseY));
  }
}

void draw() {
  stroke(100);
  size(600,600);
  background(100);
  fill(255);
  rect(150,150,300,300);
  if(!(midGuide)&& !(midFlight )){
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
     if(mousePressed && !(midDraw) && !(midFlight)){
       n.guide(throwX,throwY,mouseX,mouseY);
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

int calculateSpeedX(int x1,int x2){
  return (x2-x1)/10;
}

int calculateSpeedY(int y1,int y2){
  return (y2-y1)/10;
}

void mouseReleased(){
    if(midGuide && !(midDraw) && !(midFlight)){
      stroke(255,0,0);
      fill(255,0,0);
      if(item.equals("Drone")){
        //drones always go left or right at constant speed so we modify the direction
        int mx;
        int my=throwY;
        if(mouseX<throwX){
          mx=throwX-10;
        }else{
          mx=throwX+10;
        }
        n = new Drone(throwX,throwY,calculateSpeedX(throwX,mx),calculateSpeedY(throwY,my));
        midFlight=true;
      }else if(item.equals("Atomic")){
        int mx=throwX;
        int my=throwY+10;
        n = new Atomic(throwX,throwY,calculateSpeedX(throwX,mx),calculateSpeedY(throwY,my));
      }else{
        n = new Grenade(throwX,throwY,calculateSpeedX(throwX,mouseX),calculateSpeedY(throwY,mouseY));
      }
      n.setMidThrow(true);
      weapons.add(n);
      n.display();
    }else if(midFlight){
      midFlight=false;
    }
      midGuide=false;
}

void mousePressed(){
  if(midFlight){
      n.explode();
      weapons.remove(n);
  }
}
