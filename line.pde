int px = 100;
int py = 99;
boolean valid;

void setup() {
  size(300, 300);
  background(255);
  smooth();
  line(98, 98, 102, 98);
  loadPixels();
}

void draw() {
  stroke(0);
  strokeWeight(4);
  // Draw a line from previous mouse location to current mouse location.
  if (mousePressed)
    line(pmouseX, pmouseY, mouseX, mouseY);
  loadPixels();
  for (int i=0; i<width*height; i++)
    if (pixels[i] < -100000)
      pixels[i] = -16777216;
  updatePixels();
  noFill();
  stroke(255, 0, 0);
  line(px, py, px, py-4);
}

void keyPressed() {
  switch(key) {
    case('a'):
    move(true);
    break;
    case('d'):
    move(false);
    break;
  }
  loadPixels();
  for (int i=0; i<width*height; i++)
    if (pixels[i] != -16777216)
      pixels[i] = color(255);
  updatePixels();
  System.out.println(pixels[mouseY*width + mouseX]);
}

void move(boolean left) {
  if (left) {
    valid = true;
    for (int i=10; i<30; i++) {
      if (get(px-6, py+i) == -16777216)
        valid = false;
    }
    valid = true;
    if (valid) {
      for (int i=3; i>-3; i--) {
        for (int j=1; j>=-1; j--) {
          if (get(px-j, py+i) == -16777216) {
            px = px-1;
            py = py+i-3;
          }
        }
      }
    }
  }
  else {
    valid = true;
    for (int i=10; i<30; i++) {
      if (get(px+1, py+i) == -16777216)
        valid = false;
    }
    valid = true;
    if (valid) {
      for (int i=3; i>-3; i--) {
        for (int j=1; j>=-1; j--) {
          if (get(px+j, py+i) == -16777216) {
            px = px+1;
            py = py+i-3;
          }
        }
      }
    }
  }
}

