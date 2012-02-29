import lejos.geom.Point;
import lejos.nxt.Button;
import navigation.Wall;
import navigation.WallSet;
import navigation.particles.ParticleSet;
import robot.Robot;
import tasks.PredictOnSonar;


public class LocatingSelf {

	public static void main (String [] args) {
		
		WallSet w = new WallSet();
		w.addWall(new Wall(new Point(0, 0), new Point(0, 168)));
		w.addWall(new Wall(new Point(0, 168), new Point(84, 168)));
		w.addWall(new Wall(new Point(84, 168), new Point(84, 126)));
		w.addWall(new Wall(new Point(84, 126), new Point(84, 210)));
		w.addWall(new Wall(new Point(84, 210), new Point(168, 84)));
		w.addWall(new Wall(new Point(168, 84), new Point(210, 84)));
		w.addWall(new Wall(new Point(210, 84), new Point(210, 0)));
		w.addWall(new Wall(new Point(210, 0), new Point(0, 0)));
		
		
		Button.waitForAnyPress();
		
		Robot r = new Robot();
		ParticleSet p = new ParticleSet();
		r.motors.addMoveListener(p);
		r.wallSet = w;
		
		r.addTaskToEnd(new PredictOnSonar());
		
		r.run();
		
		Button.waitForAnyPress();
		
	}
	
}
