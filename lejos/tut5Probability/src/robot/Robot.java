package robot;

import tasks.Task;
import utils.DoubleHeadedQueue;

import java.lang.Runnable;

import lejos.robotics.RegulatedMotor;
import lejos.nxt.Motor;

public class Robot implements Runnable {
	
	private DoubleHeadedQueue<Task> tasks;
	
	private MovementController motors;
	
	private final float WHEEL_SEPARATION = 56;
	private final float WHEEL_DIAMETER = 125f;
	private final RegulatedMotor LEFT_MOTOR = Motor.C;
	private final RegulatedMotor RIGHT_MOTOR = Motor.B;
	
	public Robot () {
		
		this.motors = new MovementController (WHEEL_DIAMETER, WHEEL_SEPARATION, LEFT_MOTOR, RIGHT_MOTOR);
		tasks = new DoubleHeadedQueue<Task> ();
	}
	
	/**
	 * Runs all the tasks in the Linked list one by one;
	 */
	public void run() {
		
		while (!tasks.isEmpty()) {
			
			Task currentTask = tasks.popFront();
			
			currentTask.run(this);
			
		}
		
	}

	/**
	 * Adds a task to the head of the Task queue.
	 * @param t
	 */
	public void addTaskToHead(Task t) {
		tasks.pushToFront(t);
	}
	
	/**
	 * Adds a task to the end of the Task queue.
	 * @param t
	 */
	public void addTaskToEnd(Task t) {
		tasks.pushToBack(t);
	}
	
	public Task getNextTask() {
		return tasks.peekFront();
	}
	
	
	
	/**
	 * Moves the robot forward distanceToMove mm.
	 * @param distanceToMove distance in mm
	 */
	public void moveForward(float distanceToMove) {
		
		motors.travel(distanceToMove);		
		
	}
	
	public void moveBackwards(float distanceToMove) {
		
		motors.travel(-distanceToMove);
		
	}
	
	/**
	 * Turns the robot through angleToRotate degrees.
	 * 
	 * @param angleToRotate degrees to rotate through
	 */
	public void rotate (float angleToRotate) {
		
		motors.rotate(angleToRotate);
		
	}
	
	
	
}
