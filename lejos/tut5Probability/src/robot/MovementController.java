import lejos.nxt.Motor;
import lejos.nxt.MotorPort;
import lejos.nxt.NXTMotor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.Move;
import lejos.robotics.navigation.MoveListener;
import lejos.robotics.navigation.RotateMoveController;
import lejos.util.Delay;

/**
 * Simplistic motion functions for a differentially-driven robot using the motor encoders
 *
 * @author thomas
 *
 */
// TODO: Implement ArcRotateMoveController
public class MovementController implements RotateMoveController {
	private RegulatedMotor _l, _r;
	private float _wheel_dia, _wheel_sep;
	private float _degPerDistance;
	
	public MovementController(RegulatedMotor left, RegulatedMotor right,
			float wheel_diameter, float wheel_sep) {
		_l = left;
		_r = right;
		_wheel_dia = wheel_diameter;
		_wheel_sep = wheel_sep;
		
		_degPerDistance = (float) (360 / (Math.PI * wheel_diameter));
		
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

	@Override
	public void travel(double distance) {
		travel(distance, false);
	}

	@Override
	public void travel(double distance, boolean immediateReturn) {
		int angle = Math.round((float) (distance * _degPerDistance));
		_l.rotate(angle, true);
		_r.rotate(angle, immediateReturn);
	}

	@Override
	public void setTravelSpeed(double speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getTravelSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getMaxTravelSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Move getMovement() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addMoveListener(MoveListener listener) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotate(double angle) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void rotate(double angle, boolean immediateReturn) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setRotateSpeed(double speed) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getRotateSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getRotateMaxSpeed() {
		// TODO Auto-generated method stub
		return 0;
	}
}
