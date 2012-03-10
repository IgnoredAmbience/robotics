import lejos.nxt.Button;
import lejos.nxt.MotorPort;
import lejos.nxt.SensorPort;
import robot.RotatingSonar;

public class Tut8 {
	
	public static void main(String[] _) {
		RotatingSonar s = new RotatingSonar(MotorPort.A, SensorPort.S1);
  	s.start();
  	while(true) {
  		s.getDistance();
  	}
	}
}
