package tasks;

import robot.Robot;

public class MoveBackward implements Task {

	private float distanceToMove;
	
	/**
	 * 
	 * @param distanceToMove distance in mm
	 */
	public MoveBackward(float distanceToMove) {
		
		this.distanceToMove = -distanceToMove;
		
	}
	
	@Override
	public void run(Robot r) {
		
		r.motors.travel(distanceToMove);

	}

}
