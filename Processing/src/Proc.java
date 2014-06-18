import processing.core.*;

import java.util.*;
import java.net.*;
import java.io.*;

public class Proc extends PApplet {
	private static final int s = 400;
	//private ArrayList<Wall> walls = new ArrayList<Wall>();
	private LinkedList walls = new LinkedList(); //Linked list specifically designed for walls, so no <specification> necessary
	private Player p;
	private int energy;
	private int[] sent, rec;
	
	//private ServerSocket serverSocket;
	private Socket client; //, server;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	//These are to solve processing's issue with multiple keys pressed simultaneously
	private int stillMoving;
	private int keysDown;
	private char prevKey;
	private char bypass; //Prevents glitches when a specific key is hit and let go twice while still holding down a or d

	//For weapons
	private int throwX, throwY;
	private boolean midGuide, midDraw;
	private int item = 0;
	private ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	
	public void setup() {
		size(s*2+400, s+200);
		background(0);
		noSmooth();
		strokeWeight(8);
		stroke(0);
		//textFont(loadFont("Font.vlw"));
		textSize(20);
		walls.add(new Wall(s/2-10, s/2+10, s/2+10, s/2+10, this));
		p = new Player(s/2, s/2, this);
		
		midGuide=false;
  		midDraw=false;
  		
		energy = 600;
		int port = 6066;
		String serverName = "localhost";
		
		System.out.println("Attempting to connect to " + serverName + " on port " + port);
		
		while (true) {
			try {
				client = new Socket(serverName, port);
				System.out.println("Just connected to " + client.getRemoteSocketAddress());
				out = new ObjectOutputStream(client.getOutputStream());
				in = new ObjectInputStream(client.getInputStream());
				break;
			} catch (IOException e) {}
		}
	}

