import navigation.OccupancyMap;
import lejos.nxt.Button;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.robotics.localization.PoseProvider;
import robot.Robot;
import robot.RotatingSonar;
import tasks.MoveInSquare;
import utils.Pose;
import utils.RotatingSonarReading;

public class Tut8 {
	
	public static void main(String[] _) {
		RotatingSonar s = new RotatingSonar(MotorPort.A, SensorPort.S1);

		// Baseline defined as x = 100, world has a 3cm overlap each side, and plenty along x axis
		// On the midpoint of baseline (near exit), facing toward target
		// +90 is left, TODO: Confirm uniformity of Sonar angles
  	Robot r = new Robot(new Pose(100, 1280, 0));

  	// OM defaults to 4000, 2560 in world units
  	// TODO: Consider boundary values for clear and occupied states
  	OccupancyMap m = new OccupancyMap();
  	OccupancyMapUpdater omu = new OccupancyMapUpdater(s, m, r);

  	s.start();
  	omu.start();
  	r.run();
  	
  	System.exit(0);
	}
	
	static class OccupancyMapUpdater extends Thread {
		private final RotatingSonar s;
		private final OccupancyMap m;
		private final PoseProvider p;
		
		OccupancyMapUpdater(RotatingSonar s, OccupancyMap m, PoseProvider p) {
			this.s = s;
			this.m = m;
			this.p = p;
		}
		
		public void run() {
			while(true) {
				Pose pose = (Pose) p.getPose();
				RotatingSonarReading r = s.getDistance();
				m.updateValue(pose, r.angle, r.distance);
			}
		}
	}
}
