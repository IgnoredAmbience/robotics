import lejos.nxt.Button;
import navigation.WallSet;
import navigation.particles.ParticleSet;
import robot.Robot;
import tasks.PredictOnSonar;


public class LocatingSelf {

	public static void main (String [] args) {
		
		WallSet w = CreateWalls.createWalls();
		
		
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
