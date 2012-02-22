import lejos.nxt.Button;
import lejos.nxt.Motor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.RotateMoveController;
import map.ErrorMap;


public class Main {
	public static void main (String [] args) {
		Button.waitForAnyPress();
		
		RotateMoveController m = new MovementController(56, 106.5f, Motor.C, Motor.B);
		 
		for(int i = 0; i < 4; i++) {
		  m.travel(400);
		  m.rotate(90);
		}
	}
}
