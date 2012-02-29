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
			navigateTo((Point) pop(), r);
		}
	}
	
	private void navigateTo(Point p, Robot r) {
		PredictOnSonar pre = new PredictOnSonar();
		
		//pre.run(r);
		
		System.out.println("Travel to: " + p.toString());
		Pose pose = poser.getPose();
		float angle = pose.relativeBearing(p);
		System.out.print(pose.toString() + " rotating: ");
		System.out.println(angle);
		motor.rotate(-angle);
		
		//pre.run(r);
		
		Pose pose1 = poser.getPose();
		float dist = pose1.distanceTo(p);
		System.out.print(pose1.toString() + " travelling: ");
		System.out.println(dist);
		motor.travel(dist);
	}
}
