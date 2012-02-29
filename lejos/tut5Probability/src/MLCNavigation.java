import navigation.particles.ParticleSet;
import robot.Robot;
import utils.Pose;
import lejos.nxt.Button;


public class MLCNavigation {
	
	public static void main (String [] args) {
		
		Button.waitForAnyPress();
		
		Robot r = new Robot();
		ParticleSet p = new ParticleSet(new Pose(840, 300));
		
		r.particleSet = p;
		
		r.motors.addMoveListener(p);
		
		r.wallSet = CreateWalls.createWalls();
		
		r.addTaskToEnd(CreateWayPoints.getWayPoints(p));
		
		
		
		r.run();
	}
}
