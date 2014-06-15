import processing.core.*;

import java.util.*;
import java.net.*;
import java.io.*;

public class Proc extends PApplet {
	private static final int s = 400;
	private ArrayList<Wall> walls = new ArrayList<Wall>();
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


	int throwX;
	int throwY;
	boolean midGuide;
	boolean midDraw;
	String item="Atomic";
	Weapon n;
	ArrayList<Weapon> weapons = new ArrayList<Weapon>();
	public void setup() {
		size(s*2+400, s+200);
		background(0);
		noSmooth();
		strokeWeight(8);
		stroke(0);
		textFont(loadFont("Font.vlw"));
		textSize(20);
		walls.add(new Wall(s/2-10, s/2+10, s/2+10, s/2+10, this));
		p = new Player(s/2, s/2, this);
		midGuide=false;
  		midDraw=false;
  		if(item.equals("Drone")){
  		   n = new Drone(throwX,throwY,calculateSpeedX(throwX,mouseX),calculateSpeedY(throwY,mouseY));
  		}else if(item.equals("Atomic")){
  		   n = new Atomic(throwX,throwY,calculateSpeedX(throwX,mouseX),calculateSpeedY(throwY,mouseY));
  		}else{
  		   n = new Grenade(throwX,throwY,calculateSpeedX(throwX,mouseX),calculateSpeedY(throwY,mouseY));
  		}
		energy = 600;
		int port = 6066;
		String serverName = "localhost";
		
		System.out.println("Attempting to connect to " + serverName + " on port " + port);
		
		/*while (true) {
			try {
				client = new Socket(serverName, port);
				System.out.println("Just connected to " + client.getRemoteSocketAddress());
				out = new ObjectOutputStream(client.getOutputStream());
				in = new ObjectInputStream(client.getInputStream());
				break;
			} catch (IOException e) {}
		}*/
	}

	public void draw() {
		//Clear, redraw bottom, draw and write energy
		sent = new int[walls.size()*5+2];
		for (int i=0; i<walls.size(); i++)
			walls.get(i).addInt(sent, i);
		sent[sent.length-2] = p.getX();
		sent[sent.length-1] = p.getY();
		
		/*try {
			out.writeObject(sent);
			rec = (int[]) in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}*/
		
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
		
		if (rec != null) {
			for (int i=0; i+4<rec.length; i=i+5) {
				stroke(rec[i], rec[i], rec[i]);
				line(rec[i+1]+s+200, rec[i+2], rec[i+3]+s+200, rec[i+4]);
			}
			if (rec.length > 1) {
				stroke(255, 0, 0);
				point(rec[rec.length-2]+s+200, rec[rec.length-1]);
			}
		}
		
		// Draw a line from previous mouse location to current mouse location.
		if (mousePressed && 
			pmouseX < 100+s && mouseX < 100+s && pmouseX > 100 && mouseX > 100 &&
			pmouseY < 100+s && mouseY < 100+s && pmouseY > 100 && mouseY > 100 &&
			(!between(pmouseX, mouseX, p.getX()) || !between(pmouseY, mouseY, p.getY())))
		{
			int dist = (int)Math.sqrt((pmouseX-mouseX)*(pmouseX-mouseX) + (pmouseY-mouseY)*(pmouseY-mouseY));
			if (energy > dist) {
				walls.add(new Wall(pmouseX, pmouseY, mouseX, mouseY, this));
				energy = energy - dist;
			}
		}
		
		for (int i=0; i<walls.size(); i++)
			if(!walls.get(i).display())
				walls.remove(i);
		
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
		if(!(midGuide)){
		    if(isMouseIn()){
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
		       n.guide(throwX,throwY,mouseX,mouseY);
		     }
		    }
		  weaponsAct();
		  }
	}
	
	public void keyReleased() {
		keysDown--;
		if (keysDown <= 0) {
			keysDown = 0;
			stillMoving = 0;
		}
		if(midGuide && !(midDraw)){
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
   	   		}
	  	midGuide=false;
	}
	
	private boolean between(int a, int b, int v) {
		return (a < v+7 && b > v-7) || (a > v-7 && b < v+7);
	}

	public static void main(String[] args) {
		PApplet.main(new String[] { "--present", "Proc" });
	}

	boolean isMouseIn(){
		if(mouseX>150 && mouseY>150 && mouseX<450 && mouseY<450){
			return true;
		}else{return false;}
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
}