package tasks;

import robot.Robot;

public class MoveForward implements Task {

	private float distanceToMove;
	
	public MoveForward(float distanceToMove) {
		
		this.distanceToMove = distanceToMove;
		
	}
	
	@Override
	public void run(Robot r) {
		
		r.moveForward(distanceToMove);

	}

}
