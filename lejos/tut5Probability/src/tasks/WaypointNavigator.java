package tasks;

import java.util.Queue;

import robot.Robot;
import lejos.geom.Point;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.Pose;
import lejos.robotics.navigation.RotateMoveController;

public class WaypointNavigator extends Queue<Point> implements Task {
	private PoseProvider poser;
	private RotateMoveController motor;
	
	public WaypointNavigator(PoseProvider p) {
		super();
		poser = p;
	}
	
	@Override
	public void run(Robot r) {
		motor = r.motors;
		while(!empty()) {
			navigateTo((Point) pop());
		}
	}
	
	private void navigateTo(Point p) {
		System.err.println("Travel to: " + p.toString());
		Pose pose = poser.getPose();
		float angle = pose.relativeBearing(p);
		System.err.print(pose.toString() + " rotating: ");
		System.err.println(angle);
		motor.rotate(-angle);
		
		Pose pose1 = poser.getPose();
		float dist = pose1.distanceTo(p);
		System.err.print(pose1.toString() + " travelling: ");
		System.err.println(dist);
		motor.travel(dist);
	}
}
