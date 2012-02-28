import robot.MovementController;
import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.navigation.RotateMoveController;

public class Main {
	public static void main (String [] args) {
		Button.waitForAnyPress();
		
		RotateMoveController m = new MovementController(56, 125, Motor.C, Motor.B);
		 
		for(int i = 0; i < 4; i++) {
		  m.travel(400);
		  m.rotate(90);
		}
	}
}
