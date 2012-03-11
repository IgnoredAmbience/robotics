package robot;

import tasks.Task;
import utils.DoubleHeadedQueue;
import utils.Pose;

import java.lang.Runnable;

import navigation.WallSet;
import navigation.particles.ParticleSet;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.localization.PoseProvider;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.Move;
import lejos.robotics.navigation.MoveListener;
import lejos.robotics.navigation.MoveProvider;
import lejos.robotics.navigation.RotateMoveController;
import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

public class Robot implements Runnable, PoseProvider {
	
	private DoubleHeadedQueue<Task> tasks;
	public DifferentialPilot motors;
	public UltrasonicSensor sonar;
	public WallSet wallSet;
	
	// Our PoseProvider
	private ParticleSet particleSet;
	
	private final float WHEEL_SEPARATION = 175;
	private final float WHEEL_DIAMETER = 55;
	private final RegulatedMotor LEFT_MOTOR = Motor.C;
	private final RegulatedMotor RIGHT_MOTOR = Motor.B;
	public final double sonarDeviation = 20;
	private final int sonarOffset = 30;
	
	public Robot(Pose p) {
		this.motors = new DifferentialPilot(WHEEL_DIAMETER, WHEEL_SEPARATION, LEFT_MOTOR, RIGHT_MOTOR);
		tasks = new DoubleHeadedQueue<Task> ();
		sonar =  new UltrasonicSensor(SensorPort.S1);
		wallSet = new WallSet();
		particleSet = new ParticleSet(p);
		
		motors.addMoveListener(particleSet);
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

	public int getSensorDist() {
		// TODO: calibratioon
		return this.sonar.getDistance() * 10 + this.sonarOffset;
	}

	@Override
	public lejos.robotics.navigation.Pose getPose() {
		return particleSet.getPose();
	}

	@Override
	public void setPose(lejos.robotics.navigation.Pose aPose) {
		particleSet.setPose(aPose);
	}
}