	public void draw() {
		//loadPixels();
		//System.out.println(pixels[mouseY*width + mouseX]);
		//Clear, redraw bottom, draw and write energy
	
		sent = new int[walls.size()*5+3*weapons.size()+3];
		/*for (int i=0; i<walls.size(); i++, c=c+5)
			walls.get(i).addInt(sent, c);*/
		
		int c = walls.addInt(sent);
		
		sent[c] = -1;
		c++;
		for (int i=0; i<weapons.size(); i++, c=c+3)
			weapons.get(i).addInt(sent, c);
		sent[sent.length-2] = p.getX();
		sent[sent.length-1] = p.getY();
		
		try {
			out.writeObject(sent);
			rec = (int[]) in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
			rec = new int[2];
			rec[0] = s + s/2 + 300;
			rec[1] = s/2 + 100;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		background(0);
		fill(255);
		stroke(255);
		rect(100, 100, s, s);
		rect(300+s, 100, s, s);
		stroke(0);
		strokeWeight(8);
		smooth();
		line(0, height, width, height);
		stroke(0, 0, 255);
		fill(0);
		text("Energy", s*2/3+100, s/17+100);
		line(s*2/3+100, s/12+100, s*2/3+energy/5+100, s/12+100);
		noSmooth();
		if (energy < 600)
			energy = energy+2;
		
		weaponsAct();
		
		//Copy walls, player, weapons from other computer		
		if (rec != null) {
			for (c=0; rec[c] != -1; c=c+5) {
				stroke(rec[c], rec[c], rec[c]);
				line(rec[c+1]+s+200, rec[c+2], rec[c+3]+s+200, rec[c+4]);
			}
			
			strokeWeight(1);
			for (c++; c+2 < rec.length; c=c+3) {
				switch (rec[c]) {
				case 0:
					stroke(0, 100, 0);
					fill(0, 100, 0);
					ellipse(rec[c+1]-200-s, rec[c+2], 10, 15);
					break;
				case 1:
					stroke(50);
					fill(50);
					ellipse(rec[c+1]-200-s, rec[c+2], 25, 10);
					ellipse(rec[c+1]-200-s, rec[c+2] + 5, 5, 10);
					ellipse(rec[c+1]-200-s, rec[c+2] - 5, 5, 10);
					break;
				case 2:
					stroke(50);
					fill(50);
					ellipse(rec[c+1]-200-s, rec[c+2], 20, 30);
					rect(rec[c+1] - 10-200-s, rec[c+2] - 25, 20, 10);
					break;
				case 4:
					stroke(50);
					fill(50);
					ellipse(rec[c+1]-200-s, rec[c+2], 10, 10);
					break;
				case 6:
					strokeWeight(8);
					stroke(200, 10, 10);
			   		fill(200,10,10);
			    	ellipse(rec[c+1]-200-s,rec[c+2],10,10);
				}
			}
			strokeWeight(8);
			
			if (rec.length > 1) {
				stroke(255, 0, 0);
				point(rec[rec.length-2]+s+200, rec[rec.length-1]);
			}
		}
		
		// Draw a line from previous mouse location to current mouse location.
		if (mousePressed && !midGuide &&
			pmouseX < 100+s && mouseX < 100+s && pmouseX > 100 && mouseX > 100 &&
			pmouseY < 100+s && mouseY < 100+s && pmouseY > 100 && mouseY > 100 &&
			(!between(pmouseX, mouseX, p.getX()) || !between(pmouseY, mouseY, p.getY())))
		{
			midDraw = true;
			int dist = (int)Math.sqrt((pmouseX-mouseX)*(pmouseX-mouseX) + (pmouseY-mouseY)*(pmouseY-mouseY));
			if (energy > dist) {
				walls.add(new Wall(pmouseX, pmouseY, mouseX, mouseY, this));
				energy = energy - dist;
			}
		}
		else if (mousePressed && !midGuide && mouseX > 300+s && mouseX < 300+2*s && mouseY > 100 && mouseY < 100+s)
			midDraw = true;
		else if (!midDraw) {
			strokeWeight(1);
			if (midGuide)
				guide(throwX, throwY, mouseX, mouseY);
			else if (mousePressed && mouseX > 200+s && (mouseY < 100 || mouseY > 100+s || (mouseX > 2*s+300 || mouseX < s+300))) {
				throwX = mouseX;
				throwY = mouseY;
				midGuide = true;
				guide(throwX, throwY, mouseX, mouseY);
			}
		}
		
		/*for (int i=0; i<walls.size(); i++)
			if(!walls.get(i).display())
				walls.remove(i);*/
		walls.display();
		
		if (keyPressed) { //void keyPressed() is not called often enough
			if (key != prevKey || key == bypass) { 
				prevKey = key;
				bypass = '|';
				keysDown++;
			}
			switch(key) {
			case('a'):
				p.move(-2);
				stillMoving = -2;
				break;
			case('d'):
				p.move(2);
				stillMoving = 2;
				break;
			case('w'):
				if (stillMoving != 0) {
					p.move(stillMoving);
					if (keysDown == 1)
						bypass = key;
				}
				if(p.jump() && energy > 30)
					energy = energy - 30;
				break;
			default:
				if (stillMoving != 0) {
					p.move(stillMoving);
					if (keysDown == 1)
						bypass = key;
				}
				break;
			}
		}
		else if (keysDown == 0)
			stillMoving = 0;
		else if (stillMoving != 0) {
			p.move(stillMoving);
			if (keysDown == 1)
				bypass = key;
		}
		
		p.run();
	}
	
	public void mouseReleased() {
		if (midGuide) {
			stroke(255, 0, 0);
			fill(255, 0, 0);
			Weapon n = null;
		
			if (item == 1) {
				if (mouseX < throwX)
					n = new Drone(throwX, throwY, calculateSpeed(throwX, throwX - 10), calculateSpeed(throwY, throwY), this);
				else
					n = new Drone(throwX, throwY, calculateSpeed(throwX, throwX + 10), calculateSpeed(throwY, throwY), this);
			}
			else if (item == 2)
				n = new Atomic(throwX, throwY, calculateSpeed(throwX, throwX), calculateSpeed(throwY, throwY + 10), this);
			else if (item == 0)
				n = new Grenade(throwX, throwY, calculateSpeed(throwX, mouseX), calculateSpeed(throwY, mouseY), this);
			else if (item == 4)
				n = new Homing(throwX, throwY, calculateSpeed(throwX, mouseX), calculateSpeed(throwY, mouseY), 
						rec[rec.length-2]+s+200, rec[rec.length-1], this);
			else if (item == 3){
				n=new Laser(throwX,throwY,mouseX,mouseY, this); //Parameters irrelevant
			}
			weapons.add(n);
			n.display();
		}
		
		midDraw = false;
		midGuide = false;
	}
	
	public void keyReleased() {
		keysDown--;
		if (keysDown <= 0) {
			keysDown = 0;
			stillMoving = 0;
		}
	}
	
	private void guide(int x1, int y1, int x2, int y2) {
		stroke(255, 0, 0);
		if (item == 2)
			arrow(x1, y1, x1, y1 + 50);
		else if (item == 1) {
			if (x2 < x1)
				arrow(x1, y1, x1 - 50, y1);
			else
				arrow(x1, y1, x1 + 50, y1);
		}
		else if (item == 0 || item == 4)
			arrow(x1, y1, x2, y2);
	}
	
	private void arrow(int x1, int y1, int x2, int y2) {
		line(x1, y1, x2, y2);
		pushMatrix();
		translate(x2, y2);
		float a = atan2(x1 - x2, y2 - y1);
		rotate(a);
		line(0, 0, -10, -10);
		line(0, 0, 10, -10);
		popMatrix();
	}
	
	private boolean between(int a, int b, int v) {
		return (a < v+7 && b > v-7) || (a > v-7 && b < v+7);
	}

	private void weaponsAct() {
		for (int x = 0; x < weapons.size(); x++) {
			Weapon n = weapons.get(x);
			switch (n.act(rec[rec.length-2]+s+200, rec[rec.length-1])) {
			case 0:
				System.out.println("Game Over. You Win!");
				try {
					in.close();
					out.close();
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				System.exit(0);
				break;
			case 1:
				if (n.explode())
					weapons.remove(x);
				break;
			case 2:
				strokeWeight(1);
				n.display();
				strokeWeight(8);
				break;
			}
		}
	}

	private int calculateSpeed(int x1, int x2) {
		return (x2 - x1) / 10;
	}
	
	public static void main(String[] args) {
		PApplet.main(new String[] { "--present", "Proc" });
	}
}
