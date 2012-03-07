package robot;

import lejos.nxt.I2CPort;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.Sound;
import lejos.nxt.TachoMotorPort;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.RegulatedMotor;

public class RotatingSonar {
	RegulatedMotor m;
	UltrasonicSensor s;
	
	public RotatingSonar(TachoMotorPort mp, I2CPort sp) {
		m = new NXTRegulatedMotor(mp);
		s = new UltrasonicSensor(sp);
	}

	public int[] scanRange(int fromAngle, int toAngle) {
		return scanRange(fromAngle, toAngle, 1);
	}
	
	public int[] scanRange(int fromAngle, int toAngle, int step) {
		int[] scan = new int[(toAngle-fromAngle)/step + 1];
		for(int i = fromAngle; i <= toAngle; i+=step) {
			m.rotateTo(i);
			scan[i-fromAngle] = s.getDistance();
		}
		
		return scan;
	}
	
	public byte[] scanFrequencies(int fromAngle, int toAngle) {
		return scanFrequencies(fromAngle, toAngle, 1);
	}
	
	public byte[] scanFrequencies(int fromAngle, int toAngle, int step) {
		byte[] scan = new byte[256];
		for(int i = fromAngle; i <= toAngle; i+=step) {
			m.rotateTo(i);
			scan[s.getDistance()]++;
		}
		
		return scan;
	}
	
	public void rotateTo(int angle) {
		m.rotateTo(angle);
	}
}
