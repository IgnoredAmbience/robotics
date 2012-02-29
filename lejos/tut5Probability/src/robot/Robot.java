package robot;

import tasks.Task;
import utils.DoubleHeadedQueue;
import utils.Pose;

import java.lang.Runnable;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Move;
import lejos.robotics.navigation.MoveListener;
import lejos.robotics.navigation.MoveProvider;
import lejos.robotics.navigation.RotateMoveController;
import lejos.nxt.Motor;

public class Robot implements Runnable, MoveListener, PoseProvider {
	
	private DoubleHeadedQueue<Task> tasks;
	public RotateMoveController motors;
	private Pose pose = new Pose(1000,1000,0);
	
	private final float WHEEL_SEPARATION = 200;
	private final float WHEEL_DIAMETER = 56;
	private final RegulatedMotor LEFT_MOTOR = Motor.C;
	private final RegulatedMotor RIGHT_MOTOR = Motor.B;
	
	public Robot() {
		this.motors = new DifferentialPilot(WHEEL_DIAMETER, WHEEL_SEPARATION, LEFT_MOTOR, RIGHT_MOTOR);
		tasks = new DoubleHeadedQueue<Task> ();
		motors.addMoveListener(this);
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

	/*
	 * Pose provider and updater functions
	 */
	
	@Override
	public Pose getPose() {
		return pose;
	}
	
	public synchronized Pose getCurrentPose() {
		Pose p = new Pose(pose.getX(), pose.getY(), pose.getHeading());
		p.updatePose(motors.getMovement());
		return p;
	}

	@Override
	public synchronized void setPose(lejos.robotics.navigation.Pose aPose) {
		pose = (Pose) aPose;
	}

	@Override
	public void moveStarted(Move event, MoveProvider mp) {}

	@Override
	public synchronized void moveStopped(Move m, MoveProvider mp) {
		pose.updatePose(m);
	}
}
