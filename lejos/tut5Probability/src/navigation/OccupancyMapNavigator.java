package navigation;

import robot.Robot;
import lejos.geom.Point;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.Pose;

public class OccupancyMapNavigator extends Thread {
	private OccupancyMap m;
	private Robot r;
	
	public OccupancyMapNavigator(OccupancyMap m, Robot r) {
		this.m = m;
		this.r = r;
	}
	
	public void run() {
		int max = 400;
		int dist = max;
		
		while(dist == max) {
			try {
				sleep(1000);
			} catch (InterruptedException e) {}
			
			Pose p = r.getPose();
  		dist = m.findOccupiedDistance(p, max);
  		System.out.println(p);
  		System.out.println(dist);
  		if(dist != max) dist -= 200;
  		if(dist >= 0) r.motors.travel(dist);
		}
		
		
		// While something is not in front of us
		//		Go forwards
		// Else
		//		If gap, Go towards gap
		// 		Else, wall follow
		
		
	}
}
