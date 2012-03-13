import navigation.OccupancyMap;
import navigation.OccupancyMapNavigator;
import lejos.nxt.Button;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import lejos.robotics.localization.PoseProvider;
import robot.Robot;
import robot.RotatingSonar;
import tasks.MoveForward;
import utils.Pose;
import utils.RotatingSonarReading;

public class Tut8 {
	
	public static void main(String[] _) {
		RotatingSonar s = new RotatingSonar(MotorPort.A, SensorPort.S1);

		// Baseline defined as x = 100, world has a 3cm overlap each side, and plenty along x axis
		// On the midpoint of baseline (near exit), facing toward target
		// +90 is left
  	Robot r = new Robot(new Pose(100, 1280, 0));
  	
  	r.motors.setTravelSpeed(100);

  	// OM defaults to 4000x2560 in world units
  	// TODO: Consider boundary values for clear and occupied states
  	OccupancyMap m = new OccupancyMap();
  	OccupancyMapUpdater omu = new OccupancyMapUpdater(s, m, r);
  	
  	OccupancyMapNavigator omn = new OccupancyMapNavigator(m, r);

  	Button.waitForAnyPress();
  	s.start();
  	omu.start();
  	omn.start();
  	
  	while(true) {
  		if(Button.waitForAnyPress() == Button.ID_ESCAPE) break;
  		System.out.println(r.getPose());
  		m.clear();
  	}
  		
  	
  	// TODO: Navigation system
  	// We could consider a vector field, low draw to goal, high repel from features
  	// High draw to 'very' empty areas?
  	
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
				if(r.distance < 255) {
					m.updateValue(pose, r.angle, r.distance * 10); // 10 = cm to mm
				}
			}
		}
	}
}
