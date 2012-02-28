package robot;

import java.util.ArrayList;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.RegulatedMotorListener;
import lejos.robotics.navigation.Move;
import lejos.robotics.navigation.Move.MoveType;
import lejos.robotics.navigation.MoveListener;
import lejos.robotics.navigation.RotateMoveController;

/**
 * Simplistic motion functions for a differentially-driven robot using the motor encoders
 *
 * @author thomas
 *
 */
// TODO: Implement ArcRotateMoveController
public class MovementController implements RotateMoveController, RegulatedMotorListener {
	private RegulatedMotor _l, _r;
	
	/** 
	 * Diameter of wheels, in any unit
	 */
	private float _wheelDia;
	
	/**
	 * Separation between wheels, in any unit
	 */
	private float _wheelSep;
	
	/**
	 * Wheel rotations required for a given distance (deg/unit)
	 * Calculated from _wheel_dia
	 */
	private float _degPerDistance;
	
	/**
	 * Wheel rotations required for 360 degree rotation of robot
	 */
	private float _turnRatio;
	
	private float _travelSpeed;
	private float _rotateSpeed;
	
	// MoveListener support
	private ArrayList<MoveListener> _moveListeners = new ArrayList<MoveListener>();
	private MoveType _move;
	private double _moveDistance;
	private double _moveRotate;
	
	private int _moveLTacho;
	private int _moveRTacho;
	
	public MovementController(float wheel_diameter, float wheel_sep,
			RegulatedMotor left, RegulatedMotor right) {
		_l = left;
		_r = right;
		_wheelDia = wheel_diameter;
		_wheelSep = wheel_sep;
		
		_turnRatio = _wheelSep / _wheelDia;
		_degPerDistance = (float) (360 / (Math.PI * _wheelDia));
		
		setTravelSpeed(0.5f * getMaxTravelSpeed());
		setRotateSpeed(0.3f * getRotateMaxSpeed());
		
		resetTacho();
	}
	
	/*
	 * Predicates
	 */

	@Override
	public boolean isMoving() {
		return _l.isMoving() || _r.isMoving();
	}
	
	public boolean isStalled() {
		return _l.isStalled() || _r.isStalled();
	}
	
	/*
	 * General methods
	 */
	
	/**
	 * Stops movement
	 * Side-effect: notifies listeners via the Motor listeners
	 */
	@Override
	public void stop() {
		_l.stop(true);
		_r.stop(true);
		waitComplete();
	}

	/**
	 * Waits until operations are completed by motors
	 */
	private void waitComplete() {
		while(isMoving()) {
			_l.waitComplete();
			_r.waitComplete();
		}
	}
	
	private void setWheelSpeed(int left, int right) {
		_l.setSpeed(left);
		_r.setSpeed(right);
	}
	
	/*
	 * Linear Movement (Travel)
	 */

	@Override
	public void forward() {
		_move = MoveType.TRAVEL;
		_moveDistance = Double.POSITIVE_INFINITY;
		_moveRotate = 0;
		movementStarted();
		
		_l.forward();
		_r.forward();
	}

	@Override
	public void backward() {
		_move = MoveType.TRAVEL;
		_moveDistance = Double.NEGATIVE_INFINITY;
		_moveRotate = 0;
		movementStarted();
		
		_l.backward();
		_r.backward();
	}
	
	@Override
	public void travel(double distance) {
		travel(distance, false);
	}

	@Override
	public void travel(double distance, boolean immediateReturn) {
		_move = MoveType.TRAVEL;
		_moveDistance = distance;
		_moveRotate = 0;
		movementStarted();
		
		setTravelSpeed();
		int angle = Math.round((float) (distance * _degPerDistance));
		_l.rotate(angle, true);
		_r.rotate(angle, immediateReturn);
	}
	

	public void setTravelSpeed() {
		setTravelSpeed(_travelSpeed);
	}
	
	@Override
	public void setTravelSpeed(final double speed) {
		_travelSpeed = (float) speed;
		int wheel_speed = (int) Math.round(speed * _degPerDistance);
		setWheelSpeed(wheel_speed, wheel_speed);
	}

