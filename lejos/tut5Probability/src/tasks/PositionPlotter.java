package tasks;

import lejos.nxt.LCD;
import lejos.robotics.navigation.Pose;
import robot.Robot;

public class PositionPlotter extends Thread {
	private Robot robot;
	private float unitsPerPixel = 30;
	
	public PositionPlotter(Robot r) {
		LCD.clear();
		robot = r;
	}
	
	@Override
	public void run() {
		while(true) {
			Pose p = robot.getCurrentPose();
			LCD.setPixel((int) (p.getX()/unitsPerPixel), (int) (p.getY()/unitsPerPixel), 1);
			try {
				sleep(10);
			} catch (InterruptedException e) {}
		}
	}
}
