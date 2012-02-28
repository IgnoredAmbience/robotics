import robot.Robot;
import tasks.MoveInSquare;
import tasks.PositionPlotter;
import lejos.nxt.Button;

public class Main {
	public static void main (String [] args) {
		Button.waitForAnyPress();
		
		Robot r = new Robot();
		PositionPlotter p = new PositionPlotter(r);
		p.setDaemon(true);
		
		r.addTaskToEnd(new MoveInSquare());
		
		p.start();
		r.run();
	}
}
