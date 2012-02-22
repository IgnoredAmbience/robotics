package tasks;

import robot.Robot;

/**
 * A Task is a representation of a set of actions for a robot to acheive.  
 * 
 * @author cb2109
 *
 */
public interface Task {

	/**
	 * Given a robot r, a certain task is performed, changing the state of the Robot.
	 * 
	 * @param r robot for the task to be performed on.
	 */
	public void run (Robot r);
	
}
