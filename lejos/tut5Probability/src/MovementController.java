import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.Move;
import lejos.robotics.navigation.MoveListener;
import lejos.robotics.navigation.RotateMoveController;

/**
 * Simplistic motion functions for a differentially-driven robot using the motor encoders
 *
 * @author thomas
 *
 */
// TODO: Implement ArcRotateMoveController
public class MovementController implements RotateMoveController {
	private RegulatedMotor _l, _r;
	
	/** 
	 * Diameter of wheels, in any unit
	 */
	private float _wheel_dia;
	
	/**
	 * Separation between wheels, in any unit
	 */
	private float _wheel_sep;
	
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
	
	public MovementController(float wheel_diameter, float wheel_sep,
			RegulatedMotor left, RegulatedMotor right) {
		_l = left;
		_r = right;
		_wheel_dia = wheel_diameter;
		_wheel_sep = wheel_sep;
		_turnRatio = wheel_sep / wheel_diameter;
		
		_degPerDistance = (float) (360 / (Math.PI * wheel_diameter));
		
		setTravelSpeed(0.5f * getMaxTravelSpeed());
		setRotateSpeed(0.3f * getRotateMaxSpeed());
	}

	@Override
	public void forward() {
		_l.forward();
		_r.forward();
	}

	@Override
	public void backward() {
		_l.backward();
		_r.backward();
	}

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

	@Override
	public boolean isMoving() {
		return _l.isMoving() || _r.isMoving();
	}
	
	public boolean isStalled() {
		return _l.isStalled() || _r.isStalled();
	}
	
	/*
	 * Linear Movement
	 */

	@Override
	public void travel(double distance) {
		travel(distance, false);
	}

	@Override
	public void travel(double distance, boolean immediateReturn) {
		setTravelSpeed();
		int angle = Math.round((float) (distance * _degPerDistance));
		_l.rotate(angle, true);
		_r.rotate(angle, immediateReturn);
	}
	
	private void setWheelSpeed(int left, int right) {
		_l.setSpeed(left);
		_r.setSpeed(right);
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
	
	@Override
	public void rotate(double angle) {
		rotate(angle, false);
	}

	@Override
	public void rotate(double angle, boolean immediateReturn) {
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMoveListener(MoveListener listener) {
		// TODO Auto-generated method stub
		
	}
}
