import java.lang.Math;
import java.util.Random;
import java.io.File;
import processing.core.PImage;
import java.lang.Object;


public class WalkingSim {
	
private static Random randGen;
private static int bgColor;
private static PImage[] frames;
private static Walker[] walkers;


	public static void setup() {
		
		randGen = new Random();
		bgColor = randGen.nextInt();
		frames = new PImage[Walker.NUM_FRAMES]; 
		frames[0] = Utility.loadImage("images" + File.separator + "walk-0.png");
		walkers = new Walker[8];
		
		for(int i=0; i<8; i++) {
		walkers[i] = new Walker();
		walkers[i].setPositionX(randGen.nextInt(Utility.width()/2)); 
		walkers[i].setPositionY(randGen.nextInt(Utility.width()/2)); 
		}
	}
	public static void draw() {
//		frame = new PImage();
		Utility.background(bgColor);
		for(int i=0; i<walkers.length; i++) {
			if(walkers[i] !=null) {
				if(walkers[i].isWalking()== true) {
					walkers[i].update();
					walkers[i].setPositionX(walkers[i].getPositionX()+3);
					if(walkers[i].getPositionX() >797) {
						walkers[i].setPositionX(0);
					}
				}
			}
		}
		Utility.image(frames[0], walkers[0].getPositionX(), walkers[0].getPositionY());
		
		
		
	}
	
	
	public static boolean isMouseOver(Walker walker) {
		float positionX = walker.getPositionX();
		float positionY = walker.getPositionY();
		int height = Utility.height();
		int width = Utility.width();
		
		if(Utility.mouseX()< positionX + width/2 ) {
		return true;
		}
		
		
		return false;
	}
	
	public static void mousePressed() {
		for(int i=0; i< walkers.length; i++) {
			if(walkers[i]!= null && isMouseOver(walkers[i])) {
				walkers[i].setWalking(true);
				break;
			}
		}
	}
	public static void keyPressed(char key) {
		if(key == 'a' || key=='A') {
			for(int i=0; i< walkers.length; i++) {
				if(walkers[i] == null) {
					walkers[i].setPositionX(randGen.nextInt(Utility.width())); 
					walkers[i].setPositionY(randGen.nextInt(Utility.width())); 
				break;
				}
			}
		}
		if(key == 's' || key == 'S') {
			for(int i =0; i< walkers.length; i++) {
				if(walkers[i] != null) {
				walkers[i].setWalking(false);
				}
			}
		}
	}
	
	public static void main(String[] args) {
		
		Utility.runApplication(); 
	}

}
