package tasks;

import robot.Robot;

import java.lang.Math;

public class Rotate implements Task {

	private float angleToRotate;
	
	/**
	 * Use other constructor Rotate(float angle, false) if your angle is in radians. 
	 * 
	 * @param angleToRotate the amount to rotate by in degrees
	 */
	public Rotate(float angleToRotate) {
		this.angleToRotate = angleToRotate;
	}
	
	/**
	 * 
	 * 
	 * @param angleToRotate angle to rotate through
	 * @param isDegrees whether the angle is in degrees or radians
	 */
	public Rotate(float angleToRotate, boolean isDegrees) {
		
		if (isDegrees) {
			this.angleToRotate = angleToRotate;
		angleToRotate = (isDegrees ? angleToRotate : Math.toDegrees(angleToRotate));
		
	}
	
	@Override
	public void run(Robot r) {
		r.rotate(this.angleToRotate);

	}

}
