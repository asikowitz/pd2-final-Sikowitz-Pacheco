	private int px = 300;
	private int py = 300;
	private ArrayList<Wall> walls = new ArrayList<Wall>();
	private Player p;

	public void setup() {
	  size(600, 600);
	  background(255);
	  noSmooth();
	  strokeWeight(8);
	  stroke(0);
	  walls.add(new Wall(290, 310, 310, 310, this));
	  p = new Player(300, 300, this);
	}

	public void draw() {
		background(255);
		stroke(0);
		strokeWeight(8);
		// Draw a line from previous mouse location to current mouse location.
		if (mousePressed && (!between(pmouseX, mouseX, p.getX()) || !between(pmouseY, mouseY, p.getY())))
			walls.add(new Wall(pmouseX, pmouseY, mouseX, mouseY, this));
		for (Wall w : walls)
			w.display();
		if (keyPressed) { //void keyPressed() is not called often enough
			  switch(key) {
			    case('a'):
			      p.move(-2);
			      break;
			    case('d'):
			      p.move(2);
			      break;
			  }
		}
		/*loadPixels();
		for (int i=0; i<width*height; i++)
			if (pixels[i] < -100000)
				pixels[i] = -16777216;
		updatePixels();*/
		p.run();
		//noFill();
		//stroke(255, 0, 0);
		//line(px, py, px, py);
	}
	
	/*public void keyPressed() {
	  switch(key) {
	    case('a'):
	      p.move(-5);
	      break;
	    case('d'):
	      p.move(5);
	      break;
	  }
	  /*loadPixels();
	  for (int i=0; i<width*height; i++)
		  if (pixels[i] != -16777216)
			  pixels[i] = color(255);
	  updatePixels();*/
	//}
	
private boolean between(int a, int b, int v) {
	return (a < v+7 && b > v-7) || (a > v-7 && b < v+7);
}
