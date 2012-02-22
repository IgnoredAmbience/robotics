package robot;

import tasks.Task;

import java.util.LinkedList;
import java.lang.Runnable;

import lejos.robotics.RegulatedMotor;
import lejos.nxt.Motor;

public class Robot implements Runnable {
	
	private LinkedList<Task> tasks;
	
	private MovementController motors;
	
	private final float WHEEL_SEPARATION = 1.0f;
	private final float WHEEL_DIAMETER = 1.0f;
	private final RegulatedMotor LEFT_MOTOR = Motor.C;
	private final RegulatedMotor RIGHT_MOTOR = Motor.B;
	
	public Robot () {
		
		this.motors = new MovementController (LEFT_MOTOR, RIGHT_MOTOR, WHEEL_SEPARATION, WHEEL_DIAMETER);
		tasks = new LinkedList<Task> ();
	}
	
	/**
	 * Runs all the tasks in the Linked list one by one;
	 */
	public void run() {
		
		while (!tasks.isEmpty()) {
			
			Task currentTask = tasks.poll();
			
			currentTask.run(this);
			
		}
		
	}

	/**
	 * Adds a task to the head of the Task queue.
	 * @param t
	 */
	public void addTaskToHead(Task t) {
		tasks.addFirst(t);
	}
	
	/**
	 * Adds a task to the end of the Task queue.
	 * @param t
	 */
	public void addTaskToEnd(Task t) {
		tasks.addLast(t);
	}
	
	public Task getNextTask() {
		return tasks.peek();
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
