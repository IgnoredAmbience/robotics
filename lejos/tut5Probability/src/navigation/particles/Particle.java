package navigation.particles;

import navigation.Pose;

/**
 * A class that shows a particle with a position,  facing and positive weight.
 * 
 * @author Chris Bates
 *
 */
public class Particle extends Pose {
	
	
	
	private float weight;

	public Particle() {
		super();
		this.setWeight(0);
	}

	public Particle(double x, double y, double angle) {
		super(x, y, angle);
		this.setWeight(0);
	}

	public Particle(double x, double y) {
		super(x, y);
		this.setWeight(0);
	}
	
	public Particle(double x, double y, double angle, float weight) {
		super(x, y, angle);
		this.setWeight(weight);
	}

	/**
	 * @param weight a positive double expressing the weight associated with this particle
	 */
	private void setWeight(float weight) {
		
		if (weight < 0) {
			throw new NumberFormatException("The weight given (" + weight + ") was negative. Weights cannot be negative.");
		}
		this.weight = weight;
		
	}

	/**
	 * 
	 * @return a positive double expressing the weight of the particle
	 */
	public float getWeight() {
		return this.weight;
	}
}