	@Override
	public double getTravelSpeed() {
		return _travelSpeed;
	}

	@Override
	public double getMaxTravelSpeed() {
		// deg/s / deg/mm = mm/s
		return Math.min(_l.getMaxSpeed(), _r.getMaxSpeed()) / _degPerDistance;
	}

	/*
	 * Rotational movement
	 */
	
	public void rotateLeft() {
		_move = MoveType.ROTATE;
		_moveDistance = 0;
		_moveRotate = Double.POSITIVE_INFINITY;
		movementStarted();
		
		setRotateSpeed();
		_l.forward();
		_r.backward();
	}
	
	public void rotateRight() {
		_move = MoveType.ROTATE;
		_moveDistance = 0;
		_moveRotate = Double.NEGATIVE_INFINITY;
		movementStarted();
		
		setRotateSpeed();
		_l.backward();
		_r.forward();
	}
	
	@Override
	public void rotate(double angle) {
		rotate(angle, false);
	}

	@Override
	public void rotate(double angle, boolean immediateReturn) {
		_move = MoveType.ROTATE;
		_moveDistance = 0;
		_moveRotate = angle;
		movementStarted();
		
		setRotateSpeed();
		int parity = angle < 0 ? -1 : 1;
		int turns = (int) (_turnRatio * angle);
		_l.rotate(parity * turns, true);
		_r.rotate(-parity * turns, immediateReturn);
	}

	public void setRotateSpeed() {
		setRotateSpeed(_rotateSpeed);
	}
	
	@Override
	public void setRotateSpeed(double speed) {
		_rotateSpeed = (float) speed;
		int wheel_speed = (int) Math.round(speed * _turnRatio);
		setWheelSpeed(wheel_speed, wheel_speed);
	}

	@Override
	public double getRotateSpeed() {
		return _rotateSpeed;
	}

	@Override
	public double getRotateMaxSpeed() {
		return Math.min(_l.getMaxSpeed(), _r.getMaxSpeed()) / _turnRatio;
	}
	
	/*
	 * Movement reporting
	 */

	@Override
	public Move getMovement() {
		return new Move(_move, getMovedDistance(), getMovedAngle(),
				_travelSpeed, _rotateSpeed, isMoving());
	}

	@Override
	public void addMoveListener(MoveListener listener) {
		_moveListeners.add(listener);
	}
	
	/**
	 * Notifies all registered MovementListeners of the event 
	 */
	private void movementStarted() {
		for(MoveListener ml : _moveListeners) {
			Move m = new Move(_move, (float) _moveDistance, (float) _moveRotate,
											  _travelSpeed, _rotateSpeed, isMoving());
			ml.moveStarted(m, this);
		}
	}

	/**
	 * Notifies all registered MovementListeners of the stop
	 * Synchronised with the MotorListeners, as these events are interrupts
	 */
	private synchronized void movementStopped() {
		for(MoveListener ml : _moveListeners) {
			ml.moveStopped(getMovement(), this);
		}
	}
	
	private void resetTacho() {
		_moveLTacho = _l.getTachoCount();
		_moveRTacho = _r.getTachoCount();
	}
	
	public float getMovedDistance() {
		int l = _l.getTachoCount() - _moveLTacho;
		int r = _r.getTachoCount() - _moveRTacho;
		return ((l + r) / 2) / (_degPerDistance);
	}
	
	public float getMovedAngle() {
		int l = _l.getTachoCount() - _moveLTacho;
		int r = _r.getTachoCount() - _moveRTacho;
		
		return ((l - r) / 2) / _turnRatio;
	}

	/*
	 * MotorListener implementation
	 */
	@Override
	public synchronized void rotationStarted(RegulatedMotor motor, int tachoCount,
			boolean stalled, long timeStamp) {}

	@Override
	public synchronized void rotationStopped(RegulatedMotor motor, int tachoCount,
			boolean stalled, long timeStamp) {
		if(motor.isStalled()) {
			stop();
		}	else if(!isMoving()) { 
			movementStopped();
		}
	}
}
