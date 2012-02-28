package navigation;

import java.awt.geom.Point2D;

public class Pose extends Point2D.Double {

	private double angle;
	private static final double DEGREES = 360;
	
	public Pose(double x, double y) {
		super(x, y);
		this.setAngle(0);
	}

	public Pose() {
		super();
		this.setAngle(0);		
	}
	
	public Pose(double x, double y, double angle) {
		super(x, y);
		this.setAngle(angle);
	}
	
	public void setAngle(double angle) {
		this.angle = angle % DEGREES;
		if (this.angle < 0) {
			this.angle = DEGREES + this.angle;
		}
	}
	
	public double getAngle() {
		return angle;
	}
	
	
	
}
