package tasks;

import lejos.nxt.LCD;
import robot.Robot;

public class PositionPlotter extends Thread {
	private Robot robot;
	
	public PositionPlotter(Robot r) {
		LCD.clear();
		robot = r;
	}
	
	@Override
	public void run() {
		while(true) {
			robot.getCurrentPose().drawToLCD();
			try {
				sleep(10);
			} catch (InterruptedException e) {}
		}
	}
}
