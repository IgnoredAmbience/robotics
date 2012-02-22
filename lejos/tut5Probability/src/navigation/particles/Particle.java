package particles;

/**
 * A class that shows a particle with a position and facing.
 * 
 * @author Chris Bates
 *
 */
public class Particle {
	private float x;
	private float y;
	private float angle;
	
	public Particle(float x, float y, float angle) {
		this.x = x;
		this.y = y;
		this.angle = angle;
	}
	
	public float getX() {
		return x;
	}
	public void setX(float x) {
		this.x = x;
	}
	public float getY() {
		return y;
	}
	public void setY(float y) {
		this.y = y;
	}
	public float getAngle() {
		return angle;
	}
	public void setAngle(float angle) {
		this.angle = angle;
	}
}
