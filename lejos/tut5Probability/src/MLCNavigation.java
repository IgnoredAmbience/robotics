import navigation.particles.ParticleSet;
import robot.Robot;
import tasks.WaypointNavigator;
import utils.Pose;
import lejos.geom.Point;
import lejos.nxt.Button;
import lejos.robotics.localization.PoseProvider;


public class MLCNavigation {
	
	public static void main (String [] args) {
		
		Button.waitForAnyPress();
		
		Robot r = new Robot();
		ParticleSet p = new ParticleSet(new Pose(840, 300));
		
		r.particleSet = p;
		
		r.motors.addMoveListener(p);
		
		r.wallSet = new LabMap();
		
		r.addTaskToEnd(getWayPoints(p));
		
		
		
		r.run();
	}
	
	public static WaypointNavigator getWayPoints(PoseProvider p) {
		WaypointNavigator w = new WaypointNavigator(p); 
		// Initial location - w.addElement(new Point(840, 300));
		w.addElement(new Point(1800, 300));
		w.addElement(new Point(1800, 540));
		w.addElement(new Point(1260, 540));
		w.addElement(new Point(1260, 1680));
		w.addElement(new Point(1260, 1260));
		w.addElement(new Point(300, 540));
		w.addElement(new Point(840, 540));
		w.addElement(new Point(840, 300));
		return w;
	}
}
