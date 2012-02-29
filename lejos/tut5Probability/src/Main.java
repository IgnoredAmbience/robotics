import navigation.particles.ParticleSet;
import robot.Robot;
import tasks.MoveInSquare;
import tasks.PositionPlotter;
import tasks.WaypointNavigator;
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
		
		ParticleSet p = new ParticleSet();
		r.motors.addMoveListener(p);
		WaypointNavigator w = new WaypointNavigator(p);
		w.addElement(new Point(500, 500));
		w.addElement(new Point(500, -200));
		w.addElement(new Point(0, 0));
		
		r.addTaskToEnd(w);
		r.run();
		
		Button.waitForAnyPress();
	}
}
