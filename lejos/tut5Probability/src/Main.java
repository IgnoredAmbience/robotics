import navigation.particles.ParticleSet;
import robot.Robot;
import tasks.MoveInSquare;
import tasks.PositionPlotter;
import lejos.nxt.Button;

public class Main {
	public static void main (String [] args) {
		Button.waitForAnyPress();
		
		Robot r = new Robot();
		ParticleSet p = new ParticleSet();
		r.motors.addMoveListener(p);
		
		r.addTaskToEnd(new MoveInSquare());
		
		r.run();
		
		Button.waitForAnyPress();
	}
}
