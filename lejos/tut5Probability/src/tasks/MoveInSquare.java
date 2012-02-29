package tasks;

import robot.Robot;

public class MoveInSquare implements Task {
	
	public float squareSize;

	/**
	 * Moves the robot in a square (forward, right, forward, right, forward, right, forward) of size 40cm.
	 */
	public MoveInSquare() {
		this.squareSize = 40*10;
	}
	
	/**
	 * Moves the robot in a square (forward, right, forward, right, forward, right, forward) of size squareSize.
	 * @param squareSize the required size of the square in mm
	 */
	public MoveInSquare(float squareSize) {
		this.squareSize = squareSize;
	}
	
	@Override
	public void run(Robot r) {
		
		r.addTaskToEnd(new MoveForward(squareSize));
		r.addTaskToEnd(new Rotate(90));
		r.addTaskToEnd(new MoveForward(squareSize));
		r.addTaskToEnd(new Rotate(90));
		r.addTaskToEnd(new MoveForward(squareSize));
		r.addTaskToEnd(new Rotate(90));
		r.addTaskToEnd(new MoveForward(squareSize));
		r.addTaskToEnd(new Rotate(90));

	}

}
