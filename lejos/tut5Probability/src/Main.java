import navigation.particles.ParticleSet;
import robot.Robot;
import tasks.MoveInSquare;
import tasks.WaypointNavigator;
import utils.Pose;
import lejos.geom.Point;
import lejos.nxt.Button;

public class Main {
	public static void main (String [] args) {
		Button.waitForAnyPress();
		
		Robot r = new Robot();
		
		/*
		r.addTaskToEnd(new MoveInSquare());
		r.run();
		*/
		
		r.motors.rotateLeft();
		/*
		ParticleSet p = new ParticleSet(new Pose());
		r.motors.addMoveListener(p);
		WaypointNavigator w = new WaypointNavigator(p);
		w.addElement(new Point(500, 500));
		w.addElement(new Point(500, -200));
		w.addElement(new Point(0, 0));
		
		r.addTaskToEnd(w);
		r.run();
		*/
		
		Button.waitForAnyPress();
	}
}
