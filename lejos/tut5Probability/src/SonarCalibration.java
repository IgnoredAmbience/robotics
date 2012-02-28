import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.UltrasonicSensor;


public class SonarCalibration {

	/**
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		UltrasonicSensor sonar = new UltrasonicSensor(SensorPort.S1);
		
		sonar.continuous();
		for(;;) {
			float d = sonar.getDistance();
//			System.out.println(d);
			LCD.drawString(String.valueOf(d), 0, 0);
			Sound.playTone((int) d * 100, 30, 50);
			Thread.sleep(100);
		}
	}

}
