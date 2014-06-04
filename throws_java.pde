int throwX;
int throwY;
boolean midThrow;
void setup() {
  size(600,600);
  background(0);
  fill(255);
  rect(150,150,300,300);
  midThrow=false;
}

void draw() {
  stroke(0);
  if(!(midThrow)){
    if(mouseX>150 && mouseY>150 && mouseX<450 && mouseY<450){
      if (mousePressed == true) {
        line(mouseX, mouseY, pmouseX, pmouseY);}
    }else{
     if (mousePressed==true){
       throwX=mouseX;
       throwY=mouseY;
       midThrow=true;}}
   }else{
     if(mousePressed){
       stroke(255,0,0);
       arrow(throwX,throwY,mouseX,mouseY);
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

void mouseReleased(){
    if(midThrow){
      stroke(255,0,0);
      fill(255,0,0);
      rect(throwX,throwY,10,10);}
      midThrow=false;
}


